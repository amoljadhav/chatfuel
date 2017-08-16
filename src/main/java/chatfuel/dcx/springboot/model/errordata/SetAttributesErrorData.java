package chatfuel.dcx.springboot.model.errordata;

public class SetAttributesErrorData {

	private String error;
	private String error_description;
	
	public SetAttributesErrorData(String error, String description){
		this.error = error;
		this.error_description = description;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
