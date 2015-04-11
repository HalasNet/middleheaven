/**
 * 
 */
package org.middleheaven.collections.nw;


/**
 * 
 */
public abstract class AbstractEditableSequence<T> extends AbstractSequence<T> implements EditableSequence<T> {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(int index, T value) {
		if (index < 0 && index >= size()){
			throw new IndexOutOfBoundsException();
		}
		boundSafeSet(index, value);
	}

	/**
	 * @param index
	 * @param value
	 */
	protected abstract void boundSafeSet(int index, T value);



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}


}
