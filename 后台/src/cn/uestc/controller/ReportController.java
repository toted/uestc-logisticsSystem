package cn.uestc.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import cn.uestc.util.*;
import cn.uestc.json.*;
import cn.uestc.pojo.*;
import cn.uestc.service.*;

@Controller
public class ReportController {

	@Autowired
	GoodsService goodsService;
	@Autowired
	SessionService sessionService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/report")
	public void reportController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,ParseException {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		Report object = new Report();
		
		String token = request.getParameter("token");
		int startId = Integer.parseInt(request.getParameter("startId"));
		int endId = Integer.parseInt(request.getParameter("endId"));
		String session = request.getParameter("session");
		int weight = Integer.parseInt(request.getParameter("weight"));
		if(token.equals(TOKEN) == false) {
			object.setCode(1);
			object.setMessage("parameter error");
		}
		else {
			/*
			 * 增加
			 */
			String time = df.format(new Date());
			Session sessionNow = new Session(null,session,time);
			if(sessionService.selectSessionCount(sessionNow) == 0) {
				object.setCode(2);
				object.setMessage("session error");
			}
			else {
				sessionService.update(sessionNow);
				String userId = sessionService.selectSession(sessionNow);
				Goods goods = new Goods(userId,Generate.generateGoodsId(time),startId,endId,time,weight);
				LogisticsInfo logi = Dijkstra.dijkstra(startId, endId, weight, (new User()).vip(userId), time);
				goods.setPrice(logi.getPrice());
				goods.setWasteTime(logi.getTime());
				goodsService.insert(goods);
				object.setCode(0);
				object.setMessage("success");
				object.setPrice(logi.getPrice());
				object.setTime(logi.getTime());
			}
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
	
}
