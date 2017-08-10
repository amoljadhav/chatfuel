package chatfuel.dcx.springboot.model.backend.data;

import org.json.JSONObject;

public class ClaimDetailsModel {

	private String Transaction_status__c;
	private AttributeType attributes;
	private String Id;
	private String Claim_No__c;
	private String Name;

	
	public ClaimDetailsModel(JSONObject response) {
		
		Transaction_status__c = response.getString("Transaction_status__c");
		Name = response.getString("Name");
		Claim_No__c = response.getString("Claim_No__c");
		Id = response.getString("Id");

		JSONObject jsonObj2 = response.getJSONObject("attributes");
		String type = jsonObj2.getString("type");
		String url = jsonObj2.getString("url");
		AttributeType attr = new AttributeType();
		attr.setType(type);
		attr.setUrl(url);
		attributes = attr;
		System.out.println(toString());
	}
	
	public String getTransaction_status__c() {
		return Transaction_status__c;
	}

	public void setTransaction_status__c(String transaction_status__c) {
		Transaction_status__c = transaction_status__c;
	}

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
	
    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", Claim_No__c = "+Claim_No__c+", Transaction_status__c = "+Transaction_status__c+", Id = "+Id+", attributes = "+attributes+"]";
    }


}
