package chatfuel.dcx.springboot.model;

import chatfuel.dcx.springboot.model.backend.data.ClaimDetailsModel;
import chatfuel.dcx.springboot.model.backend.data.PolicyDetailsModel;
import chatfuel.dcx.springboot.utils.API_CONST;
import chatfuel.dcx.springboot.utils.UserStaticData;
import chatfuel.dcx.springboot.utils.Utils;

public class SetAttributes {

	private SetAttributesData set_attributes;
	private TextMessages[] messages;

	public SetAttributes(PolicyDetailsModel policyDetailsModel) {

		messages = new TextMessages[1];
		TextMessages tm1 = new TextMessages();
		tm1.setText(Utils.getPolicyModelData(policyDetailsModel));
		messages[0] = tm1;

	}

	public SetAttributes(ClaimDetailsModel claimDetailsModel) {
		messages = new TextMessages[1];

		TextMessages tm1 = new TextMessages();
		tm1.setText(Utils.getClaimsModelData(claimDetailsModel));
		messages[0] = tm1;
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
