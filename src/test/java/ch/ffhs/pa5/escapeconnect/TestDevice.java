/** Code has been formated */
/** @authors Yvo von Kaenel and Ludovic Renevey */
package ch.ffhs.pa5.escapeconnect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import ch.ffhs.pa5.escapeconnect.handlers.DeviceAPIimplement;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
// @RunWith(MockitoJUnitRunner.class)
public class TestDevice {

  @InjectMocks DeviceAPIimplement devapi;

  @Mock DAOdevice daodevice;

  @Mock DAOpanel daopanel;

  @Mock DAOaction daoaction;

  @Mock DAOvalue daovalue;

  @Mock DAOsettings daosetting;

  @Mock DAOecsettings daoecsettings;

  @Mock MQTTconnector mqtt;

  @Test
  public void addDevice() {
    MockitoAnnotations.initMocks(this);

    // Wenn die Funktion addDevice() ein DAO aufruft, mocken wir den Rueckgabewert
    Mockito.when(daodevice.write(Mockito.any())).thenReturn(true);
    Mockito.when(daopanel.write(Mockito.any())).thenReturn(1);
    Mockito.when(daoaction.write(Mockito.any())).thenReturn(1);
    Mockito.when(daovalue.write(Mockito.any())).thenReturn(1);
    Mockito.when(daosetting.write(Mockito.any())).thenReturn(1);

    // Erstellen von einem leeren Body, welches normalerweise eine Datei (Bytes) enthaelt
    AddDeviceBody body = new AddDeviceBody();

    // Fehler bei leerem Payload
    System.out.println(devapi.addDevice(null, null));
    assertEquals(
        Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),
        devapi.addDevice(null, null).getStatus());

    // Fehler bei fehlender Datei
    assertEquals(
        Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),
        devapi.addDevice(body, null).getStatus());

    // Fehler bei ungueltiger Datei
    body.setFile(String.valueOf("sdfsdfsfsdf").getBytes());
    assertEquals(418, devapi.addDevice(body, null).getStatus());

    // Fehler bei ungueltiger Datei
    body.setFile(String.valueOf("{}").getBytes());
    assertEquals(
        Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),
        devapi.addDevice(body, null).getStatus());

    // siehe naechstes assertEquals()
    String testFile =
        "{\r\n"
            + "	\"$schema\" : \"https://github.com/MajorTwip/EscapeConnect/schemas/riddledefine_v0.json\",\r\n"
            + "	\"definition\" : {\r\n"
            + "		\"name\" : \"Sandriddle\",\r\n"
            + "		\"version\" : \"1.0\",\r\n"
            + "		\"author\" : \"Yvo von Kanel\",\r\n"
            + "		\"contact\" : \"yvo.vonkaenel@students.ffhs.ch\"\r\n"
            + "	},\r\n"
            + "	\"device\" : {\r\n"
            + "		\"default_name\" : \"Sandratsel 1\",\r\n"
            + "		\"mac\" : \"12:AF:19:CB:43:23\",\r\n"
            + "		\"allows_ota\" : true\r\n"
            + "	},\r\n"
            + "	\"panel\" : {\r\n"
            + "		\"values\" : [\r\n"
            + "			{\r\n"
            + "				\"label\" : \"Sandniveau\",\r\n"
            + "				\"subtopic\" : \"tank/sandlevel\",\r\n"
            + "				\"type\" : \"string\"\r\n"
            + "			},\r\n"
            + "			{\r\n"
            + "				\"label\" : \"Schlusselklappe\",\r\n"
            + "				\"subtopic\" : \"tank/trap\",\r\n"
            + "				\"type\" : \"string\"\r\n"
            + "			}\r\n"
            + "		],\r\n"
            + "		\"actions\" : [\r\n"
            + "			{\r\n"
            + "				\"label\" : \"Schlussel freigeben\",\r\n"
            + "				\"subtopic\" : \"tank/trap/set\",\r\n"
            + "				\"payload\": \"1\"\r\n"
            + "			}\r\n"
            + "		]\r\n"
            + "	},\r\n"
            + "	\"settings\" : \r\n"
            + "	[\r\n"
            + "		{\r\n"
            + "			\"name\" : \"settings/riddleSolution\",\r\n"
            + "			\"label\" : \"Losungswert\",\r\n"
            + "			\"type\" : \"long\",\r\n"
            + "			\"min\" : 0,\r\n"
            + "			\"max\" : 1024\r\n"
            + "		},\r\n"
            + "		{\r\n"
            + "			\"name\" : \"settings/riddleSolutionPrecision\",\r\n"
            + "			\"label\" : \"Losungstoleranz\",\r\n"
            + "			\"type\" : \"long\",\r\n"
            + "			\"min\" : 0,\r\n"
            + "			\"max\" : 1024\r\n"
            + "		},\r\n"
            + "		{\r\n"
            + "			\"name\" : \"settings/sensorIntervall\",\r\n"
            + "			\"label\" : \"Sendeintervall in Sekunden\",\r\n"
            + "			\"type\" : \"long\",\r\n"
            + "			\"min\" : 1,\r\n"
            + "			\"max\" : 3600\r\n"
            + "		},\r\n"
            + "		{\r\n"
            + "			\"name\" : \"wifi/ssid\",\r\n"
            + "			\"label\" : \"WLAN-Name\",\r\n"
            + "			\"type\" : \"constchar\"\r\n"
            + "		},\r\n"
            + "		{\r\n"
            + "			\"name\" : \"wifi/password\",\r\n"
            + "			\"label\" : \"WLAN-Passwort\",\r\n"
            + "			\"type\" : \"constchar\"\r\n"
            + "		}\r\n"
            + "	]\r\n"
            + "}\r\n";

    // Erfolg bei gueltiger Datei
    body.setFile(testFile.getBytes());
    assertEquals(Response.Status.OK.getStatusCode(), devapi.addDevice(body, null).getStatus());
  }

  @Test
  public void deleteDevice() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(daodevice.delete(Mockito.anyString())).thenReturn(true);

    // Wenn "Forced" nicht gesetzt"
    assertEquals(
        Response.Status.EXPECTATION_FAILED.getStatusCode(),
        devapi.deleteDevice("mac", false, null).getStatus());
    // Wenn "Forced" gesetzt"
    assertEquals(
        Response.Status.OK.getStatusCode(), devapi.deleteDevice("mac", true, null).getStatus());
  }

  @Test
  public void upgradeDevice() {
    MockitoAnnotations.initMocks(this);

    // Wenn body = null
    assertEquals(
        Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),
        devapi.upgradeFirmware(null, 0, null, null).getStatus());
    // Wenn firmware leer
    UpdateDeviceBody udb = new UpdateDeviceBody();
    assertEquals(
        Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),
        devapi.upgradeFirmware(udb, 0, null, null).getStatus());
    // Wenn unbekanntes panel
    udb.setFirmware("Firmware".getBytes());
    Mockito.when(daopanel.getById(0)).thenReturn(null);
    assertEquals(
        Response.Status.NOT_FOUND.getStatusCode(),
        devapi.upgradeFirmware(udb, 0, null, null).getStatus());

    // Wenn alles IO
    EcSettings ecs = new EcSettings("123", "url");
    Mockito.when(daoecsettings.get()).thenReturn(ecs);

    PanelDAOBean pdb = new PanelDAOBean();
    pdb.setDevice_mac("1212121212");
    Mockito.when(daopanel.getById(1)).thenReturn(pdb);

    DeviceDAOBean ddb = new DeviceDAOBean();
    ddb.setBasetopic("bt");
    ddb.setDeviceid("dId");
    ddb.setsupportsOTA(true);
    Mockito.when(daodevice.getByMac("1212121212")).thenReturn(ddb);

    Map<String, String> fw = new HashMap<>();
    fw.put("bt/dId/$fw/checksum", "actuamd5hash");
    // Mockito.when(mqtt.getMessages(Mockito.any(), Mockito.any())).thenReturn(fw);

    assertEquals(
        Response.Status.OK.getStatusCode(), devapi.upgradeFirmware(udb, 1, null, null).getStatus());
  }
}
