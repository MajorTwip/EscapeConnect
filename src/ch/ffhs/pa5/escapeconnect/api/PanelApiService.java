package ch.ffhs.pa5.escapeconnect.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public interface PanelApiService {
    
    public Response getPanes(SecurityContext securityContext);
    
    public Response swapPanes( @NotNull Integer pid1, @NotNull Integer pid2,SecurityContext securityContext);
    
}

