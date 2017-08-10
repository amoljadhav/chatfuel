package chatfuel.dcx.springboot.utils;

import org.json.JSONObject;

public class Utils {

	public static String getData(JSONObject response,String val){
		
		String dataValue =(response.has(val) && !response.isNull(val))?response.getString(val):"";
		String result = dataValue!=null?dataValue:"";
		return result;
	}
	
	
	
	
	
	
}
