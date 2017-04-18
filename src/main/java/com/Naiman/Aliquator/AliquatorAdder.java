import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

import com.mongodb.client.model.Indexes;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

import java.util.Scanner; //Temporary, just for basic testing purposes for now.

//THIS WILL BE USED FOR UPDATING DATABSES, WILL NOT BE HOOKED UP TO WEBSITE
public class AliquatorAdder{

	private MongoClient mongoClient; //MongoDB client connector
	private MongoDatabase mongoDatabase; //MongoDB database
	private MongoCollection<Document> equationCollection; //Get collection of equations
	private MongoCollection<Document> algorithmCollection; //Get collection of Algorithms
	private Scanner input; //Simple scanner for testing purposes for right now

	//Self explanatory. May change this later depending on what is needed to hook up to web
	private String algorithmName;
	private String algorithmDescription;
	private String algorithmItself;

	private String equationName;
	private String equationDescription;
	private String equationItself;
	//private LinkedList<String> equationParts; POSSIBLY COME BACK TO LATER IF NECESSARY
	private LinkedList<String> equationUnits;

	public AliquatorAdder(){
		mongoClient = new MongoClient(); //Maybe change this later when host and port and url is established?
		mongoDatabase = new mongoClient.getDatabase("aliquatorTester"); //Currently uses just test databases, change later.
		equationCollection = mongoDatabase.getCollection("equations");
		algorithmCollection = mongoDatabase.getCollection("algorithms");
		input = new Scanner(System.in); //Using Scanner for now to get info

		//Instantiates linked lists for equation parts and units
		//equationParts = new LinkedList<String>(); COME BACK TO LATER IF NECESSARY
		equationUnits = new LinkedList<String>();
	}

	/**
	 * @return the number of equations in database
	 */
	public int howManyEquations(){
		int num = equationCollection.count();
		return num;
	}

	/**
	 * @return the number of algorithms in database
	 */
	public int howManyAlgorithms(){
		int num = algorithmCollection.count();
		return num;
	}

	/**
	 * Adds either an equation or an algorithm into database
	 */
	public void addTo(){
		System.out.println("Enter 'a' for algorithm or 'e' for equation:\n");
		String aOrE = input.nextLine();
		while(!aOrE.equalsIgnoreCase("a") || !aOrE.equalsIgnoreCase("e")){
			System.out.println("Only 'a' and 'e' are valid options:\n");
			aOrE = input.nextLine();
		}

		if(aOrE.equalsIgnoreCase("e")){
			addEquation();
		}
		else{
			addAlgorithm();
		}
	}

	//Adds an equation to equationCollection
	private void addEquation(){
			System.out.println("Please enter your equation's name:\n");
			equationName = input.nextLine();
			System.out.println("Please enter a description of your equation:\n");
			equationDescription = input.nextLine();
			System.out.println("Please enter your equation:\n");
			equationItself = input.nextLine();

			Document newDoc = new Document("name", equationName)
							.append("description", equationDescription)
							.append("equation", equationItself);
			equationCollection.insertOne(newDoc);
	}

	//Adds an algorithm to algorithmCollection
	private void addAlgorithm(){
		System.out.println("Please enter your algorithm's name:\n");
		algorithmName = input.nextLine();
		System.out.println("Please enter a description of your algorithm:\n");
		algorithmDescription = input.nextLine();
		System.out.println("Please enter your algorithm:\n");
		algorithmItself = input.nextLine();

		System.out.println("Please enter all units your array will have. Enter 'x' to quit:\n");
		String part = input.nextLine();

		do{
			equationUnits.add(part);
			System.out.println("Please enter next unit, or 'x' to quit:\n")
		}while(!part.equalsIgnoreCase("x"));

		String[] equUnits = equationUnits.toArray(new String[0]);
		Document newDoc = new Document("name", algorithmName)
							.append("description", algorithmDescription)
							.append("equation", algorithmItself);
							.append("units", Arrays.asList(equUnits));
		algorithmCollection.insertOne(newDoc);
	}

}