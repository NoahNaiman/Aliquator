$(function(){
    $("input").autoGrowInput({minWidth:0, comfortZone:0});
});

$(document).keydown(function(e){
	$(document).off("keydown");
	$(document).unbind("keydown");
	if(e.which == 13){
		$('input[type="text"]').change(function(){
		     var id = $(this).attr('id');
		     console.log($('input[id="' + id + '"]').val());
		     $('input[type="text"]').unbind();
		});
	}
});