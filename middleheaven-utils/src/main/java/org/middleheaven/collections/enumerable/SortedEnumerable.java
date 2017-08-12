/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;
import org.middleheaven.quantity.math.Int;
import org.middleheaven.util.Maybe;

/**
 * 
 */
class SortedEnumerable<T> extends AbstractOperationalEnumerable<T> {

	
	private Comparator<T> comparator;

	public SortedEnumerable(AbstractBaseEnumerable<T> original, Comparator<T> comparator){
		super(original);
		this.comparator = comparator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		return original().getSizeInfo();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return original().count();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO execute at .next()
		Maybe<Int> size = Maybe.of(original().getSizeInfo()).maybeCast(PreciseEnumerableSize.class).map( s-> s.getValue()).alsoAbsent( v-> !v.isInteger());
		
		ArrayList<T> all;
		
		if (size.isPresent()){
			all = new ArrayList<T>(size.get().asInteger());
		} else {
			all = new ArrayList<T>();
		}
		
		for(T t : this.original()){
			all.add(t);
		}

		Collections.sort(all, comparator);
		
		return all.iterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		// TODO execute at .next()
		Maybe<Int> size = Maybe.of(original().getSizeInfo()).maybeCast(PreciseEnumerableSize.class).map( s-> s.getValue()).alsoAbsent( v-> !v.isInteger());
		
		ArrayList<T> all;
		
		if (size.isPresent()){
			all = new ArrayList<T>(size.get().asInteger());
		} else {
			all = new ArrayList<T>();
		}
		
		for(T t : this){
			all.add(t);
		}

		Collections.sort(all, comparator.reversed());
		
		return all.iterator();
	}


}
