package org.middleheaven.quantity.math.structure;

import java.util.Comparator;

public interface OrderedField<T extends OrderedFieldElement<T, A> , A extends OrderedField<T, A>> extends Field<T, A>{

	
	public Comparator<T> getComparator();
}
