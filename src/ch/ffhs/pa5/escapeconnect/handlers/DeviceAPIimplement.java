package ch.ffhs.pa5.escapeconnect.handlers;

import java.io.IOException;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;
import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;

public class DeviceAPIimplement implements DeviceApiService {

	@Override
	public Response addDevice(AddDeviceBody addDeviceBody, SecurityContext securityContext) {
		if(addDeviceBody==null||addDeviceBody.getFile()==null) {
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity("no File provided").build();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode root = objectMapper.readTree(addDeviceBody.getFile());
			System.out.println("Got JSON-Riddledefinition with name: " + root.path("definition").path("name").asText());
			
			JsonNode device = root.path("device");
			DeviceDAOBean deviceDAOBean = objectMapper.treeToValue(device, DeviceDAOBean.class);
			System.out.println("creating Device with MAC: " + deviceDAOBean.getMac());
			if(!addDeviceBody.getName().isBlank()) {
				deviceDAOBean.setName(addDeviceBody.getName());
			}
			DAOdevice.write(deviceDAOBean);
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		return Response.status(Response.Status.OK).entity(addDeviceBody.getFile()).build();
	}

	@Override
	public Response deleteDevice(@NotNull Integer deviceid, Boolean forces, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response upgradeFirmware(UpdateDeviceBody body, @NotNull Integer deviceid, Boolean forces,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
