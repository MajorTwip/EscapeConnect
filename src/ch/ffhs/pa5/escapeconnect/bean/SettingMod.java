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




/**
 * Änderungsauftrag einer Einstellung
 */
@ApiModel(description = "Änderungsauftrag einer Einstellung")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")
public class SettingMod   {
  
    
      
  
  @JsonProperty("id")
  
  
  
  
  private Integer id = null;
  

  
    
      
  
  @JsonProperty("value")
  
  
  
  
  private String value = null;
  

  
  
  
  public SettingMod id(Integer id) {
    this.id = id;
    return this;
  }
  
  

  
  /**
  
   * EinstellungsID
  
  
  
  
   * @return id
   **/
 
  
  @JsonProperty("id")
  
  @ApiModelProperty(required = true, value = "EinstellungsID")

  @NotNull

  public Integer getId() {
    return id;
  }
  

  public void setId(Integer id) {
    this.id = id;
  }
  

  
  
  public SettingMod value(String value) {
    this.value = value;
    return this;
  }
  
  

  
  /**
  
   * Neuer Wert als String
  
  
  
  
   * @return value
   **/
 
  
  @JsonProperty("value")
  
  @ApiModelProperty(required = true, value = "Neuer Wert als String")

  @NotNull

  public String getValue() {
    return value;
  }
  

  public void setValue(String value) {
    this.value = value;
  }
  

  


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SettingMod settingMod = (SettingMod) o;
    return Objects.equals(this.id, settingMod.id) &&
        Objects.equals(this.value, settingMod.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value);
  }




  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SettingMod {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


