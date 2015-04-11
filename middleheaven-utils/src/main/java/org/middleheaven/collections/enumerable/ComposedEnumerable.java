/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.iterators.ComposedIterator;

/**
 * 
 */
class ComposedEnumerable<T> extends AbstractBaseEnumerable<T>  {

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <X> Enumerable<X> compose(Enumerable<? extends X> left , Enumerable<? extends X> right){
		if (left instanceof ComposedEnumerable){
			return ((ComposedEnumerable)left).concat(right);
		}
		else if (right instanceof ComposedEnumerable){
			ComposedEnumerable<X> composed = new ComposedEnumerable();
			composed.add(left);
			composed.addAll(((ComposedEnumerable)right).list);
			return composed;
		}
		return new ComposedEnumerable<X>((Enumerable<X>)left, (Enumerable<X>)right);
	}
	
	private List<Enumerable<T>> list = new LinkedList<Enumerable<T>>();
	
	private ComposedEnumerable(){
	}
	
	private ComposedEnumerable(Enumerable<T> left , Enumerable<T> right){
		list.add(left);
		list.add(right);
	}

	@SuppressWarnings("unchecked")
	private void add(Enumerable<? extends T> other){
		Enumerable<T> o = (Enumerable<T>)other;
		this.list.add(o);
	}
	
	private void addAll(Collection<Enumerable<T>> all){
		this.list.addAll(all);
	}
	
	public Enumerable<T> concat(Enumerable<? extends T> other){
		 add(other);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		 return ComposedIterator.aggregateIterables(list);
	}
	
	public EnumerableSize getSizeInfo(){
		EnumerableSize size = IntPreciseEnumerableSize.of(0);
		for (Enumerable<T> e  : list){
			size = size.add(e.getSizeInfo());
		}
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return getSizeInfo().count();
	}
	
	public boolean isEmpty(){
		
		for ( Enumerable<T> e : list){
			if (!e.isEmpty()){
				return false;
			}
		}
		return true;
	}
	
}
