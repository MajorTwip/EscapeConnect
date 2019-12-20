package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;

/**
 * Interface Logic to DB relation action
 * @author Yvo von Känel
 *
 */
public interface DAOactionIF {

	/**
	 * Writes an action to the DB
	 * If id is allready given, update
	 * @param action Action to write to DB
	 * @return -1 if update, otherwise the newly given id (Autoincrement)
	 */
	int write(ActionDAOBean action);
	/**
	 * Returns List with an Action by its id or empty List
	 * @param actionId Id of the requested Action
	 * @return Action or empty List
	 */
	List<ActionDAOBean> getActionByID(int actionId);
	/**
	 * Returns List with Actions linked to a given panel by id or empty List
	 * @param id Id of the panel
	 * @return Actions or empty List
	 */
	List<ActionDAOBean> getActionByPanelID(int id);
}