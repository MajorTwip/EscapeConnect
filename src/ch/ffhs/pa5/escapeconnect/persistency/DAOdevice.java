package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.DeviceDAOBean;

public class DAOdevice implements DAOdeviceIF {
	@Override
	public  boolean write(DeviceDAOBean device) {
		String query = "INSERT INTO device (name,mac,basetopic,deviceid,supportsOTA,firmware_id) VALUES(?,?,?,?,?,?)" + 
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
			
			int firmwareid = device.getFirmwareid();
			if(firmwareid>0) {
				pstm.setInt(6, device.getFirmwareid());
				pstm.setInt(11, device.getFirmwareid());
			}else {
				pstm.setNull(6, java.sql.Types.INTEGER);
				pstm.setNull(11, java.sql.Types.INTEGER);
			}
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		return true;
	}

	@Override
	public  boolean delete(String mac) {
		String query = "DELETE FROM device WHERE mac=?;";
		
		//Connection con = DBAdapter.getConnection();
		try (Connection con = DBAdapter.getConnection();
				PreparedStatement pstm = con.prepareStatement(query);){
			pstm.setString(1, mac);
			pstm.executeUpdate();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		return true;
	}

	public DeviceDAOBean getByMac(String device_mac) {
		String query = "SELECT * FROM device WHERE mac = ?";
		// Create an empty DAOBean
		DeviceDAOBean device = new DeviceDAOBean();
		// Get the device
		try (Connection con = DBAdapter.getConnection();
				PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setString(1, device_mac);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and get the first line.
			if(rs.next() != false) {
				device.setMac(device_mac);
				device.setFirmwareid(rs.getInt("firmware_id"));
				device.setName(rs.getString("name"));
				device.setsupportsOTA(rs.getBoolean("supportsOTA"));
				device.setBasetopic(rs.getString("basetopic"));
				device.setDeviceid(rs.getString("deviceid"));
			}
			// Close the connection to the DB
			pstm.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the rs with the panel or empty.
		return device;			
	}
	
	public DeviceDAOBean getByDeviceID(String deviceId) {
		String query = "SELECT * FROM device WHERE deviceid = ?";
		// Create an empty DAOBean
		DeviceDAOBean device = new DeviceDAOBean();
		// Get the device
		try (Connection con = DBAdapter.getConnection();
				PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setString(1, deviceId);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and get the first line.
			if(rs.next() != false) {
				device.setMac(rs.getString("mac"));
				device.setFirmwareid(rs.getInt("firmware_id"));
				device.setName(rs.getString("name"));
				device.setsupportsOTA(rs.getBoolean("supportsOTA"));
				device.setBasetopic(rs.getString("basetopic"));
				device.setDeviceid(deviceId);
			}
			// Close the connection to the DB
			pstm.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the rs with the panel or empty.
		return device;			
	}
}
