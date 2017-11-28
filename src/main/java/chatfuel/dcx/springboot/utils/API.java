package chatfuel.dcx.springboot.utils;

public class API {

	public static final String USERNAME = "nabendu.chattopad@sfdc-coe.com.assetrepos";
        public static final String PASSWORD = "S@iram01";
       
       
        public static final String HOST_NAME = "https://cs83.salesforce.com";
        public static final String TOKEN_URL = "https://cs83.salesforce.com/services/oauth2/token";
        public static final String CALLBACK_URL = "https://cs83.salesforce.com/auth2/callback";
 
        public static final String CONSUMER_KEY = "3MVG9w8uXui2aB_rvCGZKHtAuQUv6y.qHsqKr50Syg3PpzOdDaBaIJGa3Rv8kxthu_gJPl1QlONayT5ScIVy6";
        public static final String CONSUMER_SECRET = "2171964183736661239";
 
       
       
        public static final String GET_POLICY_BY_POLICYNO = HOST_NAME+  "services/apexrest/ AXA_ChatbotRestservice?type=Policy&policyNum=";
        public static final String GET_CLAIM_BY_CLAIMNO = HOST_NAME+  "services/apexrest/ AXA_ChatbotRestservice?type=Claim&claimnum=";
       
       
       
        public static final String FIRST_NAME = "&Firstname=";
        public static final String LAST_NAME = "&Lastname=";
        public static final String DOB = "&DOB=";
 
        public static final String GET_POLICY_BYPOLICY_FLD = HOST_NAME+ "services/apexrest/AXA_ChatbotRestservice?type=Policy";
        public static final String GET_CLAIM_BYCLAIM_FLD = HOST_NAME+ "services/apexrest/AXA_ChatbotRestservice?type=claim";
	
	
	
}
