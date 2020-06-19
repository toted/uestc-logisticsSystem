package cn.uestc.json;

public class Login {

	private int code;
	private String message;
	private String session;
	
	public void setCode(int code) {
	    this.code=code;
	}
	public int getCode() {
	    return this.code;
	}
	public void setMessage(String message) {
	    this.message=message;
	}
	public String getMessage() {
	    return this.message;
	}
	public void setSession(String session) {
	    this.session=session;
	}
	public String getSession() {
	    return this.session;
	}
	
}
