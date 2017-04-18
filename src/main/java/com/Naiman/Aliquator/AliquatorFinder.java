import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.model.Indexes;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

import java.util.Scanner; //Temporary, just for basic testing purposes for now. Will hook up to website at a later date.

public class AliquatorFinder{

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

	public AliquatorFinder(){
		mongoClient = new MongoClient(); //Maybe change this later when host and port and url is established?
		mongoDatabase = new mongoClient.getDatabase("aliquatorTester");
		equationCollection = mongoDatabase.getCollection("equations");
		algorithmCollection = mongoDatabase.getCollection("algorithms");
		input = new Scanner(System.in); //Using Scanner for now to get info
	}

	public void addTo(){
		System.out.println("Would you like to add an algorithm or an equation?\n");
		String equaOrAlgor = input.nextLine();

		while(!equaOrAlgor.equalsIgnoreCase("equation") || .equalsIgnoreCase("algorithm")){
			System.out.println("Only 'equation' or 'algorithm' are valid:\n");
			equaOrAlgor = input.nextLine();
		}

		//To add an equation
		if(equaOrAlgor.equalsIgnoreCase("equation")){
			System.out.println("Please enter your equation's name:\n");
			equationName = input.nextLine();
			System.out.println("Please enter a description of your equation:\n");
			equationDescription = input.nextLine();
			System.out.println("Please enter your equation:\n");
			equationItself = input.nextLine();
		}
		//To add an algorithm
		else{
			System.out.println("Please enter your algorithm's name:\n");
			algorithmName = input.nextLine();
			System.out.println("Please enter a description of your algorithm:\n");
			algorithmDescription = input.nextLine();
			System.out.println("Please enter your algorithm:\n");
			algorithmItself = input.nextLine();
		}
	}
	

}