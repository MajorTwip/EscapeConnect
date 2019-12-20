package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import javax.validation.constraints.NotNull;

import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;
/**
 * Interface Logic to DB relation setting
 * @author Yvo von Känel
 *
 */
public interface DAOsettingIF {
	/**
	 * Writes an action to the DB
	 * If id is allready given, update
	 * @param setting
	 * @return -1 if update, otherwise the newly given id (Autoincrement)
	 */
	int write(SettingDAOBean setting);
	/**
	 * Get list of Settings linked to a given Panel
	 * @param panelId Id of the panel
	 * @return List with Settings or empty List
	 */
	List<SettingDAOBean> getSettingsByPanelId(Integer panelId);
	/**
	 * Gets a Setting by it's Id
	 * @param id Settings id
	 * @return Setting or null
	 */
	SettingDAOBean getSettingById(@NotNull Integer id);
	
}