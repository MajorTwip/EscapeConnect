package ch.ffhs.pa5.escapeconnect.handlers;

import java.io.IOException;
import java.util.Iterator;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOactionIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdeviceIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanelIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettingIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalueIF;
import ch.ffhs.pa5.escapeconnect.utils.MACformating;

public class DeviceAPIimplement implements DeviceApiService {

	DAOdeviceIF daodevice;
	DAOpanelIF daopanel;
	DAOactionIF daoaction;
	DAOvalueIF daovalue;
	DAOsettingIF daosetting;
	
	public DeviceAPIimplement() {
		daodevice = new DAOdevice();
		daopanel = new DAOpanel();
		daoaction = new DAOaction();
		daovalue = new DAOvalue();
		daosetting = new DAOsettings();
	}

	@Override
	public Response addDevice(AddDeviceBody addDeviceBody, SecurityContext securityContext) {
	//wird aufgerufen wenn /device/add aufgerufen wird
		if(addDeviceBody==null||addDeviceBody.getFile()==null) { //Fehler wenn keine Datei  gesendet wird
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity("no File provided").build();
		}
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			//parse Datei
			JsonNode root = objectMapper.readTree(addDeviceBody.getFile());
			System.out.println("Got JSON-Riddledefinition with name: " + root.path("definition").path("name").asText());
			
			//lade "device" aus dem JSON-File und transformiere dieses in ein Bean f�r die DAO-Methoden
			JsonNode devicejson = root.path("device");
			if(devicejson.isEmpty()) {
				return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity("not compling to JSON-schema, no 'device'-Node").build();
			}
			DeviceDAOBean device = objectMapper.treeToValue(devicejson, DeviceDAOBean.class);
			
			//schreibe Device in DB
			daodevice.write(device);
			System.out.println("creating Device with MAC: " + device.getMac());
			
			//Lade Panel aus dem JSON 
			JsonNode paneljson = root.path("panel");
			PanelDAOBean panel = new PanelDAOBean();
			panel.setName(device.getName());
			//Falls explizit Name gew�nscht, w�hle diesen f�r das Panel, sonst Devicename
			if(addDeviceBody.getName()!=null&&addDeviceBody.getName().length()>0) {
				panel.setName(addDeviceBody.getName());
			}
			//Setze FK mac
			panel.setDevice_mac(device.getMac());
			//schreibe Panel in DB und erhalte ID (fortlaufende Int)
			int panel_id = daopanel.write(panel); 
			System.out.println("Wrote Panel with id: " + String.valueOf(panel_id));
			
			//iteriere �ber alle values im Panel und schreibe diese in DB
			Iterator<JsonNode> values = paneljson.path("values").elements();
			while(values.hasNext()) {
				JsonNode valuejson = values.next();
				ValueDAOBean value = objectMapper.treeToValue(valuejson, ValueDAOBean.class);
				value.setPanel_id(panel_id);
				int value_id=daovalue.write(value);
				System.out.println("Wrote value with id: " + String.valueOf(value_id));
			}
			
			//iteriere �ber alle actions im Panel und schreibe diese in DB
			Iterator<JsonNode> actions = paneljson.path("actions").elements();
			while(actions.hasNext()) {
				JsonNode actionjson = actions.next();
				ActionDAOBean action = objectMapper.treeToValue(actionjson, ActionDAOBean.class);
				action.setPanel_id(panel_id);
				int action_id = daoaction.write(action);
				System.out.println("Wrote action with id: " + String.valueOf(action_id));
			}
			
			//iteriere �ber alle settings im Device und schreibe diese in DB
			Iterator<JsonNode> settings = root.path("settings").elements();
			while(settings.hasNext()) {
				JsonNode settingjson = settings.next();
				SettingDAOBean setting = objectMapper.treeToValue(settingjson, SettingDAOBean.class);
				setting.setDevice_mac(device.getMac());
				setting.setPanel_id(panel_id);
				int setting_id = daosetting.write(setting);
				System.out.println("Wrote Setting with id: " + String.valueOf(setting_id));
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();		
			return Response.status(418).build();
		} catch (IOException e1) {
			e1.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();	
		}
		return Response.status(Response.Status.OK).build();
	}

	@Override
	public Response deleteDevice(@NotNull String devicemac, Boolean forces, SecurityContext securityContext) {
		if(forces==null||!forces) {
			return Response.status(Response.Status.EXPECTATION_FAILED).entity("forced must be set").build();
		}
		String mac = MACformating.sanitizeMAC(devicemac);
		System.out.println("Deleting device: " + mac);
		daodevice.delete(mac);
		return Response.status(Response.Status.OK).build();
	}

	@Override
	public Response upgradeFirmware(UpdateDeviceBody body, @NotNull Integer deviceid, Boolean forces,
			SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
