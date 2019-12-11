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

import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;

import org.junit.jupiter.api.MethodOrderer;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestValueDAO {

	@InjectMocks
	DAOvalue daovalue;

	@Mock
	DBAdapter dba;
	
	@BeforeAll
	public static void cleanDB() throws SQLException {
		try(Connection con = DriverManager.getConnection("jdbc:sqlite:/data/test.db");
				Statement stm = con.createStatement();){
			stm.executeUpdate(
					"DROP TABLE IF EXISTS \"value\";" +
					"CREATE TABLE IF NOT EXISTS \"value\"(" +
					"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
					"  \"panel_id\" INTEGER," +
					"  \"label\" VARCHAR(45) NOT NULL," +
					"  \"topic\" VARCHAR(45) NOT NULL," +
					"  \"type\" VARCHAR(45) NOT NULL);");
			System.out.println("valuetable created");
			stm.close();
			con.close();
		};
	}

	@Test
	@Order(1)
	public void write() throws NamingException, SQLException {

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		ValueDAOBean vdb = new ValueDAOBean();
		vdb.setLabel("Label");
		vdb.setSubtopic("topic");
		vdb.setType("string");
		vdb.setPanel_id(2);
		
		daovalue.write(vdb);

		vdb.setLabel("Label2");
		vdb.setSubtopic("subtopic2");
		
		int id = daovalue.write(vdb);

		System.out.println("values written, second was nr " + id);
		
	}
	
	
	@Test
	@Order(2)
	public void update(){

		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));
		
		ValueDAOBean vdb = new ValueDAOBean();
		vdb.setId(1);
		vdb.setLabel("Label3");
		vdb.setSubtopic("topic");
		vdb.setType("string");
		vdb.setPanel_id(3);

		daovalue.write(vdb);//updated value 1
		
	}
	
	@Test
	@Order(3)
	public void getByPanelId() {
		Mockito.when(dba.getConnection()).thenAnswer(invocation -> DriverManager.getConnection("jdbc:sqlite:/data/test.db"));

		List<ValueDAOBean> values = daovalue.getValuesByPanelID(3);
		assertEquals(1, values.size(), "List should be 1 long");
		assertEquals("Label3", values.get(0).getLabel(), "If not Label3 possible error on update");
		
	}
	
	

}
