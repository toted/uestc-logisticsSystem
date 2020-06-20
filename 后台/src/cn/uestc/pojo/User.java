package cn.uestc.pojo;

public class User {

	private String userId;//�û���
	private String password;//����
	private String email;//����
	private String code;//��֤��
	private boolean isVip;//�Ƿ�Ϊvip
	private boolean status;//�Ƿ񼤻�
	
	public User(){

	}
	public User(String userId,String password,String email,String code,boolean isVip,boolean status){
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.code = code;
		this.status = status;
		this.isVip = isVip;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean getIsVip() {
		return isVip;
	}
	public void setIsVip(boolean isVip) {
		this.isVip = isVip;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean vip(String name) {
		return name.contains("admin");
	}
	
}
