package ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


import dao.omvc01_s01_dao;


/**
 * Servlet implementation class courseCtl
 */
@WebServlet("/omvc01_s01")
public class omvc01_s01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public omvc01_s01() {
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


		omvc01_s01_dao omvc01_s01_dao =new omvc01_s01_dao();
		JSONObject jobj =new JSONObject();
		jobj =omvc01_s01_dao.GetCourseList();


		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jobj.toString());
		response.getWriter().flush();		
		
	}

}
