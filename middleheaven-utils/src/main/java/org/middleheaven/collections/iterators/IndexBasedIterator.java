package org.middleheaven.collections.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class IndexBasedIterator<T> implements Iterator<T>{

	int index=-1;
	public IndexBasedIterator(){}
	
	@Override
	public final boolean hasNext() {
		return index < size() -1;
	}

	@Override
	public final T next() {
		index++;
		if (index >= size()){
			throw new NoSuchElementException();
		}
		
		return getObject(index);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	protected abstract int size();
	protected abstract T getObject(int index);

}
