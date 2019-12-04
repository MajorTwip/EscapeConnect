package ch.ffhs.pa5.escapeconnect.persistency;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;

public interface DAOecsettingsIF {

	EcSettings get();
	void write(EcSettings setting);
	void delete();

}