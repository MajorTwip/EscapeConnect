package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.SettingApiService;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.Setting;
import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.SettingMod;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettings;
import ch.ffhs.pa5.escapeconnect.utils.ParseSettingsJSON;

public class SettingAPIimplement implements SettingApiService {
	
	DAOsettings daosettings = new DAOsettings();
	DAOpanel daopanel = new DAOpanel();
	DAOdevice daodevice = new DAOdevice();
	DAOecsettings daoecsettings = new DAOecsettings();
	MQTTconnector mqtt = new MQTTconnector();
	


	@Override
	public Response getSettingsByPanelId(Integer panelId, SecurityContext securityContext) {
		
		if(panelId==null) {
			return Response.status(Response.Status.CONFLICT).entity("no panelId provided").build();
		}
		
		//get current config from device
		PanelDAOBean panel = daopanel.getById(panelId);
		
		DeviceDAOBean device = daodevice.getByMac(panel.getDevice_mac());
		
		EcSettings ecsettings = daoecsettings.get();
		
		mqtt.config(ecsettings.getMqttUrl(), ecsettings.getMqttName(), ecsettings.getMqttPass());
		
		String settingstopic = String.join("/", device.getBasetopic(),device.getDeviceid(),"$implementation/config");
		List<String> topics = new LinkedList<>();
		topics.add(settingstopic);
		
		Map<String,String> results = mqtt.getMessages(topics, 1000);
		
		String settingsjson = results.get(settingstopic);
		
		Map<String,String> settingsactual = ParseSettingsJSON.parseJSON(settingsjson);
		
		List<SettingDAOBean> settings = daosettings.getSettingsByPanelId(panelId);
		List<Setting> settingsresponse = new LinkedList<>();
		for(SettingDAOBean set:settings) {
			Setting respset = new Setting();
			respset.setId(set.getId());
			respset.setLabel(set.getLabel());
			respset.setMax(String.valueOf(set.getMax()));
			respset.setMin(String.valueOf(set.getMin()));
			respset.setType(Setting.TypeEnum.fromValue(set.getType()));
			respset.setValue(set.getValue());
			
			if(settingsactual.containsKey(set.getName())) {
				String value = settingsactual.get(set.getName());
				respset.setValue(value);
				set.setValue(value);
				daosettings.write(set);
			}
			
			settingsresponse.add(respset);
		}
		

		
		
		
		return Response.status(200).entity(settingsresponse).build();
	}

	@Override
	public Response setSetting(List<SettingMod> body, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

} 
