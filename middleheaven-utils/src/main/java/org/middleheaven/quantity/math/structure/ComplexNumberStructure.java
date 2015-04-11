/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import org.middleheaven.collections.nw.Assortment;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.quantity.math.Complex;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.util.Maybe;

/**
 * 
 */
public class ComplexNumberStructure implements NumeralFactory<Complex>{


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assortment<Class<?>> getNumeralStructureTypes() {
		return Sequences.of(Complex.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U extends Complex> Maybe<U> newInstance(Class<U> numberType,Object[] values) {
		if (values.length ==  2){
			return Maybe.of(new RealPairComplex(Real.valueOf(values[0].toString()), Real.valueOf(values[1].toString()))).maybeCast(numberType);
		} else {
			return Maybe.absent();
		}
	}

}
