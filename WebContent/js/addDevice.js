
async function addDevice() {
	var name = $("#txt_deviceName").val().trim();
	var file = document.getElementById("file_deviceFile").files[0];
	const formData = new FormData();
	formData.append('name', name);
	console.log(file);
	formData.append('file', file);

	try {
		  const response = await fetch('rest/device/add', {
		    method: 'POST',
		    body: formData
		  });
		  const result = await response;
		  console.log('Success:', JSON.stringify(result));
		} catch (error) {
		  console.error('Error:', error);
		}
}