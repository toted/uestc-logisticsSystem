package cn.uestc.json;

public class OrderInfo {

	private String orderNo;
	private String orderCreateTime;
	private String sendTime;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String time) {
		this.sendTime = "商品预计在" + time.split(" ")[0] + "前送达";
	}
	
}
