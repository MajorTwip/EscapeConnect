package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
/**
 * Interface Logic to DB relation value
 * @author Yvo von Känel
 *
 */
public interface DAOvalueIF {

	/**
	 * 	 * Writes an action to the DB
	 * If id is allready given, update
	 * @param value
	 * @return -1 if update, otherwise the newly given id (Autoincrement)
	 */
	int write(ValueDAOBean value);
	/**
	 * Get values linked to a given PanelId
	 * @param id of the Panel
	 * @return List with Values or empty list
	 */
	List<ValueDAOBean> getValuesByPanelID(int id);

}