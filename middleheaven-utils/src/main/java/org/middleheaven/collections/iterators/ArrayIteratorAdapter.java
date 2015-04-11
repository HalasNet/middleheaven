package org.middleheaven.collections.iterators;



/**
 * An iterator based on a raw array.
 * @param <T> the array items type.
 */
 public final class ArrayIteratorAdapter<T> extends IndexBasedIterator<T> {

	 
    public static <X> ArrayIteratorAdapter<X> adapt(X[] array){
    	return new ArrayIteratorAdapter<X>(array);
    }
    
	private T[] array;

	private ArrayIteratorAdapter(T[] array) {
		super();
		this.array = array;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected T getObject(int index) {
		return array[index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int size() {
		return array.length;
	}

}
