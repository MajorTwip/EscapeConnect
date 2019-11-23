package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;

public interface DAOvalueIF {

	int write(ValueDAOBean value);
	List<ValueDAOBean> getValuesByPanelID(int id);

}