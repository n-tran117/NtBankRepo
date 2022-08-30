package components;

public class Client {
	
	private String name;
	private String firstName;
	private static int clientNumber=0;
	
	public Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		clientNumber++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public static int getClientNumber() {
		return clientNumber;
	}

	@Override
	public String toString() {
		return "Client [Client Number= "+clientNumber+" name= " + name + ", firstName= " + firstName + "]";
	}

	

}
