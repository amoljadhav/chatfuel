package chatfuel.dcx.springboot.model;

import java.util.ArrayList;
import java.util.List;

import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;
import chatfuel.dcx.springboot.utils.API_CONST;
import chatfuel.dcx.springboot.utils.UserStaticData;
import chatfuel.dcx.springboot.utils.Utils;

public class MessageBody {

	private List<TextMessages> messages;

	public List<TextMessages> getMessages() {
		return messages;
	}
	public MessageBody(){
		messages = new ArrayList<>();
	}
	public MessageBody(PolicyDetailsModel policyDetailsModel){
		messages = new ArrayList<TextMessages>();
		TextMessages tm1 = new TextMessages();
		tm1.setText(Utils.getPolicyModelData(policyDetailsModel));
		messages.add(tm1);
		
	}
	
	
	
	public MessageBody(ClaimDetailsModel claimDetailsModel){
		messages = new ArrayList<TextMessages>();
		
		TextMessages tm1 = new TextMessages();
		tm1.setText(Utils.getClaimsModelData(claimDetailsModel));
		messages.add(tm1);
	}

	public void setMessages(List<TextMessages> messages) {
		this.messages = messages;
	}
	
	public String toString(){
		return "";
	}
}
