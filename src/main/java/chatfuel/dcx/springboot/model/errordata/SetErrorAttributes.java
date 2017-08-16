package chatfuel.dcx.springboot.model.errordata;

public class SetErrorAttributes {
	
	private SetAttributesErrorData set_attributes;
//	private String block_name;
//	private String type;
//	private String title;
	
	

	public SetErrorAttributes(String error, String description) {
		this.set_attributes = new SetAttributesErrorData(error, description);
	}
	public SetAttributesErrorData getSet_attributes() {
		return set_attributes;
	}
	public void setSet_attributes(SetAttributesErrorData set_attributes) {
		this.set_attributes = set_attributes;
	}
//	public String getBlock_name() {
//		return block_name;
//	}
//	public void setBlock_name(String block_name) {
//		this.block_name = block_name;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	
	
}
