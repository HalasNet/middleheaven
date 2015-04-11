/**
 * 
 */
package org.middleheaven.domain.criteria;

import org.middleheaven.reflection.inspection.Introspector;
import org.middleheaven.util.QualifiedName;
import org.middleheaven.util.criteria.AbstractBuildingConstraint;

class BuildingEntityFieldConstraint<T,V, B extends AbstractEntityCriteriaBuilder<T, B>> 
extends AbstractBuildingConstraint<T,V,B> 
implements EntityFieldConstraint<T,V, B> {

	private Class referencedEntityType;
	protected BuildingEntityFieldConstraint(B builder,Class referencedEntityType, QualifiedName qualifiedFileName) {
		super(builder, qualifiedFileName);
		this.referencedEntityType = referencedEntityType;
	}

	@Override
	public  B is(V candidate) {
		if (candidate == null){
			return this.whereValue().isNull();
		} else {
			return doNavigateTo(Introspector.of(candidate).getRealType())
			.is(candidate)
			.back();
		}
	}

	@Override
	public JunctionCriteriaBuilder<V, T, ?> navigateFrom() {
		
		FieldJuntionCriterion criterion = new FieldJuntionCriterion(this.getQualifiedName(),referencedEntityType,getBuilder().getCurrentType(), false);
		getBuilder().addCriterion(criterion);
		return new JunctionCriteriaBuilder<V,T,B>(criterion,referencedEntityType,getBuilder());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JunctionCriteriaBuilder<V, T, ?> navigateTo() {
		return doNavigateTo(referencedEntityType);
	}
	
	private JunctionCriteriaBuilder<V, T, B> doNavigateTo(Class type) {
		FieldJuntionCriterion criterion = new FieldJuntionCriterion(this.getQualifiedName(),referencedEntityType,getBuilder().getCurrentType(), true);
		getBuilder().addCriterion(criterion);
		return new JunctionCriteriaBuilder<V,T,B>(criterion,type,getBuilder());
	}



}