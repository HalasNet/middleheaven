package org.middleheaven.domain.criteria;

import org.middleheaven.util.criteria.ComparableValueConstraint;
import org.middleheaven.util.criteria.TextValueConstraint;
import org.middleheaven.util.criteria.ValueConstraint;

/**
 * Extention for the CriteriaBuilder to support creation of fields 
 * constrains a fluente interface. 
 *
 * @param <E> the base type for the criteria where the field belongs to.
 * @para <V> the type of field's value.
 * @param <B> the builder where this contrains will be applied upon
 */
public interface EntityFieldConstraint<E, V, B extends AbstractEntityCriteriaBuilder<E,B> > {

	
	public ValueConstraint<E,V,B> whereValue();
	
	public ComparableValueConstraint<E,V,B> whereComparableValue();

	public TextValueConstraint<E,V,B> whereText();
	

	/**
	 * The attribute value is an entity equals to {@code candidate}.
	 * @param candidate candidate to be tested
	 * @return
	 */
	public  B is(V candidate);
	
	/**
	 * Transfers criteria building to the <code>referencedEntityType</code>.
	 * 
	 * @param referencedEntityType
	 * @return
	 */
	public JunctionCriteriaBuilder<V, E, ?> navigateTo();
	
	/**
	 * Transfers criteria building to the <code>referencedEntityType</code>.
	 * 
	 * @param referencedEntityType
	 * @return
	 */
	public JunctionCriteriaBuilder<V, E, ?> navigateFrom();
}
