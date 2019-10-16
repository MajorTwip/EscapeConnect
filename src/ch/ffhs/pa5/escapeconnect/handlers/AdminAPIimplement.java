package ch.ffhs.pa5.escapeconnect.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.AdminApiService;
import ch.ffhs.pa5.escapeconnect.bean.Body2;
import ch.ffhs.pa5.escapeconnect.bean.Setup;

public class AdminAPIimplement implements AdminApiService {

	@Override
	public Response doLogin(Body2 body, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response setup(Setup body, SecurityContext securityContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
