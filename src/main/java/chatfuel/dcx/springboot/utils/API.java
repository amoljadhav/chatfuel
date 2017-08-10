package chatfuel.dcx.springboot.utils;

public class API {

	public static final String USERNAME = "sandeep.thumu@ktaxa.th.uat";
	public static final String PASSWORD = "Chatbot@7890-";
	
			
			

	
	public static final String HOST_NAME = "https://ktaxa-ipro--uat.cs58.my.salesforce.com/";
	public static final String TOKEN_URL = "https://ktaxa-ipro--uat.cs58.my.salesforce.com/services/oauth2/token";
	public static final String CALLBACK_URL = "https://ktaxa-ipro--uat.cs58.my.salesforce.com/auth2/callback";
	public static final String CONSUMER_KEY = "3MVG99S6MzYiT5k9GUaYZ3jPNdX.BhjrzpYawst0Wm18S5xosgGVJZcdD.6aPL0V9X4TyfI5sftbZk2PBbRCS";
	public static final String CONSUMER_SECRET = "8983106995679876801";
	
	
	public static final String GET_POLICY_BY_POLICYNO = HOST_NAME+  "services/apexrest/AXA_ChatbotService?type=Policy&policyNum=";
	public static final String GET_CLAIM_BY_CLAIMNO = HOST_NAME+  "services/apexrest/AXA_ChatbotService?type=Claim&claimnum=";
	
	
	
	public static final String FIRST_NAME = "&Firstname=";
	public static final String LAST_NAME = "&Lastname=";
	public static final String DOB = "&DOB=";

	public static final String GET_POLICY_BYPOLICY_FLD = HOST_NAME+ "services/apexrest/AXA_ChatbotService?type=Policy";
	public static final String GET_CLAIM_BYCLAIM_FLD = HOST_NAME+ "services/apexrest/AXA_ChatbotService?type=claim";

	
	
	
}
