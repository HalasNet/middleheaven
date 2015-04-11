/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;

import org.middleheaven.collections.CollectionUtils;

/**
 * 
 */
public class ReverseSequence<T>  extends AbstractSequence<T> implements Sequence<T> {


	private Sequence<T> original;
	
	/**
	 * Constructor.
	 * @param abstractSequence
	 */
	public ReverseSequence(Sequence<T> original) {
		this.original = original;
	}
	

	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return original.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEmpty() {
		return original.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(Object object) {
		return original.contains(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public Sequence<T> reverse() {
		return original;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] toArray() {
		Object[] array = new Object[this.size()];
		
		int index = this.size();
		for (T t : original){
			array[--index] = t;
		}
		
		return array;
	}

	/**
	 * {@inheritDoc}
	 */
	public T[] toArray(T[] array) {
		int length = this.size();
		if (array.length < length){
			array = CollectionUtils.ensureExactLength(array, length);
		} 
		
		int index = this.size();
		for (T t : original){
			array[--index] = t;
		}
		
		if (array.length > length){
			array[length] = null;
		}
		
		return array;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		return original.get(original.size() - index- 1);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("Not implememented yet");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int initialPosition) {
		throw new UnsupportedOperationException("Not implememented yet");
	}



}
