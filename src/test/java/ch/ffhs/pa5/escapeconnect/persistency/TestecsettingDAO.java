package ch.ffhs.pa5.escapeconnect.persistency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;

import org.junit.jupiter.api.MethodOrderer;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestecsettingDAO {

	@InjectMocks
	DAOecsettings daosetting;

	@Mock
	DBAdapter dba;
	
	@BeforeAll
	public static void cleanDB() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:/data/test.db");
				Statement stm = con.createStatement();){
			stm.executeUpdate(
					"DROP TABLE IF EXISTS \"ecsettings\";" +
					"CREATE TABLE IF NOT EXISTS \"ecsettings\"(" +
					"  \"adminpass\" VARCHAR(45) NOT NULL," +
					"  \"mqtturl\" VARCHAR(45) NOT NULL," +
					"  \"mqttport\" INTEGER NOT NULL DEFAULT 1883," +
					"  \"mqttuser\" VARCHAR(45)," +
					"  \"mqttpass\" VARCHAR(45)" +
					");" + 
			"INSERT INTO ecsettings (adminpass,mqtturl) VALUES(\"1234\",\"mqtt.comstock,ch\");");
			System.out.println("valuetable created");
			stm.close();
			con.close();
		};
	}

	@Test
	@Order(1)
	public void write() throws NamingException, SQLException {

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		EcSettings setting = new EcSettings();
		setting.setMqttName("mqttName");
		setting.setMqttPass("mqttPass");
		setting.setMqttUrl("mqttUrl:1234");
		setting.setPassword("password");
		
		daosetting.write(setting);
	}
	

	
	@Test
	@Order(2)
	public void get() {
		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		EcSettings setting = daosetting.get();
		assertEquals("mqttName",setting.getMqttName());
		assertEquals("mqttPass",setting.getMqttPass());
		assertEquals("tcp://mqttUrl:1234",setting.getMqttUrl());
		assertEquals("password",setting.getPassword());
		
	}
	
	

}
