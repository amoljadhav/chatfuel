package chatfuel.dcx.springboot.model;

public class Elements {
	private String title;

	private String image_url;

	private String subtitle;

	private Buttons[] buttons;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Buttons[] getButtons() {
		return buttons;
	}

	public void setButtons(Buttons[] buttons) {
		this.buttons = buttons;
	}

}
