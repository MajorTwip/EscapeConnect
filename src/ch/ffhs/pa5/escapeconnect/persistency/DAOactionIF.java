package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;

public interface DAOactionIF {

	int write(ActionDAOBean action);
	
	List<ActionDAOBean> getActionByPanelID(int id);
}