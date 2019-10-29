package ch.ffhs.pa5.escapeconnect.persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ch.ffhs.pa5.escapeconnect.bean.EcSettings;

public class DAOecsettings{

	public EcSettings get() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void write(EcSettings settings) {
		
		String query = "UPDATE ecsettings SET adminpass = ?, mqtturl = ?, mqttport = ?, mqttuser = ?, mqttpass = ?";
		
		Connection con = DBAdapter.getConnectio();
		try (PreparedStatement pstm = con.prepareStatement(query);){
			pstm.setString(1, settings.getPassword());
			String mqtturl[] = settings.getMqttUrl().split(":");
			pstm.setString(2, mqtturl[0]);
			if(mqtturl.length>1) {
				pstm.setInt(3, Integer.valueOf(mqtturl[1]));
			}
			pstm.setString(4, settings.getMqttName());
			pstm.setString(5, settings.getMqttPass());
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
