package chatfuel.dcx.springboot.model;

import java.util.ArrayList;
import java.util.List;

import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;

public class MessageBody {

	private List<TextMessages> messages;

	public List<TextMessages> getMessages() {
		return messages;
	}
	
	public MessageBody(PolicyDetailsModel policyDetailsModel){
		messages = new ArrayList<TextMessages>();
		
		TextMessages tm1 = new TextMessages();
		tm1.setText("Policy Number is " + policyDetailsModel.getName());
		
		TextMessages tm2 = new TextMessages();
		tm2.setText("Policy Id is "+ policyDetailsModel.getId());
		
		TextMessages tm3 = new TextMessages();
		tm3.setText("Policy paid date is "+ policyDetailsModel.getPaid_to_date__c());
		
		TextMessages tm4 = new TextMessages();
		tm4.setText("Policy Payment method name is "+ policyDetailsModel.getPayment_method__c());
		
		TextMessages tm5 = new TextMessages();
		tm5.setText("Policy mode is "+ policyDetailsModel.getPayment_mode__c());
		
		TextMessages tm6 = new TextMessages();
		tm6.setText("Policy status is "+ policyDetailsModel.getPolicy_status__c());
		
//		TextMessages tm7 = new TextMessages();
//		tm7.setText("Stage name is "+ policyDetailsModel.getStageName());
//		
		TextMessages tm8 = new TextMessages();
		tm8.setText("Total permium for the policy is "+ policyDetailsModel.getTotal_Premium__c());
		
		TextMessages tm9 = new TextMessages();
		tm9.setText("Attribute type for the policy is "+ policyDetailsModel.getAttributes().getType());
		
		TextMessages tm10 = new TextMessages();
		tm10.setText("Attribute url for the policy is "+ policyDetailsModel.getAttributes().getUrl());
		
		
		messages.add(tm1);
		messages.add(tm2);
		messages.add(tm3);
		messages.add(tm4);
		messages.add(tm5);
		messages.add(tm6);
//		messages.add(tm7);
		messages.add(tm8);
		messages.add(tm9);
		messages.add(tm10);
		
	}
	
	
	
	public MessageBody(ClaimDetailsModel claimDetailsModel){
		messages = new ArrayList<TextMessages>();
		
		TextMessages tm1 = new TextMessages();
		tm1.setText("Claim Number is " + claimDetailsModel.getName());
		
		TextMessages tm2 = new TextMessages();
		tm2.setText("Claim Id is "+ claimDetailsModel.getId());
		
		TextMessages tm3 = new TextMessages();
		tm3.setText("Claim number is "+ claimDetailsModel.getClaim_No__c());
		
		TextMessages tm4 = new TextMessages();
		tm4.setText("Transaction status is "+ claimDetailsModel.getTransaction_status__c());
		
		TextMessages tm9 = new TextMessages();
		tm9.setText("Attribute type for the policy is "+ claimDetailsModel.getAttributes().getType());
		
		TextMessages tm10 = new TextMessages();
		tm10.setText("Attribute url for the policy is "+ claimDetailsModel.getAttributes().getUrl());
		
		
		messages.add(tm1);
		messages.add(tm2);
		messages.add(tm3);
		messages.add(tm4);
		messages.add(tm9);
		messages.add(tm10);
	}

	public void setMessages(List<TextMessages> messages) {
		this.messages = messages;
	}
	
	public String toString(){
		return "this is a string";
	}
}
