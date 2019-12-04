package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.bean.AddDeviceBody;
import ch.ffhs.pa5.escapeconnect.bean.UpdateDeviceBody;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(
    value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen",
    date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")
public interface DeviceApiService {

  public Response addDevice(AddDeviceBody addDeviceBody, SecurityContext securityContext);

  public Response deleteDevice(
      @NotNull String devicemac, Boolean forces, SecurityContext securityContext);

  public Response upgradeFirmware(
      UpdateDeviceBody body,
      @NotNull String deviceid,
      Boolean forces,
      SecurityContext securityContext);
}
