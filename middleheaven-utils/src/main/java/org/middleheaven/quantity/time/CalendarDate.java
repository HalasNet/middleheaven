/*
 * Created on 10/Mar/2007
 */
package org.middleheaven.quantity.time;

import java.util.Date;

import org.middleheaven.collections.progression.Progression;
import org.middleheaven.collections.progression.StepLinearProgression;
import org.middleheaven.quantity.math.structure.GroupIncrementable;
import org.middleheaven.quantity.math.structure.Rangeable;
import org.middleheaven.util.StepIncrementable;


/**
 * 
 */
public class CalendarDate extends CalendarDateTime 
	implements StepIncrementable<CalendarDate, ElapsedTime>, 
	GroupIncrementable<CalendarDate, ElapsedTime, CalendarDateIncrementStructure>,
	Rangeable<CalendarDate, ElapsedTime, CalendarDateIncrementStructure>,
	DiscreteIncrementable<CalendarDate>{

	private static final long serialVersionUID = -5640403398323384454L;
	
	public static CalendarDate today(){
    	return new CalendarDate(TimeContext.getTimeContext(), TimeContext.getTimeContext().now().getMilliseconds());
    }
	
	public static CalendarDate valueOf(Date date){
		return CalendarDateTime.valueOf(date).toDate();
	}
	
    public static CalendarDate date(int year , int month, int day){
    	return date(true,year,month,day);
    }
  
    public static CalendarDate date(boolean lenient , int year , int month, int day){
    	return date(TimeContext.getTimeContext(), lenient, year,month,day);
    }
    
    public static CalendarDate date( TimeContext context,boolean lenient , int year , int month, int day){
    	return new CalendarDate(context,context.getChronology().milisecondsFor(lenient,year, month, day));
    }
    
    protected CalendarDate (TimeContext context, long timeFromEpoc){
        super(context,timeFromEpoc);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public CalendarDate next(){
    	return context.getChronology().add(this, Duration.of().days(1));
    }
    
	
	public CalendarDateTime atTime(int hour, int minute, int second){
		return CalendarDateTime.at(this.year().ordinal(), this.month().ordinal(), this.dayOfMonth().getDay(), hour, minute, second);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
    public CalendarDate previous(){
    	return context.getChronology().add(this, Duration.of().days(-1));
    }

    public CalendarDate plus (ElapsedTime elapsed){
    	return this.context.getChronology().add(this, elapsed);
    }
    
    public CalendarDate minus (ElapsedTime elapsed){
    	return this.context.getChronology().add(this, elapsed.negate());
    }
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElapsedTime until(CalendarDate other) {
		return this.context.getChronology().periodBetween(this, other);
	}
    
	
	public Progression<CalendarDate, ElapsedTime> upTo(CalendarDate other) {
		return new StepLinearProgression<ElapsedTime,CalendarDate>(this, other,Duration.of().days(1)); 
	}

	public Progression<CalendarDate, ElapsedTime> to(CalendarDate other, ElapsedTime elapsedTime) {
		return new StepLinearProgression<ElapsedTime,CalendarDate>(this, other,elapsedTime); 
	}
	
	public String toString(){
		return TimeUtils.toDate(this).toString();
	}

	@Override
	public CalendarDate nextBy(ElapsedTime increment) {
		return this.plus(increment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CalendarDate previousBy(ElapsedTime increment) {
		return this.minus(increment);
	}

	
	@Override
	public int compareTo(DateHolder other) {
		int comp = this.year().ordinal() - other.year().ordinal();
		if (comp ==0){
			comp = this.month().ordinal() - other.month().ordinal();
			if (comp ==0){
				comp = this.dayOfMonth().getDay() - other.dayOfMonth().getDay();
				
			}
		}
		return comp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CalendarDateIncrementStructure getAlgebricStructure() {
		return new CalendarDateIncrementStructure();
	}





	
}
