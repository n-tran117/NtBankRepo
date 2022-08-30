package components;

public abstract class Account {

	protected int accountNumber;
	protected Client client;
	protected String label;
	protected double balance;

	protected static int accountNumberCounter = 0;

	public Account(Client client, String label) {
		this.client = client;
		this.label = label;
		accountNumber=accountNumberCounter++;
		balance = Math.random()*100;
	}

	public int getaccountNumber() {
		return accountNumber;
	}

	public void setbalance(double balance) {
		balance = balance;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getbalance() {
		return balance;
	}

	public void setbalance(String flow) {
		switch (flow) {
		case "transfer": {

		}
		case "credit": {

		}
		case "debit": {

		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + flow);
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", client=" + client + ", label=" + label + ", balance="
				+ balance + "]";
	}

}
