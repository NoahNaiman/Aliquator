$(function(){
    $("input").autoGrowInput({minWidth:0, comfortZone:0});
});

$(document).keydown(function(e){
	if(e.which == 13){
		$('input[type="text"]').change(function(){
		     var id = $(this).attr('id');
		     var toParse = String($('input[id="' + id + '"]').val()).replace(' ', '').split('');
		     // console.log(toParse);
		     // var parsed = [];
		     // var cur = '';
		     // for(var i = 0; i < toParse.length; i++){
		     // 	cur += toParse[i];
		     // 	if(i+1 == toParse.length){
		     // 		parsed.push(cur);
		     // 	}
		     // 	else if("=+-*/".includes(toParse[i+1])){
		     // 		parsed.push(cur);
		     // 		parsed.push(toParse[i+1]);
		     // 		i++;
		     // 		cur = '';
		     // 	}
		     // }
		     console.log(toParse);
		     $('input[type="text"]').unbind();
		     $("input").autoGrowInput({minWidth:0, comfortZone:0});
		});
	}
});

function solve(equation){
	var restructured =[];
	for(var i = 0; i < equation.length; i++) {
		if(("1234567890".includes(equation[i])) && ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".includes(equation[i+2]) || "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".includes(equation[i-2]))){

		}
	}
}