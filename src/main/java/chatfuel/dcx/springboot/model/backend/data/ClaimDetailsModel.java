package chatfuel.dcx.springboot.model.backend.data;

import org.json.JSONObject;

import chatfuel.dcx.springboot.utils.Utils;

public class ClaimDetailsModel {

	//private String Transaction_status__c;
	private AttributeType attributes;
	private String Id;
	private String Claim_No__c;
	private String Name;
	private String Bot_Claim_Status__c;
	private double Claim_Amount_Payable_in_Claim_Currency__c;

	public ClaimDetailsModel(JSONObject response) {

		//Transaction_status__c = Utils.getData(response, "Transaction_status__c");
		Bot_Claim_Status__c  = Utils.getData(response, "Bot_Claim_Status__c");
		if(response.has("Claim_Amount_Payable_in_Claim_Currency__c") && !response.isNull("Claim_Amount_Payable_in_Claim_Currency__c")){
			Claim_Amount_Payable_in_Claim_Currency__c = response.getDouble("Claim_Amount_Payable_in_Claim_Currency__c");
		}
		Name = Utils.getData(response, "Name");
		Claim_No__c = Utils.getData(response, "Claim_No__c");
		Id = Utils.getData(response, "Id");

		if (response.has("attributes") && !response.isNull("attributes")) {
			JSONObject jsonObj2 = response.getJSONObject("attributes");

			String type = Utils.getData(jsonObj2, "type");
			String url = Utils.getData(jsonObj2, "url");
			AttributeType attr = new AttributeType();
			attr.setType(type+" : "+Name);
			attr.setUrl(url);
			attributes = attr;
		}

		Utils.printLn(toString());
	}
//
//	public String getTransaction_status__c() {
//		return Transaction_status__c;
//	}
//
//	public void setTransaction_status__c(String transaction_status__c) {
//		Transaction_status__c = transaction_status__c;
//	}

	public AttributeType getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributeType attributes) {
		this.attributes = attributes;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getClaim_No__c() {
		return Claim_No__c;
	}

	public void setClaim_No__c(String claim_No__c) {
		Claim_No__c = claim_No__c;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public String getBot_Claim_Status__c() {
		return Bot_Claim_Status__c;
	}

	public void setBot_Claim_Status__c(String bot_Claim_Status__c) {
		Bot_Claim_Status__c = bot_Claim_Status__c;
	}

	public double getClaim_Amount_Payable_in_Claim_Currency__c() {
		return Claim_Amount_Payable_in_Claim_Currency__c;
	}

	public void setClaim_Amount_Payable_in_Claim_Currency__c(double claim_Amount_Payable_in_Claim_Currency__c) {
		Claim_Amount_Payable_in_Claim_Currency__c = claim_Amount_Payable_in_Claim_Currency__c;
	}


	@Override
	public String toString() {
		return "ClassPojo [Name = " + Name + ", Claim_No__c = " + Claim_No__c 
			//	+", Transaction_status__c = "+ Transaction_status__c 
				+ ", Id = " + Id + ", attributes = " + attributes + "]";
	}

}
