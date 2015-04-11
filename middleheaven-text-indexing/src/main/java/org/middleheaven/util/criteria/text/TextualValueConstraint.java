/**
 * 
 */
package org.middleheaven.util.criteria.text;

import org.middleheaven.util.criteria.ComparableValueConstraint;
import org.middleheaven.util.criteria.TextValueConstraint;

/**
 * 
 */
public interface TextualValueConstraint <E, V, B> extends ComparableValueConstraint<E, V, B> , TextValueConstraint<E,V,B> {

	/**
	 * The field value contains {@code value}. This is only 
	 * applied if the atribute's value is a textual value 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B near(CharSequence text);
}
