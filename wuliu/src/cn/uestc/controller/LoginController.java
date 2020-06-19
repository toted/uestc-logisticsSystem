package cn.uestc.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
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
import cn.uestc.pojo.Session;
import cn.uestc.pojo.User;
import cn.uestc.service.*;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired 
	SessionService sessionService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/login")
	public void loginController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		Login object = new Login();
		String token = request.getParameter("token");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		if(token.equals(TOKEN) == false) {
			object.setCode(3);
			object.setMessage("token error");
		}
		else if(Validate.validateId(userId) == false) {
			object.setCode(3);
			object.setMessage("userId error");
		}
		else if(Validate.validatePassword(password) == false) {
			object.setCode(3);
			object.setMessage("password error");
		}
		else {
			/*
			 * 判断登录(用户名或者邮箱和密码都可以登录)
			 */
			User user = new User(userId,password," ",null,false,false);
			User user1 = new User(" ",password,userId,null,false,false);
			if(userService.selectCount(user1) != 0) {
				User user2 = userService.loginSelectCount1(user1);
				if(user2 == null) {
					object.setCode(2);
					object.setMessage("password error");
				}
				else if(user2.getPassword().equals(user1.getPassword()) == true) {
					//登录成功
					String time = df.format(new Date());
					Session session = new Session(user2.getUserId(),Generate.generateSession(time),time);
					if(sessionService.selectCount(session) == 0) {
						sessionService.insert(session);
						object.setCode(0);
						object.setMessage("success");
						object.setSession(session.getSession());
					}
					else {
						Session session1 = sessionService.select(session);
						session1.setTime(time);
						sessionService.update(session1);
						object.setCode(0);
						object.setMessage("success");
						object.setSession(session1.getSession());
					}	
				}
				else {
					object.setCode(2);
					object.setMessage("password error");
				}
			}
			else if(userService.selectCount(user) != 0) {
				User user2 = userService.loginSelectCount(user);
				if(user2 == null) {
					object.setCode(2);
					object.setMessage("password error");
				}
				else if(user2.getPassword().equals(user.getPassword()) == true) {
					//登录成功
					String time = df.format(new Date());
					Session session = new Session(user2.getUserId(),Generate.generateSession(time),time);
					if(sessionService.selectCount(session) == 0) {
						sessionService.insert(session);
						object.setCode(0);
						object.setMessage("success");
						object.setSession(session.getSession());
					}
					else {
						Session session1 = sessionService.select(session);
						session1.setTime(time);
						sessionService.update(session1);
						object.setCode(0);
						object.setMessage("success");
						object.setSession(session1.getSession());
					}
				}
				else {
					object.setCode(2);
					object.setMessage("password error");
				}
			}
			else {
				object.setCode(1);
				object.setMessage("userId error");
			}
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
//	@RequestMapping("/test")
//	public void test(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,Exception {
//		//初始化
//		Init.init(request,response,df);
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
//		User user = new User(userId,password," ",null,false,false);
//		System.out.println(userService.loginSelectCount(user));
//		User user1 = new User(" ","cbh123!",userId,null,false,false);
//		System.out.println(userService.loginSelectCount1(user1));
//		
//	}
}
