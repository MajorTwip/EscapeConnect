package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;

public class DAOdevice {
	public static void write(DeviceDAOBean device) {
		String query = "INSERT INTO device VALUES(?,?,?,?,?,?)" + 
				"  ON CONFLICT(mac) DO UPDATE SET name=?, basetopic=?, deviceid=?, supportsOTA=?,firmware_id=? WHERE mac=?;";
		
		//Connection con = DBAdapter.getConnection();
		try (Connection con = DBAdapter.getConnection();
				PreparedStatement pstm = con.prepareStatement(query);){
			pstm.setString(1, device.getName());
			pstm.setString(7, device.getName());
			
			pstm.setString(2, device.getMac());
			pstm.setString(12, device.getMac());
			

			pstm.setString(3, device.getBasetopic());
			pstm.setString(8, device.getBasetopic());
			
			pstm.setString(4, device.getDeviceid());
			pstm.setString(9, device.getDeviceid());
			
			pstm.setBoolean(5, device.issupportsOTA());
			pstm.setBoolean(10, device.issupportsOTA());
			
			pstm.setInt(6, device.getFirmwareid());
			pstm.setInt(11, device.getFirmwareid());

			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
	}
}
