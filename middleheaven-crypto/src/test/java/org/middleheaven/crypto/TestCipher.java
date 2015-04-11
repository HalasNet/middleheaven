package org.middleheaven.crypto;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class TestCipher {

	
	@Test
	public void testHexCipher(){
		
		HexCipherAlgorithm cipher = new HexCipherAlgorithm();
		
		String s = new String(cipher.cipher(new byte[]{2, 15 }));
		
		assertEquals("20f", s);
	}
	
	@Test
	public void testBase64Cipher(){
		
		Base64CipherAlgorithm cipher = new Base64CipherAlgorithm();
		
		String s = cipher.encode("MiddleHeaven".getBytes());
		
		assertEquals("TWlkZGxlSGVhdmVu", s);
		
		assertTrue(Arrays.equals("MiddleHeaven".getBytes(), cipher.decode(s)));
	}
}
