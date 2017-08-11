package chatfuel.dcx.springboot.model;


public class ListAttachment {
	private ListPayload payload;

	private String type = "template";

	public ListAttachment(ListPayload payload){
		this.payload = payload;
	}
	
	public ListPayload getPayload() {
		return payload;
	}

	public void setPayload(ListPayload payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
