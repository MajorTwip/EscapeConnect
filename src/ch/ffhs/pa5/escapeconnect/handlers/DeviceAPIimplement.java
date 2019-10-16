package ch.ffhs.pa5.escapeconnect.handlers;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;
import ch.ffhs.pa5.escapeconnect.bean.Body;
import ch.ffhs.pa5.escapeconnect.bean.Body1;

public class DeviceAPIimplement implements DeviceApiService {

	@Override
	public Response addDevice(Body body, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteDevice(@NotNull Integer deviceid, Boolean forces, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response upgradeFirmware(Body1 body, @NotNull Integer deviceid, Boolean forces,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
