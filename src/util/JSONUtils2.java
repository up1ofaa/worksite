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
import org.json.JSONException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

		
	
public abstract class JSONUtils2 {

	


 /**
  * Map을 json으로 변환한다.
  *
  * @param map Map<String, Object>.
  * @return JSONObject.
  */
 public static JSONObject getJsonStringFromMap( Map<String, Object> map )
 {
     JSONObject jsonObject = new JSONObject();
     for( Map.Entry<String, Object> entry : map.entrySet() ) {
         String key = entry.getKey();
         Object value = entry.getValue();
         jsonObject.put(key, value);
     }
     
     return jsonObject;
 }
 
 /**
  * List<Map>을 jsonArray로 변환한다.
  *
  * @param list List<Map<String, Object>>.
  * @return JSONArray.
  */
 public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
 {
     JSONArray jsonArray = new JSONArray();
     for( Map<String, Object> map : list ) {
         jsonArray.add( getJsonStringFromMap( map ) );
     }
     
     return jsonArray;
 }
 
 /**
  * List<Map>을 jsonString으로 변환한다.
  *
  * @param list List<Map<String, Object>>.
  * @return String.
  */
 public static String getJsonStringFromList( List<Map<String, Object>> list )
 {
     JSONArray jsonArray = getJsonArrayFromList( list );
     return jsonArray.toJSONString();
 }



	
	
}
