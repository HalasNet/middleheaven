package org.middleheaven.collections.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ReverseIndexBasedIterator<T> implements Iterator<T>{

	int index;
	public ReverseIndexBasedIterator(){
		this.index = size();
	}
	
	@Override
	public final boolean hasNext() {
		return index >0 ;
	}

	@Override
	public final T next() {
		index--;
		if (index < 0){
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
