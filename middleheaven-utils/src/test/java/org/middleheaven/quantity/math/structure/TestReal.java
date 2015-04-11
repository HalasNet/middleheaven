/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Ignore;
import org.junit.Test;
import org.middleheaven.quantity.math.Rational;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.structure.DecimalReal;

/**
 * 
 */
public class TestReal {

	@Test
	public void testFraction() {
		
		Rational third = Real.rational(1, 3);
		
		assertEquals(Real.valueOf("1") , third.times(3));
	}
	
	
	@Test
	public void testRaise() {
		assertEquals(Real.valueOf(1),Real.valueOf(2).raise(0));
		assertEquals(Real.valueOf(8),Real.valueOf(2).raise(3));
		assertEquals(Real.valueOf("0.1"),Real.valueOf(10).raise(-1));
	}
	
	@Test
	public void testDecimal (){
		
		DecimalReal d1 = DecimalReal.valueOf("20.05");
		DecimalReal d2 = DecimalReal.valueOf("2.1");
		
		DecimalReal sum = (DecimalReal)d1.plus(d2);
		
		assertEquals(DecimalReal.valueOf("22.15"), sum );
		
		DecimalReal prod = (DecimalReal)d1.times(d2);
		
		assertEquals(DecimalReal.valueOf("42.105"), prod );
		
		Real quoc = d1.over(d2);
		
		assertEquals( Real.valueOf(2005,210), quoc );
		
	}
	
	@Test @Ignore
	public void testMovingSpeed(){
		final Real one = Real.valueOf(1.1);
		this.doSpeed(one, r -> r.nextBy(one));
		this.doSpeed(one, r -> r.plus(one));
		this.doSpeed(DecimalReal.valueOf("1.1"), r -> r.nextBy(one));
		this.doSpeed(DecimalReal.valueOf("1.1"), r -> r.plus(one));
	}
	
	
	private void doSpeed (Real initialValue, Function<Real, Real> func){
		Real r = initialValue;
		
		int[] test = new int []{ 1000, 10000, 100000, 1000000}; 
		double[] results = new double [test.length];
		
		
		for ( int t =0; t < test.length; t++){
			double sumElapsed = 0;
			for (int y = 0; y < 100; y ++){
				long tr = test[t];
				long a = System.currentTimeMillis();
				for(int i =0 ; i < tr; i++){
					r = func.apply(r);
				}
				sumElapsed += (System.currentTimeMillis() - a);
			}
			results[t] = sumElapsed / 100;
		}
		

		for (int i = 0; i < test.length; i++){
			System.out.print(test[i] + "\t");
		}
		System.out.println();
		for (int i = 0; i < test.length; i++){
			System.out.print(results[i] + "\t");
		}
		System.out.println();
	}
}
