/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.interval.Interval;
import org.middleheaven.collections.progression.Progression;

/**
 * 
 */
public class SingleSequence<T> extends  AbstractEditableSequence<T> implements Sequence.SequenceView<T> {

	private T value;

	public SingleSequence(T value){
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
		return (obj == null ? value == null : obj.equals(value)) ? 0 : -1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> baseIterator() {
		return CollectionUtils.singleIterator(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return baseIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void boundSafeSet(int index, T value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> concat(T value) {
		 Object[] newArray =  new Object[2];
		 newArray[0] = this.value;
		 newArray[1] = value;
		 return new ArraySequence<T>(newArray);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sort(Comparator<T> comparator) {
		//no-op, only a single value
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object object) {
		 return this.value.equals(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return new Object[]{value};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T[] toArray(T[] array) {
		if (array.length == 0){
			array = CollectionUtils.ensureExactLength(array, 1);
			array[0] = value;
		} else if (array.length == 1){
			array[0] = value;
		} else {
			array[0] = value;
			array[1] = null;
		}
		
		return array;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> reverse() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> sample(Progression<Integer, Integer> progression) {
		 if (progression.getFirst() <= 0 && progression.getLast() >=0 ){
			 return this;
		 }
		 return EmptySequence.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> slice(Interval<Integer> interval) {
		 if (interval.start() <= 0 && interval.end() >=0 ){
			 return this;
		 }
		 return EmptySequence.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator() {
		return listIterator(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int initialPosition) {
		return new ArrayListIterator<T>(initialPosition, new Object[]{value});
	}

}
