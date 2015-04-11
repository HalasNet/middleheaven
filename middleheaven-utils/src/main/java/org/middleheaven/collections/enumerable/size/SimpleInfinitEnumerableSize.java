/**
 * 
 */
package org.middleheaven.collections.enumerable.size;

import org.middleheaven.collections.enumerable.InfiniteSizeException;

/**
 * 
 */
public class SimpleInfinitEnumerableSize implements InfinitEnumerableSize {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInfinit() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize min(EnumerableSize other) {
		return other;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize add(EnumerableSize size) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize subtract(EnumerableSize other) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasSameValue(EnumerableSize other) {
		return other instanceof InfinitEnumerableSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		throw new InfiniteSizeException();
	}

}
