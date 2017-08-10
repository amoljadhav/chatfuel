package chatfuel.dcx.springboot.model;


public class Attachment {
	private Payload payload;

	private String type;

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
