/*
 * EscapeManager
 * Dies ist die Dokumentation des REST API des EscapeManager
 *
 * OpenAPI spec version: 0.0.3
 * Contact: yvo.vonkaenel@students.ffhs.ch
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package ch.ffhs.pa5.escapeconnect.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/** Installation von EscapeConnect */
@ApiModel(description = "Installation von EscapeConnect")
@javax.annotation.Generated(
    value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen",
    date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")
public class Setup {

  @JsonProperty("mqtturl")
  private String mqtturl = null;

  @JsonProperty("mqttuser")
  private String mqttuser = null;

  @JsonProperty("mqttpass")
  private String mqttpass = null;

  @JsonProperty("adminpass")
  private String adminpass = null;

  public Setup mqtturl(String mqtturl) {
    this.mqtturl = mqtturl;
    return this;
  }

  /**
   * IP oder FQDN des MQTT-servers
   *
   * @return mqtturl
   */
  @JsonProperty("mqtturl")
  @ApiModelProperty(required = true, value = "IP oder FQDN des MQTT-servers")
  @NotNull
  public String getMqtturl() {
    return mqtturl;
  }

  public void setMqtturl(String mqtturl) {
    this.mqtturl = mqtturl;
  }

  public Setup mqttuser(String mqttuser) {
    this.mqttuser = mqttuser;
    return this;
  }

  /**
   * Benutzername für den MQTT-server. Leer für Anonym
   *
   * @return mqttuser
   */
  @JsonProperty("mqttuser")
  @ApiModelProperty(value = "Benutzername für den MQTT-server. Leer für Anonym")
  public String getMqttuser() {
    return mqttuser;
  }

  public void setMqttuser(String mqttuser) {
    this.mqttuser = mqttuser;
  }

  public Setup mqttpass(String mqttpass) {
    this.mqttpass = mqttpass;
    return this;
  }

  /**
   * Passwort für den MQTT-server. Nur mit mqttuser
   *
   * @return mqttpass
   */
  @JsonProperty("mqttpass")
  @ApiModelProperty(value = "Passwort für den MQTT-server. Nur mit mqttuser")
  public String getMqttpass() {
    return mqttpass;
  }

  public void setMqttpass(String mqttpass) {
    this.mqttpass = mqttpass;
  }

  public Setup adminpass(String adminpass) {
    this.adminpass = adminpass;
    return this;
  }

  /**
   * Passwort ür EscapeConnect, SHA256
   *
   * @return adminpass
   */
  @JsonProperty("adminpass")
  @ApiModelProperty(value = "Passwort ür EscapeConnect, SHA256")
  public String getAdminpass() {
    return adminpass;
  }

  public void setAdminpass(String adminpass) {
    this.adminpass = adminpass;
  }

  @Override
  public int hashCode() {
    return Objects.hash(mqtturl, mqttuser, mqttpass, adminpass);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Setup {\n");

    sb.append("    mqtturl: ").append(toIndentedString(mqtturl)).append("\n");
    sb.append("    mqttuser: ").append(toIndentedString(mqttuser)).append("\n");
    sb.append("    mqttpass: ").append(toIndentedString(mqttpass)).append("\n");
    sb.append("    adminpass: ").append(toIndentedString(adminpass)).append("\n");
    sb.append("}");
    return sb.toString();
  }


  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
