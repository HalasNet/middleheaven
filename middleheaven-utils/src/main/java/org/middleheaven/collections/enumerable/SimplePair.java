/**
 * 
 */
package org.middleheaven.collections.enumerable;

import org.middleheaven.collections.Pair;

class SimplePair<A, B> implements Pair<A,B>{


	private A a;
	private B b;

	public SimplePair(A a , B b){
		this.a = a;
		this.b = b;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public A left() {
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public B right() {
		return b;
	}

}