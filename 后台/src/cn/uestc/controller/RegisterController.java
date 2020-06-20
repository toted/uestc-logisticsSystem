package cn.uestc.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import cn.uestc.util.*;
import cn.uestc.json.*;
import cn.uestc.pojo.User;
import cn.uestc.service.*;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/register")
	public void registerController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		Register object = new Register();
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String password = request.getParameter("password"); 
		String code = request.getParameter("verificationCode");
		if(token.equals(TOKEN) == false || Validate.validateId(userId) == false || Validate.validatePassword(password) == false || code.length() != 4) {
			object.setCode(1);
			object.setMessage("parameter error");
		}
		else {
			/*
			 * 判断user表中是否存在相应记录，存在就激活
			 */
			User user = new User(userId,password,email,code,(new User()).vip(userId),true);
			int cnt = userService.selectCount(user);
			if(cnt == 0) {
				object.setCode(3);
				object.setMessage("verification error");
			}
			else if(cnt == 1) {
				User userNow = userService.select(user);
				if(userNow.getStatus() == true) {
					object.setCode(2);
					object.setMessage("userId error");
				}
				else {
					if(userId.equals(userNow.getUserId()) == false) {
						object.setCode(2);
						object.setMessage("userId error");
					}
					else if(email.equals(userNow.getEmail()) == false) {
						object.setCode(2);
						object.setMessage("email error");
					}
					else if(code.equals(userNow.getCode()) == false) {
						object.setCode(2);
						object.setMessage("code error");
					}
					else {
						user.setIsVip(user.vip(userId));
						user.setCode(code);
						userService.update(user);
						object.setCode(0);
						object.setMessage("success");
					}
				}
			}
			else {
				object.setCode(2);
				object.setMessage("userId error");
			}
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
	
}
