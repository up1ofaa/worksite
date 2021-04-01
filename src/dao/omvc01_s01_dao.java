package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import oracle.jdbc.OracleTypes;
import util.dbConnection;





public class omvc01_s01_dao {
	

	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	CallableStatement cstmt;
	ResultSet rs;
	
	
	public omvc01_s01_dao() {

	}
	

	
	

	public JSONObject GetCourseList(){

		JSONObject outRec =new JSONObject();
		List<JSONObject> jlist= new ArrayList();
		System.out.println("omvc01_s01_dao  �떆�옉");
		
		try {
			String squery="";
			squery +=" { call omvc01.omvc01_s01( ? ,?              "; //output
			squery +="                           ,?				 )} "; //output	
			con  = dbConnection.getConnection();
			cstmt = con.prepareCall(squery);	
			
			
			cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);
	
			cstmt.execute();	
			
			int i=0;
			rs = (ResultSet)cstmt.getObject(3);
			System.out.println("寃곌낵媛�:"+cstmt.getString(2));
			
			while(rs.next()) {
				
				JSONObject jsonobj= new JSONObject();
				
				jsonobj.put("course_id"		, rs.getString("course_id"));
				jsonobj.put("title"			, rs.getString("title"));
				jsonobj.put("c_number"		, rs.getString("c_number"));
				jsonobj.put("professor_id"	, rs.getString("professor_id"));
				jsonobj.put("course_fee"	, rs.getString("course_fee"));
				
				jlist.add(jsonobj);
				i++;
			}
			
			outRec.put("JLIST", jlist);
			outRec.put("JLIST_CNT", i);
			
			rs.close();
			cstmt.close();
			con.close();
			//return outRec;
		
			System.out.println("omvc01_s01_dao  醫낅즺");
		}catch(Exception ex) {
			System.out.println("getCourseList :"+ex);
		}finally {
			try {
				rs.close();
				cstmt.close();
				con.close();
			}catch(Exception e) {
				
			}
		}

		return outRec;
	}

	
	
}


