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

	@InjectMocks
	ActionAPIimplement actionapi;
	
	@Mock
	DAOaction daoaction;
	
	@Mock
	DAOecsettings daoecsettings;
	
	@Mock
  	MQTTconnector mqtt;
	
	@Test
	public void doAction() {
        MockitoAnnotations.initMocks(this);
        
		List<ActionDAOBean> act0 = new ArrayList<>();
		List<ActionDAOBean> act = new ArrayList<>();
		ActionDAOBean adb = new ActionDAOBean();
		adb.setBasetopic("bt");
		adb.setDeviceid("id");
		adb.setId(2);
		adb.setPayload("pl");
		adb.setSubtopic("st");
		act.add(adb);
		Mockito.when(daoaction.getActionByID(1)).thenReturn(act0);
		Mockito.when(daoaction.getActionByID(2)).thenReturn(act);
		
		EcSettings ecs = new EcSettings("123","url");
		Mockito.when(daoecsettings.get()).thenReturn(ecs);
		
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), actionapi.doAction(1, null).getStatus());
		assertEquals(Response.Status.OK.getStatusCode(), actionapi.doAction(2, null).getStatus());
		

	}
	
}
