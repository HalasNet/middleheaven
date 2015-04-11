/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

/**
 * 
 */
public class ListSequence<T> extends AbstractEditableSequence<T> implements ResizableSequence<T> {

	
	private final java.util.ArrayList<T> list;
	
	
	public ListSequence(){
		list = new java.util.ArrayList<>();
	}
	
     ListSequence(List<T> other){
		list = new java.util.ArrayList<>(other);
	}
	
	public ListSequence(int size){
		list = new java.util.ArrayList<>(size);
	}

	private ListSequence(ListSequence<T> other, T value){
		list = new java.util.ArrayList<>(other.size() + 1);
		list.addAll(other.list);
		list.add(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> concat(T value) {
		return new ListSequence<T>(this, value);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> baseIterator() {
		return list.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return list.size();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		 return list.get(index);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void boundSafeSet(int index, T value) {
	     list.set(index, value);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(T value) {
		return list.add(value);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAt(int index) {
		if (index < 0 && index >= size()){
			return false;
		}
	    list.remove(index);
	    return false;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAll(T value) {
		return list.removeAll(Collections.singleton(value));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> getAndRemove(int index) {
		if (index < 0 && index >= size()){
			return Optional.empty();
		}
	    return Optional.of(list.remove(index));
	}

	public void clear (){
		list.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(Assortment<T> values) {
		 if (values.isEmpty()){
			 return false;
		 }
		 this.list.ensureCapacity(this.list.size() + values.size());
		 for(T val : values){
			 this.list.add(val);
		 }
		 return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sort(Comparator<T> comparator) {
		 list.sort(comparator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object object) {
		return list.contains(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAt(int index, T value) {
		list.add(index, value);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		 return list.toArray();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T[] toArray(T[] array) {
		return list.toArray(array);
	}

	
	public String toString(){
		return list.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object obj) {
		 return list.lastIndexOf(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object obj) {
		 return list.indexOf(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator() {
		return list.listIterator(); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int initialPosition) {
		return list.listIterator(initialPosition); 
	}



}
