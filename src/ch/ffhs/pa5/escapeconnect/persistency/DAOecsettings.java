package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;

public class DAOecsettings implements DAOecsettingsIF{

	@Override
	public EcSettings get() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getpassword() {
		Connection con = DBAdapter.getConnection();
		Statement selectStmt = null;
		try {
				selectStmt = con.createStatement();
				ResultSet rs = selectStmt.executeQuery("select adminpass from ecsettings");
				return rs.getString(1);
		} catch (Exception e) {
		    System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			throw new WebApplicationException(e.getMessage());
		}
		return null;
	}
	public static void write(EcSettings settings) {
		
		String query = "UPDATE ecsettings SET adminpass = ?, mqtturl = ?, mqttport = ?, mqttuser = ?, mqttpass = ?";
		
		Connection con = DBAdapter.getConnection();
		try (PreparedStatement pstm = con.prepareStatement(query);){
			pstm.setString(1, settings.getPassword());
			String mqtturl[] = settings.getMqttUrl().split(":");
			pstm.setString(2, mqtturl[0]);
			if(mqtturl.length>1) {
				pstm.setInt(3, Integer.valueOf(mqtturl[1]));
			}
			pstm.setString(4, settings.getMqttName());
			pstm.setString(5, settings.getMqttPass());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			throw new WebApplicationException(e.getMessage());
		}
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
