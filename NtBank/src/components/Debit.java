package components;

import java.util.Date;

public class Debit extends Flow {

	public Debit(double amount, int tragetAccountNumber, boolean effect, Date flowDate,
			String comment) {
		super("debit", amount, tragetAccountNumber, effect, flowDate, comment);
		// TODO Auto-generated constructor stub
	}

}
