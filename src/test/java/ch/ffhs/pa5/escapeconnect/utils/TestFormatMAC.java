package ch.ffhs.pa5.escapeconnect.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestFormatMAC {
	@Test
	public void testMACformat() {
		assertEquals("1234567890ab", MACformating.sanitizeMAC("12:34:56:78:90:AB"));
		assertEquals("1234567890ab", MACformating.sanitizeMAC("12-34-56-78-90-AB"));
		assertEquals("1234567890ab", MACformating.sanitizeMAC("1234567890AB"));
		assertEquals("1234567890ab", MACformating.sanitizeMAC("1234567890ab"));
	}
	
}
