/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.Function;

/**
 * 
 */
public class GroupByEnumerable<K, T> extends AbstractTransformationEnumerable<GroupedElements<K, T>, T>  {

	
	private Function<K, T> classificator;

	public GroupByEnumerable(AbstractBaseEnumerable<T> original, Function<K, T> classificator){
		super(original);
		this.classificator = classificator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<GroupedElements<K, T>> iterator() {
		return new GroupByIterator<K, T>(original().iterator(), classificator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<GroupedElements<K, T>> reverseIterator() {
		return new GroupByIterator<K, T>(original().iterator(), classificator);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		// this enumerable size is the count of keys found in the grouping process
		return new IterableBasedComputableEnumeratorSize(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return getSizeInfo().count();
	}



}
