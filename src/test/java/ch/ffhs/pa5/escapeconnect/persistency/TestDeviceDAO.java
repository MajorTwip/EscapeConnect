package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;

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
	public void write() throws NamingException, SQLException {
        MockitoAnnotations.initMocks(this);

		Mockito.when(dba.getConnection()).thenReturn(DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		DeviceDAOBean ddb = new DeviceDAOBean();
		ddb.setMac("1234567890ab");
		ddb.setName("Name");
		
		daodevice.write(ddb);
		
		ddb.setMac("1234567890ac");
		ddb.setFirmwareid(1);
		daodevice.write(ddb);

		
	}

}
