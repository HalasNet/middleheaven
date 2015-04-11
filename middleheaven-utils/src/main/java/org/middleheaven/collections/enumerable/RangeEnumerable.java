/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public class RangeEnumerable extends AbstractIndexableEnumerable<Integer> {

	
	private int start;
	private int end;

	public RangeEnumerable(int start, int end ){
		this.start = start;
		this.end = end;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return end - start + 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return start == end;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer safeBoundGet(int index) {
		return Integer.valueOf(start + index);
	}

}
