
public class Client {
	
	private String name;
	private String firstName;
	private static int clientNumber=0;
	
	public Client(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
		clientNumber++;
	}

}
