package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.api.SettingApiService;

import io.swagger.annotations.ApiParam;

import ch.ffhs.pa5.escapeconnect.bean.Setting;
import ch.ffhs.pa5.escapeconnect.bean.SettingMod;


import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;


@Path("/setting")


@io.swagger.annotations.Api(description = "the setting API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public class SettingApi  {

   private SettingApiService delegate;

   protected SettingApi() {
   }

   @javax.inject.Inject
   public SettingApi(SettingApiService delegate) {
      this.delegate = delegate;
   }


    @GET
    @Path("/get")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Abfrage der Einstellungen eines Gerätes", notes = "", response = Setting.class, responseContainer = "List", tags={ "setting", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Liste der Einstellungen", response = Setting.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Token nicht mitgeliefert oder ungültig", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "DeviceId nicht verfügbar", response = Void.class) })
    public Response getSettingsByDeviceId(@ApiParam(value = "") @QueryParam("deviceId") Integer deviceId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSettingsByDeviceId(deviceId,securityContext);
    }

    @POST
    @Path("/set")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Setzen der Einstellungen eines Gerätes", notes = "", response = Void.class, tags={ "setting", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Token nicht mitgeliefert oder ungültig", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "DeviceId nicht verfügbar", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Eine Einstellung enthielt Ungütligen Wert", response = Integer.class, responseContainer = "List") })
    public Response setSetting(@ApiParam(value = "" ) List<SettingMod> body
,@ApiParam(value = "") @QueryParam("deviceId") Integer deviceId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.setSetting(body,deviceId,securityContext);
    }

}

