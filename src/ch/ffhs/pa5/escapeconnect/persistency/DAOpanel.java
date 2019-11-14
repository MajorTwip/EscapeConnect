package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.Panel;
import ch.ffhs.pa5.escapeconnect.bean.PanelDAOBean;

public class DAOpanel implements DAOpanelIF {
	
	@Override
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
	
	public List<PanelDAOBean> getAllPanels() {
		
		// start the connection with the BD
		String query = "SELECT * FROM panel";
		Connection con = DBAdapter.getConnection();
		// We use an ArrayList so that TomCat can easily convert it to a JSON.
		List<PanelDAOBean> list_panels = new ArrayList<>();
		try (PreparedStatement pstm = con.prepareStatement(query)){
			ResultSet rs = pstm.executeQuery();
			// if the DB has no Panels, skip the code with the if-statement
			// Take the ResultSet rs and iterate through it in order to create the beans "Panel"
			if(rs.next() != false) {	
				do {
					PanelDAOBean generated_panel = new PanelDAOBean();
					generated_panel.setId(rs.getInt("id"));
					generated_panel.setName(rs.getString("name"));
					list_panels.add(generated_panel);
				} while (rs.next());	
			}
			// Close the connection to the DB
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}	
		// Return the list of beans "Panel" or empty.
		return list_panels;
	}
	
	public PanelDAOBean getByDevice(int deviceId) {
		
		// start the connection with the BD
		String query = "SELECT * FROM panel WHERE device_id = ?";
		Connection con = DBAdapter.getConnection();
		// Create an empty PanelDAOBean
		PanelDAOBean generated_panel = new PanelDAOBean();
		// We use an ArrayList so that TomCat can easily convert it to a JSON.
		try (PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setInt(1, deviceId);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and get the first line.
			if(rs.next() != false) {
				generated_panel.setId(rs.getInt("id"));
				generated_panel.setName(rs.getString("name"));
			}
			// Close the connection to the DB
			pstm.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the rs with the panel or empty.
		return generated_panel;			
	}
	
	public List<PanelDAOBean> getById(int id) {
		// start the connection with the BD
		String query = "SELECT * FROM panel WHERE id = ?";
		Connection con = DBAdapter.getConnection();
		// Create an empty PanelDAOBean
		PanelDAOBean generated_panel = new PanelDAOBean();
		try (PreparedStatement pstm = con.prepareStatement(query)){
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and get the first line.
			if(rs.next() != false) {
				generated_panel.setId(rs.getInt("id"));
				generated_panel.setName(rs.getString("name"));
			}
			// Close the connection to the DB
			pstm.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the rs with the panel or empty.
		return generated_panel;			
	}
	
	public void delete(int id) {
		
	}
	
	
}
