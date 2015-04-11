package org.middleheaven.quantity.math.structure;

/**
 * Marks a class as belonging to a ring.
 *
 * @param <T> the type of the element in the group.
 */
public interface RingElement<T extends RingElement<T, A>, A  extends Ring<T,A>> extends GroupAdditiveElement<T,A > , MonoidMultiplicativeElement<T,A> {


	
}
