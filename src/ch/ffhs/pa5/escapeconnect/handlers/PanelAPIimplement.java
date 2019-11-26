package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
			
			if(device.getBasetopic()==""||device.getDeviceid()==null) {
				LinkedList<String> topicToQuery = new LinkedList<>();
				System.out.println("looking for device " + generated_panel.getDevice_mac());
				topicToQuery.add("+/+/$mac");
				DAOecsettings daoecsettings = new DAOecsettings();
				EcSettings settings = daoecsettings.get();
				MQTTconnector mqtt = new MQTTconnector(settings.getMqttUrl(), settings.getMqttName(), settings.getMqttPass());
				Map<String,String> devices = mqtt.getMessages(topicToQuery, 1000, true,false);
				for(String key:devices.keySet()) {
					System.out.println(key + ":" + devices.get(key));
					if(MACformating.sanitizeMAC(devices.get(key)).equals(generated_panel.getDevice_mac())) {
						String[] topiclevels = key.split("/");
						String basetopic = topiclevels[0];
						String deviceid = topiclevels[1];
						System.out.println("Basetopic: " + basetopic);
						System.out.println("deviceid: " + deviceid);
						device.setBasetopic(basetopic);
						device.setDeviceid(deviceid);
						daodevice.write(device);
					}
				}
			}
			
			// Add the action and add the values
			List<ActionDAOBean> list_daoActions = daoaction.getActionByPanelID(panelToShow.getId());
			for(ActionDAOBean generated_action : list_daoActions) {
				Action actionToShow = new Action();
				actionToShow.setId(generated_action.getId());
				actionToShow.setLabel(generated_action.getLabel());
				panelToShow.addActionsItem(actionToShow);  
			}
			List<ValueDAOBean> list_daoValues = daovalue.getValuesByPanelID(panelToShow.getId());
			for(ValueDAOBean generated_value : list_daoValues) {
				Value valueToShow = new Value();
				valueToShow.setId(generated_value.getId());
				valueToShow.setLabel(generated_value.getLabel());
				panelToShow.addValuesItem(valueToShow);  
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
