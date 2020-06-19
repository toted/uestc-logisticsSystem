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
public class GetNodeInfoController {

	@Autowired
	GoodsService goodsService;
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间格式
	public static final String TOKEN = "UZIYONGYUANDESHEN"; //token
	
	@RequestMapping("/getNodeInfo")
	public void getNodeInfoController(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,ParseException {
		//初始化
		Init.init(request,response,df);
		Gson gson = new Gson();
		Writer out = response.getWriter();
		GetNodeInfo object = new GetNodeInfo();
		String token = request.getParameter("token");
		int nodeNum = Integer.parseInt(request.getParameter("nodeNum"));
		if(token.equals(TOKEN) == false) {
			object.setCode(1);
			object.setMessage("parameter error");
		}
		else {
			/*
			 * 返回该节点所有物品信息
			 */
			Goods goods = new Goods();
			List<Goods> list = goodsService.selectAll(goods);
			String time = df.format(new Date());
			List<GetNodeInfoInfo> infoList = new ArrayList<GetNodeInfoInfo>();
			int cnt = 0;
			for(int i=0;i<list.size();i++) {
				GetNodeInfoInfo info = new GetNodeInfoInfo();
				Goods good = list.get(i);
				LogisticsInfo logi = Dijkstra.dijkstra(good.getStart(), good.getEnd(), good.getWeight(), (new User()).vip(good.getUserId()), good.getTime());
				List<Node> path = logi.getPath();
				String lastName = Dijkstra.name[good.getEnd()];
				int flag = 0;
				for(int j=0;j<path.size();j++) {
					Node node = path.get(j);
					if(Dijkstra.name[nodeNum].equals(node.getPosition()) == true && flag == 0) {
						if(nodeNum == good.getEnd()) {
							info.setToArea("-");
							flag = 1;
							if(time.compareTo(node.getArriveTime()) >= 0) {
								info.setType("已签收");
							}
							else {
								info.setType("未接收");
							}
						}
						else if(nodeNum == good.getStart()){
							info.setFromArea("-");
							info.setToArea(lastName);
							flag = 1;
							info.setCode(String.valueOf(cnt+1));
							cnt++;
							info.setText(good.getGoodsId());
							if(time.compareTo(node.getSendTime()) > 0) {
								info.setType("已发送");
							}
							else if(time.compareTo(node.getArriveTime()) < 0) {
								info.setType("未接收");
							}
							else {
								info.setType("已接收");
							}
							infoList.add(info);
							break;
						}
						else {
							info.setToArea(lastName);
							flag = 1;
							if(time.compareTo(node.getSendTime()) > 0) {
								info.setType("已发送");
							}
							else if(time.compareTo(node.getArriveTime()) < 0) {
								info.setType("未接收");
							}
							else {
								info.setType("已接收");
							}
						}
					}
					else if(flag == 1) {
						info.setFromArea(node.getPosition());
						info.setCode(String.valueOf(cnt+1));
						cnt++;
						info.setText(good.getGoodsId());
						infoList.add(info);
						break;
					}
					lastName = node.getPosition();
				}
			}
			object.setCode(0);
			object.setInfo(infoList);
			object.setMessage("success");
		}
		String json = gson.toJson(object);
		out.write(json);
		out.flush();
	}
	
}
