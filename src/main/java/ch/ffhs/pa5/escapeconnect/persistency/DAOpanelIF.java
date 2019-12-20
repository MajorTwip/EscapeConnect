package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
/**
 * Interface Logic to DB relation panel
 * @author Yvo von Känel
 *
 */
public interface DAOpanelIF {
	/**
	 * Writes an action to the DB
	 * If id is allready given, update
	 * @param panel
	 * @return -1 if update, otherwise the newly given id (Autoincrement)
	 */
	int write(PanelDAOBean panel);
	/**
	 * Get a list of all registered panels
	 * @return List with all panels or empty
	 */
	List<PanelDAOBean> getAllPanels();
	/**
	 * Get a panel by its Id
	 * @param id of the Panel
	 * @return Panel or null
	 */
	PanelDAOBean getById(int id);

}