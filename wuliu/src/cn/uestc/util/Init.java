package cn.uestc.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Init {

	public static void init(HttpServletRequest request,HttpServletResponse response,SimpleDateFormat df) throws ServletException,IOException {
		//��Ӧ����
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//����ʱ��
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		//��������
		request.setCharacterEncoding("UTF-8");
	}
	
}
