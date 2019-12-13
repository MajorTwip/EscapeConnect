/**
 * 
 */
package ch.ffhs.pa5.escapeconnect;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.handlers.PanelAPIimplement;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.*;

/**
 * @author ludovicrenevey
 *
 */
class TestPanel {

	@InjectMocks
	PanelAPIimplement panelApi;
	
	@Mock
	DAOpanel daoPanel;
	
	@Mock
	DAOdevice daoDevice;
	
	@Mock
	DAOecsettings daoEcsettings;
	
	@Mock
  	MQTTconnector mqtt;
	
	@Mock
	DAOaction daoAction; 
	
	@Mock
	DAOvalue daoValue; 
	
  /**
   * Test method for {@link ch.ffhs.pa5.escapeconnect.handlers.PanelAPIimplement#getPanes(javax.ws.rs.core.SecurityContext)}.
   */
  @Test
  void testGetPanes(){
	  MockitoAnnotations.initMocks(this);
	  
	  // Prepare test with data which would come from the DB in reality
	  PanelDAOBean myPanelDao = new PanelDAOBean();
	  myPanelDao.setId(1);
	  myPanelDao.setName("UTpanel");
	  myPanelDao.setDevice_mac("FFFFFFFFFFFF");
	  List<PanelDAOBean> dbList = new ArrayList<>(); 
	  dbList.add(myPanelDao);
	  
	  DeviceDAOBean myDeviceDao = new DeviceDAOBean();
	  myDeviceDao.setName("UTdevice");
	  myDeviceDao.setMac("FFFFFFFFFFFF");
	  myDeviceDao.setsupportsOTA(true);
	  myDeviceDao.setBasetopic("homie");
	  myDeviceDao.setDeviceid("FFFFFFFFFFFF");
	  
	  EcSettings ecs = new EcSettings("123","url");
	  	  
	  LinkedList<String> topicToQuery = new LinkedList<>();
	  topicToQuery.add(String.join("/", myDeviceDao.getBasetopic(), myDeviceDao.getDeviceid(), "$state"));
	  Map<String, String> myResults = new HashMap<>();
	  myResults.put("homie/FFFFFFFFFFFF/$state", "ready");
	  
	  ActionDAOBean myActionDao = new ActionDAOBean();
	  myActionDao.setId(1);
	  myActionDao.setPanel_id(1);
	  myActionDao.setLabel("UTaction");
	  myActionDao.setSubtopic("tank/trap/set");
	  myActionDao.setPayload("1");
	  myActionDao.setBasetopic("homie");
	  myActionDao.setDeviceid("FFFFFFFFFFFF");
	  List<ActionDAOBean> myActions = new ArrayList<>();
	  myActions.add(myActionDao);
	  
	  ValueDAOBean myValueDao = new ValueDAOBean();
	  myValueDao.setId(1);
	  myValueDao.setPanel_id(1);
	  myValueDao.setLabel("UTvalue");
	  myValueDao.setSubtopic("tank/trap");
	  myValueDao.setType("string");
	  List<ValueDAOBean> myValues = new ArrayList<>();
	  myValues.add(myValueDao);
	  
	  List<String> topics = new ArrayList<String>();
	  Map<String, String> myValuesMQTT = new HashMap<>();
	  myValuesMQTT.put("homie/FFFFFFFFFFFF/tank/trap:", "1");
	  	  	
	  Mockito.when(daoPanel.getAllPanels()).thenReturn(dbList);
	  Mockito.when(daoDevice.getByMac(Mockito.anyString())).thenReturn(myDeviceDao);
	  Mockito.when(daoEcsettings.get()).thenReturn(ecs);
	  Mockito.when(mqtt.getMessages(topicToQuery, 500)).thenReturn(myResults);
	  Mockito.when(daoAction.getActionByPanelID(1)).thenReturn(myActions);
	  Mockito.when(daoValue.getValuesByPanelID(1)).thenReturn(myValues);
	  Mockito.when(mqtt.getMessages(topics, 1000)).thenReturn(myValuesMQTT);
	  
	  assertEquals(Response.Status.OK.getStatusCode(), panelApi.getPanes(null).getStatus());

  }

}
