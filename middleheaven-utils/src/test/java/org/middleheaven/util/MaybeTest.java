/**
 * 
 */
package org.middleheaven.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.middleheaven.util.function.BinaryOperator;

/**
 * 
 */
public class MaybeTest {

	@Test
	public void testMap() {
		Maybe<String> s = Maybe.of("Middleheaven");
		
		Maybe<Integer> r = s.map( it -> it.length());
		
		assertTrue(r.isPresent());
		assertEquals(Maybe.of(12) ,r );
		
		Maybe<Integer> rr = s.flatMap( it -> Maybe.of(it.length()));
		
		assertTrue(rr.isPresent());
		assertEquals(Maybe.of(12) ,rr );
	}
	
	@Test
	public void testApply() {
		
		BinaryOperator<Integer> op = (a, b) -> a +b;
		
		Maybe<Integer> a = Maybe.of(1);
		Maybe<Integer> b = Maybe.of(2);

		Maybe<Integer> r = a.apply(b, op );
		
		assertTrue(r.isPresent());
		assertEquals(Maybe.of(3) ,r );
		
		Maybe<Integer> n = Maybe.absent();
		
		r = a.apply(n, op);
		
		assertTrue(r.isAbsent());
	}

	@Test
	public void testMaybeString (){
		
		Maybe<Integer> two = Maybe.of("1").coerce(Integer.class).map( n -> n *2);
		
		assertEquals(Maybe.of(2) , two );
	}
}
