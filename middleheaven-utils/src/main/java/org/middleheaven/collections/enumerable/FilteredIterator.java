/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public class FilteredIterator<T> implements Iterator<T> {


	private Predicate<T> predicate;
	private Iterator<T> original;

	private Element<T> next;

	/**
	 * Constructor.
	 * @param iterator
	 * @param predicate
	 */
	public FilteredIterator(Iterator<T> iterator, Predicate<T> predicate) {
		this.original = iterator;
		this.predicate = predicate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		Element<T> item = fetchNext();
		if (item.isFound()){
			this.next = item;
			return true;
		} 
		return false;
	}

	private Element<T> fetchNext(){
		while(original.hasNext()){
			T n = original.next();
			if (predicate.test(n)){
				return new Element<T>(n);
			}
		}
		return Element.noSuchElement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T next() {
		if (next == null){
			next = fetchNext();
		}
		if (next.isAfterLast()){
			throw new NoSuchElementException();
		}
		T obj = next.object();
		next = null;
		return obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove() {
		original.remove();
	}

}
