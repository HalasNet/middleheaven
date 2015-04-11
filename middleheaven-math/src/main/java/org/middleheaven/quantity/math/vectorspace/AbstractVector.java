package org.middleheaven.quantity.math.vectorspace;

import org.middleheaven.quantity.math.Conjugatable;
import org.middleheaven.quantity.math.structure.FieldElement;

public abstract class AbstractVector<F extends FieldElement<F, ?>> implements Vector<F> , Conjugatable<Vector<F>>{


	private final VectorSpace<F> vectorSpace;

	protected AbstractVector (VectorSpace<F> vectorSpace ){
		this.vectorSpace = vectorSpace;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final F get(int index) {
		if (index < 0 && index >= size()){
			throw new IndexOutOfBoundsException();
		}
		return boundSafeGet(index);
	}
	
	protected abstract F boundSafeGet(int index);
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final F pointwiseTimes(Vector<F> other){
		if (this.size() != other.size()){
			throw new ArithmeticException("Dimentions must be equals");
		}

		F result = this.get(0).times(other.get(0));
		for (int i =1; i <this.size(); i++){
			result = result.plus((this.get(i).times(other.get(i))));
		}
		return result;
	}

	@Override
	public Vector<F> times(final F a) {

		return new CachedVector<F>(this, new ValueResolver<F>(){

			@Override
			public F resolve(int i, Vector<F> original) {
				return original.get(i).times(a);
			}

		});

	}

	@Override
	public final Vector<F> minus(final Vector<F> other) {
		if (this.size() != other.size()){
			throw new ArithmeticException("Dimentions must be equals");
		}

		return new CachedVector<F>(this,  new ValueResolver<F>(){

			@Override
			public F resolve(int i, Vector<F> original) {
				return original.get(i).minus(other.get(i));
			}

		});
	}

	@Override
	public Vector<F> negate() {

		return new CachedVector<F>(this,  new ValueResolver<F>(){

			@Override
			public F resolve(int i, Vector<F> original) {
				return original.get(i).negate();
			}

		});

	}

	@Override
	public Vector<F> plus(final Vector<F> other) {
		if (this.size() != other.size()){
			throw new ArithmeticException("Dimentions must be equals");
		}

		return new CachedVector<F>(this, new ValueResolver<F>(){

			@Override
			public F resolve(int i, Vector<F> original) {
				return original.get(i).plus(other.get(i));
			}

		});

	}


	public final Vector<F> conjugate() {

		return new CachedVector<F>(this, new ValueResolver<F>(){

			@SuppressWarnings("unchecked")
			@Override
			public F resolve(int i, Vector<F> original) {
				F value = original.get(i);
				if (value instanceof Conjugatable){
					return ((Conjugatable<F>) value).conjugate();
				} else {
					return value;
				}
			}

		});

	}

	@SuppressWarnings("unchecked")
	public boolean equals (Object other){
		return other instanceof Vector && equalsOther((Vector<F>)other);
	}

	protected boolean equalsOther(Vector<F> other){
		for (int i=0; i < this.size(); i++){
			if (!this.get(i).equals(other.get(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public int hashCode(){
		return this.size();
	}

	public String toString(){
		StringBuilder builder = new StringBuilder("[");

		for (int i=0; i < this.size(); i++){
			builder.append(this.get(i)).append( ",");
		}

		return builder.append("]").toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		return this.equals(this.getAlgebricStructure().zero());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VectorSpace<F> getAlgebricStructure() {
		return vectorSpace;
	}

}
