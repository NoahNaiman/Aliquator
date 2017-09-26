//Hosted server, used to process and retrieve data

//Library imports
const Server = require('mongo-sync').Server;
const server = new Server('127.0.0.1');
const Fiber = require('fibers');
const express = require('express');
const app = express();
app.set('view engine', 'pug');

//Goes through this database
var url = 'mongodb://localhost/Aliquator';

//Express Routes
app.get('/', function(req, res){	//For Homepage
	res.sendFile(__dirname + '/index.html');
});

app.get('/lookup', function(req, res){	//When information request is made

	//Cleans up given keywords
	var informations = req.query.informationSearch;
	informations = informations.replace(/,/g, '');
	var infoArray = [];
   	var toAdd = '';
    for(var i = 0; i < informations.length; i++) {
    	toAdd += informations[i];
    	if(informations[i+1] == ' ' || (i+1) == informations.length) {
    		infoArray.push(toAdd);
    		toAdd = '';
    	}
    }
    for(var i = 0; i < infoArray.length; i++){
    	if(infoArray[i][0] == ' '){
    		infoArray[i] = infoArray[i].replace(' ', '');
    	}
    }

    //Variables to be used by Pug
    var equationNames = [];
    var equationActuals = [];
    var equationDescriptions = [];

    //Query Mongodb collection, then adds them to arrays, before rendering using Pug
    Fiber(function(){
    	var results = server.db('Aliquator').getCollection('equations').find({units: {$in: infoArray}}).toArray();

    	for(var i = 0; i < results.length; i++){
    		equationNames.push(results[i].name);
			equationActuals.push(results[i].equation);
			equationDescriptions.push(results[i].description);
    	}

    	res.render('answers', {equNames: equationNames, equActuals: equationActuals, equDescriptions: equationDescriptions});

    }).run();

});

app.use(express.static(__dirname + '/'));	//For CSS


//Server starts listening
app.listen(3000, function(){
	console.log('Listening on port: 3000');
});