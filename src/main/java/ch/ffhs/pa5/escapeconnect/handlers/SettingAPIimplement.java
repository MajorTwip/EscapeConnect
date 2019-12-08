package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.SettingApiService;
import ch.ffhs.pa5.escapeconnect.bean.Setting;
import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.SettingMod;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettings;

public class SettingAPIimplement implements SettingApiService {
	
	DAOsettings daosettings = new DAOsettings();
	

	@Override
	public Response getSettingsByPanelId(Integer panelId, SecurityContext securityContext) {
		
		if(panelId==null) {
			return Response.status(Response.Status.CONFLICT).entity("no panelId provided").build();
		}
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
