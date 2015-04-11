package org.middleheaven.util.criteria.text;

import org.middleheaven.text.indexing.IndexableDocument;
import org.middleheaven.util.criteria.ComparableValueConstraint;
import org.middleheaven.util.criteria.ValueConstraint;


public interface TextFieldConstraint<D extends IndexableDocument, V, B extends AbstractTextCriteriaBuilder<D,B>> {

	public ValueConstraint<D,V,B> whereValue();
	
	public ComparableValueConstraint<D,V,B> whereComparableValue();

	public TextualValueConstraint<D,V,B> whereText();
	
}
