package wuliu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.sound.midi.Soundbank;

import java.text.SimpleDateFormat;

public class dijkstra {
	public ArrayList<String> print= new ArrayList<String>();
	int nodenum=6;
	final static int inf=999;
	Calendar c1 = Calendar.getInstance();
//	String[] dayin = (String[]) print.toArray();
	 class node {
		    int order;
			String name;
			boolean fly;
			public node(int t, String a,boolean b){
				this.order=t;
				this.name=a;
				this.fly=b;
			}
			public int getorder() {
				return order;
			}
			public String getname() {
				return name;
			}
			public boolean getfly() {
				return fly;
			}
	}
	public class Set{
		public int number;
		public int distance;
		public boolean discovered;
		Set(int i)
		{
			this.discovered=false;
			this.distance=inf;
			this.number=i;
		}
	}
	node[] nod=new node[7];{
		nod[0]=new node(0, null, false);
		nod[1]=new node(1,"����",true);
		nod[2]=new node(2,"֣��",false);
		nod[3]=new node(3,"����",false);
		nod[4]=new node(4,"����",true);
		nod[5]=new node(5,"����",false);
		nod[6]=new node(6,"�ɶ�",true);
		}
	
	public class tempMin
	{
		int distance;//�ӳ�ʼλ�õ��ýڵ����̾���
	    int prev;//ǰ���ڵ�
	    int num;//���
	    public tempMin() {
			// TODO Auto-generated constructor stub
	    	this.num=inf;
	    	this.distance=10*inf;
	    	this.prev=inf;
		}
	}
	
	public String[] PathPrint(int start,int End,long t,int prev[],int imoney[][],int Matrix[][])
	{
		String[] dayin=new String[1000];
		int l=0;
		long q=10000000000L;
		long w=100000000000L;
		long r=1000000000000L;
		long y=10000000000000L;
		long a=t%10+(t/10%10)*10;
		long b=t/100%10+(t/1000%10)*10;
		long c=t/10000%10+(t/100000%10)*10;
		long d=t/1000000%10+(t/10000000%10)*10;
		long e=t/100000000%10+(t/1000000000%10)*10;
		long f=t/q%10+(t/w%10)*10+(t/r%10)*100+(t/y%10)*1000;
		int miao=(int)a;
		int fen=(int)b;
		int shi=(int)c;
		int ri=(int)d;
		int yue=(int)e-1;
		int nian=(int)f;
		c1.set(nian,yue,ri,shi,fen,miao);
		int month=c1.get(Calendar.MONTH)+1;
		c1.add(Calendar.HOUR_OF_DAY, 2);
		System.out.println("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	    print.add("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
		int i=End;
	    int money=0;
	    String route=new String();
	    System.out.print("·��Ϊ:");
	    print.add("·��Ϊ:");
	    if(prev[End]<0)
	        System.out.println("can't reach!");
	    else
	    {
	    	
	    	System.out.print(nod[End].name+"<=");
	    	route=route+nod[End].name+"<=";
//	    	print.add(nod[End].name+"<=");
	        while(prev[i]!=start)
	        {
	        	l=l+Matrix[prev[i]][i]+1;
	 //       	haoshi[i]=Matrix[prev[i]][i];
	        	money=money+imoney[prev[i]][i];
	        	i=prev[i];
	        	System.out.print(nod[i].name+"<=");
	        	route=route+nod[i].name+"<=";
//	        	print.add(nod[i].name+"<=");
	        }
	        l=l+Matrix[nod[start].order][i];
	        money=money+imoney[nod[start].order][i];
	        System.out.println(nod[start].name);
	        route=route+nod[start].name;
	        print.add(route);
//	        print.add(nod[start].name);
	    		System.out.println("�˷�Ϊ"+money+"Ԫ");
	    		print.add("�˷�Ϊ"+money+"Ԫ");
	    		c1.add(Calendar.HOUR_OF_DAY, l);
	    		System.out.println("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	            print.add("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	    }
	    int j=End;
	    if(prev[End]<0) {
	        System.out.println("can't reach!");
	    print.add("can't reach!");
	    }
	    else
	    {
	        while(prev[j]!=start)
	        {
	    		c1.add(Calendar.HOUR_OF_DAY, -Matrix[prev[j]][j]);
	    		System.out.println("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	    	    print.add("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	    		
	    		c1.add(Calendar.HOUR_OF_DAY, -1);
	    		System.out.println("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	            print.add("����ʱ��Ϊ:"+c1.get(Calendar.YEAR)+"��"+month+"��"+c1.get(Calendar.DATE)+"��"+c1.get(Calendar.HOUR_OF_DAY)+"ʱ"+c1.get(Calendar.MINUTE)+"��"+c1.get(Calendar.SECOND)+"��");
	    		j=prev[j];
	        }
	    }
	    return  dayin;
	}
//	@SuppressWarnings("null")
	public void dijkstra1(String s,String d,long t,int Matrix[][],int imoney[][]) {
		int prevNode[] = new int[100];
		int destination=0;
		int start=0;
		for(node no:nod) {
			if(s.equals(no.getname()))
			{
				start=no.getorder();
		//	System.out.println(start);
			}
				
		}
		
		for(node no:nod) {
			if(d.equals(no.getname()))
			{
				destination=no.getorder();
		//	System.out.println(destination);
			}
				
		}
		prevNode[0]=-1;
		int NodeNum=Matrix[0].length;
		String cityString[]=new String[NodeNum];
		
		Set []Set = new Set[20];
		for(int i=1;i<=NodeNum;i++)
		{
			Set[i]=new Set(i);
//			System.out.println(""+Set[i].distance+" "+Set[i].number);
		}
//		System.out.println(Set[1].discovered);
//		for(int i=1; i<=NodeNum; i++)
//	    {
//			Set aSet=null;
//			if(aSet.equals(Set[i]))
//			{
//				System.out.println("222");
//				System.out.println(Set[i].discovered);
//			}
//	        
//	    }
		Set[start].discovered=true;
		Set[start].distance=0;
		for(int j=1; j<NodeNum-1; j++) //NodeNum-1������
	    {
			//System.out.println("j"+j);
	        tempMin temp=new tempMin();//��쵱ǰ���·��
	        for(int i=1; i<NodeNum; i++) //ÿ�μ���set
	        {
	            if(Set[i].discovered)//�������·����ǰ���ڵ㽫����ȷ�Ͻڵ��в���
	            {
	                int k=Set[i].number;
	                int PrevDistance=Set[i].distance;
	                for(int l=1; l<NodeNum; l++)
	                {
	                    if((l!=k)&&(!Set[l].discovered))//�Խ��߼���ȷ���ڵ㲻��ȡ
	                    {
	                        if((Matrix[k][l]+PrevDistance)<temp.distance)//�����Ż�����¼��Ϣ
	                        {
	                            temp.distance=(Matrix[k][l]+PrevDistance);
	                            temp.num=l;
	                            temp.prev=k;
	                        }
	                    }
	                }
	            }
	        }
//	        System.out.println(temp.prev);
	        Set[temp.num].discovered=true;//̽�����Ľڵ���Ϊ��ȷ��״̬
	        Set[temp.num].distance=temp.distance;
	        if(temp.distance<300)
	            prevNode[temp.num]=temp.prev;//����temp������ǰ����
	        else
	            prevNode[temp.num]=-1;//��ǲ��ɴ�
//	        System.out.println("num"+temp.num+"prev"+temp.prev+nod[temp.prev].name+nod[temp.num].name);
	    }
//		for(int i=0;i<prevNode.length;i++)
//			System.out.print(prevNode[i]+" ");
//		System.out.println(prevNode[2]);
		if(prevNode[destination]>=0)
			System.out.println("��"+nod[start].getname()+"��"+nod[destination].getname()+"����ʱ��Ϊ"+Set[destination].distance);
		print.add("��"+nod[start].getname()+"��"+nod[destination].getname()+"����ʱ��Ϊ"+Set[destination].distance);
	//	System.out.printf("·��Ϊ:");
		PathPrint(start, destination,t, prevNode,imoney, Matrix);
		System.out.printf("\n");
//	        cout<<endl<<"�ܳ��ȣ�"<<Set[End].distance<<endl;
	}
}