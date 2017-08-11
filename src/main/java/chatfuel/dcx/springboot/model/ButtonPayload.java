package chatfuel.dcx.springboot.model;

public class ButtonPayload {

	private String template_type = "button";

	private String text;
		
	private Buttons[] buttons;
	
	public ButtonPayload(Buttons[] buttons, String text){
		this.text = text;
		this.buttons = buttons;
	}


	public String getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Buttons[] getButtons() {
		return buttons;
	}

	public void setButtons(Buttons[] buttons) {
		this.buttons = buttons;
	}


}
