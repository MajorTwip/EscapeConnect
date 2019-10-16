package ch.ffhs.pa5.escapeconnect.handlers;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.ActionApiService;

public class ActionAPIimplement implements ActionApiService {

	@Override
	public Response doAction(@NotNull Integer actionId, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
