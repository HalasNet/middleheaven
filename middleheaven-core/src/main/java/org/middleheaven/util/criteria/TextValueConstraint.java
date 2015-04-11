/**
 * 
 */
package org.middleheaven.util.criteria;


/**
 * 
 */
public interface TextValueConstraint<E, V, B> extends ComparableValueConstraint<E, V, B> {

	/**
	 * The attribute value start with {@code value}. This is only 
	 * applied if the atribute's value is a textual value 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B startsWith(CharSequence text);
	/**
	 * The attribute value ends with {@code value}. This is only 
	 * applied if the atribute's value is a textual value 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B endsWith(CharSequence text);
	/**
	 * The attribute value contains {@code value}. This is only 
	 * applied if the atribute's value is a textual value 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B contains(CharSequence text);
	

}
