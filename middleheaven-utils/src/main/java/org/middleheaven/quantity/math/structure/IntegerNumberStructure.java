/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.middleheaven.collections.nw.Assortment;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.quantity.math.Int;
import org.middleheaven.util.Maybe;

/**
 * 
 */
public class IntegerNumberStructure implements NumeralFactory<Int> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assortment<Class<?>> getNumeralStructureTypes() {
		return Sequences.of(Int.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U extends Int> Maybe<U> newInstance(Class<U> numberType,Object[] values) {
		if (values.length == 1){
			if (values[0] instanceof BigInteger)
			{
				return Maybe.of(new BigInt((BigInteger)values[0]).reduce()).maybeCast(numberType);
			} 
			else if (values[0] instanceof BigDecimal)
			{
				return Maybe.of(new BigInt(((BigDecimal)values[0]).toBigInteger()).reduce()).maybeCast(numberType);
			} 
			else
			{
				String str = values[0].toString();
				int pos = str.indexOf('.');
				if (pos >=0 ){
					str = str.substring(0, pos);
				}
				return Maybe.of(new BigInt(new BigInteger(str)).reduce()).maybeCast(numberType);
			} 
		} else {
			return Maybe.absent();
		}
	}

}
