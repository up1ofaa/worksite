package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/Action")		
public class ActionClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Result result = new Result();
	
    public ActionClass() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//RequestAdapter reqAdapter 			= new RequestAdapter();
		//ResponseAdapter resAdapter 			= new ResponseAdapter();
		Map<String, Object> inputMap 		= new HashMap();
		Map<String,Object> outputMap 		= new HashMap();
		Map<String,Object> resultMap		= new HashMap();
		MappedRecordASRVS inRec 			= new MappedRecordASRVS();
		MappedRecordASRVS outRec			= new MappedRecordASRVS();
		List<Map<String,Object>> resList 	= null;
		
	    Object requsetJSON 					= null;

		try {
			System.out.println("ActionClass2.doPost 시작");
			String sJson = readJSONStringFromRequestBody(request);
			requsetJSON =JSONUtils.jsonToMap(new JSONObject(sJson));
			//requsetJSON = reqAdapter.convert(request);
			inputMap = (Map<String, Object>) requsetJSON;
			System.out.println("inputMap:"+inputMap.toString());
			inRec.copy(inputMap);

			if(request.getSession() != null){
				inRec.setHttpSession(request.getSession());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Map<String, Object> sysRefMap = inRec.getObj("SYS_REF");
		if(sysRefMap != null){
		    Iterator<String> sysRef = sysRefMap.keySet().iterator();
		    int i = 0;
		    while( sysRef.hasNext() ){
	            String key = sysRef.next();
	            String type = sysRefMap.get(key).getClass().getName();
	            if(sysRefMap.get(key) instanceof JSONArray){
	            	List<Map<String, Object>> tmpList = (List<Map<String, Object>>)sysRefMap.get(key);
	            	//inRec.put(key, tmpList);
	        		int index=0;
	            	for(Map<String, Object> tmpMap : tmpList){
	            		Iterator<String> tmpMapIter = tmpMap.keySet().iterator();
	            		while( tmpMapIter.hasNext() ){
	            			String tmpMapKey 	= tmpMapIter.next();
	    	            	Object tmpVal 		= tmpMap.get(tmpMapKey);
	    	            	if(tmpVal instanceof Integer){
	    	                	inRec.putInt(tmpMapKey, (int) tmpVal, index);
	    	            	}else if(tmpVal instanceof Long){
	    	                	inRec.putLong(tmpMapKey, (Long) tmpVal, index);
	    	            	}else if(tmpVal instanceof String){
	    	                	inRec.put(tmpMapKey, (String) tmpVal, index);
	    	            	}else if(tmpVal instanceof Float){
	    	            		inRec.putFloat(tmpMapKey, (Float) tmpVal, index);
	    	            	}else if(tmpVal instanceof Short){
	    	            		inRec.putFloat(tmpMapKey, (Short) tmpVal, index);
	    	            	}else if(tmpVal instanceof Double){
	    	            		inRec.putDouble(tmpMapKey, (Double) tmpVal, index);
	    	            	}else if(tmpVal instanceof BigDecimal){
	    	            		inRec.putBigDecimal(tmpMapKey, (BigDecimal) tmpVal, index);
	    	            	}else if(tmpVal instanceof Short){
	    	            		inRec.putFloat(tmpMapKey, (Short) tmpVal, index);
	    	            	}
	            		}
		            	index++;
	            	}
	            }else{ 
		            Map<String, Object> tmpMap = (Map<String, Object>)sysRefMap.get(key);
		            Iterator<String> tmpMapIter = tmpMap.keySet().iterator();
		            while( tmpMapIter.hasNext() ){
		            	String tmpMapKey 	= tmpMapIter.next();
		            	Object tmpVal 		= tmpMap.get(tmpMapKey);
		            	if(tmpVal instanceof Integer){
		                	inRec.putInt(tmpMapKey, (int) tmpVal);
		            	}else if(tmpVal instanceof Long){
		                	inRec.putLong(tmpMapKey, (Long) tmpVal);
		            	}else if(tmpVal instanceof String){
		                	inRec.put(tmpMapKey, (String) tmpVal);
		            	}else if(tmpVal instanceof Float){
		            		inRec.putFloat(tmpMapKey, (Float) tmpVal);
		            	}else if(tmpVal instanceof Short){
		            		inRec.putFloat(tmpMapKey, (Short) tmpVal);
		            	}else if(tmpVal instanceof Double){
		            		inRec.putDouble(tmpMapKey, (Double) tmpVal);
		            	}else if(tmpVal instanceof BigDecimal){
		            		inRec.putBigDecimal(tmpMapKey, (BigDecimal) tmpVal);
		            	}else if(tmpVal instanceof Short){
		            		inRec.putFloat(tmpMapKey, (Short) tmpVal);
		            	}
		            }
	            }
		    }
		}
            
    	    String className = (String)request.getParameter("name");
    		try {
    			Class<?> callClass = Class.forName(className);
    			Object obj = callClass.newInstance();
    			Method method = callClass.getMethod("execute", MappedRecordASRV.class, MappedRecordASRV.class);
    			outRec = (MappedRecordASRVS) method.invoke(obj, inRec, outRec);
    		} catch (
    				InstantiationException   | IllegalAccessException | 
    				NoSuchMethodException    | SecurityException | 
    				IllegalArgumentException | InvocationTargetException | ClassNotFoundException e
    				) 
    		{
    			e.printStackTrace();
    		}
	        String dataId = "dlt_rsList";
	        String targetIds = inRec.get("SYS_TARGET_ID");
	        if(!"".equals(targetIds) && targetIds != null){
	        	String[] targetId = targetIds.split(",");
	        	if(targetId.length < 2){
	        		dataId = targetIds;
	    			outRec.addMapList(dataId);
	        	}
	        }else{
	        	outRec.addMapList(dataId);
	        }
		try {
			//resAdapter.convertAndSend(request, response, outRec.getAllMap());
			this.sendData(request, response, outRec.getAllMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private String readJSONStringFromRequestBody(HttpServletRequest request) {
		 StringBuffer json = new StringBuffer();
	        String line = null;
	        try {
	            BufferedReader reader = request.getReader();
	            while((line = reader.readLine()) != null) {
	                json.append(line);
	                System.out.println("json.toString()"+json.toString());
	            }
	        }
	        catch(Exception e) {
	            System.out.println("Error reading JSON string: " + e.toString());
	        }
	        return null2blank(json.toString());		
	}

	private void sendData(HttpServletRequest request, HttpServletResponse response, Map object) {
		JSONObject jobj =new JSONObject();
		try {
			jobj.put("request", null2blank(request));
			jobj.put("response", null2blank(response));
			jobj.put("outRec", JSONUtils2.getJsonStringFromMap(object));
			System.out.println("sendData"+jobj.toString());
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jobj.toString());
			response.getWriter().flush();		
		}catch(Exception e) {
            System.out.println("Error reading JSON string: " + e.toString());
        }
	}
	
	 /**
    *
    * null ==> blank 리턴
    */
    public static String null2blank(Object obj) {
       if (obj == null)
           return "";
       else
           return obj.toString();
   }
    
  
	
}
	