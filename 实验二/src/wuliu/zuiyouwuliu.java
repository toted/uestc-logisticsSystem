package wuliu;


import java.util.*;


class sorting implements Comparator<object>{
	@Override
	public int compare(object o1,object o2) {
		String time1=String.valueOf(o1.gettime());
		String time2=String.valueOf(o2.gettime());
		long t1=Long.parseLong(time1);
		long t2=Long.parseLong(time2);
		if(o1.vip) 
			t1=-10000;
		if(o2.vip)
			t2=-10000;
		if(t1>t2) return 1;
		else if(t1==t2) return 0;
		else return -1;
	}
}
public class zuiyouwuliu  {
	final static int inf=999;
	 public static int[][] cost={
				{0,0,2,inf,14,inf,inf},
				{0,0,2,inf,14,inf,inf},
				{0,2,0,24,inf,inf,inf},
				{0,inf,24,0,18,6,2},
				{0,14,inf,18,0,inf,30},
				{0,inf,inf,6,inf,inf,11},
				{0,inf,inf,2,30,11,0}
		};//代价矩阵
	 public static int[][] flycost= {
				{0,0,2,inf,14,inf,inf},
				{0,0,2,inf,2,inf,inf},
				{0,2,0,24,inf,inf,inf},
				{0,inf,24,0,18,6,2},
				{0,2,inf,18,0,inf,2},
				{0,inf,inf,6,inf,inf,11},
				{0,inf,inf,2,2,11,0}
		};//时间矩阵
	public static int[][] money1= {
			{0,0,0,0,0,0,0},
			{0,0,5,inf,7,inf,inf},
			{0,5,0,6,inf,inf,inf},
			{0,inf,6,0,6,4,4},
			{0,7,inf,6,0,inf,8},
			{0,inf,inf,4,inf,0,7},
			{0,inf,inf,4,8,7,0}
	};
	public static int[][] money2= {
			{0,0,0,0,0,0,0},
			{0,0,5,inf,10,inf,inf},
			{0,5,0,6,inf,inf,inf},
			{0,inf,6,0,6,4,4},
			{0,10,inf,6,0,inf,11},
			{0,inf,inf,4,inf,0,7},
			{0,inf,inf,4,11,7,0}
	};
public static void main(String[] args) {
	ArrayList<object> article=new ArrayList<object>();
	object[] ob=new object[7];{
	ob[0]=new object("衣服",20200601132402L,"北京","成都",3,true);
	ob[1]=new object("裤子",20200601113212L,"北京","郑州",3,false);
	ob[2]=new object("帽子",20200602145802L,"济南","成都",3,false);
	ob[3]=new object("手机",20200604120812L,"北京","西安",3,false);
	ob[4]=new object("手表",20200607090100L,"重庆","成都",3,false);
	ob[5]=new object("零食",20200609070801L,"郑州","济南",3,true);
	ob[6]=new object("教材",20200610070602L,"郑州","成都",3,true);
	int i;
	for(i=0;i<7;i++) {
	article.add(ob[i]);
	}
	}
	Collections.sort(article, new sorting());
	dijkstra x=new dijkstra();
	for(object obj:ob) {
		if(obj.getvip()) {
			System.out.println("您的货物将尽可能空运");
			x.print.add("您的货物将尽可能空运");
			 x.dijkstra1(obj.gets(),obj.getdes(),obj.gettime(),flycost,money2);
			 }
		else {
			x.dijkstra1(obj.gets(),obj.getdes(),obj.gettime(),cost,money1);
			}
	};
	for(object obj:ob) {
		System.out.println("货物单号为"+obj.getnum()+"的货物发货成功");
	article.remove(obj);
	}
	System.out.println();
}
}



