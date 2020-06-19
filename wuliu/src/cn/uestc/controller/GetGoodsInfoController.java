package cn.uestc.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class GetGoodsInfoController {

	@Autowired
	GoodsService goodsService;
	@Autowired
	SessionService sessionService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/getGoodsInfo")
	public void getGoodsInfoController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,ParseException {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		GetGoodsInfo object = new GetGoodsInfo();
		String token = request.getParameter("token");
		String session = request.getParameter("session");
		String text = request.getParameter("text");
		if(token.equals(TOKEN) == false) {
			object.setCode(1);
			object.setMessage("parameter error");
		}
		else {
			/*
			 * 判断session，返回物品信息
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
				Goods goods = new Goods(null,text,0,0,time,0);
				Goods goodsNow = goodsService.select(goods);
				object.setCode(0);
				object.setMessage("success");
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderCreateTime(goodsNow.getTime());
				orderInfo.setOrderNo(text);
				LogisticsInfo logi = Dijkstra.dijkstra(goodsNow.getStart(), goodsNow.getEnd(), goodsNow.getWeight(),(new User()).vip(userId) ,goodsNow.getTime());
				orderInfo.setSendTime(Dijkstra.addTime(goodsNow.getTime(),1440*60 + 60*Dijkstra.timeConvert(logi.getTime())));
				List<GetGoodsInfoInfo> logisticsData = new ArrayList<GetGoodsInfoInfo>();
				List<Node> path = logi.getPath();
				int flag = 0;//标志
				for(int i=0;i<path.size();i++) {
					GetGoodsInfoInfo info = new GetGoodsInfoInfo();
					if(flag == 0) {
						if(path.get(i).getSendTime().equals("-") == true) {
							if(time.compareTo(path.get(i).getArriveTime()) >= 0) {
								info.setTime(path.get(i).getArriveTime());
								info.setContent("您的订单已到达终点(" + path.get(i).getPosition() + ")");
								flag = 1;
								logisticsData.add(info);
								info = new GetGoodsInfoInfo();
							}
						}
						else {
							if(time.compareTo(path.get(i).getSendTime()) >= 0) {
								info.setTime(time);
								info.setContent("您的订单正在路上飞奔");
								logisticsData.add(info);
								info = new GetGoodsInfoInfo();
								info.setTime(path.get(i).getSendTime());
								info.setContent("您的订单已从"+path.get(i).getPosition()+"出发");
								logisticsData.add(info);
								info = new GetGoodsInfoInfo();
								info.setTime(path.get(i).getArriveTime());
								info.setContent("您的订单已到达"+path.get(i).getPosition());
								logisticsData.add(info);
								info = new GetGoodsInfoInfo();
								flag = 1;
							}
							else if(time.compareTo(path.get(i).getArriveTime()) >= 0){
								info.setTime(time);
								info.setContent("您的订单正在"+path.get(i).getPosition());
								logisticsData.add(info);
								info = new GetGoodsInfoInfo();
								info.setTime(path.get(i).getArriveTime());
								info.setContent("您的订单已到达"+path.get(i).getPosition());
								logisticsData.add(info);
								info = new GetGoodsInfoInfo();
								flag = 1;
							}
						}
					}
					else {
						info.setTime(path.get(i).getSendTime());
						info.setContent("您的订单已从"+path.get(i).getPosition()+"出发");
						logisticsData.add(info);
						info = new GetGoodsInfoInfo();
						info.setTime(path.get(i).getArriveTime());
						info.setContent("您的订单已到达"+path.get(i).getPosition());
						logisticsData.add(info);
						info = new GetGoodsInfoInfo();
					}
				}
				object.setLogisticsData(logisticsData);
				object.setOrderInfo(orderInfo);
			}
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
	
	
}
