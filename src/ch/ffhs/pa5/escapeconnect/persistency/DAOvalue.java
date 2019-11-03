package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;

public class DAOvalue {
	public static int write(ValueDAOBean value) {
		
		String query = "";
		
		if(value.getId()<1) {
			query = "INSERT INTO value (panel_id,label,topic,type) VALUES(?,?,?,?)";
			
			try (Connection con = DBAdapter.getConnection();
					PreparedStatement pstm = con.prepareStatement(query);){
				pstm.setInt(1, value.getPanel_id());				
				pstm.setString(2, value.getLabel());				
				pstm.setString(3, value.getSubtopic());				
				pstm.setString(4, value.getType());
				
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
			query = "UPDATE value SET panel_id = ?,label = ?,topic = ?,type = ? WHERE id = ?";
			
			try (Connection con = DBAdapter.getConnection();
					PreparedStatement pstm = con.prepareStatement(query);){
				pstm.setInt(1, value.getPanel_id());				
				pstm.setString(2, value.getLabel());				
				pstm.setString(3, value.getSubtopic());				
				pstm.setString(4, value.getType());
				pstm.setInt(5, value.getId());
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
