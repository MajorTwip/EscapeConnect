package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;

public class DAOsettings {

	public static int write(SettingDAOBean setting) {

		String query = "";

		if (setting.getId() < 1) {
			query = "INSERT INTO setting (device_mac,panel_id,label,value,name,type,min,max) VALUES(?,?,?,?,?,?,?,?)";

			try (Connection con = DBAdapter.getConnection(); PreparedStatement pstm = con.prepareStatement(query);) {
				pstm.setString(1, setting.getDevice_mac());
				pstm.setInt(2, setting.getPanel_id());
				pstm.setString(3, setting.getLabel());
				pstm.setString(4, setting.getValue());
				pstm.setString(5, setting.getName());
				pstm.setString(6, setting.getType());
				pstm.setInt(7, setting.getMin());
				pstm.setInt(8, setting.getMax());

				int rowsAffected = pstm.executeUpdate();
				if (rowsAffected == 1) {
					ResultSet rs = pstm.getGeneratedKeys();
					if (rs.next()) {
						return rs.getInt(1);
					}
				}
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WebApplicationException(e.getMessage());
			}
		} else {
			query = "UPDATE setting SET device_mac = ?,panel_id = ?,label = ?,value = ?,name = ?,type = ?,min = ?,max = ? WHERE id = ?";

			try (Connection con = DBAdapter.getConnection(); PreparedStatement pstm = con.prepareStatement(query);) {
				pstm.setString(1, setting.getDevice_mac());
				pstm.setInt(2, setting.getPanel_id());
				pstm.setString(3, setting.getLabel());
				pstm.setString(4, setting.getValue());
				pstm.setString(5, setting.getName());
				pstm.setString(6, setting.getType());
				pstm.setInt(7, setting.getMin());
				pstm.setInt(8, setting.getMax());
				pstm.setInt(9, setting.getId());
				pstm.executeUpdate();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WebApplicationException(e.getMessage());
			}
		}
		return -1;
	}
}
