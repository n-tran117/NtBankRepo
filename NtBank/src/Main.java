import java.lang.reflect.Array;
import java.util.ArrayList;

import components.Client;

public class Main {

	public static void main(String[] args) {
		ArrayList<Client> clientsCollection = fillClientCollection(5);
		displayClientsCollection(clientsCollection);

	}
	
	public static ArrayList<Client> fillClientCollection(int nbClient) {
	ArrayList<Client> clientsCollection = new ArrayList<>();
		
		for(int i = 0; i < nbClient; i++) {
			clientsCollection.add(new Client("Name"+i, "FirtName"+i));
		}
		return clientsCollection;
	}

	public static void displayClientsCollection(ArrayList<Client> Collection) {
		
		for (Client client : Collection) {
			System.out.println(client.toString());
		}
		
	}
}
