package cn.uestc.pojo;

public class Session {

	private String userId;
	private String session;
	private String time;
	
	public Session() {
		
	}
	public Session(String userId,String session,String time) {
		this.userId = userId;
		this.session = session;
		this.time = time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
