package ch.ffhs.pa5.escapeconnect;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import ch.ffhs.pa5.escapeconnect.api.PanelApiService;
import ch.ffhs.pa5.escapeconnect.handlers.PanelAPIimplement;

public class EscapeConnect extends ResourceConfig{

		public EscapeConnect() {
			register(new AbstractBinder() {
				
				@Override
				protected void configure() {
					bind(PanelAPIimplement.class).to(PanelApiService.class);
				}
			});
		}
}
