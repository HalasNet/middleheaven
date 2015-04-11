package org.middleheaven.util.criteria;

import java.util.Arrays;
import java.util.Collection;

import org.middleheaven.collections.interval.Interval;
import org.middleheaven.util.QualifiedName;

public abstract class AbstractBuildingConstraint<T,V, B extends AbstractCriteriaBuilder<T, B>> {

	B builder;
	boolean negateFlag = false;
    QualifiedName qname;
    V valueObject;

	protected AbstractBuildingConstraint (B builder, QualifiedName qualifiedFileName){
		this.qname = qualifiedFileName;
		this.builder = builder;
	}
	
	public B getBuilder() {
		return builder;
	}

	public QualifiedName getQualifiedName() {
		return qname;
	}

	protected final void toogleNegate() {
		this.negateFlag = !this.negateFlag;
	}
	
	protected CriterionOperator applyOperationNegation(CriterionOperator op){
		if (negateFlag){
			negateFlag=false;
			return op.negate();
		}
		return op;
	}

	public ValueConstraint<T ,V,B> whereValue(){
		return new ValueConstaintBuilder<T,V,B>(this);
	}
	
	public ComparableValueConstraint<T ,V,B> whereComparableValue(){
		return new ValueConstaintBuilder<T,V,B>(this);
	}
	
	public TextValueConstraint<T ,V,B> whereText(){
		return new ValueConstaintBuilder<T,V,B>(this);
	}
	
	protected static class ValueConstaintBuilder<E, V, B extends AbstractCriteriaBuilder<E, B>> implements TextValueConstraint<E,V, B>{

		private AbstractBuildingConstraint<E,V,B> constraint;
		
		public ValueConstaintBuilder(AbstractBuildingConstraint<E,V,B> constraint){
			this.constraint = constraint;
		}
		
		protected B constrainField(CriterionOperator op, Object value){

			constraint.builder.addCriterion(
					new FieldValueCriterion(
							constraint.qname,
							constraint.applyOperationNegation(op),
							new SingleObjectValueHolder(value)
					)
			);
			return constraint.builder;
		}
		
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public B lt(V value) {
			return constrainField(CriterionOperator.LESS_THAN,value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B gt(V value) {
			return constrainField(CriterionOperator.GREATER_THAN,value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B le(V value) {
			return constrainField(CriterionOperator.LESS_THAN_OR_EQUAL,value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B ge(V value) {
			return constrainField(CriterionOperator.GREATER_THAN_OR_EQUAL,value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <C extends Comparable<? super C>> B in(Interval<C> interval) {
			return constrainField(CriterionOperator.IN, interval);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <C extends Comparable<? super C>> B bewteen(C min, C max) {
			return in(Interval.between(min, max));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B eq(V value) {
			return constrainField(CriterionOperator.EQUAL,value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B isNull() {
			return constrainField(CriterionOperator.IS_NULL,null);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B in(Collection<V> values) {
			CriterionOperator op = CriterionOperator.IN;
			if (constraint.negateFlag){
				constraint.negateFlag=false;
				op = op.negate();
			}

			constraint.builder.addCriterion(
					new CollectionFieldInSetCriteria(
							constraint.qname,
							op,
							values
					)
			);
			return constraint.builder;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B in(V... values) {
			return this.in(Arrays.asList(values));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ValueConstraint<E, V, B> not() {
			constraint.toogleNegate();
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B startsWith(CharSequence text) {
			return constrainField(CriterionOperator.STARTS_WITH, text);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B endsWith(CharSequence text) {
			return constrainField(CriterionOperator.ENDS_WITH, text);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public B contains(CharSequence text) {
			return constrainField(CriterionOperator.CONTAINS, text);
		}
		
		public B near(CharSequence text) {
			return constrainField(CriterionOperator.NEAR, text);
		}
	}
}
