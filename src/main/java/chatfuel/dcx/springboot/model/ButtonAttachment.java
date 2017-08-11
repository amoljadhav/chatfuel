package chatfuel.dcx.springboot.model;


public class ButtonAttachment {
	private ButtonPayload payload;

	private String type = "template";

	public ButtonAttachment(ButtonPayload payload){
		this.payload = payload;
	}
	
	public ButtonPayload getPayload() {
		return payload;
	}

	public void setPayload(ButtonPayload payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
