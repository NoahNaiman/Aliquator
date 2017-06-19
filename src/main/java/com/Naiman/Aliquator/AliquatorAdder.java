package com.Naiman.Aliquator;

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
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.Block;


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
		mongoClient = new MongoClient("localhost", 27017); //Maybe change this later when host and port and url is established?
		mongoDatabase = mongoClient.getDatabase("Aliquator"); //Official Database switched to as of 6/19/2017.
		equationCollection = mongoDatabase.getCollection("equations");
		algorithmCollection = mongoDatabase.getCollection("algorithms"); //Currently not used, possible for later usage.
		input = new Scanner(System.in); //Using Scanner for now to get info

		//Instantiates linked lists for equation parts and units
		//equationParts = new LinkedList<String>(); COME BACK TO LATER IF NECESSARY
		equationUnits = new LinkedList<String>();
	}

	/**
	 * @return the number of equations in database
	 */
	public int howManyEquations(){
		int num = (int)equationCollection.count();
		return num;
	}

	/**
	 * @return the number of algorithms in database
	 */
	public int howManyAlgorithms(){
		int num = (int)algorithmCollection.count();
		return num;
	}

	/**
	 * Adds either an equation or an algorithm into database
	 */
	public void addTo(){
		System.out.println("Enter 'a' for algorithm or 'e' for equation:\n");
		String aOrE = input.nextLine();
		while(!aOrE.equalsIgnoreCase("a") && !aOrE.equalsIgnoreCase("e")){
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

			System.out.println("Please enter one unit of your equation at a time. When all are entered, type 'x' to quit:\n");
			String part = input.nextLine();

			do{
				equationUnits.add(part);
				System.out.println("Please enter next unit, or 'x' to quit:\n");
				part = input.nextLine();
			}while(!part.equalsIgnoreCase("x"));

			String[] equUnits = equationUnits.toArray(new String[0]);
			Document newDoc = new Document("name", equationName)
							.append("description", equationDescription)
							.append("equation", equationItself)
							.append("units", Arrays.asList(equUnits));
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

		Document newDoc = new Document("name", algorithmName)
							.append("description", algorithmDescription)
							.append("equation", algorithmItself);
		algorithmCollection.insertOne(newDoc);
	}

}