package Lab2;

public class graph {
	int nodenum=6;
	final int inf=999;
	int[][] v= {
			{inf,1,inf,3,inf,inf},
			{2,inf,2,inf,inf,inf},
			{inf,1,inf,2,3,1},
			{2,inf,1,inf,inf,3},
			{inf,inf,2,inf,2},
			{inf,inf,1,2,2,inf}
	};//瀹归噺鐭╅樀
	int[][] cost={
			{0,2,inf,14,inf,inf},
			{2,0,24,inf,inf,inf},
			{inf,24,0,18,6,2},
			{14,inf,18,0,inf,30},
			{inf,inf,6,11,inf,11},
			{inf,inf,2,30,11,0}
	};//浠ｄ环鐭╅樀
	int[][] flycost= {
			{0,1,inf,2,inf,inf},
			{1,0,6,inf,inf,inf},
			{inf,6,0,5,6,8},
			{2,inf,5,0,inf,2},
			{inf,inf,6,inf,0,4},
			{inf,inf,8,2,4,inf}
	};//鏃堕棿鐭╅樀
	node[] nod=new node[6];{
	nod[0]=new node(1,"鍖椾含",true);
	nod[1]=new node(2,"閮戝窞",false);
	nod[2]=new node(3,"娴庡崡",false);
	nod[3]=new node(4,"瑗垮畨",true);
	nod[4]=new node(5,"閲嶅簡",false);
	nod[5]=new node(6,"鎴愰兘",true);
	}
	public int[][] getcost(){
		return cost;
	}
	public node[] getnod() {
		return nod;
	}
	//鏅�氱敤鎴烽噰鐢ㄤ唬浠锋渶灏�
	public void dijkstra(String start,String dest) {
		int s=0;
		int t=0;
		for(node no:nod) {
			if(start.equals(no.getname()))
				s=no.getorder()-1;
		}
		for(node no:nod) {
			if(dest.equals(no.getname()))
				t=no.getorder()-1;
		}
		    boolean[] flag = new boolean[nodenum];
		    int[] prev=new int[6];
		    int[] dist=new int[6];
		    for (int i = 0; i <nodenum; i++) {
		        flag[i] = false;         
		        prev[i] = 0;             
		        dist[i] = cost[s][i];  
		    }
		    flag[s] = true;
		    dist[s] = 0;
		    int k=0;
		    for (int i = 1; i < nodenum; i++) {
		        int min = inf;
		        for (int j = 0; j < nodenum; j++) {
		            if (flag[j]==false && dist[j]<min) {
		                min = dist[j];
		                k = j;
		            }
		        }
		        flag[k] = true;
		        for (int j = 0; j < nodenum; j++) {
		            int tmp = (cost[k][j]==inf ? inf : (min + cost[k][j]));
		            if (flag[j]==false && (tmp<dist[j]) ) {
		                dist[j] = tmp;
		                prev[j] = k;
		            }
		        }
		    }
		        System.out.printf("浠�"+ nod[s].getname()+"鍒�"+nod[t].getname()+"鏈�灏忎唬浠锋槸"+dist[t]+"\n");
	}
	//vip鐢ㄦ埛涓嶈�冭檻鎴愭湰閲囩敤鏃堕棿鏈�鐭�
	public void mintime(String start,String dest) {
		int s=0;
		int t=0;
		for(node no:nod) {
			if(start.equals(no.getname()))
				s=no.getorder()-1;
		}
		for(node no:nod) {
			if(dest.equals(no.getname()))
				t=no.getorder()-1;
		}
		    boolean[] flag = new boolean[nodenum];
		    int[] prev=new int[6];
		    int[] dist=new int[6];
		    for (int i = 0; i <nodenum; i++) {
		        flag[i] = false;         
		        prev[i] = 0;             
		        dist[i] = flycost[s][i];  
		    }
		    flag[s] = true;
		    dist[s] = 0;
		    int k=0;
		    for (int i = 1; i < nodenum; i++) {
		        int min = inf;
		        for (int j = 0; j < nodenum; j++) {
		            if (flag[j]==false && dist[j]<min) {
		                min = dist[j];
		                k = j;
		            }
		        }
		        flag[k] = true;
		        for (int j = 0; j < nodenum; j++) {
		            int tmp = (flycost[k][j]==inf ? inf : (min + flycost[k][j]));
		            if (flag[j]==false && (tmp<dist[j]) ) {
		                dist[j] = tmp;
		                prev[j] = k;
		            }
		        }
		    }
		    System.out.println("鎮ㄧ殑璐х墿灏嗗敖鍙兘绌鸿繍");
		        System.out.printf("浠�"+ nod[s].getname()+"鍒�"+nod[t].getname()+"鏈�灏忔椂闂存槸"+dist[t]+"\n");
	}
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
}

