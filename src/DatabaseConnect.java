

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ResourceBundle;

public class DatabaseConnect {

	public Connection con;
	public Statement query;
	public ResultSet resultSet;
	
	
	
	public DatabaseConnect() throws Exception {
		// TODO Auto-generateconstructor stub
	
		ResourceBundle rb = ResourceBundle.getBundle("Configuration.ConfigFile");
		//Class.forName("com.mysql.jdbc.Driver");
		String dbURL = rb.getString("dbURL");
		String dbUser = rb.getString("dbUser");
		String dbPassword = rb.getString("dbPassword");
		
		con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		
		System.out.println("Successfully connected to database " + dbURL);
	

	}
	
	
	public void getCustomerName(String customerName) throws Exception{
		
		query = con.createStatement();

		resultSet = query.executeQuery("select * from classicmodels.customers where customerName='" + customerName +"'");
		
		 if (resultSet != null) {
			    while (resultSet.next()) {
			        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

			            int type = resultSetMetaData.getColumnType(i);
			            if (type == Types.VARCHAR || type == Types.CHAR) {
			                 System.out.println(resultSet.getString(i));
			            } else {
			                 System.out.println(resultSet.getLong(i));
			            }
			        }

			         System.out.println("-----------");
			    }
			}
			
			
		}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DatabaseConnect db = new DatabaseConnect();
		db.getCustomerName("Land of Toys Inc.");
		
	}

}
