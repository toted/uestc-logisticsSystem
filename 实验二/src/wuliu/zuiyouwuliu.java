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
		};//���۾���
	 public static int[][] flycost= {
				{0,0,2,inf,14,inf,inf},
				{0,0,2,inf,2,inf,inf},
				{0,2,0,24,inf,inf,inf},
				{0,inf,24,0,18,6,2},
				{0,2,inf,18,0,inf,2},
				{0,inf,inf,6,inf,inf,11},
				{0,inf,inf,2,2,11,0}
		};//ʱ�����
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
	ob[0]=new object("�·�",20200601132402L,"����","�ɶ�",3,true);
	ob[1]=new object("����",20200601113212L,"����","֣��",3,false);
	ob[2]=new object("ñ��",20200602145802L,"����","�ɶ�",3,false);
	ob[3]=new object("�ֻ�",20200604120812L,"����","����",3,false);
	ob[4]=new object("�ֱ�",20200607090100L,"����","�ɶ�",3,false);
	ob[5]=new object("��ʳ",20200609070801L,"֣��","����",3,true);
	ob[6]=new object("�̲�",20200610070602L,"֣��","�ɶ�",3,true);
	int i;
	for(i=0;i<7;i++) {
	article.add(ob[i]);
	}
	}
	Collections.sort(article, new sorting());
	dijkstra x=new dijkstra();
	for(object obj:ob) {
		if(obj.getvip()) {
			System.out.println("���Ļ��ｫ�����ܿ���");
			x.print.add("���Ļ��ｫ�����ܿ���");
			 x.dijkstra1(obj.gets(),obj.getdes(),obj.gettime(),flycost,money2);
			 }
		else {
			x.dijkstra1(obj.gets(),obj.getdes(),obj.gettime(),cost,money1);
			}
	};
	for(object obj:ob) {
		System.out.println("���ﵥ��Ϊ"+obj.getnum()+"�Ļ��﷢���ɹ�");
	article.remove(obj);
	}
	System.out.println();
}
}



