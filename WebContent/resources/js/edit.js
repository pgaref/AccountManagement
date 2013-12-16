
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