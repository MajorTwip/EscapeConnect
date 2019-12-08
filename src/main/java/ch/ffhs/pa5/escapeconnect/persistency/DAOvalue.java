package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.ValueDAOBean;

public class DAOvalue implements DAOvalueIF {
	DBAdapter dba = new DBAdapter();

	
	@Override
	public int write(ValueDAOBean value) {
		
		String query = "";
		
		if(value.getId()<1) {
			query = "INSERT INTO value (panel_id,label,topic,type) VALUES(?,?,?,?)";
			
			try (Connection con = dba.getConnection();
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
			
			try (Connection con = dba.getConnection();
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
	
	public List<ValueDAOBean> getValuesByPanelID(int panelId) {
		String query = "SELECT * FROM value WHERE panel_id = ?";
		// We use an ArrayList so that TomCat can easily convert it to a JSON.
		List<ValueDAOBean> list_values = new ArrayList<>();
		try (Connection con = dba.getConnection();
				PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setInt(1, panelId);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and iterate through it in order to create the beans "Values"
			if(rs.next() != false) {	
				do {
					ValueDAOBean generated_value = new ValueDAOBean();
					generated_value.setId(rs.getInt("id"));
					generated_value.setPanel_id(panelId);
					generated_value.setLabel(rs.getString("label"));
					generated_value.setSubtopic(rs.getString("topic"));
					generated_value.setType(rs.getString("type"));
					list_values.add(generated_value);
						} while (rs.next());	
					}
					// Close the connection to the DB
					pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}	
		// Return the list of actions or empty.
		return list_values;
	}
}
