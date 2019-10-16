package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.Panel;

public class DAOpanelService implements DAOpanel {

	@Override
	public Panel getById(int id) {
		String sql = "SELECT * FROM panel WHERE id=?";
		try(Connection con = DBAdapter.getConnectio();
			PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Panel panel = new Panel();
			panel.setId(rs.getInt("id"));
			//etc¨
			con.close();
			return panel;
		}catch(SQLException e) {
			throw new WebApplicationException(e.getMessage());
		}
	}

	@Override
	public List<Panel> getByDevice(int deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(Panel panel, boolean overwrite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
