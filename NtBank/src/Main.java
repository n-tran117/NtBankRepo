import java.lang.reflect.Array;
import java.util.ArrayList;

import components.Account;
import components.Client;

public class Main {

	public static void main(String[] args) {
		ArrayList<Client> clientsCollection = fillClientCollection(5);
		ArrayList<Account> accountCollection = fillAccountCollection(clientsCollection);
		displayClientsCollection(clientsCollection);
		displayAccountCollection(accountCollection);

	}

	public static ArrayList<Client> fillClientCollection(int nbClient) {
		ArrayList<Client> clientsCollection = new ArrayList<>();

		for (int i = 0; i < nbClient; i++) {
			clientsCollection.add(new Client("Name" + i, "FirtName" + i));
		}
		return clientsCollection;
	}

	public static ArrayList<Account> fillAccountCollection(ArrayList<Client> Collection) {
		ArrayList<Account> accountCollection = new ArrayList<>();
		for (Client client : Collection) {
			accountCollection.add(new Account(client,"account"+client.getClientNumber()) {});
		}

		return accountCollection;
	}
	
	public static void displayAccountCollection(ArrayList<Account> Collection) {

		for (Account account : Collection) {
			System.out.println(account.toString());
		}
	}

	public static void displayClientsCollection(ArrayList<Client> Collection) {

		for (Client client : Collection) {
			System.out.println(client.toString());
		}

	}
}
