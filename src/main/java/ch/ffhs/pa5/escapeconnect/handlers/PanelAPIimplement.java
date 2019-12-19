/* Code has been formated */
package ch.ffhs.pa5.escapeconnect.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.core.JsonParseException;

import ch.ffhs.pa5.escapeconnect.api.PanelApiService;
import ch.ffhs.pa5.escapeconnect.bean.Action;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.Panel;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.Value;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;
import ch.ffhs.pa5.escapeconnect.utils.MACformating;

/** 
 * handler "PanelAPIimplement" manage the panels (riddle) linked to a device. Used in the first line to send the panels (saved in the database) back to the API caller. 
 * 
 * @author Ludovic Renevey 
 * 
 */

public class PanelAPIimplement implements PanelApiService {

  // Instances must be created here in order to mock them later
  DAOpanel daopanel = new DAOpanel();
  DAOaction daoaction = new DAOaction();
  DAOvalue daovalue = new DAOvalue();
  DAOdevice daoDevice = new DAOdevice();
  DAOecsettings daoecsettings = new DAOecsettings();
  MQTTconnector mqtt = new MQTTconnector();

  /** 
   * 
   * getPanes() gets the panels (riddles) from the database (panelDAOBean) in order to send them back the the API caller (panel).
   * 
   * @param securityContext An injectable interface that provides access to security related information
   * @return a list of panels (see corresponding java bean in the package bean)
   *
   */
  
  @Override
  public Response getPanes(SecurityContext securityContext) {

    List<Panel> resultsToShow = new ArrayList<>();
    // get the data through the DAO
    List<PanelDAOBean> resultsFromDB = daopanel.getAllPanels();

    int place = 0;
    // convert the PanelDAOBeans to Panels
    for (PanelDAOBean generatedPanel : resultsFromDB) {
      Panel panelToShow = new Panel();
      panelToShow.setId(generatedPanel.getId());
      panelToShow.setTitle(generatedPanel.getName());
      panelToShow.setOrder(place);
      place = place + 1;

      // check if already recognized on server
      // the function getByMac returns a DeviceDAOBean
      // Only device has boolean OTA but panel use it on frontend
      DeviceDAOBean device = daoDevice.getByMac(generatedPanel.getDevice_mac());

      // Info will be used on the frontend (Button "Upgrade riddle" visible)
      panelToShow.setUpgradeenabled(device.issupportsOTA());

      LinkedList<String> topicToQuery = new LinkedList<>();

      // Start the connection with MQTT with the correct credentials
      EcSettings settings = daoecsettings.get();
      mqtt.config(settings.getMqttUrl(), settings.getMqttName(), settings.getMqttPass());

      String baseTopic = device.getBasetopic();
      String deviceId = device.getDeviceid();

      // When new riddle is uploaded, this is not set.
      if (baseTopic == "" || deviceId == null) {
        System.out.println("looking for device " + generatedPanel.getDevice_mac());
        // This use MQTT wildcards in order to get the baseTopic and the device id
        topicToQuery.add("+/+/$mac");
        Map<String, String> devices = mqtt.getMessages(topicToQuery, 1000, true, false);
        topicToQuery.clear();
        // loop in order to get the baseTopic and the deviceId from the device
        // loop goes through all the devices linked to this MQTT broker
        for (String key : devices.keySet()) {
          System.out.println(key + ":" + devices.get(key));
          if (MACformating.sanitizeMAC(devices.get(key)).equals(generatedPanel.getDevice_mac())) {
            String[] topicLevels = key.split("/");
            baseTopic = topicLevels[0];
            deviceId = topicLevels[1];
            System.out.println("Basetopic: " + baseTopic);
            System.out.println("Device Id: " + deviceId);
            device.setBasetopic(baseTopic);
            device.setDeviceid(deviceId);
            daoDevice.write(device);
          }
        }
      }

      // get status "ready"
      topicToQuery.add(String.join("/", baseTopic, deviceId, "$state"));
      Map<String, String> result = mqtt.getMessages(topicToQuery, 500);
      panelToShow.setStatus(!result.isEmpty() && result.containsValue("ready"));

      // Add the actions (labels)
      List<ActionDAOBean> listDaoActions = daoaction.getActionByPanelID(panelToShow.getId());
      for (ActionDAOBean generatedAction : listDaoActions) {
        Action actionToShow = new Action();
        actionToShow.setId(generatedAction.getId());
        actionToShow.setLabel(generatedAction.getLabel());
        panelToShow.addActionsItem(actionToShow);
      }

      // Add the values (labels)
      List<ValueDAOBean> listDaoValues = daovalue.getValuesByPanelID(panelToShow.getId());
      Map<String, Value> topicToValue = new HashMap<>();
      for (ValueDAOBean generatedValue : listDaoValues) {
        Value valueToShow = new Value();
        valueToShow.setId(generatedValue.getId());
        valueToShow.setLabel(generatedValue.getLabel());
        String topic = String.join("/", baseTopic, deviceId, generatedValue.getSubtopic());
        topicToValue.put(topic, valueToShow);
      }

      // Retrieve the real values from MQTT (snapshot, not a stream)
      List<String> topics = topicToValue.keySet().stream().collect(Collectors.toList());
      Map<String, String> valuesMQTT = mqtt.getMessages(topics, 1000);
      for (String key : topicToValue.keySet()) {
        System.out.println("Current value is: " + key + ":" + valuesMQTT.get(key));
        Value valueElement = topicToValue.get(key);
        String value = valuesMQTT.get(key);
        if (value == null || value.length() == 0) value = "N/A";
        valueElement.setValue(value);
        panelToShow.addValuesItem(valueElement);
      }
      resultsToShow.add(panelToShow);
    }

    // Return the panels to the API
    return Response.status(Response.Status.OK).entity(resultsToShow).build();
  }

  /** 
   * 
   * swapPanes() can change the order of 2 panels. This method has not been implemented in this version.
   * 
   */
  
  @Override
  public Response swapPanes(Integer pid1, Integer pid2, SecurityContext securityContext) {
    // TODO Auto-generated method stub
    return Response.status(Response.Status.OK).entity("TEST").build();
  }
}
