package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.api.ActionApiService;

import io.swagger.annotations.ApiParam;



import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;


@Path("/action")


@io.swagger.annotations.Api(description = "the action API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public class ActionApi  {

   private ActionApiService delegate;

   protected ActionApi() {
   }

   @javax.inject.Inject
   public ActionApi(ActionApiService delegate) {
      this.delegate = delegate;
   }


    @POST
    
    
    
    @io.swagger.annotations.ApiOperation(value = "löst eine Aktion aus", notes = "", response = Void.class, tags={ "action", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Ausführung übermittelt", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "AktionsID nicht gefunden", response = Void.class) })
    public Response doAction(@ApiParam(value = "",required=true) @QueryParam("actionId") Integer actionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.doAction(actionId,securityContext);
    }

}

