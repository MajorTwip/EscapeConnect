package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.bean.*;
import ch.ffhs.pa5.escapeconnect.api.PanelApiService;

import io.swagger.annotations.ApiParam;

import ch.ffhs.pa5.escapeconnect.bean.Panel;


import java.util.Map;
import java.util.List;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

import javax.validation.constraints.*;


@Path("/panel")


@io.swagger.annotations.Api(description = "the panel API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public class PanelApi  {

   private PanelApiService delegate;

   protected PanelApi() {
   }

   @javax.inject.Inject
   public PanelApi(PanelApiService delegate) {
      this.delegate = delegate;
   }


    @GET
    @Path("/getAll")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Erhalt einer Liste der konfigurierten panels", notes = "", response = Panel.class, responseContainer = "List", tags={ "panel", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Lister der Panels", response = Panel.class, responseContainer = "List") })
    public Response getPanes(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getPanes(securityContext);
    }

    @POST
    @Path("/swap")
    
    @Produces({ "application/JSON" })
    @io.swagger.annotations.ApiOperation(value = "tauscht zwei Panele auf dem Dashboard", notes = "", response = Panel.class, responseContainer = "List", tags={ "panel", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Liste der modifizierten Panels", response = Panel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Mindestens einer der Parameter war keine gültige PanelID", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Token nicht mitgeliefert oder ungültig", response = Void.class) })
    public Response swapPanes(@ApiParam(value = "Ein Panel welches mit einem anderen ausgetauscht werden soll",required=true) @QueryParam("pid1") Integer pid1
,@ApiParam(value = "Das andere Panel",required=true) @QueryParam("pid2") Integer pid2
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.swapPanes(pid1,pid2,securityContext);
    }

}

