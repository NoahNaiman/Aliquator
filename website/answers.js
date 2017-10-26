$(function(){
    $("input").autoGrowInput({minWidth:0, comfortZone:0});
});

$(document).keydown(function(e){
	if(e.which == 13){
		$('input[type="text"]').change(function(){
		     var id = $(this).attr('id');
		     var toParse = String($('input[id="' + id + '"]').val()).replace(' ', '').split('');
		     var parsed = [];
		     var cur = '';
		     for(var i = 0; i < toParse.length; i++){
		     	cur += toParse[i];
		     	if(i+1 == toParse.length){
		     		parsed.push(cur);
		     	}
		     	else if("=+-*/".includes(toParse[i+1])){
		     		parsed.push(cur);
		     		parsed.push(toParse[i+1]);
		     		i++;
		     		cur = '';
		     	}
		     }
		     console.log(parsed);
		     $('input[type="text"]').unbind();
		});
	}
});