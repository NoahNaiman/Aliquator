$(function(){
    $("input").autoGrowInput({minWidth:0, comfortZone:0});
});

$('form').submit(function(){
	$input = $('form :input');
	console.log($input);
});

$('input').keypress(function(event){
    if(event.which == 13){
        event.preventDefault();
        $("form").submit();
    }
});