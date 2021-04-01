package ctl;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;


import dao.omvc01_u01_dao;

/**
 * Servlet implementation class courseCtl
 */
@WebServlet("/omvc01_u01")
public class omvc01_u01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public omvc01_u01() {
        super();
        // TODO Auto-generated constructor stub
    }



	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {


		String sJson = readJSONStringFromRequestBody(request);

		
		//org.json.simple.JSONObject를 제거
		//org.json.JSONObject로 업데이트 => java-json.jar
		JSONObject obj =new JSONObject(sJson);
		

		omvc01_u01_dao omvc01_u01_dao =new omvc01_u01_dao();

		JSONObject outRec =new JSONObject();
		outRec =omvc01_u01_dao.UpdateCourseList(obj);


		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(outRec.toString());
		response.getWriter().flush();		
		}catch(Exception e) {
					
		} //end try
	}//end method
	
	
	private String readJSONStringFromRequestBody(HttpServletRequest request) {
		 StringBuffer json = new StringBuffer();
	        String line = null;
	        try {
	            BufferedReader reader = request.getReader();
	            while((line = reader.readLine()) != null) {
	                json.append(line);
	            }
	        }
	        catch(Exception e) {
	            System.out.println("Error reading JSON string: " + e.toString());
	        }
	        return json.toString();		
	}

}
