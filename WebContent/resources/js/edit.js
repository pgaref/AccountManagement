
				
				var years = document.getElementById("years_str").value;
				var month = document.getElementById("months_str").value;
				var day = document.getElementById("days_str").value;
				
				var mo="";
				if (month==1){mo="January";}
				else if (month==2){mo="February";}
				else if (month==3){mo="March";}
				else if (month==4){mo="April";}
				else if (month==5){mo="May";}
				else if (month==6){mo="June";}
				else if (month==7){mo="July";}
				else if (month==8){mo="August";}
				else if (month==9){mo="September";}
				else if (month==10){mo="October";}
				else if (month==11){mo="November";}
				else if (month==12){mo="December";}

				document.getElementById(mo).selected=true;                 
				getDays();
				document.getElementById(day).selected=true;



				var pathArray = window.location.pathname.split( '/' );

				var lis = document.getElementById("navi").getElementsByTagName("li");
				
				for (var i = 0; i < lis.length; i++) {
					if (lis[i].className=="active"){
						
						var id=lis[i].getAttribute("id");
						document.getElementById(id).className = "";
					}
				}
				
				
				var ids=pathArray[pathArray.length-1];
				
				if (ids=="form"){
					
					document.getElementById('t1').className="active";
				}
				if (ids=="edit"){
					clearPersistenceCookie();
					if (document.getElementById("email_form").value==""){
						document.getElementById("email1").checked=false;
					}
					if (document.getElementById("phone_form").value==""){
						document.getElementById("phone1").checked=false;
					}
					if (document.getElementById("email_form").value!=""){
						document.getElementById("email1").checked=true;
						
					}
					if (document.getElementById("phone_form").value!=""){
						document.getElementById("phone1").checked=true;
					}
					make_visible();
					restorePersistedCheckBoxes();
				}
				
				
				
				
				
				
				