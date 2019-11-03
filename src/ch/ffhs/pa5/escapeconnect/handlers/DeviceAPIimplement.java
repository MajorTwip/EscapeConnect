package ch.ffhs.pa5.escapeconnect.handlers;

import java.io.IOException;
import java.util.Iterator;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;

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
			
			JsonNode devicejson = root.path("device");
			DeviceDAOBean device = objectMapper.treeToValue(devicejson, DeviceDAOBean.class);
			System.out.println("creating Device with MAC: " + device.getMac());
			
			DAOdevice.write(device);
			
			
			JsonNode paneljson = root.path("panel");
			PanelDAOBean panel = new PanelDAOBean();
			panel.setName(device.getName());
			//Falls explizit Name gewünscht, wähle diesen.
			if(!addDeviceBody.getName().isBlank()) {
				panel.setName(addDeviceBody.getName());
			}
			panel.setDevice_mac(device.getMac());
			int panel_id = DAOpanel.write(panel); 
			System.out.println("Wrote Panel with id: " + String.valueOf(panel_id));
			
			Iterator<JsonNode> values = paneljson.path("values").elements();
			while(values.hasNext()) {
				JsonNode valuejson = values.next();
				ValueDAOBean value = objectMapper.treeToValue(valuejson, ValueDAOBean.class);
				value.setPanel_id(panel_id);
				DAOvalue.write(value);
			}
			
			Iterator<JsonNode> actions = paneljson.path("actions").elements();
			while(actions.hasNext()) {
				JsonNode actionjson = actions.next();
				ActionDAOBean action = objectMapper.treeToValue(actionjson, ActionDAOBean.class);
				action.setPanel_id(panel_id);
				DAOaction.write(action);
			}
			
			
			
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
