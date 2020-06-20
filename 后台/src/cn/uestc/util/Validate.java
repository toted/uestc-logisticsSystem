package cn.uestc.util;

public class Validate {

	public static boolean validateId(String id) {
		if(id.length() >= 3 && id.length() <=18) {
			return true;
		}
		else return false;
	}
	
	public static boolean validatePassword(String password) {
		String []group = {
				"!@#$%^&*()-+.",
				"abcdefghijklmnopqrstuvwxyz",
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"0123456789",
		};
		if(!(password.length() >= 6 && password.length() <= 12)) {
			return false;
		}
		int[] status = {0,0,0,0};
		for(int i = 0; i < password.length(); i++) {
			int flag = 0;//检查是否包含非法字符
			for(int j = 0; j < 4; j++) {
				if(group[j].indexOf(password.charAt(i)) != -1) {
					flag = 1;
					status[j] = 1;
					break;
				}
			}
			if(flag == 0)return false;
		}
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			cnt += status[i];
		}
		if(cnt<3) {
			return false;
		}
		return true;
	}
	
}
