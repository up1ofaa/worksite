package util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class testMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null; // DB
        PreparedStatement pstm = null;  // SQL 
        ResultSet rs = null;  //
	
        String exp= "";
        
        try{

        	String quary = "SELECT SYSDATE FROM DUAL";
            
            conn = dbConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            rs = pstm.executeQuery();
            
            while(rs.next()){
            	exp=rs.getString(1);
            }

            System.out.println("exp: "+exp);
		
		
        }catch (SQLException sqle) {
            System.out.println("SELECTë¬? ?‹¤?Œ¨");
            sqle.printStackTrace();
            
        }finally{
            // DB ?—°ê²? ?‹«ê¸?
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
		

	}

}
