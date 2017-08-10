package chatfuel.dcx.springboot.model;

public class Payload {

	private String template_type = "button";

	private String text;

	private Buttons[] buttons;
	
	public Payload(Buttons[] buttons, String text){
		this.text = text;
		this.buttons = buttons;
	}

	public String getTop_element_style() {
		return template_type;
	}

	public void setTop_element_style(String template_type) {
		this.template_type = template_type;
	}

	public String getTemplate_type() {
		return text;
	}

	public void setTemplate_type(String text) {
		this.text = text;
	}

	public Buttons[] getElements() {
		return buttons;
	}

	public void setElements(Buttons[] buttons) {
		this.buttons = buttons;
	}
}
