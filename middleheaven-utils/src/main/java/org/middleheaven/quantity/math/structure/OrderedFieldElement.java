package org.middleheaven.quantity.math.structure;

public interface OrderedFieldElement<T extends OrderedFieldElement<T, A>, A extends OrderedField<T, A>> extends FieldElement<T, A> {

	public abstract int compareTo(T other);

}
