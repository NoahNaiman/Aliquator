//Hosted server, used to retrieve data

//Library Imports
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectID = require('mongodb').ObjectID
var express = require('express');

//Initialize express
var app = express();

//Goes through this database
var url = 'mongodb://localhost:3000/aliquatorTester';

MongoClient.connect(url, function(err, db){
	assert.equal(null, err);
	console.log("Connected succesfully to server");

	db.close();
});

// function connectToMongodb(){
// 	MongoClient.connect(url, function(err, db){
// 		assert.equal(null, err);
// 		console.log("Connected succesfully to server");

// 		db.close();
// 	});
// }

var findDocuments = function(args, db, callback){
	var equCollection = db.collection('equations');
	var algCollection = db.collection('algorithms');

	equCollection.find({units:{$all: args}}).toArray(function(err, docs){
		assert.equal(err, null);
		console.log("Found the following records: ");
		console.log(docs);
		callback(docs);
	});
}

var pull = function(args, db, callback){
	MongoClient.connect(url, function(err, db){
		assert.equal(null, err);
		console.log("Connected succesfully to server");

		findDocuments(args, db, callback);
		db.close();
	});
}