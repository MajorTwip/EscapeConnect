package ch.ffhs.pa5.escapeconnect.bean;

public class PanelDAOBean {
	private int id = 0;
	private String name;
	private String device_mac;
	public PanelDAOBean() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDevice_mac() {
		return device_mac;
	}
	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}

}
