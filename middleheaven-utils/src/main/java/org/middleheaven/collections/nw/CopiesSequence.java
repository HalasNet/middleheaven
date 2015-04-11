/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;


/**
 * 
 */
public class CopiesSequence<T> extends AbstractSequence<T> {

	
	private T value;
	private int copiesCount;

	public CopiesSequence(T value, int copiesCount){
		this.copiesCount = copiesCount;
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object obj) {
		return (obj == null ? value == null : obj.equals(value)) ? 0 : -1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object obj) {
		return (obj == null ? value == null : obj.equals(value)) ? (copiesCount- 1) : -1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return copiesCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return copiesCount == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		return value;
	}

	@Override
	public Sequence<T> reverse(){
		return this;
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
