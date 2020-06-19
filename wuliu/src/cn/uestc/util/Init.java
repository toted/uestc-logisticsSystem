package cn.uestc.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Init {

	public static void init(HttpServletRequest request,HttpServletResponse response,SimpleDateFormat df) throws ServletException,IOException {
		//响应设置
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//设置时区
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		//请求设置
		request.setCharacterEncoding("UTF-8");
	}
	
}
