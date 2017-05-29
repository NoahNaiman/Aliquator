//Hosted server, used to retrieve and process data

//Library imports
var MongoClient = require('mongodb').MongoClient;
var fs = require('fs');
var express = require('express');
var app = express();

//Goes through this database
var url = 'mongodb://localhost/aliquatorTester';

//Express Routes
app.get('/', function(req, res){	//For Homepage
	res.sendFile(__dirname + '/index.html');
});

app.get('/lookup', function(req, res){	//When information request is made
	var informations = req.query.informationSearch;
	informations = informations.replace(/,/g, '');
	var infoArray = [];
   	var toAdd = '';
    for(var i = 0; i < informations.length; i++){
    	toAdd += informations[i];
    	if(informations[i+1] == ' ' || (i+1) == informations.length){
    		infoArray.push(toAdd);
    		toAdd = '';
    	}
    }
    for(var i = 0; i < infoArray.length; i++){
    	if(infoArray[i][0] == ' '){
    		infoArray[i] = infoArray[i].replace(' ', '');
    	}
    }
    console.log(infoArray);

	MongoClient.connect(url, function(err, db){
		console.log("Connected succesfully to Mongodb Server");
		var equCollection = db.collection('equations');
		var algCollection = db.collection('algorithms');
		equCollection.find({units: {$in: infoArray}}).toArray(function(err, docs){
			res.send(docs);
		});
		db.close();
	});
});

app.use(express.static(__dirname + '/'));	//For CSS


//Server starts listening
app.listen(3000, function(){
	console.log('Listening on port: 3000');
});