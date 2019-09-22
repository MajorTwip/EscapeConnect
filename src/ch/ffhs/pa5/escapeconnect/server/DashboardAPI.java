package ch.ffhs.pa5.escapeconnect.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dashboard")
public class DashboardAPI {
	
	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser() {
		return Response.status(200).entity("Hello World").build();
	}
	
}
