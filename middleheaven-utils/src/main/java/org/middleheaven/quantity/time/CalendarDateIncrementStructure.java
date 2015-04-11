/**
 * 
 */
package org.middleheaven.quantity.time;

import org.middleheaven.quantity.math.structure.GroupIncrementableStructure;
import org.middleheaven.quantity.math.structure.RangeableStructure;

/**
 * 
 */
public class CalendarDateIncrementStructure 
	implements GroupIncrementableStructure<CalendarDate , ElapsedTime,CalendarDateIncrementStructure>,
	 RangeableStructure<CalendarDate , ElapsedTime,CalendarDateIncrementStructure> 
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isGroupAdditive() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRing() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isField() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CalendarDate plus(CalendarDate a, ElapsedTime elapsed) {
	    return a.plus(elapsed);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CalendarDate minus(CalendarDate a, ElapsedTime elapsed) {
		return a.minus(elapsed);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public  ElapsedTime until(CalendarDate a, CalendarDate b) {
	    return a.until(b);
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElapsedTime zero() {
		throw new UnsupportedOperationException("Not implememented yet");
	}

}
