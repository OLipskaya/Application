package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class DataBaseManager {
	
	private List<Contact> contacts;
	private List<Contact> db_contacts;
	private Connection connect = null;

	private String TB_NAME = "table";

	public DataBaseManager() throws SQLException {
		contacts = new ArrayList<Contact>();
		db_contacts = new ArrayList<Contact>();
	    connect = getConnection();
	}	
	
	private Connection getConnection() {
		Connection connect = null;
		try {	
			connect = DataSource.getInstance().getConnection();		
		} catch (Exception e){
			e.printStackTrace();
		}	
		return connect;
	}
	
	public void closeConnection(){
		try {
			connect.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void uploadDataBase() {
		String insertStatement = "INSERT INTO " + TB_NAME +" (name,surname,login,mail,phone_num,id) VALUES (?,?,?,?,?,?)";	
		updateDataBase();
		PreparedStatement ps = null;		
		try {
			ps = connect.prepareStatement(insertStatement);				
			for(Contact ct : contacts) {									
				ps.setString(1, ct.getName());
				ps.setString(2, ct.getSurName());
				ps.setString(3, ct.getLogin());
				ps.setString(4, ct.getMail());
				ps.setString(5, ct.getPhoneNumber());
				ps.setInt(6, ct.getId());
				ps.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){
				try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
	}

	public void updateDataBase() {
		String deleteStatement = "DELETE FROM " + TB_NAME;
		Statement statement = null;
		try {
			statement = connect.createStatement();
			statement.execute(deleteStatement);	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null){ try { statement.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
	}
	
	/** only tables with different names */
	public void createTable(String tabName) {
		TB_NAME = tabName;
		String createStatement = "CREATE TABLE if not exists "+TB_NAME
				+" (name varchar(100),"
				+ "surname varchar(100),"
				+ "login varchar(100),"
				+ "mail varchar(100),"
				+ "phone_num varchar(100), "
				+ "id integer)";
		Statement statement = null;
		try {	
			statement = connect.createStatement();
			statement.execute(createStatement);		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null){
				try { statement.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
	}
	
	public void makeSelection() {
		String selectStatement = "SELECT * FROM "+TB_NAME;
		Statement ps = null;
		ResultSet rs = null;
		try {
			ps = connect.createStatement();
			rs = ps.executeQuery(selectStatement);	
			while(rs.next()) {	
				Contact cn = new Contact(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				db_contacts.add(cn);
			}		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }          
        }		
	}
	
	public List<Contact> getDBList(){
		makeSelection();
		return db_contacts;
	}
	
	public void addList(List<Contact> list) {
		contacts = list;
		uploadDataBase();
	}
	
}
