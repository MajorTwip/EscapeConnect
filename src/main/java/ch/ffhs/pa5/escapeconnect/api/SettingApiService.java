package ch.ffhs.pa5.escapeconnect.api;

import ch.ffhs.pa5.escapeconnect.bean.SettingMod;


import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyDIServerCodegen", date = "2019-10-16T16:22:47.544870800+02:00[Europe/Berlin]")

public interface SettingApiService {
    
    public Response getSettingsByPanelId( Integer panelId,SecurityContext securityContext);
    
    public Response setSetting(List<SettingMod> body,SecurityContext securityContext);
    
}

