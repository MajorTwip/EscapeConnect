package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.WebApplicationException;
import javax.xml.crypto.Data;

public class DBAdapter {

	public static Connection getConnectio() throws WebApplicationException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/escapeconnect");
			return ds.getConnection();
		} catch (NamingException e) {
			throw new WebApplicationException(e.getMessage());
		} catch (SQLException e) {
			throw new WebApplicationException(e.getMessage());
		}		
	}

	public static void createDBifNone() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/escapeconnect");
			try (Connection con = ds.getConnection();
					Statement stmt = con.createStatement()){



				 // BuildMyString.com generated code. Please enjoy your string responsibly.

				String query = 
				"BEGIN;" +
				"CREATE TABLE IF NOT EXISTS \"ecsettings\"(" +
				"  \"adminpass\" VARCHAR(45) NOT NULL," +
				"  \"mqtturl\" VARCHAR(45) NOT NULL," +
				"  \"mqttport\" INTEGER NOT NULL DEFAULT 1883," +
				"  \"mqttuser\" VARCHAR(45)," +
				"  \"mqttpass\" VARCHAR(45)" +
				");" +
				"CREATE TABLE IF NOT EXISTS \"firmware\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"version\" VARCHAR(45) NOT NULL," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"file\" BLOB NOT NULL" +
				");" +
				"CREATE TABLE IF NOT EXISTS \"device\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"mac\" VARCHAR(12) NOT NULL," +
				"  \"basetopic\" VARCHAR(45) NOT NULL," +
				"  \"deviceid\" VARCHAR(45) NOT NULL," +
				"  \"supportsOTA\" INTEGER NOT NULL DEFAULT 0," +
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
				"  \"device_id\" INTEGER," +
				"  \"name\" VARCHAR(45)," +
				"  CONSTRAINT \"device_id\"" +
				"    FOREIGN KEY(\"device_id\")" +
				"    REFERENCES \"device\"(\"id\")" +
				"    ON DELETE CASCADE" +
				"    ON UPDATE CASCADE" +
				");" +
				"CREATE INDEX IF NOT EXISTS \"panel.device_id_idx\" ON \"panel\" (\"device_id\");" +
				"CREATE TABLE IF NOT EXISTS \"value\"(" +
				"  \"id\" INTEGER PRIMARY KEY NOT NULL," +
				"  \"panel_id\" INTEGER," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"topic\" VARCHAR(45) NOT NULL," +
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
				"  \"device_id\" INTEGER NOT NULL," +
				"  \"panel_id\" INTEGER," +
				"  \"label\" VARCHAR(45) NOT NULL," +
				"  \"value\" VARCHAR(45)," +
				"  \"name\" VARCHAR(45) NOT NULL," +
				"  \"type\" VARCHAR(45) NOT NULL," +
				"  \"min\" FLOAT," +
				"  \"max\" FLOAT," +
				"  CONSTRAINT \"setting_device_id\"" +
				"    FOREIGN KEY(\"device_id\")" +
				"    REFERENCES \"device\"(\"id\")" +
				"    ON DELETE CASCADE" +
				"    ON UPDATE CASCADE" +
				");" +
				"CREATE INDEX IF NOT EXISTS \"setting.setting_device_id_idx\" ON \"setting\" (\"device_id\");" +
				"COMMIT;";


					stmt.executeUpdate(query);
				} catch (SQLException e) {
					throw new WebApplicationException(e.getMessage());
				}
		} catch (NamingException e1) {
			throw new WebApplicationException(e1.getMessage());
		}
		
	}
}
