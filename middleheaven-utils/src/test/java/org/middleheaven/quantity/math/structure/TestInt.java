/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import org.middleheaven.quantity.math.Int;
import org.middleheaven.quantity.math.Rational;

/**
 * 
 */
public class TestInt {

	@Test
	public void testCreationFromDecimal() {
		Int a = Int.valueOf(new Double(1.5));
		
		assertEquals(1 , a.asInteger());
	}
	
	@Test
	public void testInverse() {
		Int a = Int.valueOf(3);
		
		assertEquals(Rational.rational(1,3) , a.inverse());
		
		assertEquals(Rational.rational(1,3) ,Int.valueOf(1).over(a));
	}
	
	@Test
	public void testPromotion() throws ClassNotFoundException {
		Int a = Int.valueOf(1);
		
		assertTrue( Class.forName("org.middleheaven.quantity.math.structure.Int32").isInstance(a));
		assertEquals(1 , a.asInteger());
		
		Int intmax = Int.valueOf(Integer.MAX_VALUE);
		assertTrue( Class.forName("org.middleheaven.quantity.math.structure.Int64").isInstance(intmax));
		assertEquals(Integer.MAX_VALUE , intmax.asInteger());
		
		Int next = a.plus(intmax); 
		
		assertTrue( Class.forName("org.middleheaven.quantity.math.structure.Int64").isInstance(next));
		
		BigInteger correct = BigInteger.valueOf(Integer.MAX_VALUE).add(BigInteger.valueOf(1));
		
		Int overmax = Int.valueOf(correct);
		
		assertEquals(overmax, next);
		
		Int longMax = Int.valueOf(Long.MAX_VALUE);
		
		assertTrue( Class.forName("org.middleheaven.quantity.math.structure.BigInt").isInstance(longMax));
		assertEquals(Long.MAX_VALUE , longMax.asLong());
		
		final BigInteger bigLong = BigInteger.valueOf(Long.MAX_VALUE);
		correct = bigLong.add(BigInteger.valueOf(1));
		
		overmax = Int.valueOf(correct);
		
		Int b = a.plus(longMax); 
		
		assertTrue( Class.forName("org.middleheaven.quantity.math.structure.BigInt").isInstance(b));
		assertEquals(overmax, b);
		
		
		Int overload = longMax.times(longMax);
		
		correct = bigLong.multiply(bigLong);
		
		assertTrue( Class.forName("org.middleheaven.quantity.math.structure.BigInt").isInstance(overload));
		assertEquals(Int.valueOf(correct), overload);
	}

}
