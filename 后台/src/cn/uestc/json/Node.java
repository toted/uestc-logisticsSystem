package cn.uestc.json;

public class Node {

	private String position;
	private String arriveTime;
	private String sendTime;
	private boolean isFly;
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public boolean getIsFly() {
		return isFly;
	}
	public void setIsFly(boolean isFly) {
		this.isFly = isFly;
	}
	
}
