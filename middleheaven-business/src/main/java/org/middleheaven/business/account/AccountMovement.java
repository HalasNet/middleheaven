package org.middleheaven.business.account;

import org.middleheaven.quantity.measure.Measure;
import org.middleheaven.quantity.time.DateTimeHolder;
import org.middleheaven.quantity.unit.Measurable;

public abstract class AccountMovement<E extends Measurable, M extends Measure<E>> {

	private Account<E,M> acount;
	private DateTimeHolder timsestamp;
	private M amount;
	private AccountOperation operation;
	
	
	public Account<E,M> getAcount() {
		return acount;
	}
	public void setAcount(Account<E,M> acount) {
		this.acount = acount;
	}
	public DateTimeHolder getTimsestamp() {
		return timsestamp;
	}
	public void setTimsestamp(DateTimeHolder timsestamp) {
		this.timsestamp = timsestamp;
	}
	public M getAmount() {
		return amount;
	}
	public void setAmount(M amount) {
		this.amount = amount;
	}
	public AccountOperation getOperation() {
		return operation;
	}
	public void setOperation(AccountOperation operation) {
		this.operation = operation;
	}
	
	
}
