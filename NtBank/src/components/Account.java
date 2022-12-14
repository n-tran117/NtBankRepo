package components;

public abstract class Account {

	protected int accountNumber;
	protected Client client;
	protected String label;
	protected Double balance = 0.0;

	protected static int accountNumberCounter = 0;

	public Account(Client client, String label) {
		this.client = client;
		this.label = label;
		accountNumber = accountNumberCounter++;
	}

	public int getaccountNumber() {
		return accountNumber;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getbalance() {
		return balance;
	}

	public void setbalance(Flow flow) {
		switch (flow.getIdentifier()) {
		case "transfer": {
//			System.out.println("transfert :");
//			System.out.println(((Transfert) flow).getTransferingAccountNumber());
			if (flow.getTragetAccountNumber() == this.accountNumber) {
				this.balance += flow.getAmount();
			} else if (((Transfert) flow).getTransferingAccountNumber() == this.accountNumber) {
				this.balance -= flow.getAmount();
			}
			break;
		}
		case "credit": {
			balance += flow.getAmount();
			break;
		}
		case "debit": {
			balance -= flow.getAmount();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + flow.getIdentifier());
		}

	}

	public void setBalance(Double amount) {
		balance = amount;
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
