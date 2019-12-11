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

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;

import org.junit.jupiter.api.MethodOrderer;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestActionDAO {

	@InjectMocks
	DAOaction daoaction;

	@Mock
	DBAdapter dba;
	
	@BeforeAll
	public static void cleanDB() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:/data/test.db");
				Statement stm = con.createStatement();){
			stm.executeUpdate(
					"DROP TABLE IF EXISTS \"action\";" +
					"CREATE TABLE IF NOT EXISTS \"action\"(" +
					"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
					"  \"panel_id\" INTEGER," +
					"  \"label\" VARCHAR(45) NOT NULL," +
					"  \"payload\" VARCHAR(45) NOT NULL," +
					"  \"topic\" VARCHAR(45) NOT NULL);");
			System.out.println("actiontable created");
			stm.close();
			con.close();
		};
	}

	@Test
	@Order(1)
	public void write() throws NamingException, SQLException {

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		ActionDAOBean adb = new ActionDAOBean();
		adb.setLabel("Label");
		adb.setPayload("PayLoad");
		adb.setSubtopic("subtopic");
		
		daoaction.write(adb);

		adb.setLabel("Label2");
		adb.setPayload("PayLoad2");
		adb.setSubtopic("subtopic2");
		
		int id = daoaction.write(adb);

		System.out.println("actions written, second was nr " + id);
		
	}
	
	
	@Test
	@Order(2)
	public void update(){

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		ActionDAOBean adb = new ActionDAOBean();
		adb.setId(1);
		adb.setLabel("Label3");
		adb.setPayload("PayLoad3");
		adb.setSubtopic("subtopic3");
		adb.setPanel_id(21);

		daoaction.write(adb);//updated action 1
		
	}
	
	@Test
	@Order(3)
	public void getByPanelId() {
		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		List<ActionDAOBean> actions = daoaction.getActionByPanelID(21);
		assertEquals(1, actions.size(), "List should be 1 long (Action with label Label3)");
		assertEquals("Label3", actions.get(0).getLabel(), "If not Label3 possible error on update");
		
	}
	
	

}
