package components;

import java.util.Date;

public class Transfert extends Flow{
	
	private int transferingAccountNumber;

	public Transfert(int identifier, double amount, int tragetAccountNumber, boolean effect, Date flowDate,
			String comment, int transferingAccountNumber) {
		super(identifier, amount, tragetAccountNumber, effect, flowDate, comment);
		this.transferingAccountNumber = transferingAccountNumber;
	}

}
