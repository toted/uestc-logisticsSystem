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
import cn.uestc.service.*;
import cn.uestc.pojo.*;

@Controller
public class GetGoodsListController {

	@Autowired
	GoodsService goodsService;
	@Autowired
	SessionService sessionService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/getGoodsList")
	public void getGoodsListController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,ParseException {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		GetGoodsList object = new GetGoodsList();
		String token = request.getParameter("token");
		String session = request.getParameter("session");
		if(token.equals(TOKEN) == false) {
			object.setCode(1);
			object.setMessage("parameter error");
		}
		else {
			/*
			 * 判断session，访问物品表，返回和该用户有关的所有物流信息
			 */
			String time = df.format(new Date());
			Session sessionNow = new Session(null,session,time);
			if(sessionService.selectSessionCount(sessionNow) == 0) {
				object.setCode(2);
				object.setMessage("session error");
			}
			else {
				Goods goods = new Goods();
				String userId = sessionService.selectSession(sessionNow);
				goods.setUserId(userId);
				sessionService.update(sessionNow);//更新时间
				List<Goods> list = goodsService.selectId(goods);
				List<GetGoodsListInfo> info = new ArrayList<GetGoodsListInfo>();
				for(int i=0;i<list.size();i++) {
					GetGoodsListInfo listInfo = new GetGoodsListInfo();
					listInfo.setCode(String.valueOf(i+1));
					listInfo.setPrice(list.get(i).getPrice());
					listInfo.setText(list.get(i).getGoodsId());
					listInfo.setTime(list.get(i).getWasteTime());
					if(time.compareTo(Dijkstra.addTime(list.get(i).getTime(),60*Dijkstra.timeConvert(list.get(i).getWasteTime()))) >= 0) {
						listInfo.setType("已签收");
					}
					else {
						listInfo.setType("未签收");
					}
					info.add(listInfo);
				}
				object.setCode(0);
				object.setInfo(info);
				object.setMessage("success");
			}
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
	
}
