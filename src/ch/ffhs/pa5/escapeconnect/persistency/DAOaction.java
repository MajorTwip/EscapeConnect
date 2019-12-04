package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.ActionDAOBean;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;

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
	public List<ActionDAOBean> getActionByID(int actionId) {
		String query = "select basetopic,deviceid,topic,payload from device,panel,action where device.mac in(select panel.device_mac from panel where panel.id in (select action.panel_id from action where action.id = ?)) and panel.id in (select action.id from action where action.id = ?) and action.id = ?";
		List<ActionDAOBean> list_actions = new ArrayList<>();
		try (Connection con = DBAdapter.getConnection();
				PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setInt(1, actionId);
			pstm.setInt(2, actionId);
			pstm.setInt(3, actionId);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and get the first line.
			if(rs.next() != false) 
			do {
				ActionDAOBean generated_action = new ActionDAOBean();
				generated_action.setBasetopic(rs.getString("basetopic"));
				generated_action.setDeviceid(rs.getString("deviceid"));
				generated_action.setPayload(rs.getString("payload"));
				generated_action.setSubtopic(rs.getString("topic"));
				list_actions.add(generated_action);
			} while (rs.next());
			// Close the connection to the DB
			pstm.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the rs with the panel or empty.
		return list_actions;			
	}
	
	public List<ActionDAOBean> getActionByPanelID(int panelId) {
		String query = "SELECT * FROM action WHERE panel_id = ?";
		// We use an ArrayList so that TomCat can easily convert it to a JSON.
		List<ActionDAOBean> list_actions = new ArrayList<>();
		try (Connection con = DBAdapter.getConnection();
				PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setInt(1, panelId);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and iterate through it in order to create the beans "Action"
			if(rs.next() != false) {	
				do {
					ActionDAOBean generated_action = new ActionDAOBean();
					generated_action.setId(rs.getInt("id"));
					generated_action.setPanel_id(panelId);
					generated_action.setLabel(rs.getString("label"));
					generated_action.setPayload(rs.getString("payload"));
					generated_action.setSubtopic(rs.getString("topic"));
					list_actions.add(generated_action);
						} while (rs.next());	
					}
					// Close the connection to the DB
					pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}	
		// Return the list of actions or empty.
		return list_actions;
	}
}
