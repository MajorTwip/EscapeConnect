package ch.ffhs.pa5.escapeconnect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.SettingMod;
import ch.ffhs.pa5.escapeconnect.handlers.SettingAPIimplement;
import ch.ffhs.pa5.escapeconnect.mqtt.MQTTconnector;
import ch.ffhs.pa5.escapeconnect.persistency.DAOdevice;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOsettings;

@ExtendWith(MockitoExtension.class)
public class TestSetting {

	@InjectMocks
	SettingAPIimplement settingapi;
	
	@Mock
	DAOsettings daosetting;
	
	@Mock
	DAOpanel daopanel;
	
	@Mock
	DAOdevice daodevice;
	
	@Mock
	DAOecsettings daoecsettings;
	
	@Mock
  	MQTTconnector mqtt;
	
	@Test
	public void getsetting() {
        MockitoAnnotations.initMocks(this);
        
		assertEquals(Response.Status.CONFLICT.getStatusCode(), settingapi.getSettingsByPanelId(null, null).getStatus());
        
        Mockito.when(daopanel.getById(1)).thenReturn(new PanelDAOBean());
        
        DeviceDAOBean ddb = new DeviceDAOBean();
        ddb.setBasetopic("bt");
        ddb.setDeviceid("di");
        Mockito.when(daodevice.getByMac(Mockito.any())).thenReturn(ddb);
        
        List<SettingDAOBean> setlist = new LinkedList<SettingDAOBean>();
        SettingDAOBean setting = new SettingDAOBean();
        setting.setId(1);
        setting.setLabel("Label");
        setting.setValue("Value");
        setting.setName("wifi/ssid");
        setting.setType("constchar");
        setting.setPanel_id(1);
        setlist.add(setting);
        
		Mockito.when(daosetting.getSettingsByPanelId(1)).thenReturn(setlist);
		
		EcSettings ecs = new EcSettings("123","url");
		Mockito.when(daoecsettings.get()).thenReturn(ecs);
				
		assertEquals(Response.Status.OK.getStatusCode(), settingapi.getSettingsByPanelId(1, null).getStatus());

	}
	
	@Test
	public void setsetting() {
        MockitoAnnotations.initMocks(this);
        List<SettingMod> settinglist = new LinkedList<>();
        SettingMod settingmod = new SettingMod();
        settingmod.setId(1);
        settingmod.setValue("12");
        settinglist.add(settingmod);
        
        SettingMod settingmod2 = new SettingMod();
        settingmod2.setId(2);
        settingmod2.setValue("12");
        settinglist.add(settingmod2);
        
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), settingapi.setSetting(settinglist, null).getStatus());
		
		SettingDAOBean setting = new SettingDAOBean();
        setting.setId(1);
        setting.setLabel("Label");
        setting.setValue("Value");
        setting.setName("wifi/ssid");
        setting.setDevice_mac("1234567890ab");
        setting.setType("constchar");
        setting.setPanel_id(1);		
        
        Mockito.when(daosetting.getSettingById(Mockito.anyInt())).thenReturn(setting,setting);
        
        
        DeviceDAOBean ddb = new DeviceDAOBean();
        ddb.setBasetopic("bt");
        ddb.setDeviceid("di");
        Mockito.when(daodevice.getByMac(Mockito.any())).thenReturn(ddb);
        
		EcSettings ecs = new EcSettings("123","url");
		Mockito.when(daoecsettings.get()).thenReturn(ecs);
        //normal, nur ein Setting
		assertEquals(Response.Status.OK.getStatusCode(), settingapi.setSetting(settinglist, null).getStatus());
		
		SettingDAOBean setting2 = new SettingDAOBean();
        setting2.setDevice_mac("1234567890db");
        
        Mockito.when(daosetting.getSettingById(Mockito.anyInt())).thenReturn(setting,setting2);

		assertEquals(Response.Status.CONFLICT.getStatusCode(), settingapi.setSetting(settinglist, null).getStatus());
	}
	
}
