package wuliu;

import org.springframework.util.DigestUtils;


public class object{
	String name;
	String num;
	long time;
	String s;
	String des;
	String road;
	int weight;
	boolean vip;
		public object(String a,long c,String d,String e,int f,boolean g) {
			this.name =a;
			String b=String.valueOf(c);
			String md5 = DigestUtils.md5DigestAsHex(b.getBytes());
	        String session = md5.substring(md5.length()-16, md5.length());
			this.num=session;
			this.time=c;
			this.s=d;
			this.des=e;
			this.weight=f;
			this.vip=g;
		}
		public String getname() {
			return name;
		}
		public String getnum() {
			return num;
		}
		public long gettime() {
			return time;
		}
		public String gets() {
			return s;
		}
		public String getdes() {
			return des;
		}
		public String getroad() {
			return road;
		}
		public int getweight() {
			return weight;
		}
		public boolean getvip() {
			return vip;
		}

	}