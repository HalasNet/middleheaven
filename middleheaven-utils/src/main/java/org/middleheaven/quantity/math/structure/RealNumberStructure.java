/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import org.middleheaven.collections.nw.Assortment;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.quantity.math.Decimal;
import org.middleheaven.quantity.math.Rational;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.util.Maybe;

/**
 * 
 */
public class RealNumberStructure implements NumeralFactory<Real>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assortment<Class<?>> getNumeralStructureTypes() {
		return Sequences.of(Real.class, Rational.class, Decimal.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U extends Real> Maybe<U> newInstance(Class<U> numberType,Object[] values) {
		if (values.length == 1){
			if (numberType.isInstance(values[0])){
				return Maybe.of(numberType.cast(values[0]));
			}
			
			if (numberType.equals(Decimal.class)){
				return Maybe.of(DecimalReal.valueOf(values[0].toString())).maybeCast(numberType);
				
			} else {  // Rational or Real
				return Maybe.of(Fraction.valueFrom(values[0].toString())).maybeCast(numberType);
			} 
		
		} else if (values.length == 2){
			return Maybe.of(new Fraction(values[0].toString()).over(Fraction.valueOf(values[1].toString()))).maybeCast(numberType);
		} else {
			return Maybe.absent();
		}
	}

}
