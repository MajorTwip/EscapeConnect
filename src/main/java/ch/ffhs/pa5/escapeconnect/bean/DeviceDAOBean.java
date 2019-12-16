/** Code has been formated */
/** @author Yvo von Kaenel */
package ch.ffhs.pa5.escapeconnect.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DeviceDAOBean {
  private String name;
  private String mac;
  private boolean supportsOTA;
  @JsonIgnore private String basetopic;
  @JsonIgnore private String deviceid;
  @JsonIgnore private int firmwareid;

  public DeviceDAOBean() {}

  DeviceDAOBean(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @JsonSetter("default_name")
  public void setName(String name) {
    this.name = name;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac.toLowerCase().replaceAll("[:-]", "");
  }

  public boolean issupportsOTA() {
    return supportsOTA;
  }

  @JsonSetter("allows_ota")
  public void setsupportsOTA(boolean allowOTA) {
    this.supportsOTA = allowOTA;
  }

  public String getBasetopic() {
    return basetopic;
  }

  public void setBasetopic(String basetopic) {
    this.basetopic = basetopic;
  }

  public String getDeviceid() {
    return deviceid;
  }

  public void setDeviceid(String deviceid) {
    this.deviceid = deviceid;
  }

  public int getFirmwareid() {
    return firmwareid;
  }

  public void setFirmwareid(int firmwareid) {
    this.firmwareid = firmwareid;
  }
}
