package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.paho.client.mqttv3.MqttMessage;

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
		Map<String,String> settings = new HashMap<>();
		String device_mac = "";
		for(SettingMod setting:body) {
			SettingDAOBean settingbean = daosettings.getSettingById(setting.getId());
			if(settingbean==null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Activity " + setting.getId() + " not found").build();
			}else {
				if(device_mac.isEmpty()) {
					device_mac=settingbean.getDevice_mac();
				}else {
					if(!device_mac.equals(settingbean.getDevice_mac())) {
						return Response.status(Response.Status.CONFLICT).entity("Can not change Settings from multiple devices at a time").build();
					}
				}
				if(isValid(setting.getValue(), settingbean)) {
					settings.put(settingbean.getName(), setting.getValue());
				}else{
					return Response.status(Response.Status.CONFLICT).entity("Setting " + setting.getId() + " is invalid").build();
				}
			}
		}
		
		DeviceDAOBean device = daodevice.getByMac(device_mac);
		
		String topic = String.join("/", device.getBasetopic(), device.getDeviceid(), "$implementation/config/set");
		MqttMessage msg = new MqttMessage(ParseSettingsJSON.prepareJSON(settings).getBytes());
		mqtt.publish(topic, msg);
		return Response.status(Response.Status.OK).build();
	}
	
	private boolean isValid(String val, SettingDAOBean condition) {
		return true;
	}

} 
