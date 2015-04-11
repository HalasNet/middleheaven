package org.middleheaven.quantity.measure;

import org.middleheaven.quantity.Quantity;
import org.middleheaven.quantity.math.structure.GroupAdditiveElement;
import org.middleheaven.quantity.unit.Measurable;

/**
 * An amount represents a addable number associated with a {@link AbstractUncertainMeasure}.
 * 
 * @param <T> the subjacent {@link GroupAdditiveElement}.
 * @param <E> the subjacent {@link AbstractUncertainMeasure}.
 */
public interface Amount<M extends Amount<M,E>, E extends Measurable> 
	extends Quantity<E>, GroupAdditiveElement<M, AmountAlgebricStructure<M,E>> , Scalable<M>{

	Measure<E> asMeasure();
}
