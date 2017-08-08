package chatfuel.dcx.springboot.controller;

import org.json.JSONObject;

public class PolicyDetailsModel {

	
	private String Name;

    private String StageName;

    private String Premium_Due_Date__c;

    private double Amount;

    private String GWPolicyNumber__c;

    private String Id;

	private AttributeType attributes;
	public PolicyDetailsModel(JSONObject response) {
		Premium_Due_Date__c = response.getString("Premium_Due_Date__c");
		StageName = response.getString("StageName");
		GWPolicyNumber__c = response.getString("GWPolicyNumber__c");
		Amount = response.getDouble("Amount");
		JSONObject jsonObj2 = response.getJSONObject("attributes");
		String type = jsonObj2.getString("type");
		String url = jsonObj2.getString("url");
		AttributeType attr = new AttributeType();
		attr.setType(type);
		attr.setUrl(url);
		attributes = attr;
		System.out.println(toString());
	}

	public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getStageName ()
    {
        return StageName;
    }

    public void setStageName (String StageName)
    {
        this.StageName = StageName;
    }

    public String getPremium_Due_Date__c ()
    {
        return Premium_Due_Date__c;
    }

    public void setPremium_Due_Date__c (String Premium_Due_Date__c)
    {
        this.Premium_Due_Date__c = Premium_Due_Date__c;
    }

    public double getAmount ()
    {
        return Amount;
    }

    public void setAmount (double Amount)
    {
        this.Amount = Amount;
    }

    public String getGWPolicyNumber__c ()
    {
        return GWPolicyNumber__c;
    }

    public void setGWPolicyNumber__c (String GWPolicyNumber__c)
    {
        this.GWPolicyNumber__c = GWPolicyNumber__c;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public AttributeType getAttributes ()
    {
        return attributes;
    }

    public void setAttributes (AttributeType attributes)
    {
        this.attributes = attributes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", StageName = "+StageName+", Premium_Due_Date__c = "+Premium_Due_Date__c+", Amount = "+Amount+", GWPolicyNumber__c = "+GWPolicyNumber__c+", Id = "+Id+", attributes = "+attributes+"]";
    }

}
