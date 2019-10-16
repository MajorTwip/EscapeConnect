package ch.ffhs.pa5.escapeconnect.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.PanelApiService;

public class PanelAPIimplement implements PanelApiService {

	@Override
	public Response getPanes(SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response swapPanes(Integer pid1, Integer pid2, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
