package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.SettingDAOBean;

public class DAOsettings implements DAOsettingIF {
	DBAdapter dba = new DBAdapter();

	@Override
	public int write(SettingDAOBean setting) {

		String query = "";

		if (setting.getId() < 1) {
			query = "INSERT INTO setting (device_mac,panel_id,label,value,name,type,min,max) VALUES(?,?,?,?,?,?,?,?)";

			try (Connection con = dba.getConnection(); PreparedStatement pstm = con.prepareStatement(query);) {
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

			try (Connection con = dba.getConnection(); PreparedStatement pstm = con.prepareStatement(query);) {
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

	public List<SettingDAOBean> getSettingsByPanelId(Integer panelId) {
		String query = "SELECT * FROM setting WHERE panel_id = ?";
		// We use an ArrayList so that TomCat can easily convert it to a JSON.
		List<SettingDAOBean> settings = new LinkedList<>();
		try (Connection con = dba.getConnection(); PreparedStatement pstm = con.prepareStatement(query)) {
			pstm.setInt(1, panelId);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and iterate through it in order to create the beans
			// "Values"
			while (rs.next()) {
				SettingDAOBean set = new SettingDAOBean();
				set.setId(rs.getInt("id"));
				set.setDevice_mac(rs.getString("device_mac"));
				set.setPanel_id(panelId);
				set.setLabel(rs.getString("label"));
				set.setValue(rs.getString("value"));
				set.setMax(rs.getInt("max"));
				set.setMin(rs.getInt("min"));
				set.setName(rs.getString("name"));
				set.setType(rs.getString("type"));
				settings.add(set);
			}
			// Close the connection to the DB
			pstm.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the list of actions or empty.
		return settings;
	}

	public SettingDAOBean getSettingById(@NotNull Integer id) {
		String query = "SELECT * FROM setting WHERE id = ?";
		SettingDAOBean set = null;
		try (Connection con = dba.getConnection(); PreparedStatement pstm = con.prepareStatement(query)) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and iterate through it in order to create the bean
			// "Setting"
			if (rs.next()) {
				set = new SettingDAOBean();
				set.setId(rs.getInt("id"));
				set.setDevice_mac(rs.getString("device_mac"));
				set.setPanel_id(rs.getInt("panel_id"));
				set.setLabel(rs.getString("label"));
				set.setValue(rs.getString("value"));
				set.setMax(rs.getInt("max"));
				set.setMin(rs.getInt("min"));
				set.setName(rs.getString("name"));
				set.setType(rs.getString("type"));
			}
			// Close the connection to the DB
			pstm.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return setting or null.
		return set;
	}
}
