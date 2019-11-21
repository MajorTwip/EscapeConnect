package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.PanelApiService;
import ch.ffhs.pa5.escapeconnect.bean.Action;
import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.Panel;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.Value;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;

public class PanelAPIimplement implements PanelApiService {
	DAOpanel daopanel = new DAOpanel();
	DAOaction daoaction = new DAOaction();
	
	@Override
	public Response getPanes(SecurityContext securityContext) {
		
		List<Panel> resultsToShow = new ArrayList<>();	
		// get the data through the DAO
		List<PanelDAOBean> resultsFromDB = daopanel.getAllPanels();
		// convert the PanelDAOBeans to Panels
		for(PanelDAOBean generated_panel : resultsFromDB) {
			Panel panelToShow = new Panel();
			panelToShow.setId(generated_panel.getId());
			panelToShow.setTitle(generated_panel.getName());
			// Add the action and add the values
			List<ActionDAOBean> list_daoActions = daoaction.getActionByPanelID(panelToShow.getId());
			for(ActionDAOBean generated_action : list_daoActions) {
				Action actionToShow = new Action();
				actionToShow.setId(generated_action.getId());
				actionToShow.setLabel(generated_action.getLabel());
				panelToShow.addActionsItem(actionToShow);  
			}
			panelToShow.addValuesItem(new Value());
			resultsToShow.add(panelToShow);
		}
		
		// Return the panels to the API
		return Response.status(Response.Status.OK).entity(resultsToShow).build();
	}

	@Override
	public Response swapPanes(Integer pid1, Integer pid2, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.OK).entity("TEST").build();
	}

}
