package ch.ffhs.pa5.escapeconnect.persistency;

import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;

public interface DAOdeviceIF {

	boolean write(DeviceDAOBean device);

	boolean delete(String mac);
	
	DeviceDAOBean getByMac(String device_mac);
	
}