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


import java.util.Scanner;

public class AliquatorTester{
	public static void main(String[] args) {
		AliquatorAdder adder = new AliquatorAdder();
		Scanner input = new Scanner(System.in);

		System.out.println("Would you like to enter something? Y/N:\n");
		String yesNo = input.nextLine();
		while(!yesNo.equalsIgnoreCase("n")){
			adder.addTo();
			System.out.println("Would you like to enter something else? Y/N:\n");
			yesNo = input.nextLine();
		}
		System.out.println("Goodbye.");
	}
}