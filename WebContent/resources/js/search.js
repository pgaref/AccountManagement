if (document.getElementById('word').value!=""){
		document.getElementById('fieldset').style.visibility="visible";
	}
	else{
		document.getElementById('fieldset').style.visibility="hidden";
	}
	if (document.getElementById('hide').value==""){
		document.getElementById('fieldset').style.visibility="hidden";
		document.getElementById('no_result').innerHTML="No Result Found";
	}
	
	if(document.getElementById("hide").value=="[]"){
		document.getElementById("fieldset").style.visibility="hidden";
		document.getElementById("formsContent").innerHTML="<fieldset ><legend>Empty List</legend></fieldset>";
		
	}