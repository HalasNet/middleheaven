/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Optional;

/**
 * 
 */
public interface EditableCollection<T> extends Assortment<T> {

	
	/**
	 * Remove all copies of value.
	 * @param value
	 * @return true if some copy has been removed
	 */
	public boolean removeAll(T value);
	
	/**
	 * Locates and removes the element at the given index and returns it.
	 * If the index is not valid returns Optional.empty().
	 * @param index
	 * @return
	 */
	public Optional<T> getAndRemove(int index);
	
	/**
	 * Removes all elements from the sequence.
	 */
	public void clear();
	
	/**
	 * Adds a value at the ende of th sequence resigin the sequence to size + 1.
	 * @param value the value to add
	 * @return true if the value has added, false otherwise.
	 */
	public boolean add(T value);
	
	/**
	 * Adds a value at the ende of th sequence resigin the sequence to size + 1.
	 * @param value the value to add
	 * @return true if the value has added, false otherwise.
	 */
	public boolean addAll(Assortment<T> value);
}
