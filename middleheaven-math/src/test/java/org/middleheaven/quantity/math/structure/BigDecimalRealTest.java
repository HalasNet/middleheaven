/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;
import org.middleheaven.quantity.math.Real;

/**
 * 
 */
public class BigDecimalRealTest {

	@Test
	public void test() {
		
		BigDecimal d = new BigDecimal("0.003");
		BigDecimal u = new BigDecimal("3000");
		u = u.setScale(-3);
		
		Assert.assertEquals(Real.rational(3, 1000), Real.valueOf(d));
		Assert.assertEquals(Real.rational(3000, 1), Real.valueOf(u));
	}

}
