package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.ffhs.pa5.escapeconnect.bean.Setup;

@ExtendWith(MockitoExtension.class)
public class DBinit {

	@InjectMocks
	DBAdapter dba;

	@Mock
	Context ctx;

	@Test
	public void initDB() throws NamingException, SQLException {
        MockitoAnnotations.initMocks(this);

		Setup body = new Setup();
		body.setAdminpass("123");
		body.setMqtturl("url");
		body.setMqttuser("MQTTUSER");
		body.setMqttpass("MQTTPASS");

		DataSource ds = new TestDataSource();
		Mockito.when(ctx.lookup("java:/comp/env/jdbc/escapeconnect")).thenReturn(ds);

		dba.createDBifNone();

	}

}
