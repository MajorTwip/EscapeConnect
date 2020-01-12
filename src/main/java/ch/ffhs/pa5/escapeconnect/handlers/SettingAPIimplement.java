/* Code has been formated */
package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.core.JsonProcessingException;

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

/**
 * Handler "SettingAPIimplement" manage the settings of a riddle. Used in the first line to send the
 * settings back to the API caller in order to modify them.
 *
 * @author Yvo von Kaenel
 */
public class SettingAPIimplement implements SettingApiService {

  DAOsettings daosettings = new DAOsettings();
  DAOpanel daopanel = new DAOpanel();
  DAOdevice daodevice = new DAOdevice();
  DAOecsettings daoecsettings = new DAOecsettings();
  MQTTconnector mqtt = new MQTTconnector();

  /**
   * getSettingsByPanelId() get the setting for a specific riddle (panel).
   *
   * @param panelId identifier of the riddle
   * @param securityContext An injectable interface that provides access to security related information
   * @return list of settings (bean)
   *   
   */
  
  @Override
  public Response getSettingsByPanelId(Integer panelId, SecurityContext securityContext) {

    if (panelId == null) {
      return Response.status(Response.Status.CONFLICT).entity("no panelId provided").build();
    }

    // get current config from device
    PanelDAOBean panel = daopanel.getById(panelId);

    DeviceDAOBean device = daodevice.getByMac(panel.getDevice_mac());

    EcSettings ecsettings = daoecsettings.get();

    mqtt.config(ecsettings.getMqttUrl(), ecsettings.getMqttName(), ecsettings.getMqttPass());

    String settingstopic =
        String.join("/", device.getBasetopic(), device.getDeviceid(), "$implementation/config");

    List<String> topics = new LinkedList<>();
    topics.add(settingstopic);

    Map<String, String> results = mqtt.getMessages(topics, 1000);

    String settingsjson = results.get(settingstopic);

    Map<String, String> settingsactual = ParseSettingsJSON.parseJSON(settingsjson);

    List<SettingDAOBean> settings = daosettings.getSettingsByPanelId(panelId);
    List<Setting> settingsresponse = new LinkedList<>();
    for (SettingDAOBean set : settings) {
      Setting respset = new Setting();
      respset.setId(set.getId());
      respset.setLabel(set.getLabel());
      respset.setMax(String.valueOf(set.getMax()));
      respset.setMin(String.valueOf(set.getMin()));
      respset.setType(Setting.TypeEnum.fromValue(set.getType()));
      respset.setValue(set.getValue());

      if (settingsactual.containsKey(set.getName())) {
        String value = settingsactual.get(set.getName());
        respset.setValue(value);
        set.setValue(value);
        daosettings.write(set);
      }

      settingsresponse.add(respset);
    }

    return Response.status(200).entity(settingsresponse).build();
  }

  /**
   * setSetting() set the setting for a specific riddle (panel).
   *
   * @param body as a list of setting
   * @param securityContext An injectable interface that provides access to security related information
   * @return a response if the device can be upgraded (more: https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/Response.html)
   *   
   */
  
  @Override
  public Response setSetting(List<SettingMod> body, SecurityContext securityContext) {
    Map<String, Object> settings = new HashMap<>();
    String device_mac = "";
    for (SettingMod setting : body) {
      SettingDAOBean settingbean = daosettings.getSettingById(setting.getId());
      if (settingbean == null) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity("Activity " + setting.getId() + " not found")
            .build();
      } else {
        if (device_mac.isEmpty()) {
          device_mac = settingbean.getDevice_mac();
        } else {
          if (!device_mac.equals(settingbean.getDevice_mac())) {
            return Response.status(Response.Status.CONFLICT)
                .entity("Can not change Settings from multiple devices at a time")
                .build();
          }
        }
        if (isValid(setting.getValue(), settingbean)) {
        	switch(settingbean.getType()) {
        	case "long":
        		try {
                    settings.put(settingbean.getName(), Long.valueOf(setting.getValue()));
        		}catch(NumberFormatException e) {
                    settings.put(settingbean.getName(), setting.getValue());
                    System.out.println("Got wrong formatting in Long-Setting, sent as String");
        		}
                break;
        	case "bool":
                settings.put(settingbean.getName(), setting.getValue().toLowerCase().equals("true"));
                break;
        	case "double":
	        	try {
	                settings.put(settingbean.getName(), Double.valueOf(setting.getValue()));
	    		}catch(NumberFormatException e) {
	                settings.put(settingbean.getName(), setting.getValue());
	                System.out.println("Got wrong formatting in Long-Setting, sent as String");
	    		}
	            break;
	        default:
	          settings.put(settingbean.getName(), setting.getValue());
        	}
        } else {
          return Response.status(Response.Status.CONFLICT)
              .entity("Setting " + setting.getId() + " is invalid")
              .build();
        }
      }
    }

    DeviceDAOBean device = daodevice.getByMac(device_mac);

    String topic =
        String.join("/", device.getBasetopic(), device.getDeviceid(), "$implementation/config/set");
    String settingsjson = ParseSettingsJSON.prepareJSON(settings);
    MqttMessage msg = new MqttMessage(settingsjson.getBytes());

    EcSettings ecsettings = daoecsettings.get();
    System.out.println(settings);
    System.out.println("Publish: " + settingsjson + " to " + topic);
    mqtt.config(ecsettings.getMqttUrl(), ecsettings.getMqttName(), ecsettings.getMqttPass());
    mqtt.publish(topic, msg);
    return Response.status(Response.Status.OK).build();
  }

  private boolean isValid(String val, SettingDAOBean condition) {
    return true;
  }
}
