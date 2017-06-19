//Hosted server, used to retrieve and process data

//Library imports
var MongoClient = require('mongodb').MongoClient;
var fs = require('fs');
var express = require('express');
var app = express();

//Goes through this database
var url = 'mongodb://localhost/Aliquator';

//Express Routes
app.get('/', function(req, res){	//For Homepage
	fs.writeFile("answers.json", '', function(err) {
		if(err){
			return console.log(err);
			}
		});
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

	MongoClient.connect(url, function(err, db){
		console.log("Connected succesfully to Mongodb Server");
		var equCollection = db.collection('equations');
		var algCollection = db.collection('algorithms');
		equCollection.find({units: {$in: infoArray}}).forEach(function(doc){
			fs.appendFile("answers.json", JSON.stringify(doc), function(err) {
				if(err){
					return console.log(err);
				}
			});
		});
		db.close();
	});
	res.sendFile(__dirname + '/answers.html');
});

app.use(express.static(__dirname + '/'));	//For CSS


//Server starts listening
app.listen(3000, function(){
	console.log('Listening on port: 3000');
});