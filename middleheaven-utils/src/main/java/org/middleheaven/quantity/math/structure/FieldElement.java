package org.middleheaven.quantity.math.structure;

/**
 * Represent a mathematical Field.
 * 
 * @param <T> the type of element on the field.
 */
public interface FieldElement<T extends FieldElement<T, A>, A extends Field<T,A>> extends GroupMultiplicativeElement<T, A>, ComutativeRingElement<T, A>{

	/**
	 * Return the multiplicative inverse of {@code this}, so that 
	 * 
	 *  {@code this.times(this.inverse()).equals(this.getAlbegricStructure().one()); }
	 * 
	 * @return the multiplicative inverse of {@code this}.
	 * @throws ArithmeticException - if inversion is not possible. mainly beacause the value is zero.
	 */
	public T inverse() throws ArithmeticException;

}
