package chatfuel.dcx.springboot.model;

public class Payload {

	private String top_element_style;

	private String template_type;

	private Elements[] elements;

	public String getTop_element_style() {
		return top_element_style;
	}

	public void setTop_element_style(String top_element_style) {
		this.top_element_style = top_element_style;
	}

	public String getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	public Elements[] getElements() {
		return elements;
	}

	public void setElements(Elements[] elements) {
		this.elements = elements;
	}
}
