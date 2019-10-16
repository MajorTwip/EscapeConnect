package ch.ffhs.pa5.escapeconnect;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import ch.ffhs.pa5.escapeconnect.api.ActionApiService;
import ch.ffhs.pa5.escapeconnect.api.AdminApiService;
import ch.ffhs.pa5.escapeconnect.api.DeviceApiService;
import ch.ffhs.pa5.escapeconnect.api.PanelApiService;
import ch.ffhs.pa5.escapeconnect.api.SettingApiService;
import ch.ffhs.pa5.escapeconnect.handlers.ActionAPIimplement;
import ch.ffhs.pa5.escapeconnect.handlers.AdminAPIimplement;
import ch.ffhs.pa5.escapeconnect.handlers.DeviceAPIimplement;
import ch.ffhs.pa5.escapeconnect.handlers.PanelAPIimplement;
import ch.ffhs.pa5.escapeconnect.handlers.SettingAPIimplement;

public class EscapeConnect extends ResourceConfig{

		public EscapeConnect() {
			register(new AbstractBinder() {
				
				@Override
				protected void configure() {
					bind(PanelAPIimplement.class).to(PanelApiService.class);
					bind(ActionAPIimplement.class).to(ActionApiService.class);
					bind(AdminAPIimplement.class).to(AdminApiService.class);
					bind(DeviceAPIimplement.class).to(DeviceApiService.class);
					bind(SettingAPIimplement.class).to(SettingApiService.class);
				}
			});
		}
}
