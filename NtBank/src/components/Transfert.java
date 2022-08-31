package components;

import java.util.Date;

public class Transfert extends Flow{
	
	protected int transferingAccountNumber;

	public Transfert(double amount, int tragetAccountNumber, boolean effect, Date flowDate,
			String comment, int transferingAccountNumber) {
		super("transfer", amount, tragetAccountNumber, effect, flowDate, comment);
		this.transferingAccountNumber = transferingAccountNumber;
	}

	@Override
	public int getTransferingAccountNumber() {
		return transferingAccountNumber;
	}
	
	

}
