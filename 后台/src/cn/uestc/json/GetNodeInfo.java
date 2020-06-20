package cn.uestc.json;

import java.util.List;

public class GetNodeInfo {

	private int code;
	private String message;
	private List<GetNodeInfoInfo> info;
	
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
	public List<GetNodeInfoInfo> getInfo() {
		return info;
	}
	public void setInfo(List<GetNodeInfoInfo> info) {
		this.info = info;
	}
	
}
