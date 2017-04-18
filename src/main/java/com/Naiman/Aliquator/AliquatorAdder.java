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

public class AliquatorAdder{

	private MongoClient mongoClient; //MongoDB client connector
	private MongoDatabase mongoDatabase; //MongoDB database
	private MongoCollection<Document> equationCollection; //Get collection of equations
	private MongoCollection<Document> algorithmCollection; //Get collection of Algorithms
	private Scanner scanner; //Simple scanner for testing purposes for right now

	//Self explanatory. May change this later depending on what is needed to hook up to web
	private String algorithmName;
	private String algorithmDescription;
	private String equationName;
	private String equationItself;
	private String equationDescription;

	public AliquatorFinder(){
		mongoClient = new MongoClient(); //Maybe change this later when host and port and url is established?
		mongoDatabase = new mongoClient.getDatabase("aliquator");
		equationCollection = mongoDatabase.getCollection("equations");
		algorithmCollection = mongoDatabase.getCollection("algorithms");
		scanner = new Scanner(System.in); //Using Scanner for now to get info
	}
	

}