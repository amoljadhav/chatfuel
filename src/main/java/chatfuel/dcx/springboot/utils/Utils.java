package chatfuel.dcx.springboot.utils;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class Utils {

	public static String getData(JSONObject response, String val) {

		String dataValue = (response.has(val) && !response.isNull(val)) ? response.getString(val) : "";
		String result = dataValue != null ? dataValue : "";
		return result;
	}

	public static String getHostUrl(HttpServletRequest request) {
		String url = null;
		if (request != null) {
			url = request.getScheme() + "://" + // "http" + "://
					request.getServerName() + // "myhost"
					":" + request.getServerPort();
		}
		return url;
	}
	
	public static void printLn(String message){
		System.out.println(message);
	}

}
