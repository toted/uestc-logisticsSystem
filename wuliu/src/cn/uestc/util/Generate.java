package cn.uestc.util;

import org.springframework.util.DigestUtils;

public class Generate {

	public static String generateSession(String time) {//根据当前时间字符串哈希生成session
		String md5 = DigestUtils.md5DigestAsHex(time.getBytes());
		String session = md5.substring(md5.length()-8, md5.length());
		return session;
	}
	
	public static String generateGoodsId(String time) {
		String md5 = DigestUtils.md5DigestAsHex(time.getBytes());
		String goodsId = md5.substring(md5.length()-10, md5.length());
		return goodsId;
	}
	
}
