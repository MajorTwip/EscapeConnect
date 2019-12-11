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

import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;

import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestPanelDAO {

	@InjectMocks
	DAOpanel daopanel;

	@Mock
	DBAdapter dba;

	@BeforeAll
	public static void cleanDB() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:/data/test.db");
				Statement stm = con.createStatement();){
			stm.executeUpdate(
					"DROP TABLE IF EXISTS \"panel\";" +
					"CREATE TABLE IF NOT EXISTS \"panel\"(" +
					"  \"id\" INTEGER PRIMARY KEY NOT NULL," + 
					"  \"device_mac\" VARCHAR(12) NOT NULL," +
					"  \"name\" VARCHAR(45));");
			System.out.println("paneltable created");
			stm.close();
			con.close();
		};
	}

	@Test
	@Order(1)
	public void write() throws NamingException, SQLException {

		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		PanelDAOBean pdb = new PanelDAOBean();
		pdb.setDevice_mac("1234567890ab");
		pdb.setName("Name");

		assertEquals(1,daopanel.write(pdb),"should be Panel Id 1");

		pdb.setDevice_mac("1234567890ac");
		pdb.setName("Name2");

		assertEquals(2,daopanel.write(pdb),"should be Panel Id 2");

	}

	@Test
	@Order(2)
	public void update() {

		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		PanelDAOBean pdb = new PanelDAOBean();
		pdb.setDevice_mac("1234567890ab");
		pdb.setName("Name3");
		pdb.setId(2);

		daopanel.write(pdb);// updated panel 2

	}

	@Test
	@Order(3)
	public void getByPanelId() {
		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		PanelDAOBean panel = daopanel.getById(2);
		assertEquals("Name3", panel.getName(), "If not Label3 possible error on update");
	}
	
	@Test
	@Order(4)
	public void getAll() {
		Mockito.when(dba.getConnection())
				.thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		List<PanelDAOBean> panels = daopanel.getAllPanels();
		assertEquals(2, panels.size());
	}

}
