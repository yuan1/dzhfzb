function showhide(objname){
	var objelement = $("#"+objname);
	var showOrHide = objelement.css("display");
	if(showOrHide == "none"){
		objelement.css({display:"block"});
		$("#"+objname+"_a").css({background:"url(resources/images/ico_1.gif) 10px 5px no-repeat"});
	}else{
		objelement.css({display:"none"});
		$("#"+objname+"_a").css({background:"url(resources/images/ico_2.gif) 10px 5px no-repeat"});
	}
}
//--------------
$(document).ready(function(){
	$('.menuu').find('.option').find('li').hover(function(){
		$(this).children('img').attr('src','resources/images/option_hover.gif');
	},function(){
		$(this).children('img').attr('src','resources/images/option_list.gif');
	});
	$('.menuu').find('.title').find('.menuu_title_img').hover(function(){
		$(this).find('img').attr('src',$(this).find('img').attr('src').replace('.png','_inset.png'));
		$(this).find('img').css('cursor','hand');
	},function(){
		$(this).find('img').attr('src',$(this).find('img').attr('src').replace('_inset.png','.png'));
	});
	$('.menuu').find('.title').find('.menuu_title_img').toggle(function(){
		$(this).find('img').attr('src','resources/images/row_down.png');
		$(this).parent().parent().next('.option').hide();
	},function(){
		$(this).find('img').attr('src','resources/images/row_up.png');
		$(this).parent().parent().next('.option').show();
	});
	
});