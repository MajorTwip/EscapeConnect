package ch.ffhs.pa5.escapeconnect.persistency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;
import org.junit.jupiter.api.MethodOrderer;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestDeviceDAO {

	@InjectMocks
	DAOdevice daodevice;

	@Mock
	DBAdapter dba;
	
	@BeforeAll
	public static void cleanDB() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:/data/test.db");
				Statement stm = con.createStatement();){
			stm.executeUpdate(
					"DROP TABLE IF EXISTS \"device\";" +
				" CREATE TABLE \"device\"(" +
				"  \"mac\" VARCHAR(12) PRIMARY KEY NOT NULL," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"basetopic\" VARCHAR(45)," +
				"  \"deviceid\" VARCHAR(45)," +
				"  \"supportsOTA\" INTEGER DEFAULT 0," +
				"  \"firmware_id\" INTEGER);");
			System.out.println("devicetable created");
			stm.close();
			con.close();
		};
	}

	@Test
	@Order(1)
	public void write() throws NamingException, SQLException {

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		DeviceDAOBean ddb = new DeviceDAOBean();
		ddb.setMac("1234567890ab");
		ddb.setName("Name");
		
		daodevice.write(ddb);
		
		ddb.setMac("1234567890ac");
		ddb.setFirmwareid(1);
		daodevice.write(ddb);
		System.out.println("Devices written");
	}
	
	
	@Test
	@Order(2)
	public void delete(){

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		daodevice.delete("1234567890ac");
		System.out.println("Device 1234567890ac deleted");

	}
	
	@Test
	@Order(3)
	public void get() {
		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		DeviceDAOBean dev = daodevice.getByMac("1234567890ab");
		assertEquals("Name", dev.getName());
		System.out.println("Device 1234567890ab checked");
		
		dev = daodevice.getByMac("1234567890ad"); //not existing
		assertEquals(null, dev.getName());
	}
	
	

}
