package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;

import io.swagger.annotations.ApiParam;

import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.InlineResponse200;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataParam;
import java.io.File;
import java.nio.file.Files;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.*;

@Path("/device")
@io.swagger.annotations.Api(description = "the device API")
@javax.annotation.Generated(
    value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen",
    date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")
public class DeviceApi {

  private DeviceApiService delegate;

  protected DeviceApi() {}

  @javax.inject.Inject
  public DeviceApi(DeviceApiService delegate) {
    this.delegate = delegate;
  }

  @POST
  @Path("/add")
  @Consumes({"multipart/form-data"})
  @io.swagger.annotations.ApiOperation(
      value = "Hinzufügen eines neuen Gerätes mittels der JSON-Datei des Herstellers",
      notes = "",
      response = Void.class,
      tags = {
        "device",
      })
  @io.swagger.annotations.ApiResponses(
      value = {
        @io.swagger.annotations.ApiResponse(
            code = 200,
            message = "Erfolgreich importiert",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 401,
            message = "Token nicht mitgeliefert oder ungültig",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 415,
            message = "Datei hat unerlaubtes Format",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 418,
            message = "Datei konnte nicht gEEparsed werden",
            response = Void.class)
      })
  public Response addDevice(
      @FormDataParam("file") InputStream file,
      @FormDataParam("name") String name,
      @Context SecurityContext securityContext)
      throws NotFoundException {
    AddDeviceBody addDeviceBody = new AddDeviceBody();
    if (name != null && name.length() > 0) addDeviceBody.setName(name);
    if (file != null) {
      try {
        addDeviceBody.setFile(file.readAllBytes());
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Error receiving File")
            .build();
      }
    }
    return delegate.addDevice(addDeviceBody, securityContext);
  }

  @POST
  @Path("/delete")
  @io.swagger.annotations.ApiOperation(
      value = "Entfernen eines Gerätes",
      notes = "",
      response = Void.class,
      tags = {
        "device",
      })
  @io.swagger.annotations.ApiResponses(
      value = {
        @io.swagger.annotations.ApiResponse(
            code = 200,
            message = "Erfolgreich gelöscht",
            response = Void.class)
      })
  public Response deleteDevice(
      @ApiParam(value = "Id des devices welches upgedatet werden soll", required = true)
          @QueryParam("devicemac")
          String devicemac,
      @ApiParam(value = "Muss gesetzt werden, zusätzliche Sicherheit") @QueryParam("forces")
          Boolean forces,
      @Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.deleteDevice(devicemac, forces, securityContext);
  }

  @POST
  @Path("/upgrade")
  @Consumes({"multipart/form-data"})
  @Produces({"application/octet-stream"})
  @io.swagger.annotations.ApiOperation(
      value = "Firmwareupgrade eines Gerätes mittels der .bin Datei des Herstellers",
      notes = "",
      response = InlineResponse200.class,
      responseContainer = "List",
      tags = {
        "device",
      })
  @io.swagger.annotations.ApiResponses(
      value = {
        @io.swagger.annotations.ApiResponse(
            code = 200,
            message = "Erfolgreich update angestossen",
            response = InlineResponse200.class,
            responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(
            code = 401,
            message = "Token nicht mitgeliefert oder ungültig",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 403,
            message = "Firmwarenamen alt/neu stimmen nicht überein, forced?",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 404,
            message = "PanelId nicht verfügbar",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 415,
            message = "Datei hat unerlaubtes Format",
            response = Void.class),
        @io.swagger.annotations.ApiResponse(
            code = 418,
            message = "Datei konnte nicht geparsed werden",
            response = Void.class)
      })
  public Response upgradeFirmware(
      @ApiParam(value = "", required = true)  @FormDataParam("firmware") InputStream file,
      @ApiParam(value = "Id des devices welches upgedatet werden soll", required = true)
          @QueryParam("panelid")
          int panelid,
      @ApiParam(value = "Muss gesetzt werden, falls Firmware-name alt und neu nicht übereinstimmen")
          @QueryParam("forced")
          Boolean forced,
      @Context SecurityContext securityContext)
      throws NotFoundException {
	  UpdateDeviceBody updateDeviceBody = new UpdateDeviceBody();
	    if (file != null) {
	      try {
	    	  updateDeviceBody.setFirmware(file.readAllBytes());
	    	  file.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	            .entity("Error receiving File")
	            .build();
	      }  
	    }
    return delegate.upgradeFirmware(updateDeviceBody, panelid, forced, securityContext);
  }
}
