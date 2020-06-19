package cn.uestc.json;

import java.util.List;

public class GetGoodsInfo {

	private int code;
	private String message;
	private OrderInfo orderInfo;
	private List<GetGoodsInfoInfo> logisticsData;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public OrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	public List<GetGoodsInfoInfo> getLogisticsData() {
		return logisticsData;
	}
	public void setLogisticsData(List<GetGoodsInfoInfo> logisticsData) {
		this.logisticsData = logisticsData;
	}
	
}
