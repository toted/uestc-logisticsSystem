package Lab2;

import java.text.ParseException;

public class object{
	String name;
	String num;
	String time;
	String s;
	String des;
	String road;
	String scheme;
	String phone;
	int weight;
	boolean vip;
		public object(String name,String num,String time,String s,String des,int weight,boolean vip,String scheme,String phone) {
			this.name =name;
			this.num=num;
			this.time=time;
			this.s=s;
			this.des=des;
			this.weight=weight;
			this.vip=vip;
			this.scheme=scheme;
			this.phone=phone;
		}
		public String getname() {
			return name;
		}
		public String getnum() {
			return num;
		}
		public String gettime() {
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
