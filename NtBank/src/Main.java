import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


import components.Account;
import components.Client;
import components.Credit;
import components.Debit;
import components.Flow;
import components.Transfert;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Client> clientsCollection = fillClientCollection(5);
		ArrayList<Account> accountCollection = fillAccountCollection(clientsCollection);
		HashMap<Integer, Account> hashedAccountCollection = fillHashAccountCollection(accountCollection);
		ArrayList<Flow> flowCollection = fillFlowCollection(accountCollection);
		

		displayClientsCollection(clientsCollection);
		displayAccountCollection(accountCollection);
		displayHashedAccountCollection(hashedAccountCollection);

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
			accountCollection.add(new Account(client, "account" + client.getClientNumber()) {
			});
		}

		return accountCollection;
	}

	public static HashMap<Integer, Account> fillHashAccountCollection(ArrayList<Account> Collection) {
		HashMap<Integer, Account> hashedAccountCollection = new HashMap<>();
		for (Account account : Collection) {
			hashedAccountCollection.put(account.getaccountNumber(), account);
		}
		return hashedAccountCollection;
	}
	
	public static ArrayList<Flow> fillFlowCollection(ArrayList<Account> accountCollection) {

		ArrayList<Flow> flowCollection = new ArrayList<>();
		
		flowCollection.add(new Debit(50, 1, false, new Date(), "50€ Debit"));
		for (Account account : accountCollection) {
			flowCollection.add(new Credit(100.50, account.getaccountNumber(), false, new Date(), "100.50€ Credit"));
		}
		for (Account account : accountCollection) {
			flowCollection.add(new Credit(1500, account.getaccountNumber(), false, new Date(), "1500€ Credit"));
		}
		
		flowCollection.add(new Transfert(50, 1, false, new Date(), "50€ Transfert from account 1 to 2", 2));

		return flowCollection;
	}

	public static void displayHashedAccountCollection(HashMap<Integer, Account> Collection) {
		System.out.println("Sort");
		Comparator<Entry<Integer, Account>> byBlance = (entry1, entry2) -> Double
				.compare(entry1.getValue().getbalance().getAmount(), entry2.getValue().getbalance().getAmount());

		Collection.entrySet().stream().sorted(byBlance).forEach(System.out::println);

	}

	public static void displayAccountCollection(ArrayList<Account> Collection) {
		System.out.println("Account collection");
		for (Account account : Collection) {
			System.out.println(account.toString());
		}
	}

	public static void displayClientsCollection(ArrayList<Client> Collection) {
		System.out.println("Client collection");
		for (Client client : Collection) {
			System.out.println(client.toString());
		}
	}

}
