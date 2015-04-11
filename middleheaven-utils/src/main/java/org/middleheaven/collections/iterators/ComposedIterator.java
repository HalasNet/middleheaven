package org.middleheaven.collections.iterators;

import java.util.Iterator;
import java.util.function.Function;

import org.middleheaven.collections.nw.ListSequence;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;

/**
 * Creates an Iterator<T> over a collection of Iterables<T>.
 * @param <T>
 */
public final class ComposedIterator<T> implements Iterator<T>{

	public static <T> ComposedIterator<T> aggregateIterables(Iterator<T> a , Iterator<T> b){
		return new ComposedIterator<T>(a,b);
	}
	
	@SafeVarargs
	public static <T> ComposedIterator<T> aggregateIterables(Iterator<T> ... iterators){

		return new ComposedIterator<T>(Sequences.of(iterators));
	}
	
	
	public static <T> ComposedIterator<T> aggregateIterables(Iterable<? extends Iterable<T>> collections){
		
		ListSequence<Iterator<T>> iterators = new ListSequence<Iterator<T>>();

		for (Iterable<T> c : collections){
			iterators.add(c.iterator());
		}

		return new ComposedIterator<T>(iterators);
	}
	
	Sequence<Iterator<T>> iterators;
	int index=0;

	private ComposedIterator(Sequence<Iterator<T>> iterables) {
		super();
		this.iterators = iterables;
	}
	
	private  ComposedIterator(Iterator<T> a , Iterator<T> b) {
		super();
		this.iterators = Sequences.of(a,b);
	}
	
	@Override
	public boolean hasNext() {
		if (iterators.isEmpty()){
			return false;
		}
		
		while (index < iterators.size()){
			
			if(iterators.get(index).hasNext()){
				return true;
			}
			index++;
		};
		
		index=0;
		return false;
	}

	@Override
	public T next() {
		return iterators.get(index).next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}


}
