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
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;
import ch.ffhs.pa5.escapeconnect.persistency.DAOaction;
import ch.ffhs.pa5.escapeconnect.persistency.DAOpanel;
import ch.ffhs.pa5.escapeconnect.persistency.DAOvalue;

public class PanelAPIimplement implements PanelApiService {
	DAOpanel daopanel = new DAOpanel();
	DAOaction daoaction = new DAOaction();
	DAOvalue daovalue = new DAOvalue();
	
	@Override
	public Response getPanes(SecurityContext securityContext) {
		
		List<Panel> resultsToShow = new ArrayList<>();	
		// get the data through the DAO
		List<PanelDAOBean> resultsFromDB = daopanel.getAllPanels();

		int place = 0;
		// convert the PanelDAOBeans to Panels
		for(PanelDAOBean generated_panel : resultsFromDB) {
			Panel panelToShow = new Panel();
			panelToShow.setId(generated_panel.getId());
			panelToShow.setTitle(generated_panel.getName());
			panelToShow.setOrder(place);
			place = place + 1;
			// Add the action and add the values
			List<ActionDAOBean> list_daoActions = daoaction.getActionByPanelID(panelToShow.getId());
			for(ActionDAOBean generated_action : list_daoActions) {
				Action actionToShow = new Action();
				actionToShow.setId(generated_action.getId());
				actionToShow.setLabel(generated_action.getLabel());
				panelToShow.addActionsItem(actionToShow);  
			}
			List<ValueDAOBean> list_daoValues = daovalue.getValuesByPanelID(panelToShow.getId());
			for(ValueDAOBean generated_value : list_daoValues) {
				Value valueToShow = new Value();
				valueToShow.setId(generated_value.getId());
				valueToShow.setLabel(generated_value.getLabel());
				panelToShow.addValuesItem(valueToShow);  
			}
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
