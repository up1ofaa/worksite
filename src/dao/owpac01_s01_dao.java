package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import oracle.jdbc.OracleTypes;
import util.MappedRecordASRV;
import util.dbConnection;





public class owpac01_s01_dao {
	
	Statement stmt;
	PreparedStatement pstmt;
	CallableStatement cstmt;
	ResultSet rs;
	
	public MappedRecordASRV execute( Connection conn, MappedRecordASRV inRec, MappedRecordASRV outRec) throws Exception {
		
		try {
			
			conn  = dbConnection.getConnection();
			
			String squery="";
			squery +=" { call omvc01.omvc01_s01( ? ,?              "; //output
			squery +="                           ,?				 )} "; //output
			
			conn  = dbConnection.getConnection();
			cstmt = conn.prepareCall(squery);	
			
			
			cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
	
			cstmt.execute();	
			int i=0;
			rs = (ResultSet)cstmt.getObject(3);
			System.out.println("owpac01_s01_dao"+cstmt.getString(2));
			while(rs.next()) {				
				outRec.put("course_id"		, rs.getString("course_id"), i);
				outRec.put("title"			, rs.getString("title"), i);
				outRec.put("c_number"		, rs.getString("c_number"), i);
				outRec.put("professor_id"	, rs.getString("professor_id"), i);
				outRec.put("course_fee"	, rs.getString("course_fee"), i);
				
				i++;
			}
			outRec.putInt("JLIST_CNT", i);
			System.out.println("owpac01_s01_dao Á¾·á"+outRec.get("JLIST_CNT"));
			
		}catch(Exception e) {
			
		}finally {	
			rs.close();
			cstmt.close();
			conn.close();
		}
		
		return  outRec;
	}
	

	
	
	
}


