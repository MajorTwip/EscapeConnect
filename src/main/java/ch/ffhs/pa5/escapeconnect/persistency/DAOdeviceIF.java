package ch.ffhs.pa5.escapeconnect.persistency;

import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
/**
 * Interface Logic to DB relation device
 * @author Yvo von Känel
 *
 */
public interface DAOdeviceIF {

	/**
	 * Writes an device to the DB
	 * If mac is already given, update
	 * @param device Device to update
	 * @return success
	 */
	boolean write(DeviceDAOBean device);

	/**
	 * Deletes a device. Cascading all linked panels/actions/vals/settings
	 * @param mac to delete
	 * @return success
	 */
	boolean delete(String mac);
	
	/**
	 * Gets a device
	 * @param device_mac MAC of device
	 * @return Device or null if not found
	 */
	DeviceDAOBean getByMac(String device_mac);
	
}