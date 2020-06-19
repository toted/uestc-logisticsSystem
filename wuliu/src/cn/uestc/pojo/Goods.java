package cn.uestc.pojo;

public class Goods {

	private String userId;
	private String goodsId;
	private int start;
	private int end;
	private String time;
	private int weight;
	private int price;
	private String wasteTime;
	public Goods() {
		
	}
	public Goods(String userId,String goodsId,int start,int end,String time,int weight) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.start = start;
		this.end = end;
		this.time = time;
		this.weight = weight;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getWasteTime() {
		return wasteTime;
	}
	public void setWasteTime(String wasteTime) {
		this.wasteTime = wasteTime;
	}
	
	
}
