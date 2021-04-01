package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	public static Connection getConnection()
    {
        Connection conn = null;
        try {
            String user = "system"; 
            String pw 	= "ora4321";
            String url 	= "jdbc:oracle:thin:@localhost:1521:oradb";
            
            Class.forName("oracle.jdbc.driver.OracleDriver");        
            conn = DriverManager.getConnection(url, user, pw);
            
            System.out.println("Database connection complete");
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB classNotFound Excetpion"+cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB Sql Excetpion"+sqle.toString());
        } catch (Exception e) {
            System.out.println("Unkonwn error");
            e.printStackTrace();
        }
        return conn;     
    }

	
}

/*
 * oracle DB connection method
 * cmd open,   sqlplus //inputKeyword 
 * userName : system	//inputKeyword
 * password : ora4321
 * 
 * quit // exit
 */

/* expired password
 * cmd open
 * sqlplus conn as sysdba
 * pw:sysdba
 * alter user system identified by ora4321;
 * 
 * */
