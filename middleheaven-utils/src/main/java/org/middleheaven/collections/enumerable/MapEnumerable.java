/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.Map;

import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;
import org.middleheaven.collections.iterators.StackReverseIterator;

/**
 * 
 */
class MapEnumerable<K, V> extends AbstractSourceEnumerable<Map.Entry<K,V>> implements FiniteEnumerable<Map.Entry<K,V>>{

	private Map<K, V> map;

	/**
	 * Constructor.
	 * @param map
	 */
	public MapEnumerable(Map<K, V> map) {
		this.map = map;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Map.Entry<K, V>> iterator() {
		return map.entrySet().iterator();
	}
	
	public PreciseEnumerableSize getSizeInfo(){
		return IntPreciseEnumerableSize.of(map.size());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<Map.Entry<K, V>> reverseIterator() { // TODO determine diference between linkedmap and hashmap
		return new StackReverseIterator<Map.Entry<K, V>>(this.iterator());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return map.size() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return map.size();
	}


}
