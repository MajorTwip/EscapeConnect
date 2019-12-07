async function doAction(deviceId, url = 'rest/action') {
		url=url+"?deviceId="+deviceId;
	  const response = await fetch(url, {
	    method: 'POST'
	  });
	  if(response.ok){
		  alert("action done");
	  }
	  if(response.status==409)
		  alert("action error wrong");
	}