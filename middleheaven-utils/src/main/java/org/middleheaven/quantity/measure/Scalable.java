package org.middleheaven.quantity.measure;

import org.middleheaven.quantity.math.Numeral;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.structure.GroupAdditiveElement;

/**
 * Represents a {@link GroupAdditiveElement} that can be scaled by a {@link Numeral} factor.
 * @param <E>
 * @param <T>
 */
public interface Scalable<T> {

	public T times(Real factor);

}