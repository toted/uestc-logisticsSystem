package cn.uestc.json;

import java.util.List;


public class GetGoodsList {

	private int code;
	private String message;
	private List<GetGoodsListInfo> info;
	
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
	public void setInfo(List<GetGoodsListInfo> info) {
	    this.info=info;
	}
	public List<GetGoodsListInfo> getInfo() {
	    return this.info;
	}
	
}
