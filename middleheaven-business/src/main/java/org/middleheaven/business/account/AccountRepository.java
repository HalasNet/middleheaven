package org.middleheaven.business.account;

import java.util.Collections;
import java.util.List;

import org.middleheaven.collections.interval.Interval;
import org.middleheaven.domain.store.DomainStore;
import org.middleheaven.quantity.measure.Measure;
import org.middleheaven.quantity.time.DateHolder;
import org.middleheaven.quantity.unit.Measurable;

public abstract class AccountRepository<E extends Measurable , M extends Measure<E>> {

	private DomainStore ds;
	private M zero;
	
	public AccountRepository(M zero){
		this.zero = zero;
	}
	
	protected void setDataStorage (DomainStore ds){
		this.ds = ds;
	}
	
	public <C extends Account<E,M>> List<C> getOwnerAccounts(AccountOwner owner){
		return getOwnerAccounts(owner, null);
	}
	
	public M getAccountBalance (Account<E,M> account, DateHolder date){
		List<AccountMovement<E,M>> movements = this.getAccountMovements(account, date, date);
		
		M total = zero;
		for ( AccountMovement<E,M> m : movements){
			total = (M) total.plus(m.getAmount());
		}

		return total;
	}
	
	public final <M extends AccountMovement> List<M> getAccountMovements( Account account,Interval<? extends DateHolder> period){
		return getAccountMovements(account, period.start(),period.end());
	}
	
	private final <M extends AccountMovement> List<M> getAccountMovements( Account account,DateHolder inicialDate, DateHolder finalDate){
		return Collections.emptyList(); // TODO
	}
	
	public <C extends Account> List<C> getOwnerAccounts(AccountOwner owner, AccountType type){
		return Collections.emptyList(); // TODO
	}
	
}
