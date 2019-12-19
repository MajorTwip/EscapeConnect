/* Code has been formated */

package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.ActionApiService;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/** 
 * Handler "ActionAPIimplement" triggers an action of a riddle (panel) over MQTT 
 * 
 * @author Javier Garcia 
 * 
 */

public class ActionAPIimplement implements ActionApiService {
  DAOaction daoaction = new DAOaction();
  DAOecsettings daoecsettings = new DAOecsettings();
  MQTTconnector mqtt = new MQTTconnector();

  String maintopic = null;
  String mainpayload = null;

  /** 
   * 
   * doAction() has the actionId has a parameter so that it can find it in the database and trigger the action over MQTT.
   * 
   * @param actionId Identifier of an action (of a panel, also called riddle)	 
   * @param securityContext An injectable interface that provides access to security related information
   * @return a response if the action has been triggered or not found (more: https://docs.oracle.com/javaee/7/api/javax/ws/rs/core/Response.html)
   *
   */
  
  @Override
  public Response doAction(@NotNull Integer actionId, SecurityContext securityContext) {
    // get the data through the DAO
    List<ActionDAOBean> listDaoActions = daoaction.getActionByID(actionId);
    for (ActionDAOBean generated_action : listDaoActions) {
      String topic =
          String.join(
              "/",
              generated_action.getBasetopic(),
              generated_action.getDeviceid(),
              generated_action.getSubtopic());
      String payload = generated_action.getPayload();
      maintopic = topic;
      mainpayload = payload;
    }
    if (listDaoActions.isEmpty()) {
      System.out.println("No Action Found with that ID");
      return Response.status(Response.Status.NOT_FOUND)
          .entity("No Action Found with that ID")
          .build();
    } else {
      EcSettings settings = daoecsettings.get();
      mqtt.config(settings.getMqttUrl(), settings.getMqttName(), settings.getMqttPass());
      MqttMessage hello = new MqttMessage();
      hello.setPayload(mainpayload.getBytes());
      mqtt.publish(maintopic, hello);
      return Response.status(Response.Status.OK).entity("Aktion ausgelöst").build();
    }
  }
}
