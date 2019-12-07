
function login() {
	var pass = $("#txt_pw").val().trim();
	if (pass.length==0) {
		alert("Passwortfeld leer");
		return;
	}
	try {
		var json = new Object();
		json.passhash=pass;
		  const resp = postJson('rest/admin/login', json);
		  console.log((resp));
		} catch (error) {
		  alert(error);
		}
}

async function postJson(url = '', data = {}) {
	  const response = await fetch(url, {
	    method: 'POST', 
	    headers: {
	      'Content-Type': 'application/json'
	    },
	    body: JSON.stringify(data) 
	  });
	  if(response.ok){
		  alert("Login successful");
	  }
	  if(response.status==409)
		  alert("Password wrong");
	}