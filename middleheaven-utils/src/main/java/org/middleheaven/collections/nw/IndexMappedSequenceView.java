/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;


/**
 * 
 */
public class IndexMappedSequenceView<T> extends AbstractSequence<T> implements Sequence.SequenceView<T> {

	private Sequence<T> original;
	private int[] indexMapping;
	private int count;

	/**
	 * Constructor.
	 * @param abstractSequence
	 * @param indexMapping
	 */
	public IndexMappedSequenceView(Sequence<T> original,int[] indexMapping, int count) {
		this.original = original;
		this.indexMapping = indexMapping;
		this.count = count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		 return original.get(indexMapping[index]);
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
