package components;

abstract class Account {
	
	protected int AccountNumber;
	protected Client client;
	protected String label;
	protected double Balance;
	
	protected static int AccountNumberCounter=0;
	
	
	
	public Account(String label, Client client) {
		this.label = label;
		this.client = client;
		AccountNumber=AccountNumberCounter++;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public double getBalance() {
		return Balance;
	}


	public void setBalance(String flow) {
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
		return "Account [AccountNumber=" + AccountNumber + ", client=" + client + ", label=" + label + ", Balance="
				+ Balance + "]";
	}
	
	
	

}
