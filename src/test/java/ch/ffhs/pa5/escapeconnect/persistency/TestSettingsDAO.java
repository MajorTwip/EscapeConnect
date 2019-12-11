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

import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;

import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestSettingsDAO {

	@InjectMocks
	DAOsettings daosettings;

	@Mock
	DBAdapter dba;

	@BeforeAll
	public static void cleanDB() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:/data/test.db");
				Statement stm = con.createStatement();){
			stm.executeUpdate(
					"DROP TABLE IF EXISTS \"setting\";" +
					"CREATE TABLE IF NOT EXISTS \"setting\"(" +
					"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
					"  \"device_mac\" VARCHAR(12) NOT NULL," +
					"  \"panel_id\" INTEGER," +
					"  \"label\" VARCHAR(45) NOT NULL," +
					"  \"value\" VARCHAR(45)," +
					"  \"name\" VARCHAR(45) NOT NULL," +
					"  \"type\" VARCHAR(45) NOT NULL," +
					"  \"min\" FLOAT," +
					"  \"max\" FLOAT);");
			System.out.println("settingstable created");
			stm.close();
			con.close();
		};
	}

	@Test
	@Order(1)
	public void write() throws NamingException, SQLException {

		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		SettingDAOBean sdb = new SettingDAOBean();
		sdb.setDevice_mac("1234567890ab");
		sdb.setLabel("Label");
		sdb.setName("Name");
		sdb.setType("string");
		sdb.setPanel_id(1);

		assertEquals(1,daosettings.write(sdb),"should be settings Id 1");

		sdb.setDevice_mac("1234567890ab");
		sdb.setLabel("Label2");
		sdb.setName("Name2");

		assertEquals(2,daosettings.write(sdb),"should be settings Id 2");

	}

	@Test
	@Order(2)
	public void update() {

		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		SettingDAOBean sdb = new SettingDAOBean();
		sdb.setDevice_mac("1234567890ab");
		sdb.setLabel("Label");
		sdb.setName("Name");
		sdb.setType("string");
		sdb.setValue("23");
		sdb.setId(2);
		sdb.setPanel_id(1);

		daosettings.write(sdb);// updated settings 2

	}

	@Test
	@Order(3)
	public void getBysettingsPanelId() {
		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		List<SettingDAOBean> settings = daosettings.getSettingsByPanelId(1);
		assertEquals(2, settings.size());
	}
	

}
