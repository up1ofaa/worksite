package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


		
	
public abstract class JSONUtils {

	  /**
     * ObjectNode에서 key에 해당하는 data를 반환한다. 	
     * @param node Object	Node
     * @param key key
     * @return key에 해당하는 data
     */

	 public static String getStringForObjectNode(ObjectNode node, String key) {
				 if(node.has(key)) {		
		 //return node.get(key).asText();
		 return node.get(key).toString();//.asText();
		 }	
		 return "";	
	 }

 

 /**
  * JSON 문자열 데이터를 ObjectNode로 변환하여 반환한다. 
  * @param jsonData JSON 문자열
  * @return 변환된 ObjectNode
  */

 public static ObjectNode getObjectNode(String jsonData) {
	 	ObjectMapper om = new ObjectMapper();
        JsonNode rootNode = null;
        try {
			 rootNode = om.readValue(jsonData, JsonNode.class);
			 for(Iterator<JsonNode> i = rootNode.iterator(); i.hasNext();) {
			 JsonNode j = i.next();
			
			 if (j.isObject()) {
			     ObjectNode obj = om.convertValue(j, ObjectNode.class);
			     return obj;}		
			 }			
			 } catch (Exception e) {			
			 e.printStackTrace();			
			 throw new RuntimeException(e);			
			 }
        return null;
 }

 

 /**
  * JSON 문자열 데이터를 String Array로 변환하여 반환한다. 
  * @param data JSON 문자열
  * @return 변환된 String Array 
  */

 public static String[] getStringArray(String data) {
		 if(data == null) {		 return null; }
		 ObjectMapper om = new ObjectMapper();
        JsonNode rootNode = null;
        Set<String> set = new HashSet<String>();
        try {
        	rootNode = om.readValue(data, JsonNode.class);
			 for(Iterator<JsonNode> i = rootNode.iterator(); i.hasNext();) {
			  set.add(i.next().getTextValue()+"");
			 }
		 } catch (Exception e) {
		 e.printStackTrace();
		 throw new RuntimeException(e);
		 }
        return set.toArray(new String[set.size()]);
 }


 
 public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
     Map<String, Object> retMap = new HashMap<String, Object>();
     if(json != JSONObject.NULL) {
         retMap = toMap(json);
     }
     return retMap;
 }

 

 public static Map<String, Object> toMap(JSONObject object) throws JSONException {
     Map<String, Object> map = new HashMap<String, Object>();
     Iterator<String> keysItr = object.keys();
     while(keysItr.hasNext()) {
         String key = keysItr.next();
         Object value = object.get(key);
         if(value instanceof JSONArray) {
             value = toList((JSONArray) value);
         }else if(value instanceof JSONObject) {
             value = toMap((JSONObject) value);
         }
         map.put(key, value);
     }
     return map;
 }
 
 

 

 public static List<Object> toList(JSONArray array) throws JSONException {
     List<Object> list = new ArrayList<Object>();
     for(int i = 0; i < array.length(); i++) {
         Object value = array.get(i);
         if(value instanceof JSONArray) {
             value = toList((JSONArray) value);
         }else if(value instanceof JSONObject) {
             value = toMap((JSONObject) value);
         }
         list.add(value);
     }
     return list;
 } 






	
	
}
