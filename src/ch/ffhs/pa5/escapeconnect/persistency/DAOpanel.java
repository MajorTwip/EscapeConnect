package ch.ffhs.pa5.escapeconnect.persistency;

import java.util.List;

import ch.ffhs.pa5.escapeconnect.bean.Panel;

public interface DAOpanel {
	public Panel getById(int id);
	public List<Panel> getByDevice(int deviceId);
	public void write(Panel panel, boolean overwrite);
	public void delete(int id);
}
