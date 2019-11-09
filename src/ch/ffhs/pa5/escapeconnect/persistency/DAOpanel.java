package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;

public class DAOpanel {
	
	public int write(PanelDAOBean panel) {
		
		String query = "";
		
		if(panel.getId()<1) {
			query = "INSERT INTO panel (name,device_mac) VALUES(?,?)";
			
			try (Connection con = DBAdapter.getConnection();
					PreparedStatement pstm = con.prepareStatement(query);){
				pstm.setString(1, panel.getName());				
				pstm.setString(2, panel.getDevice_mac());
				
				int rowsAffected = pstm.executeUpdate();
				if(rowsAffected==1) {
					ResultSet rs = pstm.getGeneratedKeys();
					if(rs.next()) {
						return rs.getInt(1);
					}
				}
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new WebApplicationException(e.getMessage());
			}
		}else {
			query = "UPDATE panel SET name = ?, device_mac = ? WHERE id = ?";
			
			try (Connection con = DBAdapter.getConnection();
					PreparedStatement pstm = con.prepareStatement(query);){
				pstm.setString(1, panel.getName());				
				pstm.setString(2, panel.getDevice_mac());
				pstm.setInt(3, panel.getId());
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
