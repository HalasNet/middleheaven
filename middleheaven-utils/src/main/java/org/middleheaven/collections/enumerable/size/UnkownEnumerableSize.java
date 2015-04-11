/**
 * 
 */
package org.middleheaven.collections.enumerable.size;

import org.middleheaven.collections.enumerable.InfiniteSizeException;
import org.middleheaven.collections.enumerable.UnpredictableEnumerableSize;

/**
 * 
 */
class UnkownEnumerableSize extends AbstractEnumerableSize implements UnpredictableEnumerableSize {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInfinit() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasSameValue(EnumerableSize other) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equals(EnumerableSize other) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		throw new UnsupportedOperationException("Not implememented yet");
	}

}
