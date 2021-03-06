package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.ws.rs.WebApplicationException;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;

public class DAOecsettings implements DAOecsettingsIF{
	DBAdapter dba = new DBAdapter();

	@Override
	public EcSettings get() {
		String query = "SELECT * FROM ecsettings";
		// Create an empty PanelDAOBean
		EcSettings settings = new EcSettings();
		try (Connection con = dba.getConnection();
				PreparedStatement pstm = con.prepareStatement(query)){
			ResultSet rs = pstm.executeQuery();
			// Take the ResultSet rs and get the first line.
			if(rs.next() != false) {
				String url = rs.getString("mqtturl");
				int port = rs.getInt("mqttport");
				
				//prepend tcp:// if missing
				if(!url.startsWith("tcp://")&&!url.startsWith("ssl://")) {
					url="tcp://"+url;
				}
				
				if(port==0) {
					if(url.startsWith("tcp://")) {
						port = 1883;
					}else {
						port = 8883;
					}
				}
				url = url + ":" + String.valueOf(port);
				
				settings.setMqttUrl(url);
				settings.setMqttName(rs.getString("mqttuser"));
				settings.setMqttPass(rs.getString("mqttpass"));
				settings.setPassword(rs.getString("adminpass"));				
			}
			// Close the connection to the DB
			pstm.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(e.getMessage());
		}
		// Return the rs with the settings.
		return settings;
	}

	public void write(EcSettings settings) {
		
		String query = "UPDATE ecsettings SET adminpass = ?, mqtturl = ?, mqttport = ?, mqttuser = ?, mqttpass = ?";
		
		Connection con = dba.getConnection();
		try (PreparedStatement pstm = con.prepareStatement(query);){
			pstm.setString(1, settings.getPassword());
			String mqtturl[] = settings.getMqttUrl().split(":");
			if(mqtturl[0].length()<1) {
				mqtturl[0] = "MQTT";
			}
			pstm.setString(2, mqtturl[0]);
			if(mqtturl.length>1) {
				pstm.setInt(3, Integer.valueOf(mqtturl[1]));
			}
			pstm.setString(4, settings.getMqttName());
			pstm.setString(5, settings.getMqttPass());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			throw new WebApplicationException(e.getMessage());
		}
		
	}

}
