package ch.ffhs.pa5.escapeconnect.utils;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public class FirmwareUtil {
	public static String getFirmwareName(byte[] file) {
		//https://homieiot.github.io/homie-esp8266/docs/develop-v3/advanced-usage/magic-bytes/
		byte[] start = DatatypeConverter.parseHexBinary("BF84E41354");
		byte[] end = DatatypeConverter.parseHexBinary("93446BA775");
		
		if(file.length>0) {
			String startstr = new String(start,StandardCharsets.UTF_8);
			String endstr = new String(end,StandardCharsets.UTF_8);
			String firmwarestr = new String(file,StandardCharsets.UTF_8);
			
			int startindex = firmwarestr.indexOf(startstr);
			int endindex = firmwarestr.indexOf(endstr);
			if(startindex<0||endindex<0) {
				return "NA";
			}
			return firmwarestr.substring(startindex+startstr.length(), endindex);
		}
		return "";
	}
	
	public static String getFirmwareVersion(byte[] file) {
		//https://homieiot.github.io/homie-esp8266/docs/develop-v3/advanced-usage/magic-bytes/
		byte[] start = DatatypeConverter.parseHexBinary("6A3F3E0EE1");
		byte[] end = DatatypeConverter.parseHexBinary("B03048D41A");
		
		if(file.length>0) {
			String startstr = new String(start,StandardCharsets.UTF_8);
			String endstr = new String(end,StandardCharsets.UTF_8);
			String firmwarestr = new String(file,StandardCharsets.UTF_8);
			
			int startindex = firmwarestr.indexOf(startstr);
			int endindex = firmwarestr.indexOf(endstr);
			if(startindex<0||endindex<0) {
				return "NA";
			}
			return firmwarestr.substring(startindex+startstr.length(), endindex);
		}
		return "";
	}
	
}
