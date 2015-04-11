/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.iterators.ComposedIterator;

/**
 * 
 */
class CompositeSequence<T> extends AbstractSequence<T> {

	private java.util.List<Sequence<T>> sequences = new ArrayList<Sequence<T>>(2);
	private int size = 0;
	
	public CompositeSequence (Sequence<T> other){
		append(other);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CompositeSequence<T> append(Sequence<T> other){
		if (!(other instanceof EmptySequence)){
			if (other instanceof CompositeSequence){
				CompositeSequence otherComposite = (CompositeSequence)other;
				
				this.sequences.addAll(otherComposite.sequences);
				this.size += otherComposite.size;
			} else {
				if (other instanceof EditableSequence || other instanceof ResizableSequence){
					other = new ArraySequence<>(other); // security copy
				}
				size += other.size();
				sequences.add(other);
			}
		}
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object object) {
		for (T t : this){
			if (object == null ? t == null : object.equals(t)){
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return simplify();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T[] toArray(T[] array) {
		simplify();
		return sequences.get(0).toArray(array);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		for (Sequence<T> seq : sequences){
			if (index < seq.size()){
				return seq.get(index);
			} else {
				index -= seq.size();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> baseIterator() {
		return ComposedIterator.aggregateIterables(sequences);
	}

	/**
	 * 
	 */
	private Object[] simplify() {
		if (sequences.size() > 1){
			
			Object[] array = CollectionUtils.newArray(Object.class, size);
			
			int index = 0;
			for (Sequence<T> seq : sequences){
				for (int j =0 ; j  < seq.size();j++){ 
					array[index++] = seq.get(j);
				}
			}
			
			sequences = new ArrayList<>(2);
			ArraySequence<T> seq = new ArraySequence<>(array);
			sequences.add(seq);
			return array;
		} else if (sequences.isEmpty()){
			return new Object[0];
		} else {
			return sequences.toArray();
		}
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
		throw new UnsupportedOperationException("Not implememented yet");
	}


}
