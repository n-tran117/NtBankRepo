import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
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
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import components.Account;
import components.Client;
import components.Credit;
import components.Debit;
import components.Flow;
import components.Transfert;

public class Main {

	public static void main(String[] args) {

		ArrayList<Client> clientsCollection = fillClientCollection(5);
		ArrayList<Account> accountCollection;
		
//		accountCollection = fillAccountCollection(clientsCollection);
		accountCollection = fillAccountCollectionByXml();
		HashMap<Integer, Account> hashedAccountCollection = fillHashAccountCollection(accountCollection);
		ArrayList<Flow> flowCollection;

//		flowCollection = fillFlowCollection(accountCollection);
		flowCollection = fillFlowCollectionJson();

		

		displayClientsCollection(clientsCollection);
		displayAccountCollection(accountCollection);
		displayFlows(flowCollection);
		processflows(flowCollection, hashedAccountCollection);
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

	public static ArrayList<Account> fillAccountCollectionByXml() {
		ArrayList<Account> accountCollection = new ArrayList<>();

		File file = new File("src/ressources/accountCollection.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("account");
			Element eElement;
			Client tmpClient;
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					eElement = (Element) node;
					tmpClient = new Client(eElement.getElementsByTagName("name").item(0).getTextContent(),
							eElement.getElementsByTagName("firstName").item(0).getTextContent());
		
					accountCollection.add(new Account(tmpClient, eElement.getElementsByTagName("label").item(0).getTextContent()) {
					}) ;

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static ArrayList<Flow> fillFlowCollectionJson() {
		ArrayList<Flow> flows = new ArrayList<>();
		try {
//			System.out.println("fillFlowJson");

			JSONParser jsonParser = new JSONParser();
			FileReader fileReader = new FileReader("src/ressources/flowCollection.json");
			JSONArray jsArr = (JSONArray) jsonParser.parse(fileReader);

//			System.out.println("arr");
//			System.out.println(jsArr);

			jsArr.forEach(data -> {
				try {
					flows.add(parseFlow((JSONObject) data));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return flows;

	}

	public static Flow parseFlow(JSONObject data) throws ParseException {
		Flow flow;
		switch ((String) data.get("identifier")) {
		case "transfer": {

			flow = new Transfert((Double) data.get("amount"), (int) (long) data.get("tragetAccountNumber"),
					(boolean) data.get("effect"),
					new SimpleDateFormat("dd/MM/yyyy").parse((String) data.get("flowDate")),
					(String) data.get("comment"), (int) (long) data.get("transferingAccountNumber"));
			break;
		}
		case "credit": {
			flow = new Credit((Double) data.get("amount"), (int) (long) data.get("tragetAccountNumber"),
					(boolean) data.get("effect"),
					new SimpleDateFormat("dd/MM/yyyy").parse((String) data.get("flowDate")),
					(String) data.get("comment"));
			break;
		}
		case "debit": {
			flow = new Debit((Double) data.get("amount"), (int) (long) data.get("tragetAccountNumber"),
					(boolean) data.get("effect"),
					new SimpleDateFormat("dd/MM/yyyy").parse((String) data.get("flowDate")),
					(String) data.get("comment"));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + data.get("identifier"));
		}
		return flow;

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

	public static void processflows(ArrayList<Flow> flowCollection, HashMap<Integer, Account> hashedAccountCollection) {
		System.out.println("process flows");
		for (Flow flow : flowCollection) {
			hashedAccountCollection.get(flow.getTragetAccountNumber()).setbalance(flow);
		}

		ArrayList<Flow> transfere = new ArrayList<>();
		// System.out.println(transfere);

		Comparator<Account> byBlance = (entry1, entry2) -> Double.compare(entry1.getbalance(), entry2.getbalance());

		hashedAccountCollection.values().stream().sorted(byBlance).filter(a -> a.getbalance() < 0)
				.forEach(a -> System.out.println(
						"Account n°" + a.getaccountNumber() + " have à balance under zero : " + a.getbalance()));

	}

}
