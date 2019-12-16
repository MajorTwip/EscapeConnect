/** Code has been formated */
/** @author Yvo von Kaenel and Javier Garcia */
package ch.ffhs.pa5.escapeconnect.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ch.ffhs.pa5.escapeconnect.api.AdminApiService;
import ch.ffhs.pa5.escapeconnect.bean.LoginBody;
import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.Setup;
import ch.ffhs.pa5.escapeconnect.persistency.DAOecsettings;
import ch.ffhs.pa5.escapeconnect.persistency.DBAdapter;

public class AdminAPIimplement implements AdminApiService {

  DAOecsettings daoecsettings = new DAOecsettings();
  DBAdapter dba = new DBAdapter();

  @Override
  public Response doLogin(LoginBody body, SecurityContext securityContext) {
    if (body == null) {
      System.out.println("Got no info from post request");
      return Response.status(Response.Status.CONFLICT)
          .entity("No information in Body. Please read API-Docs")
          .build();
    }
    if (body.getPasshash() == "") {
      System.out.println("Got empty login request");
      return Response.status(Response.Status.CONFLICT).entity("No password entered").build();
    }
    if (body.getPasshash().equals(daoecsettings.get().getPassword())) {
      System.out.println("Acess granted");
      return Response.status(Response.Status.OK).entity("Acess granted").build();
    }
    System.out.println("Acess denied");
    return Response.status(Response.Status.UNAUTHORIZED).entity("Acess denied").build();
  }

  @Override
  public Response setup(Setup body, SecurityContext securityContext) {
    if (body == null) {
      return Response.status(Response.Status.CONFLICT)
          .entity("No information in Body. Please read API-Docs")
          .build();
    }
    System.out.println(
        String.format(
            "Got setup-request:\nPassword:  %s\nMQTT-URL:  %s\nName:pass:  %s:%s",
            body.getAdminpass(), body.getMqtturl(), body.getMqttuser(), body.getMqttpass()));

    // If Databasefile is not allready created, do so
    dba.createDBifNone();

    // Read given settings and write them to DB
    String mqtturl = body.getMqtturl().trim().toLowerCase();
    EcSettings settings = new EcSettings(body.getAdminpass(), mqtturl);
    if (body.getMqttuser() != null && body.getMqttpass() != null) {
      settings.setMqttName(body.getMqttuser());
      settings.setMqttPass(body.getMqttpass());
    }
    daoecsettings.write(settings);
    return Response.status(Response.Status.OK).build();
  }
}
