package chatfuel.dcx.springboot.model;

import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;
import chatfuel.dcx.springboot.utils.UserStaticData;

public class SetAttributes {

	private SetAttributesData set_attributes;
	private TextMessages[] messages;

	public SetAttributes(PolicyDetailsModel policyDetailsModel) {

		messages = new TextMessages[6];
		TextMessages tm1 = new TextMessages();
		tm1.setText(UserStaticData.POLICY_NUMBER + policyDetailsModel.getName());

//		TextMessages tm2 = new TextMessages();
//		tm2.setText("Policy Id : " + policyDetailsModel.getId());

		TextMessages tm3 = new TextMessages();
		tm3.setText(UserStaticData.POLICY_DATE + policyDetailsModel.getPaid_to_date__c());

		TextMessages tm4 = new TextMessages();
		tm4.setText(UserStaticData.PAYMENT_METHOD + policyDetailsModel.getBot_Payment__c());

		TextMessages tm5 = new TextMessages();
		tm5.setText(UserStaticData.PAYMENT_MODE  + policyDetailsModel.getBot_Payment_Mode__c());

		TextMessages tm6 = new TextMessages();
		tm6.setText(UserStaticData.POLICY_STATUS + policyDetailsModel.getBot_Policy_Status__c());

		// TextMessages tm7 = new TextMessages();
		// tm7.setText("Stage name is "+ policyDetailsModel.getStageName());
		//
		TextMessages tm8 = new TextMessages();
		tm8.setText(UserStaticData.TOTAL_PREMIUM + policyDetailsModel.getTotal_Premium__c());

//		TextMessages tm9 = new TextMessages();
//		tm9.setText("Policy type : " + policyDetailsModel.getAttributes().getType());

		// TextMessages tm10 = new TextMessages();
		// tm10.setText("Attribute url for the policy is "+
		// policyDetailsModel.getAttributes().getUrl());
		//

		messages[0] = tm1;
	//	messages[1] = tm2;
		messages[1] = tm3;
		messages[2] = tm4;
		messages[3] = tm5;
		messages[4] = tm6;
		messages[5] = tm8;
		// messages.add(tm7);
//		messages[7] = tm9;
		// messages.add(tm10);

	}

	public SetAttributes(ClaimDetailsModel claimDetailsModel) {
		messages = new TextMessages[3];

		TextMessages tm1 = new TextMessages();
		tm1.setText(UserStaticData.CLAIM_NUMBER + claimDetailsModel.getClaim_No__c());

//		TextMessages tm2 = new TextMessages();
//		tm2.setText("Claim Id : " + claimDetailsModel.getId());

		//TextMessages tm3 = new TextMessages();
		//tm3.setText("Claim number : " + claimDetailsModel.getClaim_No__c());

		TextMessages tm4 = new TextMessages();
		tm4.setText(UserStaticData.CLAIM_STATUS + claimDetailsModel.getBot_Claim_Status__c());

		
		TextMessages tm5 = new TextMessages();
		tm5.setText(UserStaticData.CLAIM_AMOUNT_PAYABLE + claimDetailsModel.getClaim_Amount_Payable_in_Claim_Currency__c());

		
		
		
//		TextMessages tm9 = new TextMessages();
//		tm9.setText("Claim Type : " + claimDetailsModel.getAttributes().getType());

		//TextMessages tm10 = new TextMessages();
		//tm10.setText("Attribute url for the policy is " + claimDetailsModel.getAttributes().getUrl());

		messages[0] = tm1;
//		messages[1] = tm2;
		//messages[2] = tm3;
		messages[1] = tm4;
		messages[2] = tm5;
	//	messages[3] = tm9;
	}

	public SetAttributesData getSet_attributes() {
		return set_attributes;
	}

	public void setSet_attributes(SetAttributesData set_attributes) {
		this.set_attributes = set_attributes;
	}

	public TextMessages[] getMessages() {
		return messages;
	}

	public void setMessages(TextMessages[] messages) {
		this.messages = messages;
	}
}
