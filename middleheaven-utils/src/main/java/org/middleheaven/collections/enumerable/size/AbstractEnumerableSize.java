/**
 * 
 */
package org.middleheaven.collections.enumerable.size;

import org.middleheaven.collections.enumerable.InfiniteSizeException;
import org.middleheaven.quantity.math.Int;
import org.middleheaven.util.function.BinaryFunction;

/**
 * 
 */
public abstract class AbstractEnumerableSize implements EnumerableSize {

	
	public boolean equals(Object other){
		return other instanceof EnumerableSize && equals((EnumerableSize)other);
	}
	
	protected abstract boolean equals(EnumerableSize other);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize min(EnumerableSize other) {
		if (this.isInfinit()){
			if (other.isInfinit()){
				return this;
			} else {
				return other;
			}
		} else {
			if (other.isInfinit()){
				return this;
			} else if (this instanceof CountableEnumerableSize && other instanceof CountableEnumerableSize){
				return new LateOperationCountableEnumerableSize((CountableEnumerableSize) this, (CountableEnumerableSize)other, (a, b) -> a.min(b));
			} else {
				return new UnkownEnumerableSize();
			}
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize add(EnumerableSize other) {
		if (this.isInfinit()){
			 return this;
		} else {
			if (other.isInfinit()){
				return other;
			} else if ( this instanceof PreciseEnumerableSize && other instanceof PreciseEnumerableSize){
				 return IntPreciseEnumerableSize.of( ((PreciseEnumerableSize)this).count() + ((PreciseEnumerableSize)other).count()); 
			} else if (this instanceof CountableEnumerableSize && other instanceof CountableEnumerableSize){
				return new LateOperationCountableEnumerableSize((CountableEnumerableSize) this, (CountableEnumerableSize)other,(a, b) -> a.plus(b));
			} else {
				return new UnkownEnumerableSize();
			}
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize subtract(EnumerableSize other) {
		if (this.isInfinit()){
			 return other;
		} else {
			if (other.isInfinit()){
				return this;
			} else if (this instanceof CountableEnumerableSize && other instanceof CountableEnumerableSize){
				return new LateOperationCountableEnumerableSize((CountableEnumerableSize) this, (CountableEnumerableSize)other, (a,b) -> a.minus(b));
			} else {
				return new UnkownEnumerableSize();
			}
		}
	}
	
	static class LateOperationCountableEnumerableSize extends AbstractComputableEnumerableSize{

		private CountableEnumerableSize a;
		private CountableEnumerableSize b;
		private BinaryFunction<Int, Int, Int> op;
		
		public LateOperationCountableEnumerableSize (CountableEnumerableSize a , CountableEnumerableSize b, BinaryFunction<Int, Int, Int> op){
			this.a = a;
			this.b = b;
			this.op = op;
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected Int doComputeSize() {
			return op.apply(a.getValue(), b.getValue());
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasSameValue(EnumerableSize other) {
			return other instanceof ComputableEnumerableSize && ((ComputableEnumerableSize)other).getValue().equals(this.getValue()); 
		}

		@Override
		public String toString(){
			return "{" + a + "," + b + "}";
		}


	}
	

		
}
