function topage(page)
{
	var pageform  = document.getElementById("mypageform")
	if(pageform==null){
		var form = document.forms[0];
		form.pageNO.value = page;
		form.submit();
	}else{
		pageform.pageNO.value = page;
		pageform.submit();
		
	}

}

function goTo(url) {
    var a = document.createElement("a");
    if(!a.click) { //only IE has this (at the moment);
        window.location = url;
        return;
    }
    a.setAttribute("href", url);
    a.style.display = "none";
    document.body.appendChild(a);
    a.click();
}