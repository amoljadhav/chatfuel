package chatfuel.dcx.springboot.utils;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import chatfuel.dcx.springboot.model.TextMessages;
import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;

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
	
	public static String getParsedAmount(double amount){
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String result = formatter.format(amount);
        return result;
	}
	
	public static void printLn(String message){
		System.out.println(message);
	}
	
	
	public static String getClaimsModelData(ClaimDetailsModel claimDetailsModel){
		String claimNo = UserStaticData.CLAIM_NUMBER + claimDetailsModel.getClaim_No__c()+API_CONST.CHANGE_LINE;
		String claimStatus = UserStaticData.CLAIM_STATUS + claimDetailsModel.getBot_Claim_Status__c()+API_CONST.CHANGE_LINE;
		String claimAmountPayable = UserStaticData.CLAIM_AMOUNT_PAYABLE + Utils.getParsedAmount(claimDetailsModel.getClaim_Amount_Payable_in_Claim_Currency__c())+API_CONST.CHANGE_LINE;
		
		String msgText = claimNo+claimStatus+claimAmountPayable;
		return msgText;
	}
	
	public static String getPolicyModelData(PolicyDetailsModel policyDetailsModel){
		String policyNum = UserStaticData.POLICY_NUMBER + policyDetailsModel.getName()+API_CONST.CHANGE_LINE;
		String policyDate = UserStaticData.POLICY_DATE + policyDetailsModel.getPaid_to_date__c()+API_CONST.CHANGE_LINE;
		String paymentMethod = UserStaticData.PAYMENT_METHOD + policyDetailsModel.getBot_Payment__c()+API_CONST.CHANGE_LINE;
		String paymentMode = UserStaticData.PAYMENT_MODE  + policyDetailsModel.getBot_Payment_Mode__c()+API_CONST.CHANGE_LINE;
		String policyStatus = UserStaticData.POLICY_STATUS + policyDetailsModel.getBot_Policy_Status__c()+API_CONST.CHANGE_LINE;
		String totalPremium = UserStaticData.TOTAL_PREMIUM + Utils.getParsedAmount(policyDetailsModel.getTotal_Premium__c())+API_CONST.CHANGE_LINE;
	
		String msgTxt = policyNum+policyDate+paymentMethod+paymentMode+policyStatus+totalPremium;
		return msgTxt;
	}

}
