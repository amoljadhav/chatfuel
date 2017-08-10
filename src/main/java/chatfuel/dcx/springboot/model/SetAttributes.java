package chatfuel.dcx.springboot.model;

import java.util.ArrayList;

import org.json.JSONObject;

import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;

public class SetAttributes {

	private SetAttributesData set_attributes;
	private TextMessages[] messages;

	public SetAttributes(PolicyDetailsModel policyDetailsModel) {

		messages = new TextMessages[8];
		TextMessages tm1 = new TextMessages();
		tm1.setText("Policy Number is " + policyDetailsModel.getName());

		TextMessages tm2 = new TextMessages();
		tm2.setText("Policy Id is " + policyDetailsModel.getId());

		TextMessages tm3 = new TextMessages();
		tm3.setText("Policy paid date is " + policyDetailsModel.getPaid_to_date__c());

		TextMessages tm4 = new TextMessages();
		tm4.setText("Policy Payment method name is " + policyDetailsModel.getPayment_method__c());

		TextMessages tm5 = new TextMessages();
		tm5.setText("Policy mode is " + policyDetailsModel.getPayment_mode__c());

		TextMessages tm6 = new TextMessages();
		tm6.setText("Policy status is " + policyDetailsModel.getPolicy_status__c());

		// TextMessages tm7 = new TextMessages();
		// tm7.setText("Stage name is "+ policyDetailsModel.getStageName());
		//
		TextMessages tm8 = new TextMessages();
		tm8.setText("Total permium for the policy is " + policyDetailsModel.getTotal_Premium__c());

		TextMessages tm9 = new TextMessages();
		tm9.setText("Attribute type for the policy is " + policyDetailsModel.getAttributes().getType());

		// TextMessages tm10 = new TextMessages();
		// tm10.setText("Attribute url for the policy is "+
		// policyDetailsModel.getAttributes().getUrl());
		//

		messages[0] = tm1;
		messages[1] = tm2;
		messages[2] = tm3;
		messages[3] = tm4;
		messages[4] = tm5;
		messages[5] = tm6;
		messages[6] = tm8;
		// messages.add(tm7);
		messages[7] = tm9;
		// messages.add(tm10);

	}

	public SetAttributes(ClaimDetailsModel claimDetailsModel) {
		messages = new TextMessages[5];

		TextMessages tm1 = new TextMessages();
		tm1.setText("Claim Number is " + claimDetailsModel.getName());

		TextMessages tm2 = new TextMessages();
		tm2.setText("Claim Id is " + claimDetailsModel.getId());

		TextMessages tm3 = new TextMessages();
		tm3.setText("Claim number is " + claimDetailsModel.getClaim_No__c());

		TextMessages tm4 = new TextMessages();
		tm4.setText("Transaction status is " + claimDetailsModel.getTransaction_status__c());

		TextMessages tm9 = new TextMessages();
		tm9.setText("Attribute type for the policy is " + claimDetailsModel.getAttributes().getType());

		TextMessages tm10 = new TextMessages();
		tm10.setText("Attribute url for the policy is " + claimDetailsModel.getAttributes().getUrl());

		messages[0] = tm1;
		messages[1] = tm2;
		messages[2] = tm3;
		messages[3] = tm4;
		messages[4] = tm9;
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
