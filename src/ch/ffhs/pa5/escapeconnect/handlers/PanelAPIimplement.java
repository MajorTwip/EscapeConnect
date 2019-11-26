package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.PanelApiService;
import ch.ffhs.pa5.escapeconnect.bean.Action;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.Panel;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.Value;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;
import ch.ffhs.pa5.escapeconnect.utils.MACformating;

public class PanelAPIimplement implements PanelApiService {
	DAOpanel daopanel = new DAOpanel();
	DAOaction daoaction = new DAOaction();
	DAOvalue daovalue = new DAOvalue();
	
	@Override
	public Response getPanes(SecurityContext securityContext) {
		
		List<Panel> resultsToShow = new ArrayList<>();	
		// get the data through the DAO
		List<PanelDAOBean> resultsFromDB = daopanel.getAllPanels();

		int place = 0;
		// convert the PanelDAOBeans to Panels
		for(PanelDAOBean generated_panel : resultsFromDB) {
			Panel panelToShow = new Panel();
			panelToShow.setId(generated_panel.getId());
			panelToShow.setTitle(generated_panel.getName());
			panelToShow.setOrder(place);
			place = place + 1;
			
			//check if allready recognised on server
			DAOdevice daodevice = new DAOdevice();
			DeviceDAOBean device = daodevice.getByMac(generated_panel.getDevice_mac());
			
			panelToShow.setUpgradeenabled(device.issupportsOTA());
			
			LinkedList<String> topicToQuery = new LinkedList<>();

			DAOecsettings daoecsettings = new DAOecsettings();
			EcSettings settings = daoecsettings.get();
			MQTTconnector mqtt = new MQTTconnector(settings.getMqttUrl(), settings.getMqttName(), settings.getMqttPass());
			
			String basetopic = device.getBasetopic();
			String deviceid = device.getDeviceid();
			
			if(basetopic==""||deviceid==null) {
				System.out.println("looking for device " + generated_panel.getDevice_mac());
				topicToQuery.add("+/+/$mac");
				Map<String,String> devices = mqtt.getMessages(topicToQuery, 1000, true,false);
				topicToQuery.clear();
				for(String key:devices.keySet()) {
					System.out.println(key + ":" + devices.get(key));
					if(MACformating.sanitizeMAC(devices.get(key)).equals(generated_panel.getDevice_mac())) {
						String[] topiclevels = key.split("/");
						basetopic = topiclevels[0];
						deviceid = topiclevels[1];
						System.out.println("Basetopic: " + basetopic);
						System.out.println("deviceid: " + deviceid);
						device.setBasetopic(basetopic);
						device.setDeviceid(deviceid);
						daodevice.write(device);
					}
				}
			}
			
			//get status "ready"
			topicToQuery.add(String.join("/", basetopic, deviceid, "$state"));
			Map<String,String> result = mqtt.getMessages(topicToQuery, 500);
			panelToShow.setStatus(!result.isEmpty()&&result.containsValue("ready"));
			
			// Add the action and add the values
			List<ActionDAOBean> list_daoActions = daoaction.getActionByPanelID(panelToShow.getId());
			for(ActionDAOBean generated_action : list_daoActions) {
				Action actionToShow = new Action();
				actionToShow.setId(generated_action.getId());
				actionToShow.setLabel(generated_action.getLabel());
				panelToShow.addActionsItem(actionToShow);  
			}
			
			//get values
			List<ValueDAOBean> list_daoValues = daovalue.getValuesByPanelID(panelToShow.getId());
			Map<String,Value> topicToValue = new HashMap<>();
			for(ValueDAOBean generated_value : list_daoValues) {
				Value valueToShow = new Value();
				valueToShow.setId(generated_value.getId());
				valueToShow.setLabel(generated_value.getLabel());
				String topic = String.join("/", basetopic, deviceid, generated_value.getSubtopic());
				topicToValue.put(topic, valueToShow);
			}
			
			//retrieve values from mqtt
			List<String> topics = topicToValue.keySet().stream().collect(Collectors.toList());
			Map<String,String> valuesMQTT = mqtt.getMessages(topics, 1000);
			for(String key:topicToValue.keySet()) {
				System.out.println(key+":"+valuesMQTT.get(key));
				Value valueElement = topicToValue.get(key);
				String value = valuesMQTT.get(key);
				if(value==null||value.length()==0) value = "N/A";
				valueElement.setValue(value);
				panelToShow.addValuesItem(valueElement);
			}
			resultsToShow.add(panelToShow);
		}
		
		// Return the panels to the API
		return Response.status(Response.Status.OK).entity(resultsToShow).build();
	}

	@Override
	public Response swapPanes(Integer pid1, Integer pid2, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.OK).entity("TEST").build();
	}

}
