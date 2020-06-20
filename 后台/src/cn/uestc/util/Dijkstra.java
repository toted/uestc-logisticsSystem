package cn.uestc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.uestc.json.*;

public class Dijkstra {
	
//	public static void main(String[] args) throws ParseException {
//		String date="2020-6-18 16:4:07";
//		LogisticsInfo logi=dijkstra(1,5,4,false,date);
//		System.out.println(logi.getPrice());
//		System.out.println(logi.getTime());
//		List<Node> mypath=logi.getPath();
//		for(int i=mypath.size()-1;i>=0;i--){
//			Node node = mypath.get(i);
//			System.out.print("->");
//			System.out.print(node.getPosition());
//			System.out.print(node.getSendTime());
//		}
//
//	}
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final int n = 7;
	public static final int inf = 99999999;
	public static final int[][] timeCost = {
			{0,0,2,inf,14,inf,inf},
			{0,0,2,inf,14,inf,inf},
			{0,2,0,24,inf,inf,inf},
			{0,inf,24,0,18,6,2},
			{0,14,inf,18,0,inf,30},
			{0,inf,inf,6,inf,inf,11},
			{0,inf,inf,2,30,11,0}
	};//单位：分钟
	public static final int[][] flyTimeCost = {
			{0,0,2,inf,14,inf,inf},
			{0,0,2,inf,2,inf,inf},
			{0,2,0,24,inf,inf,inf},
			{0,inf,24,0,18,6,2},
			{0,2,inf,18,0,inf,2},
			{0,inf,inf,6,inf,inf,11},
			{0,inf,inf,2,2,11,0}
	};//单位：分钟
	public static final int[][] priceCost = {
			{0,0,0,0,0,0,0},
			{0,0,5,inf,7,inf,inf},
			{0,5,0,6,inf,inf,inf},
			{0,inf,6,0,6,4,4},
			{0,7,inf,6,0,inf,8},
			{0,inf,inf,4,inf,0,7},
			{0,inf,inf,4,8,7,0}
	};//单位：元
	public static final int[][] flyPriceCost = {
			{0,0,0,0,0,0,0},
			{0,0,5,inf,10,inf,inf},
			{0,5,0,6,inf,inf,inf},
			{0,inf,6,0,6,4,4},
			{0,10,inf,6,0,inf,11},
			{0,inf,inf,4,inf,0,7},
			{0,inf,inf,4,11,7,0}
	};//单位：元
	public static final String[] name = {
			" ","北京","郑州","济南","西安","重庆","成都"
	};
	public static final int[] wait = {0,1,2,3,4,5,6,7};
	public static LogisticsInfo dijkstra(int start,int end,int weight,boolean isVip,String time) throws ParseException{
		LogisticsInfo logi = new LogisticsInfo();
		if(isVip == true) { //时间最短
			int[] dis = new int[n]; //起点到所有点的最短路
			boolean[] vis = new boolean[n];
			int[] pre = new int[n];//前置节点
			for(int i=1;i<n;i++) {//初始化
				dis[i] = inf;
				vis[i] = false;
				pre[i] = -1;
			}
			dis[start] = 0;
			for(int j=1;j<n;j++) {
				int k = -1;
				int min = inf;
				for(int i=1;i<n;i++) {
					if(!vis[i]&&dis[i]<min) {
						min = dis[i];
						k = i;
					}
				}
				if(k == -1)break;
				vis[k] = true;
				for(int i=1;i<n;i++) {
					if(!vis[i]&&dis[k]+flyTimeCost[k][i]<dis[i]) {
						dis[i] = dis[k] + flyTimeCost[k][i];
						pre[i] = k;
					}
				}
			}
			logi.setTime(convertTime(dis[end]));
			List<Node> path = new ArrayList<Node>();
			Node node = new Node();
			int now = end;
			int price = 0;
			while(pre[now] != -1) {
				if(weight > 3)price += flyPriceCost[pre[now]][now] + (weight - 3) * 2;
				else price +=  flyPriceCost[pre[now]][now];
				now = pre[now];
			}
			logi.setPrice(price);
			String endTime = addTime(time,dis[end]*60);
			String nowTime = endTime;
			now = end;
			int k=0;
			while(now != -1) {
				node = new Node();
				node.setPosition(name[now]);
				node.setArriveTime(nowTime);
				if(now == end) {
					node.setSendTime("-");
				}
				else {
					node.setSendTime(nowTime);
				}
				if(pre[now] != -1) {
					nowTime = subTime(nowTime,60*flyTimeCost[pre[now]][now]);
				}			
				path.add(k,node);
				k++;
				//System.out.print(node.getPosition());
				//System.out.println(now);
				now = pre[now];
			}
			logi.setPath(path);
		}
		else { //运价最低
			int[] dis = new int[n]; //起点到所有点的最短路
			boolean[] vis = new boolean[n];
			int[] pre = new int[n];//前置节点
			for(int i=1;i<n;i++) {//初始化
				dis[i] = inf;
				vis[i] = false;
				pre[i] = -1;
			}
			dis[start] = 0;
			for(int j=1;j<n;j++) {
				int k = -1;
				int min = inf;
				for(int i=1;i<n;i++) {
					if(!vis[i]&&dis[i]<min) {
						min = dis[i];
						k = i;
					}
				}
				if(k == -1)break;
				vis[k] = true;
				for(int i=1;i<n;i++) {
					int cost; //实际运价
					if(weight > 3)cost = priceCost[k][i] + (weight - 3) * 2;
					else cost = priceCost[k][i];
					if(!vis[i]&&dis[k]+cost<dis[i]) {
						dis[i] = dis[k] + cost;
						pre[i] = k;
					}
				}
			}
			logi.setPrice(dis[end]);
			int timeSum = 0;
			int now = end;
			List<Node> path = new ArrayList<Node>();
			Node node = new Node();
			while(now != -1) {
				if(pre[now] != -1)
					timeSum += timeCost[pre[now]][now];
				if(now != end && now != start) {
					timeSum += wait[now];
				}
				if(now == start) {
					timeSum += 120;//加两个小时
				}
				now = pre[now];
			}
			logi.setTime(convertTime(timeSum));
			now = end;
			String endTime = addTime(time,timeSum*60);
			String nowTime = endTime;
			while(now != -1) {
				node = new Node();
				if(now == end) {
					node.setPosition(name[now]);
					node.setArriveTime(nowTime);
					node.setSendTime("-");
					node.setIsFly(false);
					nowTime = subTime(nowTime , timeCost[pre[now]][now]*60);
					now = pre[now];
				}
				else if(now == start) {
					node.setPosition(name[now]);
					node.setSendTime(nowTime);
					nowTime = subTime(nowTime,2*60*60);
					node.setArriveTime(nowTime);
					node.setIsFly(false);
					now = pre[now];
				}
				else {
					node.setPosition(name[now]);
					node.setSendTime(nowTime);
					nowTime = subTime(nowTime,wait[now]*60);
					node.setArriveTime(nowTime);
					nowTime = subTime(nowTime , timeCost[pre[now]][now]*60);
					node.setIsFly(false);
					now = pre[now];
				}
				path.add(node);
			}
			logi.setPath(path);
		}
		return logi;
		
	}
	public static int timeConvert(String time) {//单位为分
		int sum = 0;
		int day = 0;
		int flag = 0;
		int i = 0;
		for(;i<time.length();i++) {
			if(Character.isDigit(time.charAt(i)) == true) {
				day *= 10;
				day += Character.getNumericValue(time.charAt(i));
				flag = 1;
			}
			else if(flag == 1)break;
		}
		sum += day * 1440;
		day = 0;flag = 0;
		for(;i<time.length();i++) {
			if(Character.isDigit(time.charAt(i)) == true) {
				day *= 10;
				day += Character.getNumericValue(time.charAt(i));
				flag = 1;
			}
			else if(flag == 1)break;
		}
		sum += day * 60;
		day = 0;flag = 0;
		for(;i<time.length();i++) {
			if(Character.isDigit(time.charAt(i)) == true) {
				day *= 10;
				day += Character.getNumericValue(time.charAt(i));
				flag = 1;
			}
			else if(flag == 1)break;
		}
		sum += day * 1;
		day = 0;flag = 0;
		for(;i<time.length();i++) {
			if(Character.isDigit(time.charAt(i)) == true) {
				day *= 10;
				day += Character.getNumericValue(time.charAt(i));
				flag = 1;
			}
			else if(flag == 1)break;
		}
		sum += day * 0;
		return sum;
	}
	public static String convertTime(int timeSum) {//单位为分
		int day = timeSum / 1440;
		int hour = (timeSum - day * 1440) / 60;
		int minute = (timeSum - day * 1440 - hour * 60);
		int second = 0;
		return day + "天" + hour + "时" + minute + "分" + second + "秒";
	}
	
	/**
     * 
     * @param date 格式为yyyy-mm-dd hh-mm-ss
     * @param timeSum 单位为秒
     * @return
     * @throws ParseException
     */
    public static String addTime(String date, int timeSum) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
        long time = simpleDateFormat.parse(date).getTime();
        //System.out.println(time);
        time = time + timeSum * 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);// 24小时制
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String monthStr=new String();
        String dayStr=new String();
        String hourStr=new String();
        String minuteStr=new String();
        String secondStr=new String();
        if(month+1<10) {
        	monthStr="0"+Integer.toString(month + 1);
        }else {
        	monthStr=Integer.toString(month + 1);
        }
        if(day<10) {
        	dayStr="0"+Integer.toString(day);
        }else {
        	dayStr=Integer.toString(day);
        }
        if(hour<10) {
        	hourStr="0"+Integer.toString(hour);
        }else {
        	hourStr=Integer.toString(hour);
        }
        if(minute<10) {
        	minuteStr="0"+Integer.toString(minute);
        }else {
        	minuteStr=Integer.toString(minute);
        }
        if(second<10) {
        	secondStr="0"+Integer.toString(second);
        }else {
        	secondStr=Integer.toString(second);
        }
        //System.out.println(year + "-" + (month + 1) + "-" + day + " "+ hour + ":" + minute + ":" + second);
        String mytime=Integer.toString(year) + "-" + monthStr + "-" + dayStr + " "+ hourStr + ":" + minuteStr + ":" + secondStr;
        return mytime;
    }
    /**
     * 
     * @param date 格式为yyyy-mm-dd hh-mm-ss
     * @param timeSub 单位为秒
     * @return
     * @throws ParseException
     */
    public static String subTime(String date, int timeSub) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
        long time = simpleDateFormat.parse(date).getTime();
        //System.out.println(time);
        time=time-timeSub*1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//24小时制
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);  
        String monthStr=new String();
        String dayStr=new String();
        String hourStr=new String();
        String minuteStr=new String();
        String secondStr=new String();
        if(month+1<10) {
        	monthStr="0"+Integer.toString(month + 1);
        }else {
        	monthStr=Integer.toString(month + 1);
        }
        if(day<10) {
        	dayStr="0"+Integer.toString(day);
        }else {
        	dayStr=Integer.toString(day);
        }
        if(hour<10) {
        	hourStr="0"+Integer.toString(hour);
        }else {
        	hourStr=Integer.toString(hour);
        }
        if(minute<10) {
        	minuteStr="0"+Integer.toString(minute);
        }else {
        	minuteStr=Integer.toString(minute);
        }
        if(second<10) {
        	secondStr="0"+Integer.toString(second);
        }else {
        	secondStr=Integer.toString(second);
        }
        //System.out.println(year + "-" + (month + 1) + "-" + day + " "+ hour + ":" + minute + ":" + second);
        String mytime=Integer.toString(year) + "-" + monthStr + "-" + dayStr + " "+ hourStr + ":" + minuteStr + ":" + secondStr;
        //System.out.println(mytime);
        return mytime;
    }
    /**
     * yyyy-mm-dd hh-mm-ss --> yyyy-mm-dd hh:mm:ss
     * @param date 格式为yyyy-mm-dd hh-mm-ss
     * @return
     * @throws ParseException
     */
    public static String transTime(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
        long time = simpleDateFormat.parse(date).getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//24小时制
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);   
        String mytime=Integer.toString(year) + "-" + Integer.toString(month + 1) + "-" + Integer.toString(day) + " "+ Integer.toString(hour) + ":" + Integer.toString(minute) + ":" + Integer.toString(second);
        return mytime;
    }
}

