package cn.uestc.json;

import java.util.List;

public class LogisticsInfo {

	private int price;
	private String time;
	List<Node> path;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<Node> getPath() {
		return path;
	}
	public void setPath(List<Node> path) {
		this.path = path;
	}
	
}
