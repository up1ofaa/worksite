package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.naming.factory.ResourceFactory;

public class startFilter implements Filter {
	private static final long serialVersionUID = 1L;
	//private static final String  RESOURCE_PROPERTIES = "C:\\AND\\WORKSPACE\\AND\\src\\com\\inswave\\config\\resource.properties"; //濡쒖뺄
	private static final String  RESOURCE_PROPERTIES = "/oraweb/jeus/webroot/WEB-INF/classes/config/resource.properties"; //媛쒕컻湲�
	private List<String> excludedUrls;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	@Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {   
	 	try {
	 		String path = ((HttpServletRequest) req).getServletPath();
	
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);
	        
	       	HttpServletRequest h_request = (HttpServletRequest)req;
	      	String method = h_request.getMethod();

	      	if(method.equalsIgnoreCase("POST")) {
	      		request.setCharacterEncoding("euc-kr");
	      	}	        
	      	
	      	if(!excludedUrls.contains(path))
	 	    {
	      		
	 	    }else{
	      	
	 	    	chain.doFilter(request, response);
	 	    }
	          
	    
	        
	 	} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludePattern = filterConfig.getInitParameter("excludedUrls");
	    excludedUrls = Arrays.asList(excludePattern.split(","));
	}
}
	 	
