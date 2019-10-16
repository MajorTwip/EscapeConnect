package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.SettingApiService;
import ch.ffhs.pa5.escapeconnect.bean.SettingMod;

public class SettingAPIimplement implements SettingApiService {

	@Override
	public Response getSettingsByDeviceId(Integer deviceId, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response setSetting(List<SettingMod> body, Integer deviceId, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
