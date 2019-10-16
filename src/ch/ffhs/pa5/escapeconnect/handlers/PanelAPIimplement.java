package ch.ffhs.pa5.escapeconnect.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.PanelApiService;
import ch.ffhs.pa5.escapeconnect.bean.Action;
import ch.ffhs.pa5.escapeconnect.bean.Panel;
import ch.ffhs.pa5.escapeconnect.bean.Value;

public class PanelAPIimplement implements PanelApiService {

	@Override
	public Response getPanes(SecurityContext securityContext) {
		// TODO Auto-generated method stub
		// Demo
		
		List<Panel> panels = new ArrayList<>();
		Panel panel1 = new Panel();
		panel1.setId(1);
		panel1.setStatus(true);
		panel1.setTitle("Sandrätsel");
		Action action = new Action();
		action.setId(1);
		action.setLabel("Freischalten");
		panel1.getActions().add(action);
		Value val = new Value();
		val.setId(2);
		val.setLabel("Sandlevel");
		val.setValue("50%");
		panel1.getValues().add(val);
		panels.add(panel1);
		
		Panel panel2 = new Panel();
		panel2.setId(2);
		panel2.setStatus(false);
		panel2.setTitle("Wasserdings");
		Action action2 = new Action();
		action2.setId(12);
		action2.setLabel("Freischalten");
		panel2.getActions().add(action2);
		panels.add(panel2);
		return Response.status(Response.Status.OK).entity("TEST").build();
	}

	@Override
	public Response swapPanes(Integer pid1, Integer pid2, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
