package org.middleheaven.quantity.math.structure;

/**
 * Marks a class as belonging to a ring
 * and have inverse capabilities.
 * 
 *
 * @param <T> the tppe of the element
 */
public interface GroupMultiplicative<T extends GroupMultiplicativeElement<T, A> , A extends GroupMultiplicative<T, A>> extends MonoidMultiplicative<T, A>  {

	/**
	 * The devision of this by other.
	 * @param other the quociente.
	 * @return this / other
	 */
	public T divide(T a, T b);

}
