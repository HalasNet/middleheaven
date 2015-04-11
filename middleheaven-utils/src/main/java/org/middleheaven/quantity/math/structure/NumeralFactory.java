/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import org.middleheaven.collections.nw.Assortment;
import org.middleheaven.quantity.math.Numeral;
import org.middleheaven.util.Maybe;

/**
 * Acts as a factory of numerals
 */
public interface NumeralFactory<N extends Numeral<N , ?>> {

	/**
	 * @return
	 */
	Assortment<Class<?>> getNumeralStructureTypes();

	/**
	 * @param values
	 * @return
	 */
	<U extends N> Maybe<U> newInstance(Class<U> numberType, Object[] values);

}
