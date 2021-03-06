package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.WebApplicationException;

import org.sqlite.SQLiteConfig;

/**
 * Manages the connection to the DB
 * SQLite by Xerial
 * @author Yvo von Känel
 *
 */
public class DBAdapter {
	
	Context ctx;

	/**
	 * Gets Connection to the DB
	 * @return Connection
	 * @throws WebApplicationException Wrapped expection
 	 */
	public Connection getConnection() throws WebApplicationException {
		try {
			if(ctx==null)ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/escapeconnect");
			Connection con = ds.getConnection();
			Statement st = con.createStatement();
			st.execute("PRAGMA foreign_keys=on;");
			st.close();
			return con;
		} catch (NamingException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}		
	}
		
	/**
	 * Initializes DB if not already existing.
	 * Implements Schema given in /schema
	 */
	public void createDBifNone() {
		System.out.println("Creating DB");
		try {
			if(ctx==null)ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/escapeconnect");
			try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement()){
				DatabaseMetaData md = con.getMetaData();
		        ResultSet rs = md.getTables(null, null, "ecsettings", null);
		        if(rs.next()) return; //If table allready exists, stop creation
		        
		        
				 // BuildMyString.com generated code. Please enjoy your string responsibly.

				String query = 
				"PRAGMA foreign_keys=on;" + 
				"BEGIN;" +
				"CREATE TABLE IF NOT EXISTS \"ecsettings\"(" +
				"  \"adminpass\" VARCHAR(45) NOT NULL," +
				"  \"mqtturl\" VARCHAR(45) NOT NULL," +
				"  \"mqttport\" INTEGER NOT NULL DEFAULT 1883," +
				"  \"mqttuser\" VARCHAR(45)," +
				"  \"mqttpass\" VARCHAR(45)" +
				");" +
				"INSERT INTO ecsettings (adminpass,mqtturl) VALUES(\"1234\",\"mqtt.comstock,ch\");" +
				"CREATE TABLE IF NOT EXISTS \"firmware\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"version\" VARCHAR(45) NOT NULL," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"file\" BLOB NOT NULL" +
				");" +
				"CREATE TABLE IF NOT EXISTS \"device\"(" +
				"  \"mac\" VARCHAR(12) PRIMARY KEY NOT NULL," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"basetopic\" VARCHAR(45)," +
				"  \"deviceid\" VARCHAR(45)," +
				"  \"supportsOTA\" INTEGER DEFAULT 0," +
				"  \"firmware_id\" INTEGER," +
				"  CONSTRAINT \"device_firmware_id\"" +
				"    FOREIGN KEY(\"firmware_id\")" +
				"    REFERENCES \"firmware\"(\"id\")" +
				"    ON DELETE SET NULL" +
				"    ON UPDATE CASCADE" +
				");" +
				"CREATE INDEX IF NOT EXISTS \"device.device_firmware_id_idx\" ON \"device\" (\"firmware_id\");" +
				"CREATE TABLE IF NOT EXISTS \"panel\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," + 
				"  \"device_mac\" VARCHAR(12) NOT NULL," +
				"  \"name\" VARCHAR(45)," +
				"CONSTRAINT \"fk_panel_device1\"" +
				"  FOREIGN KEY(\"device_mac\")" +
				"  REFERENCES \"device\"(\"mac\")" +
				"    ON DELETE CASCADE" +
				"    ON UPDATE CASCADE" +
				");" + 
				"CREATE INDEX \"panel.fk_panel_device1_idx\" ON \"panel\" (\"device_mac\"); " +
				"CREATE TABLE IF NOT EXISTS \"value\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"panel_id\" INTEGER," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"topic\" VARCHAR(45) NOT NULL," +
				"  \"type\" VARCHAR(45) NOT NULL," +				
				"  CONSTRAINT \"value_panel_id\"" +
				"    FOREIGN KEY(\"panel_id\")" +
				"    REFERENCES \"panel\"(\"id\")" +
				"    ON DELETE CASCADE" +
				"    ON UPDATE CASCADE" +
				");" +
				"CREATE INDEX IF NOT EXISTS \"value.action_panel_id_idx\" ON \"value\" (\"panel_id\");" +
				"CREATE TABLE IF NOT EXISTS \"action\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"panel_id\" INTEGER," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"payload\" VARCHAR(45) NOT NULL," +
				"  \"topic\" VARCHAR(45) NOT NULL," +
				"  CONSTRAINT \"action_panel_id\"" +
				"    FOREIGN KEY(\"panel_id\")" +
				"    REFERENCES \"panel\"(\"id\")" +
				"    ON DELETE CASCADE" +
				"    ON UPDATE CASCADE" +
				");" +
				"CREATE INDEX IF NOT EXISTS \"action.action_panel_id_idx\" ON \"action\" (\"panel_id\");" +
				"CREATE TABLE IF NOT EXISTS \"setting\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"device_mac\" VARCHAR(12) NOT NULL," +
				"  \"panel_id\" INTEGER," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"value\" VARCHAR(45)," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"type\" VARCHAR(45) NOT NULL," +
				"  \"min\" FLOAT," +
				"  \"max\" FLOAT," +
				"  CONSTRAINT \"setting_device_mac\"" +
				"    FOREIGN KEY(\"device_mac\")" +
				"    REFERENCES \"device\"(\"mac\")" +
				"    ON DELETE CASCADE" +
				"    ON UPDATE CASCADE" +
				");" +
				"COMMIT;";


					stmt.executeUpdate(query);
					con.close();
					stmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new WebApplicationException(e.getMessage());
				}
		} catch (NamingException e1) {
			e1.printStackTrace();
			throw new WebApplicationException(e1.getMessage());
		}
		
	}
}
