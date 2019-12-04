package ch.ffhs.pa5.escapeconnect;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.handlers.DeviceAPIimplement;
import ch.ffhs.pa5.escapeconnect.persistency.DAOactionIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdeviceIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanelIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettingIF;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalueIF;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class TestDevice {
    
	@InjectMocks
	DeviceAPIimplement devapi;
	
	@Mock
	DAOdeviceIF daodevice;
	
	@Mock
	DAOpanelIF daopanel;
	
	@Mock
	DAOactionIF daoaction;
	
	@Mock
	DAOvalueIF daovalue;
	
	@Mock
	DAOsettingIF daosetting;
    
	@Test
	public void addDevice() {
        MockitoAnnotations.initMocks(this);

		//Mockito.doReturn(true);
		
		Mockito.when(daodevice.write(Mockito.any())).thenReturn(true);
		Mockito.when(daopanel.write(Mockito.any())).thenReturn(1);
		Mockito.when(daoaction.write(Mockito.any())).thenReturn(1);
		Mockito.when(daovalue.write(Mockito.any())).thenReturn(1);
		Mockito.when(daosetting.write(Mockito.any())).thenReturn(1);


								
		AddDeviceBody body = new AddDeviceBody();
		//Fehler bei leerem Payload
		System.out.println(devapi.addDevice(null, null));
		assertEquals(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), devapi.addDevice(null, null).getStatus());
		
		//Fehler bei fehlender Datei
		assertEquals(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), devapi.addDevice(body, null).getStatus());

		//Fehler bei ungültiger Datei
		body.setFile(String.valueOf("sdfsdfsfsdf").getBytes());
		assertEquals(418, devapi.addDevice(body, null).getStatus());
		
		//Fehler bei ungültiger Datei
		body.setFile(String.valueOf("{}").getBytes());
		assertEquals(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), devapi.addDevice(body, null).getStatus());
		
		String testFile = "{\r\n" + 
				"	\"$schema\" : \"https://github.com/MajorTwip/EscapeConnect/schemas/riddledefine_v0.json\",\r\n" + 
				"	\"definition\" : {\r\n" + 
				"		\"name\" : \"Sandriddle\",\r\n" + 
				"		\"version\" : \"1.0\",\r\n" + 
				"		\"author\" : \"Yvo von Kanel\",\r\n" + 
				"		\"contact\" : \"yvo.vonkaenel@students.ffhs.ch\"\r\n" + 
				"	},\r\n" + 
				"	\"device\" : {\r\n" + 
				"		\"default_name\" : \"Sandratsel 1\",\r\n" + 
				"		\"mac\" : \"12:AF:19:CB:43:23\",\r\n" + 
				"		\"allows_ota\" : true\r\n" + 
				"	},\r\n" + 
				"	\"panel\" : {\r\n" + 
				"		\"values\" : [\r\n" + 
				"			{\r\n" + 
				"				\"label\" : \"Sandniveau\",\r\n" + 
				"				\"subtopic\" : \"tank/sandlevel\",\r\n" + 
				"				\"type\" : \"string\"\r\n" + 
				"			},\r\n" + 
				"			{\r\n" + 
				"				\"label\" : \"Schlusselklappe\",\r\n" + 
				"				\"subtopic\" : \"tank/trap\",\r\n" + 
				"				\"type\" : \"string\"\r\n" + 
				"			}\r\n" + 
				"		],\r\n" + 
				"		\"actions\" : [\r\n" + 
				"			{\r\n" + 
				"				\"label\" : \"Schlussel freigeben\",\r\n" + 
				"				\"subtopic\" : \"tank/trap/set\",\r\n" + 
				"				\"payload\": \"1\"\r\n" + 
				"			}\r\n" + 
				"		]\r\n" + 
				"	},\r\n" + 
				"	\"settings\" : \r\n" + 
				"	[\r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"settings/riddleSolution\",\r\n" + 
				"			\"label\" : \"Losungswert\",\r\n" + 
				"			\"type\" : \"long\",\r\n" + 
				"			\"min\" : 0,\r\n" + 
				"			\"max\" : 1024\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"settings/riddleSolutionPrecision\",\r\n" + 
				"			\"label\" : \"Losungstoleranz\",\r\n" + 
				"			\"type\" : \"long\",\r\n" + 
				"			\"min\" : 0,\r\n" + 
				"			\"max\" : 1024\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"settings/sensorIntervall\",\r\n" + 
				"			\"label\" : \"Sendeintervall in Sekunden\",\r\n" + 
				"			\"type\" : \"long\",\r\n" + 
				"			\"min\" : 1,\r\n" + 
				"			\"max\" : 3600\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"wifi/ssid\",\r\n" + 
				"			\"label\" : \"WLAN-Name\",\r\n" + 
				"			\"type\" : \"constchar\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"name\" : \"wifi/password\",\r\n" + 
				"			\"label\" : \"WLAN-Passwort\",\r\n" + 
				"			\"type\" : \"constchar\"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}\r\n";
	
	//Erfolg bei gültiger Datei
	body.setFile(testFile.getBytes());
	assertEquals(Response.Status.OK.getStatusCode(), devapi.addDevice(body, null).getStatus());
	}
	
	@Test
	public void testTest() {
		assertTrue(true);
	}
}
