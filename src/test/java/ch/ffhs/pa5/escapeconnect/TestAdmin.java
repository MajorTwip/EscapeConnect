/** Code has been formated */
/** @author Yvo von Kaenel */
package ch.ffhs.pa5.escapeconnect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.LoginBody;
import ch.ffhs.pa5.escapeconnect.bean.Setup;
import ch.ffhs.pa5.escapeconnect.handlers.AdminAPIimplement;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DBAdapter;

@ExtendWith(MockitoExtension.class)
public class TestAdmin {

  @InjectMocks AdminAPIimplement adminapi;

  @Mock DAOecsettings daoecsettings;

  @Mock DBAdapter dba;

  @Test
  public void doLogin() {
    MockitoAnnotations.initMocks(this);

    // Necessary in order to have a password in the system and compare it to the body "okbody"
    EcSettings ecs = new EcSettings("123", "url");
    Mockito.when(daoecsettings.get()).thenReturn(ecs);

    // Create different body with different passwords (pwd) for the assertequals()
    LoginBody emptybody = new LoginBody();
    emptybody.setPasshash("");

    LoginBody okbody = new LoginBody();
    okbody.setPasshash("123");

    LoginBody notokbody = new LoginBody();
    notokbody.setPasshash("falsepass");

    // Test when there is no body, no pwd
    assertEquals(
        Response.Status.CONFLICT.getStatusCode(), adminapi.doLogin(null, null).getStatus());
    // when the body is there but empty
    assertEquals(
        Response.Status.CONFLICT.getStatusCode(), adminapi.doLogin(emptybody, null).getStatus());
    // when the pwd is correct
    assertEquals(Response.Status.OK.getStatusCode(), adminapi.doLogin(okbody, null).getStatus());
    // when the pwd is incorrect
    assertEquals(
        Response.Status.UNAUTHORIZED.getStatusCode(),
        adminapi.doLogin(notokbody, null).getStatus());
  }

  @Test
  public void doSetup() {
    MockitoAnnotations.initMocks(this);

    Mockito.doNothing().when(dba).createDBifNone();

    // prepare the object for the 2nd test below
    Setup body = new Setup();
    body.setAdminpass("123");
    body.setMqtturl("url");
    body.setMqttuser("MQTTUSER");
    body.setMqttpass("MQTTPASS");

    // When there is no setup object (case "unsuccessful")
    assertEquals(Response.Status.CONFLICT.getStatusCode(), adminapi.setup(null, null).getStatus());
    // When there is a setup object (case "successful")
    assertEquals(Response.Status.OK.getStatusCode(), adminapi.setup(body, null).getStatus());
  }
}
