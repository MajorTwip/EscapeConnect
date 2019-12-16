/** Code has been formated */
/** @author Yvo von Kaenel */
package ch.ffhs.pa5.escapeconnect.bean;

public class SettingDAOBean {

  private int id = 0;
  private String device_mac;
  private int panel_id;
  private String label;
  private String value;
  private String name;
  private String type;
  private int min, max;

  public SettingDAOBean() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDevice_mac() {
    return device_mac;
  }

  public void setDevice_mac(String device_mac) {
    this.device_mac = device_mac;
  }

  public int getPanel_id() {
    return panel_id;
  }

  public void setPanel_id(int panel_id) {
    this.panel_id = panel_id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
  }
}
