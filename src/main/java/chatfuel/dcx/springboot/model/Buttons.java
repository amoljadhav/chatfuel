package chatfuel.dcx.springboot.model;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

import chatfuel.dcx.springboot.utils.UserStaticData;
import chatfuel.dcx.springboot.utils.Utils;

public class Buttons {

	private String title;

	private String type = "json_plugin_url";

	private String url;
	
	public Buttons(JSONObject jsonObject, String uri){
		
		String Name = Utils.getData(jsonObject, "Name");
//		if(jsonObject.has("attributes") && !jsonObject.isNull("attributes")){
//			JSONObject jsonObj2 = new JSONObject();
//			jsonObj2 = jsonObject.getJSONObject("attributes");
//			String type = Utils.getData(jsonObj2, "type");
//			title = type;
//		}
		
		if(jsonObject.has("Claim_No__c") && !jsonObject.isNull("Claim_No__c")){
			Name = Utils.getData(jsonObject, "Claim_No__c"); // Note: In case of claims, we are getting Claim_No__c as claimnumber while for policy number its Name
		}
		
		title = UserStaticData.CLAIM_NUMBER+Name;
		url = uri+"="+Name;

	}
	
	public Buttons(JSONObject jsonObject, String uri,String title){
		
		this.title = title;
		String Name = Utils.getData(jsonObject, "Name");
		url = uri+"="+Name;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
