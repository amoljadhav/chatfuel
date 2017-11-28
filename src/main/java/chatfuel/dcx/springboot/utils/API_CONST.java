package chatfuel.dcx.springboot.utils;

import java.util.Arrays;
import java.util.List;

public class API_CONST {

	public static final String CHANGE_LINE = " \n ";
	public static final String GET_POLICY = "/getpolicydetailsbypolicynumber";
	
	public static final String GET_CLAIM = "/getclaimdetailsbyclaimnumber";
	

	public static final String GET_POLICY_DETAILS_BY_USER_DETAILS = "/getpolicydetailsbyuserdetails";
	public static final String GET_CLAIM_DETAILS_BY_USER_DETAILS = "/getclaimdetailsbyuserdetails";
	
	

	
	public static final String GET_POLICYLIST = "/getpolicylist";
	public static final String GET_CLAIMLIST = "/getclaimlist";

	
	

	public static final String POLICYNUMBER = "policynum";
	public static final String CLAIMNUMBER = "claimnumber";
	public static final String FIRST_NAME = "firstname";
	public static final String LAST_NAME = "lastname";
	public static final String DOB = "dob";

	
	
	

	// We have found multiple policies. Please choose one.
	public static final String FOUND_MULTIPLE = "We have found multiple ";
	public static final String POLICIES = "policies.";
	public static final String CLAIMS = "claims.";
	public static final String PLEASE_CHOOSE = " Please choose one.";

	public static final List<String> ELEMENTS_TITLE = Arrays.asList("AXA SmartCare Executive",                        						"AXA SmartCare Executive Plus",
					"AXA SmartCare Optimum",
                        "International Exclusive",
                        "AXA SmartCare Cancer",
                        "AXA SmartCare Senior",
                        "AXA HappyCare");

	public static final List<String> ELEMENTS_URL = Arrays.asList("https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/686d4b1486d132bdb296a46f9d330069a3778d78_thumbnail.jpg",
                        "https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/7aac3974dd72ff16e10db496af8ec50ebc43bfd8_thumbnail.jpg",
                        "https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/725123f1c39e531750cff5a99194bcc3f7994181_thumbnail.jpg",
                        "https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/a18a824f2937246d68d8b351261c5d4b7550668d_thumbnail.jpg",
                        "https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/31c19f20c777106660a8759b20a6c75734a8a274_thumb-smartcare-cancer.jpg",
                        "https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/8b13babd463c54f88d8ffd24fd4e4165b1e295f8_thumb-senior-health-sookjai.jpg",
                        "https://thailandgi.cdn.axa-contento-118412.eu/thailandgi/79e3cfaca0451629a670460437138502b28d00ca_bnr-product-care.jpg"	);
	
	public static final String VIEW_POLICY = "View Policy";

}
