/* Code has been formated */
package ch.ffhs.pa5.escapeconnect.utils;

/** 
 * MACformating allows to sanitize the mac address of a device.
 * 
 * @author Yvo von Kaenel
 * 
 */

public class MACformating {
	
  /**  
   * 
   * sanitizeMAC() change the mac address so that it only has lower cases and no ":".
   *  
   * @param mac address as a String	 
   * @return new sanitized mac address as a String
   *
   */  
	
  public static String sanitizeMAC(String mac) {
    String newmac = mac.toLowerCase().replaceAll("[:-]", "");
    return newmac;
  }
}
