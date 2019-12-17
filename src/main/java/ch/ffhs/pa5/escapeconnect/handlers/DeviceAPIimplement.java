/** Code has been formated */
/** @author Yvo von Kaenel and Ludovic Renevey */
package ch.ffhs.pa5.escapeconnect.handlers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;
import ch.ffhs.pa5.escapeconnect.utils.FirmwareUtil;
import ch.ffhs.pa5.escapeconnect.utils.MACformating;

public class DeviceAPIimplement implements DeviceApiService {

  DAOdevice daodevice;
  DAOpanel daopanel;
  DAOaction daoaction;
  DAOvalue daovalue;
  DAOsettings daosetting;
  DAOecsettings daoecsettings;

  MQTTconnector mqtt = new MQTTconnector();

  public DeviceAPIimplement() {

    // Instances must be created here in order to mock them later
    daodevice = new DAOdevice();
    daopanel = new DAOpanel();
    daoaction = new DAOaction();
    daovalue = new DAOvalue();
    daosetting = new DAOsettings();
    daoecsettings = new DAOecsettings();
  }

  @Override
  public Response addDevice(AddDeviceBody addDeviceBody, SecurityContext securityContext) {
    // wird aufgerufen wenn /device/add aufgerufen wird
    if (addDeviceBody == null
        || addDeviceBody.getFile() == null) { // Fehler wenn keine Datei  gesendet wird
      return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
          .entity("no File provided")
          .build();
    }

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      // parse Datei
      JsonNode root = objectMapper.readTree(addDeviceBody.getFile());
      System.out.println(
          "Got JSON-Riddledefinition with name: " + root.path("definition").path("name").asText());

      // lade "device" aus dem JSON-File und transformiere dieses in ein Bean f�r die DAO-Methoden
      JsonNode devicejson = root.path("device");
      if (devicejson.isEmpty()) {
        return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
            .entity("not compling to JSON-schema, no 'device'-Node")
            .build();
      }
      DeviceDAOBean device = objectMapper.treeToValue(devicejson, DeviceDAOBean.class);

      // schreibe Device in DB
      daodevice.write(device);
      System.out.println("creating Device with MAC: " + device.getMac());

      // Lade Panel aus dem JSON
      JsonNode paneljson = root.path("panel");
      PanelDAOBean panel = new PanelDAOBean();
      panel.setName(device.getName());
      // Falls explizit Name gew�nscht, w�hle diesen f�r das Panel, sonst Devicename
      if (addDeviceBody.getName() != null && addDeviceBody.getName().length() > 0) {
        panel.setName(addDeviceBody.getName());
      }
      // Setze FK mac
      panel.setDevice_mac(device.getMac());
      // schreibe Panel in DB und erhalte ID (fortlaufende Int)
      int panelId = daopanel.write(panel);
      System.out.println("Wrote Panel with id: " + String.valueOf(panelId));

      // iteriere �ber alle values im Panel und schreibe diese in DB
      Iterator<JsonNode> values = paneljson.path("values").elements();
      while (values.hasNext()) {
        JsonNode valuejson = values.next();
        ValueDAOBean value = objectMapper.treeToValue(valuejson, ValueDAOBean.class);
        value.setPanel_id(panelId);
        int valueId = daovalue.write(value);
        System.out.println("Wrote value with id: " + String.valueOf(valueId));
      }

      // iteriere �ber alle actions im Panel und schreibe diese in DB
      Iterator<JsonNode> actions = paneljson.path("actions").elements();
      while (actions.hasNext()) {
        JsonNode actionjson = actions.next();
        ActionDAOBean action = objectMapper.treeToValue(actionjson, ActionDAOBean.class);
        action.setPanel_id(panelId);
        int actionId = daoaction.write(action);
        System.out.println("Wrote action with id: " + String.valueOf(actionId));
      }

      // iteriere �ber alle settings im Device und schreibe diese in DB
      Iterator<JsonNode> settings = root.path("settings").elements();
      while (settings.hasNext()) {
        JsonNode settingjson = settings.next();
        SettingDAOBean setting = objectMapper.treeToValue(settingjson, SettingDAOBean.class);
        setting.setDevice_mac(device.getMac());
        setting.setPanel_id(panelId);
        int settingId = daosetting.write(setting);
        System.out.println("Wrote Setting with id: " + String.valueOf(settingId));
      }

    } catch (JsonParseException e) {
      // e.printStackTrace();
      return Response.status(418).build();
    } catch (IOException e1) {
      e1.printStackTrace();
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    return Response.status(Response.Status.OK).build();
  }

  @Override
  public Response deleteDevice(
      @NotNull String devicemac, Boolean forces, SecurityContext securityContext) {
    if (forces == null || !forces) {
      return Response.status(Response.Status.EXPECTATION_FAILED)
          .entity("forced must be set")
          .build();
    }
    String mac = MACformating.sanitizeMAC(devicemac);
    System.out.println("Deleting device: " + mac);
    daodevice.delete(mac);
    return Response.status(Response.Status.OK).build();
  }

  @Override
  public Response upgradeFirmware(
      UpdateDeviceBody updateDeviceBody,
      @NotNull int panelId,
      Boolean forced,
      SecurityContext securityContext) {

    if (updateDeviceBody == null || updateDeviceBody.getFirmware() == null) {
      // If there is no file, then the system sends back an error.
      return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
          .entity("no File provided")
          .build();
    }

    // get MAC
    PanelDAOBean pan = daopanel.getById(panelId);
    System.out.println("This is the panel: " + pan.getId());
    // Per default, an empty PanelDAOBean has an id of 0, see the bean
    if (pan == null || pan.getId() == 0) {
      // If there panel with this ID.
      return Response.status(Response.Status.NOT_FOUND).entity("no such panel").build();
    }

    String deviceId = pan.getDevice_mac();

    // Start the connection with MQTT with the correct credentials
    EcSettings settings = daoecsettings.get();
    mqtt.config(settings.getMqttUrl(), settings.getMqttName(), settings.getMqttPass());

    // Get the MD5 from the device via MQTT
    // In a new version of this method, this can be used and compared to the new checksum
    DeviceDAOBean deviceToUpdate = daodevice.getByMac(deviceId);
    LinkedList<String> requestMsgMd5 = new LinkedList<>();
    String fwrequest =
        String.join(
            "/", deviceToUpdate.getBasetopic(), deviceToUpdate.getDeviceid(), "$fw/checksum");
    requestMsgMd5.add(fwrequest);
    Map<String, String> receivedMsgMd5 = mqtt.getMessages(requestMsgMd5, 1000);
    System.out.println(
        "Upgrade for panel: "
            + panelId
            + " >> MAC: "
            + deviceId
            + " >> The MD5 is "
            + receivedMsgMd5.get(fwrequest));

    byte[] newFirmware = updateDeviceBody.getFirmware();

    // get name/version of the new firmware
    System.out.println(
        "New Firmwarename - Version: "
            + FirmwareUtil.getFirmwareName(newFirmware)
            + ": "
            + FirmwareUtil.getFirmwareVersion(newFirmware));

    // Calculate the MD5 of the new firmware
    String newChecksum = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] hash = md.digest(newFirmware);
      StringBuilder sb = new StringBuilder(2 * hash.length);
      for (byte b : hash) {
        sb.append(String.format("%02x", b & 0xff));
      }
      newChecksum = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    // Prepare topic for publishing
    String topic =
        String.join(
            "/",
            deviceToUpdate.getBasetopic(),
            deviceToUpdate.getDeviceid(),
            "$implementation/ota/firmware",
            newChecksum);

    // Publish the bin file (Fire & forget logic for this version)
    // No need to handle exceptions here, since the MQTT functions do it.
    MqttMessage msg = new MqttMessage(updateDeviceBody.getFirmware());
    mqtt.publish(topic, msg);

    return Response.status(Response.Status.OK).build();
  }
}
