package chatfuel.dcx.springboot.model;

public class ListPayload {

	private String template_type = "list";

	private String top_element_style = "large";

	private Elements[] elements;

	public ListPayload(Elements[] buttons) {
		// this.text = text;
		this.elements = buttons;
	}

	public String getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	// public String getText() {
	// return text;
	// }
	//
	// public void setText(String text) {
	// this.text = text;
	// }

	public Elements[] getElements() {
		return elements;
	}

	public void setElements(Elements[] elements) {
		this.elements = elements;
	}

	public String getTop_element_style() {
		return top_element_style;
	}

	public void setTop_element_style(String top_element_style) {
		this.top_element_style = top_element_style;
	}

}
