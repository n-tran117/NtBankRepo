package components;

import java.util.Date;

public class Credit extends Flow{

	public Credit(double amount, int tragetAccountNumber, boolean effect, Date flowDate,
			String comment) {
		super("credit", amount, tragetAccountNumber, effect, flowDate, comment);
		// TODO Auto-generated constructor stub
	}

}
