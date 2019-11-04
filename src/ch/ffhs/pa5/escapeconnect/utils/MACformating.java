package ch.ffhs.pa5.escapeconnect.utils;

public class MACformating {
	public static String sanitizeMAC(String mac) {
		String newmac = mac.toLowerCase().replaceAll("[:-]", "");
		return newmac;
	}
}
