package ch.ffhs.pa5.escapeconnect.persistency;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
/**
 * Interface Logic to DB relation ecsettings, containing application settings
 * @author Yvo von Känel
 *
 */
public interface DAOecsettingsIF {
	/**
	 * Get Setting
	 * @return ecSettings, containig Adminnpass, MQTT-URL and credentials
	 */
	EcSettings get();
	/**
	 * Update Settings
	 * @param setting
	 */
	void write(EcSettings setting);
}