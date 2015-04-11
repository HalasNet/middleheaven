/**
 * 
 */
package org.middleheaven.collections.enumerable;

import org.middleheaven.quantity.math.Real;

/**
 * 
 */
public class IntegersSink implements Sink<Integer> {

	private Enumerable<Integer> enumerable;

	/**
	 * Constructor.
	 * @param other
	 */
	public IntegersSink(Enumerable<Integer> other) {
		this.enumerable = other;
	}
	
	public Integer sum(){
		return enumerable.reduce(Integer.valueOf(0), (a, b) -> a + b);
	}
	
	public Integer min(){
		return enumerable.reduce(null, (Integer a, Integer b) ->{
			
			if (a == null){
				return b;
			} else if (b == null){
				return a;
			} else if( a.compareTo(b) <= 0) {
				return a;
			} else  {
				return b;
			}
			
		});
	}
	
	public Integer max(){
		return enumerable.reduce(null, (Integer a, Integer b) ->{
			
			if (a == null){
				return b;
			} else if (b == null){
				return a;
			} else if( a.compareTo(b) >= 0) {
				return a;
			} else  {
				return b;
			}
			
		});
	}
	
	public Real average(){
		int count=0;
		int sum =0;
		for (Integer i : enumerable){
			sum += i.intValue();
			count++;
		}
		return count == 0 ? Real.valueOf(0) : Real.valueOf(sum, count);
	}

}
