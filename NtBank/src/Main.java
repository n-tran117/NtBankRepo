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
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Predicate;

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
		

//		displayClientsCollection(clientsCollection);
//		displayAccountCollection(accountCollection);
//		displayFlows(flowCollection);
		processflows(flowCollection,hashedAccountCollection);
		//displayHashedAccountCollection(hashedAccountCollection);


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
		
		flowCollection.add(new Debit(5000, 1, false, new Date(), "50€ Debit"));
//		flowCollection.add(new Debit(4000, 2, false, new Date(), "50€ Debit"));
		for (Account account : accountCollection) {
			flowCollection.add(new Credit(100.50, account.getaccountNumber(), false, new Date(), "100.50€ Credit"));
		}
		for (Account account : accountCollection) {
			flowCollection.add(new Credit(1500, account.getaccountNumber(), false, new Date(), "1500€ Credit"));
		}
		
		flowCollection.add(new Transfert(50, 2, false, new Date(), "50€ Transfert from account 1 to 2", 1));

		return flowCollection;
	}

	public static void displayHashedAccountCollection(HashMap<Integer, Account> collection) {
		System.out.println("Sort");
//		HashMap<Integer, Account> tmpCollection = collection;
//		for (Map.Entry<Integer, Account> entry : tmpCollection.entrySet()) {
//			entry.getValue().setBalance(Math.random()*1000);
//		}
		
		Comparator<Entry<Integer, Account>> byBlance = (entry1, entry2) -> Double
				.compare(entry1.getValue().getbalance(), entry2.getValue().getbalance());

		collection.entrySet().stream().sorted(byBlance).forEach(System.out::println);

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
	
	public static void displayFlows(ArrayList<Flow> flowCollection) {
		System.out.println("Flow");
		for (Flow flow : flowCollection) {
			System.out.println(flow.toString());
		}
		
	}
	
	public static void processflows(ArrayList<Flow> flowCollection,HashMap<Integer, Account> hashedAccountCollection) {
		System.out.println("process flows");
		for (Flow flow : flowCollection) {
			hashedAccountCollection.get(flow.getTragetAccountNumber()).setbalance(flow);
		}
		
		Comparator<Account> byBlance = (entry1, entry2) -> Double
				.compare(entry1.getbalance(), entry2.getbalance());
		
		hashedAccountCollection.values().stream()
		.sorted(byBlance)
		.filter(a -> a.getbalance() < 0)
		.forEach(a -> System.out.println("Account n°"+a.getaccountNumber()+" have à balance under zero : "+a.getbalance()));
		
	}

}
