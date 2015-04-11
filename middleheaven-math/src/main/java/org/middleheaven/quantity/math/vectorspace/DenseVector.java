package org.middleheaven.quantity.math.vectorspace;

import java.util.Arrays;

import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.quantity.math.structure.FieldElement;

/**
 * Dense implementation of Vector
 * 
 *
 * @param <F>
 */
public class DenseVector<F extends FieldElement<F, ?>> extends AbstractVector<F>  implements EditableVector<F> {


	private Object[] elements;

	public DenseVector(VectorSpace<F> space, int dimentions){
		super(space);
		this.elements = new Object[dimentions];

	}

	public DenseVector(VectorSpace<F> space, int dimentions, F value){
		this(space, dimentions);

		if ( value!=null){
			Arrays.fill(this.elements, value);
		}
	}
	
	public DenseVector(VectorSpace<F> space,  F[] values){
		this(space, values.length);

		elements = values;
	}

	public DenseVector(VectorSpace<F> space, Sequence<F> values){
		this(space, values.size());

		elements = values.toArray();
	}
	
	public DenseVector(Vector<F> other) {
		this(other.getAlgebricStructure(), other.size());
		
		for (int i=0;  i < other.size(); i++){
			elements[i] =other.get(i);
		}
	}

	public DenseVector<F> set(int index, F value){
		this.elements[index] =  value;
		return this;
	}


	@SuppressWarnings("unchecked")
	@Override
	protected final F boundSafeGet(int index) {
		return (F) this.elements[index];
	}

	@Override
	public final int size() {
		return this.elements.length;
	}

	









}
