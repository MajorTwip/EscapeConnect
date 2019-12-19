/* Code has been formated */
/** @author Yvo von Kaenel */
package ch.ffhs.pa5.escapeconnect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.handlers.ActionAPIimplement;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;

@ExtendWith(MockitoExtension.class)
public class TestAction {

  @InjectMocks ActionAPIimplement actionapi;

  @Mock DAOaction daoaction;

  @Mock DAOecsettings daoecsettings;

  @Mock MQTTconnector mqtt;

  @Test
  public void doAction() {
    MockitoAnnotations.initMocks(this);

    // Prepare the list to be returned when mocked class is called
    List<ActionDAOBean> act0 = new ArrayList<>();
    List<ActionDAOBean> act = new ArrayList<>();
    ActionDAOBean adb = new ActionDAOBean();
    adb.setBasetopic("bt");
    adb.setDeviceid("id");
    adb.setId(2);
    adb.setPayload("pl");
    adb.setSubtopic("st");
    act.add(adb);

    EcSettings ecs = new EcSettings("123", "url");

    // Mock the data when DAO is called
    Mockito.when(daoaction.getActionByID(1)).thenReturn(act0);
    Mockito.when(daoaction.getActionByID(2)).thenReturn(act);
    Mockito.when(daoecsettings.get()).thenReturn(ecs);

    // Proceed with 2 comparisons (1st with a wrong action, the 2nd with a valid one "id")
    assertEquals(
        Response.Status.NOT_FOUND.getStatusCode(), actionapi.doAction(1, null).getStatus());
    assertEquals(Response.Status.OK.getStatusCode(), actionapi.doAction(2, null).getStatus());
  }
}
