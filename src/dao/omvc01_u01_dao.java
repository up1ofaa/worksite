package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import oracle.jdbc.OracleTypes;
import util.dbConnection;





public class omvc01_u01_dao {
	

	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	CallableStatement cstmt;
	ResultSet rs;
	
	int result=0;
	private int cnt;
	
	
	public omvc01_u01_dao() {

	}
	

	public JSONObject UpdateCourseList(JSONObject inJobj) throws Exception{			
		
			JSONArray jlist= new JSONArray();

			int cnt = inJobj.getInt("list_cnt");
			jlist   = inJobj.getJSONArray("list");
			
			JSONObject outJobj = new JSONObject();
			
			System.out.println("omvc01_u01_dao  시작");
			try {	
				con  = dbConnection.getConnection();
				
				for(int i=0; i<jlist.length(); i++) {
							String squery="";
							squery +=" { call omvc01.omvc01_u01( ? ,?              "; //output
							squery +="                           ,?, ? ,? ,? ,?  )} "; //input
							cstmt = con.prepareCall(squery);								
							
							cstmt.registerOutParameter(1, OracleTypes.VARCHAR);
							cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
							cstmt.setString(3,jlist.getJSONObject(i).getString("title"));
							cstmt.setString(4,jlist.getJSONObject(i).getString("c_number"));
							cstmt.setString(5,jlist.getJSONObject(i).getString("professor_id"));
							cstmt.setString(6,jlist.getJSONObject(i).getString("course_fee"));
							cstmt.setString(7,jlist.getJSONObject(i).getString("course_id"));
							
							cstmt.execute();
				
							jlist.getJSONObject(i).put("err_cd", cstmt.getString(1));	
							jlist.getJSONObject(i).put("err_msg", cstmt.getString(2));	
					}// end for문	
					
					con.commit();
					cstmt.close();
					con.close();				
			}catch(Exception ex) {
				con.rollback(); //에러발생시 rollback 처리
				System.out.println("getCourseList :"+ex);
			}finally {
				try {
					con.setAutoCommit(true);//트랜잭션 처리를 기본 상태로 되돌린다
					cstmt.close();
					con.close();
				}catch(Exception e) {}
			}//end try	
			System.out.println("omvc01_u01_dao  끝");
			outJobj.put("rsList", jlist );
			return outJobj;

	}

	
	
}


