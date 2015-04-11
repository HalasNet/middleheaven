package org.middleheaven.quantity.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.measurables.Time;
import org.middleheaven.quantity.measure.DecimalMeasure;
import org.middleheaven.quantity.measure.Measure;
import org.middleheaven.quantity.unit.SI;

public class TestMoney {

	@Test
	public void testMoney(){
		
		Money oneCent = Money.valueOf("0.01", "USD");
		Real half = Real.rational(1, 2);
		Real third = Real.rational(1, 3);
		
		assertFalse(oneCent.times(half).isZero());
		
		assertFalse(oneCent.times(third).isZero());
		
	}
	
	@Test
	public void testMoneyBag(){
		
		Money oneUSD = Money.valueOf(1, "USD");
		Money oneBRL = Money.valueOf(1, "BRL");
		Money oneEUR = Money.valueOf(1, "EUR");
		
		MoneyBag bag = MoneyBag.of(oneUSD, oneBRL, oneEUR);
		MoneyBag bagTwice = MoneyBag.of(oneUSD.times(2), oneBRL.times(2), oneEUR.times(2));
		
		MoneyBag result = bag.plus(bag);
		
		assertFalse(bagTwice.equals(bag));
		
		assertEquals(bagTwice , result);
		
		result = bag.minus(bag);
		
		assertEquals(MoneyBag.empty() , result);
		
		assertTrue(result.isZero());
		
		assertEquals(bagTwice , bag.times(2));
		
		assertEquals(MoneyBag.of(oneUSD, oneBRL), MoneyBag.of(oneUSD, oneBRL, Money.valueOf(0,"EUR")));
		assertEquals(MoneyBag.of(oneUSD, oneBRL, Money.valueOf(0,"EUR")), MoneyBag.of(oneUSD, oneBRL));
		
		assertFalse(bag.equals(MoneyBag.of(oneUSD, oneBRL)));
		assertFalse(MoneyBag.of(oneUSD, oneBRL).equals(bag));
	}
	

	@Test
	public void testMoneyOperations (){
		
		Money a = Money.valueOf(100, "USD");
		Money b = Money.valueOf(230, "USD");
		Money t = Money.valueOf(330, "USD");
		
		Money c = Money.valueOf(330, "EUR");
		
		Money m = a.plus(b);

		assertEquals(t, m);
		
		// money are equal if both amount and currency are equal
		assertFalse(t.equals(c));

		// divide by a real
		Real n = Real.valueOf(3);
		Money y = t.over(n);
		assertEquals (Money.valueOf(110, "USD"), y);
		
	
		
	}
	
	@Test
	public void testMoneyMeasure (){
		Money a = Money.valueOf(100, "USD");
		DecimalMeasure<Time> L = DecimalMeasure.exact(20, SI.HOUR); 
		Measure<?> q = a.asMeasure().over(L);
		assertEquals ("(5 ± 0) USDh^-1" , q.toString());
		
		DecimalMeasure<Time> h = DecimalMeasure.exact(2, SI.HOUR); 
		DecimalMeasure<org.middleheaven.quantity.measurables.Currency> total = h.times(q);
		
		Money ten = Money.valueOf(10, "USD");
		assertEquals ("(10 ± 0) USD" , total.toString());
		assertEquals (ten.asMeasure() , total);
		assertEquals (total , ten.asMeasure());
	}
	
}


