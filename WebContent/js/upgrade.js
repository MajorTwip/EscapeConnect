
async function upgradeFw() {
	let panelid = selectedpanelid;
	console.log("Upgrade device of panel " + panelid);
	var file = document.getElementById("file_deviceFw").files[0];
	const formData = new FormData();
	formData.append('file', file);
	let url = "rest/device/upgrade?panelid="+ panelid + "&forces=true"
	
	try {
		  const response = await fetch(url, {
		    method: 'POST',
		    body: formData
		  });
		  const result = await response;
		  console.log('Success:', JSON.stringify(result));
		} catch (error) {
		  console.error('Error:', error);
		}
}