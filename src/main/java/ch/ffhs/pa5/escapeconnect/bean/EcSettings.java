/** Code has been formated */
/** @author Yvo von Kaenel */
package ch.ffhs.pa5.escapeconnect.bean;

public class EcSettings {
  private String password;
  private String mqttUrl;
  private String mqttName;
  private String mqttPass;

  public EcSettings(String password, String mqttUrl, String mqttName, String mqttPass) {
    super();
    this.password = password;
    this.mqttUrl = mqttUrl;
    this.mqttName = mqttName;
    this.mqttPass = mqttPass;
  }

  public EcSettings(String password, String mqttUrl) {
    super();
    this.password = password;
    this.mqttUrl = mqttUrl;
  }

  public EcSettings() {
    super();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMqttUrl() {
    return mqttUrl;
  }

  public void setMqttUrl(String mqttUrl) {
    this.mqttUrl = mqttUrl;
  }

  public String getMqttName() {
    return mqttName;
  }

  public void setMqttName(String mqttName) {
    this.mqttName = mqttName;
  }

  public String getMqttPass() {
    return mqttPass;
  }

  public void setMqttPass(String mqttPass) {
    this.mqttPass = mqttPass;
  }
}
