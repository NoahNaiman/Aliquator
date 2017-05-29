//Scripts used for index.html concering general css stuff

// function getInformations(){
//     var informations = document.getElementById("informationsForm").elements[0].value;
//    	informations = informations.replace(/,/g, '');
//    	var infoArray = [];
//    	var toAdd = '';
//     for(var i = 0; i < informations.length; i++){
//     	toAdd += informations[i];
//     	if(informations[i+1] == ' ' || (i+1) == informations.length){
//     		infoArray.push(toAdd);
//     		toAdd = '';
//     	}
//     }
//     for(var i = 0; i < infoArray.length; i++){
//     	if(infoArray[i][0] == ' '){
//     		infoArray[i] = infoArray[i].replace(' ', '');
//     	}
//     }
//     console.log(infoArray);
//     return false;
// }

$('#informationsForm').submit(function(){
	var informations = $('informationsForm :input');
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

    $('#valuesInput').val(infoArray);
    return true;
});