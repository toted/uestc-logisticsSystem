package cn.uestc.controller;

import java.io.IOException;
import java.io.Writer;
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
public class VerificationController {

	@Autowired
	UserService userService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/verification")
	public void verificationController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,Exception {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		Verification object = new Verification();
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		if(token.equals(TOKEN) == false) {
			object.setCode(1);
			object.setMessage("token failed");
		}
		else if(Validate.validateId(userId) == false) {
			object.setCode(1);
			object.setMessage("userId error");
		}
		else if(Validate.validatePassword(password) == false) {
			object.setCode(1);
			object.setMessage("password error");
		}
		else {
			User user = new User(userId,password,email,null,(new User()).vip(userId),false);
			int cnt = userService.selectCount(user);
			if(cnt >= 2) {//邮箱或者用户名已存在
				object.setCode(2);
				object.setMessage("email or userId existed");
				String json = gson.toJson(object);
				out.write(json);
				out.flush();
				return ;
			}
			else if(cnt == 1) {
				User user1 = userService.select(user);
				if(user1.getStatus() == true) {
					object.setCode(2);
					object.setMessage("email or userId existed");
				}
				else {
					String code = Email.send(userId, email);
					user.setIsVip(user.vip(userId));
					user.setCode(code);
					userService.update(user);
					object.setCode(0);
					object.setMessage("success");
				}
				String json = gson.toJson(object);
				out.write(json);
				out.flush();
				return ;
			}
			String code = Email.send(userId, email);//验证码
			user.setCode(code);
			userService.insert(user);
			object.setCode(0);
			object.setMessage("success");
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
}
