/**
 * 
 */
package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.structure.Field;
import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * 
 */
public class DenseVector3DSpace<F extends FieldElement<F, A> , A extends Field<F,A>>  extends DenseVectorSpace<F, A> implements CrossProductVectorSpace<F> {

	
	public static <D extends FieldElement<D, G>, G extends Field<D, G>> VectorSpace<D> getVectorSpaceOver(G field) {
		return new DenseVector3DSpace<D, G>(field);
	}
	
	
	/**
	 * Constructor.
	 * @param dimensions
	 * @param field
	 */
	DenseVector3DSpace(Field<F, A> field) {
		super(3, field);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector<F> crossProduct(Vector<F> a, Vector<F> b) {
		if (a.size()!=3 || b.size()!=3){
			throw new IllegalArgumentException("Vectos does not belong in this space");
		}

		EditableVector<F> vector = vector();

		vector.set(0,a.get(1).times(b.get(2)).minus(a.get(2).times(b.get(1))));
		vector.set(1,a.get(2).times(b.get(0)).minus(a.get(0).times(b.get(2))));
		vector.set(2,a.get(0).times(b.get(1)).minus(a.get(1).times(b.get(0))));

		return vector;
	}

}
