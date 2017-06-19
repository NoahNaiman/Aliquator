$(window).on("load", function(){
	$.getJSON('answers.json', function(data){
		//Converts mongodb into a string
		var informations = JSON.stringify(data);

		//Cleans up mongodb string
		informations = informations.replace(/{/g, '');
		informations = informations.replace(/}/g, '');

		//Extracts info from mongodb string
		var toPrint = [];
		while(informations != ''){
			if((informations[0] + informations[1] + informations[2] + informations[3] + informations[4] + informations[5] + informations[6]) == 'name":"'){
				informations = informations.substring(7);
					var name = '';
					while(informations[0] != '"'){
						name += informations[0];
						informations = informations.substring(1);
					}
					toPrint.push(name);
					informations = informations.substring(3);
				}
			else if((informations[0] + informations[1] + informations[2] + informations[3] + informations[4] + informations[5] + informations[6] + informations[7] + informations[8] + informations[9] + informations[10] + informations[11] + informations[12] + informations[13]) == 'description":"'){
				informations = informations.substring(14);
				var description = '';
				while(informations[0] != '"'){
					description += informations[0];
					informations = informations.substring(1);
				}
				toPrint.push(description);
				informations = informations.substring(3);
			}
			else if((informations[0] + informations[1] + informations[2] + informations[3] + informations[4] + informations[5] + informations[6] + informations[7] + informations[8] + informations[9] + informations[10]) == 'equation":"'){
				informations = informations.substring(11);
				var equation = '';
				while(informations[0] != '"'){
					equation += informations[0];
					informations = informations.substring(1);
				}
				toPrint.push(equation);
				informations = informations.substring(3);
			}
			else{
				informations = informations.substring(1);
			}
		}
		for(var i = 0; i < toPrint.length; i++){
			for(var i = 0; i < toPrint.length; i++){
				if((i+1)%3 == 0){
					$("body").append("<hr>");
					$("body").append("<ul class='equList'>");
					$("body ul").last().append("<li class='equHeader'>" + toPrint[i-2]);
					$("body ul").last().append("<li class='equSelf'>" + toPrint[i]);
					$("body ul").last().append("<li class='equDescription'>" + toPrint[i-1]);
				}
			}
		}
	});
});