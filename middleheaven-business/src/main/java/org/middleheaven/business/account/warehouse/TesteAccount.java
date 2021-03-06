package org.middleheaven.business.account.warehouse;

import java.util.List;

import org.junit.Test;
import org.middleheaven.quantity.measurables.Mass;
import org.middleheaven.quantity.measure.DecimalMeasure;
import org.middleheaven.quantity.measure.Measure;
import org.middleheaven.quantity.time.CalendarDate;
import org.middleheaven.quantity.unit.SI;


public class TesteAccount {
	
	@Test
	public void testInterface(){
		
		Product p = null;
		ProductStockRepository<Mass> rep = new ProductStockRepository<Mass>(p);

		CalendarDate today = CalendarDate.today();
		
		List<ProductStock<Mass>> stocks = rep.getOwnerAccounts(p);
		DecimalMeasure<Mass> total = DecimalMeasure.zero(SI.KILOGRAM);
		
		for (ProductStock<Mass> s : stocks){
			Measure<Mass> d = s.getBalance(today).cast(Mass.class);
			total = total.plus(d);
		}
//		
//		List<ProductMovement<Mass>> movements = stocks.get(0).getMovements(today.upTo(today.next().next()));
//		for (ProductMovement<Mass> m : movements){
//			m.getAcount().getOwner().equals(p);
//		}
	}

}
