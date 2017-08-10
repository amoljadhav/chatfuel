package chatfuel.dcx.springboot.model.backend.data;

import org.json.JSONObject;

import chatfuel.dcx.springboot.utils.Utils;

public class PolicyDetailsModel {

	
	private String Name;

   // private String StageName;

    private String Paid_to_date__c;

    private double Total_Premium__c;

    private String Policy_status__c;
    
    private String Payment_method__c;
    
	private String Payment_mode__c;

    private String Id;

	private AttributeType attributes;

	public PolicyDetailsModel(JSONObject response) {
		Name = Utils.getData(response, "Name");
		Id = Utils.getData(response, "Id");
		Paid_to_date__c = Utils.getData(response, "Paid_to_date__c");
		Policy_status__c = Utils.getData(response, "Policy_status__c");
		Payment_method__c = Utils.getData(response, "Payment_method__c");
		Payment_mode__c = Utils.getData(response, "Payment_mode__c");
		
		if(response.has("Total_Premium__c") && !response.isNull("Total_Premium__c")){
			Total_Premium__c = response.getDouble("Total_Premium__c");
		}
		
		if(response.has("attributes") && !response.isNull("attributes")){
			JSONObject jsonObj2 = response.getJSONObject("attributes");
		
			String type =  Utils.getData(jsonObj2, "type");
			String url = Utils.getData(jsonObj2, "url");
			AttributeType attr = new AttributeType();
			attr.setType(type);
			attr.setUrl(url);
			attributes = attr;
		}
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

//    public String getStageName ()
//    {
//        return StageName;
//    }
//
//    public void setStageName (String StageName)
//    {
//        this.StageName = StageName;
//    }

    public String getPaid_to_date__c ()
    {
        return Paid_to_date__c;
    }

    public void setPaid_to_date__c (String Paid_to_date__c)
    {
        this.Paid_to_date__c = Paid_to_date__c;
    }

    public double getTotal_Premium__c ()
    {
        return Total_Premium__c;
    }

    public void setTotal_Premium__c (double Total_Premium__c)
    {
        this.Total_Premium__c = Total_Premium__c;
    }

    public String getPolicy_status__c ()
    {
        return Policy_status__c;
    }

    public void setPolicy_status__c (String Policy_status__c)
    {
        this.Policy_status__c = Policy_status__c;
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
    

    public String getPayment_method__c() {
		return Payment_method__c;
	}

	public void setPayment_method__c(String payment_method__c) {
		Payment_method__c = payment_method__c;
	}

	public String getPayment_mode__c() {
		return Payment_mode__c;
	}

	public void setPayment_mode__c(String payment_mode__c) {
		Payment_mode__c = payment_mode__c;
	}


    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", StageName = "
//       +StageName+", Paid_to_date__c = "
       +Paid_to_date__c+", Total_Premium__c = "+Total_Premium__c+", Policy_status__c = "+Policy_status__c+", Id = "+Id+", attributes = "+attributes+"]";
    }

}
