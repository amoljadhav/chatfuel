package chatfuel.dcx.springboot.model;

import org.json.JSONObject;

import chatfuel.dcx.springboot.utils.API_CONST;
import chatfuel.dcx.springboot.utils.Utils;

public class Elements {
	private String title;

	private String image_url;

	private String subtitle;
	

	private Buttons[] buttons;

	
	public Elements(JSONObject jsonObject,Buttons[] buttons){
		this.subtitle = Utils.getData(jsonObject, "Name");

		this.buttons = buttons;
		getRandomData();
	}
	
	private void getRandomData() {

		int randomNum = (int) ( Math.random() * 7); // will return either 1 or 2
		randomNum = (randomNum>=0 &&randomNum<=6)?randomNum:6;
	    title = API_CONST.ELEMENTS_TITLE.get(randomNum);
	    image_url = API_CONST.ELEMENTS_URL.get(randomNum);
	}

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
