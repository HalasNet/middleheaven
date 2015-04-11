package org.middleheaven.business.account;

import java.util.List;

import org.middleheaven.collections.interval.Interval;
import org.middleheaven.quantity.measure.Measure;
import org.middleheaven.quantity.time.CalendarDate;
import org.middleheaven.quantity.time.DateHolder;
import org.middleheaven.quantity.unit.Measurable;

public abstract class Account<E extends Measurable, M extends Measure<E>> {

	private AccountOwner owner;
	private AccountType type;
	private AccountRepository<E,M> repository;
	
    void setAccountRepository(AccountRepository<E,M> repository){
		this.repository = repository;
	}
	
	public AccountOwner getOwner() {
		return owner;
	}
	public void setOwner(AccountOwner owner) {
		this.owner = owner;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	
	public final M getBalance(DateHolder date){
		return repository.getAccountBalance(this, date);
	}
	
	public final <Mov extends AccountMovement<E,M>> List<Mov> getMovements(Interval<? extends CalendarDate> period){
		return repository.getAccountMovements(this, period);
	}
}
