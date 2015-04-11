package org.middleheaven.util.criteria.text;

import org.middleheaven.text.indexing.IndexableDocument;
import org.middleheaven.util.QualifiedName;
import org.middleheaven.util.criteria.AbstractBuildingConstraint;
import org.middleheaven.util.criteria.AbstractCriteriaBuilder;

class BuildingTextFieldConstraint<D extends IndexableDocument, V, B extends AbstractTextCriteriaBuilder<D,B>> 
extends AbstractBuildingConstraint<D, V, B>
implements TextFieldConstraint<D,V, B> {


	protected BuildingTextFieldConstraint(B builder,QualifiedName qualifiedFileName) {
		super(builder, qualifiedFileName);
	}

	public TextualValueConstraint<D, V, B> whereText(){
		return new TextualValueConstaintBuilder<D,V,B>(this);
	}
	
	
	protected static class TextualValueConstaintBuilder<D,V, B extends AbstractCriteriaBuilder<D, B>> extends ValueConstaintBuilder<D,V,B> 
		implements TextualValueConstraint<D, V, B>{

		/**
		 * Constructor.
		 * @param constraint
		 */
		public TextualValueConstaintBuilder(AbstractBuildingConstraint<D, V, B> constraint) {
			super(constraint);
		}
		

	} 
	
}
