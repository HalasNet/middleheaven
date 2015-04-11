package org.middleheaven.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;


public class StringUtilsTest {

	@Test
	public void testRepeat(){
		assertEquals("mmm", StringUtils.repeat("m", 3));
		assertEquals("acacac", StringUtils.repeat("ac", 3));
		
		assertEquals("ac:ac:ac", StringUtils.repeat("ac", 3, ":"));
	}
	
	
	@Test
	public void testIffenDelimiter(){
		assertEquals("abBcCd", StringUtils.iffenDelimitedToCamelCase("ab-bc-cd"));
	}
	
	@Test
	public void testFirstLetterToUppercase(){
		assertEquals("middleHeaven", StringUtils.firstLetterToLower("MiddleHeaven"));
		assertEquals("MiddleHeaven", StringUtils.firstLetterToUpper("middleHeaven"));
	}
	
	@Test
	public void testSimplePattern(){
		
		String phrase = "MiddleHeaven is the best framework";
		
		assertTrue(StringUtils.simplePatternMatch("*framework", phrase));
		assertTrue(StringUtils.simplePatternMatch("MiddleHeaven*", phrase));
		assertTrue(StringUtils.simplePatternMatch("*best*", phrase));
	}
	
	@Test
	public void testSplitter(){
		
		String phrase = "a=2, b = 4,, f = 67";
		
		Sequence<String> res = Splitter.on(",").split(phrase);
		
		assertTrue("Not the same : "  + res , res.equals(Sequences.of("a=2", " b = 4" ,"", " f = 67")));
		
		res = Splitter.on(",").trim().split(phrase);
		
		assertTrue("Not the same : "  + res , res.equals(Sequences.of("a=2", "b = 4" , "f = 67")));
		
		Map<String, String> map = Splitter.on(",").trim().withKeyValueSeparator("=").split(phrase).into(Sink.collections()).map( entry -> entry.getKey(), entry -> entry.getValue());;
		
		Map<String, String> mapAssert = new HashMap<String, String>();
		
		mapAssert.put("a", "2");
		mapAssert.put("b ", " 4");
		mapAssert.put("f ", " 67");
		
		assertTrue("Not the same : "  + map , CollectionUtils.equalContents(map, mapAssert));
		
		map = Splitter.on(",").trim().withKeyValueSeparator("=").trim().split(phrase).into(Sink.collections()).map( entry -> entry.getKey(), entry -> entry.getValue());;
		
		mapAssert = new HashMap<String, String>();
		
		mapAssert.put("a", "2");
		mapAssert.put("b", "4");
		mapAssert.put("f", "67");
		
		assertTrue("Not the same : "  + map , CollectionUtils.equalContents(map, mapAssert));
		
	}

}
