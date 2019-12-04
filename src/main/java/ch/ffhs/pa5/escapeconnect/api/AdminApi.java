package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.api.AdminApiService;

import io.swagger.annotations.ApiParam;

import ch.ffhs.pa5.escapeconnect.bean.Body2;
import ch.ffhs.pa5.escapeconnect.bean.Setup;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;


@Path("/admin")


@io.swagger.annotations.Api(description = "the admin API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public class AdminApi  {

   private AdminApiService delegate;

   protected AdminApi() {
   }

   @javax.inject.Inject
   public AdminApi(AdminApiService delegate) {
      this.delegate = delegate;
   }


    @POST
    @Path("/login")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Freischalten der Adminfunktionen", notes = "", response = String.class, tags={ "admin", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Login erfolgreich", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Passwort falsch", response = Void.class) })
    public Response doLogin(@ApiParam(value = "" ,required=true) Body2 body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.doLogin(body,securityContext);
    }

    @POST
    @Path("/setup")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Setzen der Einstellungen des Systems", notes = "", response = Void.class, tags={ "admin", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Token nicht mitgeliefert oder ungültig", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Eine Einstellung enthielt Ungütligen Wert", response = String.class, responseContainer = "List") })
    public Response setup(@ApiParam(value = "" ) Setup body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.setup(body,securityContext);
    }

}

