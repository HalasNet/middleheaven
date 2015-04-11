/**
 * 
 */
package org.middleheaven.collections.nw;


/**
 * 
 */
public interface ResizableSequence<T> extends EditableSequence<T> , EditableCollection<T>{

	
	/**
	 * Remove the value at the given position, resizing the sequence to size - 1.
	 * If the index if out of bounds the method return false.
	 * @param index the index to remove the value from
	 * @return true if the value was removed, false otherswise.
	 */
	public boolean removeAt(int index);
	
	/**
	 * Insert an element at the index position. If another element exists at that position that element will be moved to position index +1 and so on.
	 * 
	 * @param index the index to remove the value from
	 * @return true if the value was removed, false otherswise.
	 */
	public boolean addAt(int index, T value);

}
