package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.bean.Body2;
import ch.ffhs.pa5.escapeconnect.bean.Setup;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public interface AdminApiService {
    
    public Response doLogin(Body2 body,SecurityContext securityContext);
    
    public Response setup(Setup body,SecurityContext securityContext);
    
}

