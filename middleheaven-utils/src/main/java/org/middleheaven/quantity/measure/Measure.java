/**
 * 
 */
package org.middleheaven.quantity.measure;

import org.middleheaven.quantity.Quantity;
import org.middleheaven.quantity.math.Real;
import org.middleheaven.quantity.math.structure.GroupAdditiveElement;
import org.middleheaven.quantity.unit.Measurable;

/**
 * 
 */
public interface Measure<E extends Measurable> extends Quantity<E> , Scalable<Measure<E>>, GroupAdditiveElement<Measure<E>, MeasureStructure<E>> {

	public <O extends Measurable > Measure<O> cast(Class<O> measurableType);
	
	public boolean isExact();
	
	public <T extends Measurable, U extends Measurable> Measure<T> times(Measure<U> other);
	public <T extends Measurable, U extends Measurable> Measure<T> over(Measure<U> other); 
	public <T extends Measurable> Measure<T>  inverse() ;
}
