package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;

public class DAOaction implements DAOactionIF {
	@Override
	public int write(ActionDAOBean action) {
		
		String query = "";
		
		if(action.getId() == 0 ||action.getId() <1) {
			query = "INSERT INTO action (panel_id,label,topic,payload) VALUES(?,?,?,?)";
			
			try (Connection con = DBAdapter.getConnection();
					PreparedStatement pstm = con.prepareStatement(query);){
				pstm.setInt(1, action.getPanel_id());				
				pstm.setString(2, action.getLabel());				
				pstm.setString(3, action.getSubtopic());				
				pstm.setString(4, action.getPayload());
				
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
			query = "UPDATE action SET panel_id = ?,label = ?,topic = ?,payload = ? WHERE id = ?";
			
			try (Connection con = DBAdapter.getConnection();
					PreparedStatement pstm = con.prepareStatement(query);){
				pstm.setInt(1, action.getPanel_id());				
				pstm.setString(2, action.getLabel());				
				pstm.setString(3, action.getSubtopic());				
				pstm.setString(4, action.getPayload());
				pstm.setInt(5, action.getId());
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
