
async function getPanels(url = 'rest/panel/getAll') {
	  const response = await fetch(url, {
	    method: 'GET'
	  });
	  if(response.ok){
		  populatePanels(response.json());
		  //alert("Got them");
	  }
	  if(response.status==409)
		  alert("getpanes wrong");
	}

async function populatePanels(json){
	resp = await json;
	for(var i = 0;i<resp.length;i++){
		let paneljson = resp[i];
		let panelname = "ec-panel-" + paneljson["id"]; 
		if($("#"+panelname).length==0){
			let $ecpanel = $("#panel-tmp").clone();
			$ecpanel.attr("id", panelname);
			$ecpanel.removeClass("invisible");
			$ecpanel.find(".ec-panel-name").text(paneljson["title"]);
			$ecpanel.find(".ec-panel-status").text(paneljson["status"]?"Online":"Offline");
			
			vals = paneljson["values"];
			$vals = $ecpanel.find(".ec-panel-vals");
			for(var v = 0;v<vals.length;v++){
				val = vals[v];
				$val = $("<div></div>").attr("id", "value-" + val["id"]);
				$val.text(val["label"] + " : " + val["value"]);
				$val.appendTo($vals);
			}
			
			actions = paneljson["actions"];
			$actions = $ecpanel.find(".ec-panel-actions");
			for(var a = 0;a<actions.length;a++){
				action = actions[a];
				$action = $("<input></input>").attr("id", "action-" + action["id"]);
				$action.attr("type", "button");
				$action.addClass("btn btn-secondary my-2 my-sm-0");
				$action.val(action["label"]);				
			    $action.bind("click", function () { doAction(action["id"]); });
				$action.appendTo($actions);
			}
			
			$ecpanel.appendTo("#ec-panels");
			//$("#ec-panels").append(ecpanel);
		}
		//console.log(resp[i]);
	}
}