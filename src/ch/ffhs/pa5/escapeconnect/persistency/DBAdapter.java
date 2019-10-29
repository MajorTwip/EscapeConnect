package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.WebApplicationException;

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
		// TODO Auto-generated method stub
		
	}
}
