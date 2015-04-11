/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Comparator;

/**
 * 
 */
public interface EditableSequence<T> extends Sequence<T> {

	
	public void set(int index, T value);
	public void sort(Comparator<T> comparator);

}
