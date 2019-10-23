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
		

		return Response.status(Response.Status.OK).entity(new Panel()).build();
	}

	@Override
	public Response swapPanes(Integer pid1, Integer pid2, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return Response.status(Response.Status.OK).entity("TEST").build();
	}

}
