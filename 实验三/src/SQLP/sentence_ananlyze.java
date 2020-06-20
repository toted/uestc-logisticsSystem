package SQLP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class sentence_ananlyze 
{
	//private static Connection conn = null;
    //private static Scanner sc = new Scanner(System.in);
    public static Connection connect(String username, String passwd) 
    {
        String driver = "com.huawei.gauss.jdbc.ZenithDriver";
        String sourceURL = "jdbc:zenith:@192.168.52.130:1888?useSSL=true";
        Connection conn = null;
        try 
        {
        	//System.out.println(System.currentTimeMillis());

//加载数据库驱动。
            Class.forName(driver).newInstance();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
        try 
        {
//创建数据库连接。
        	System.out.println("正在连接...");
            conn = DriverManager.getConnection(sourceURL, username, passwd);
            System.out.println("Connection succeed!");
            //System.out.println(System.currentTimeMillis());

        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
    public String name="null";
    public String passwd="null";
	public ArrayList<String> list;
	public int p;
	public boolean checkSentence()
	{
		System.out.println("------语法分析如下------");
		if(match("select"))
		{
			if(match("*")&&match("from")&&id())
			{
				//connect(name,passwd);
				if(p==word_analyze.words.size())
				{
					readAll(word_analyze.words.get(p-1).name);
					System.out.println("语法正确，执行操作");
					System.out.println("------语法分析完毕------");
					return true;
				}
				else if(match("where")&&id()&&match("=")&&(num()||match("'")&&(id()||num())&&match("'"))&&p==word_analyze.words.size())
				{
					readAll(word_analyze.words.get(p-1).name);
					System.out.println("语法正确，执行操作");
					System.out.println("------语法分析完毕------");
					return true;
				}
				else if(match("order")&&match("by")&&id()&&p==word_analyze.words.size())
				{
					readAll(word_analyze.words.get(p-1).name);
					System.out.println("语法正确，执行操作");
					System.out.println("------语法分析完毕------");
					return true;
				}
				else
				{
					System.out.println("语法错误，重新输入sql语句");
					System.out.println("------语法分析完毕------");
					return false;
				}
					
			}
			else if(idList()&&match("from")&&id())
			{
				
				String[] column=new String[list.size()];
				for(int i=0;i<list.size();i++)
					column[i]=list.get(i);
				if(p==word_analyze.words.size()) 
				{
					read(column,word_analyze.words.get(p-1).name);
					System.out.println("语法正确，执行操作");
					System.out.println("------语法分析完毕------");
					return true;
				}

				else if(match("where")&&id()&&match("=")&&(num()||match("'")&&(id()||num())&&match("'"))&&p==word_analyze.words.size())
				{
					readAll(word_analyze.words.get(p-1).name);
					System.out.println("语法正确，执行操作");
					System.out.println("------语法分析完毕------");
					return true;
				}
				else if(match("order")&&match("by")&&id()&&p==word_analyze.words.size())
				{
					readAll(word_analyze.words.get(p-1).name);
					System.out.println("语法正确，执行操作");
					System.out.println("------语法分析完毕------");
					return true;
				}
				else
				{
					System.out.println("语法错误，重新输入sql语句");
					System.out.println("------语法分析完毕------");
					return false;
				}
			}
			else
			{
				System.out.println("语法错误，重新输入sql语句");
				System.out.println("------语法分析完毕------");
				return false;
			}
		}
		else if(match("insert")&&match("into")&&id()&&match("values")&&match("(")&&valueList()&&match(")")&&p==word_analyze.words.size())
		{
			write();
			System.out.println("语法正确，执行操作");
			System.out.println("------语法分析完毕------");
			return true;
		}
		else if(match("create")&&match("table")&&id()&&match("(")&&columnList()&&match(")")&&p==word_analyze.words.size())
		{
			adds();
			System.out.println("语法正确，执行操作");
			System.out.println("------语法分析完毕------");
			return true;
		}
		else if(match("update")&&id()&&match("set")&&setList()&&match("where")&&id()&&match("=")&&((match("'")&&id()&&match("'"))||num())&&p==word_analyze.words.size())
		{
			upnew();
			System.out.println("语法正确，执行操作");
			System.out.println("------语法分析完毕------");
			return true;
		}
		else
		{
			System.out.println("语法错误，重新输入sql语句");
			System.out.println("------语法分析完毕------");
			return false;
		}
		
	}
	public String fix()
	{
		String x="";
		int i;
		for(i=0;i<word_analyze.words.size();i++)
		{
			x=x+word_analyze.words.get(i).name+" ";
		}
		x.trim();
		return x;
	}
	public void write()
	{
		
	}
	public void upnew()
	{
		
	}
	public void adds()
	{
		
	}
    public void readAll(Connection conn,Scanner sc)
    {
    		
    		PreparedStatement pst;
			try {
				pst = conn.prepareStatement(fix());

    		//pst.setInt(1,maerid);
    		    ResultSet resultset;

				resultset = pst.executeQuery();

				while(resultset.next())
				{
					System.out.println("Staff_id:"+resultset.getInt("STAFF_ID"));
					System.out.println("First_name:"+resultset.getString("FIRST_NAME"));
					System.out.println("Last_name:"+resultset.getString("LAST_NAME"));
					System.out.println("Phone_number:"+resultset.getString("PHONE_NUMBER"));
					System.out.println("Email:"+resultset.getString("EMAIL"));
					System.out.println("Hire_date:"+resultset.getString("HIRE_DATE"));
					System.out.println("Employment_id:"+resultset.getString("EMPLOYMENT_ID"));
					System.out.println("Salary:"+resultset.getInt("SALARY"));
					System.out.println("Commission_pct:"+resultset.getInt("COMMISSION_PCT"));
					System.out.println("Manager_id:"+resultset.getInt("MANAGER_ID"));
					System.out.println("Section_id:"+resultset.getInt("SECTION_ID"));
					System.out.println("Graduated_name:"+resultset.getString("GRADUATED_NAME"));
					System.out.println("\n");
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
            //else
                //System.out.println("无效的STAFF_ID");
    	
    	
    }
	public void readAll(String name)
	{
		
	}
	public void read(String[] t,String name)
	{
		
	}
	public boolean isMatch(String regex,String token)
	{
		if(regex.equals(token))
			return true;
		else
			return false;
	}
    public boolean match(String regex)
    {
    	String token=word_analyze.words.get(p).name;
    	
    	if(isMatch(regex,token))
    	{
    		p++;
    		//System.out.println(token);
    		return true;
    	}
    	else 
    		return false;
    }
    public boolean id()
    {
    	int token_type=word_analyze.words.get(p).type;
    	if(token_type==22||token_type==0)
    	{
    		p++;
    		//System.out.println(word_analyze.words.get(p-1).name);
    		return true;
    	}
    		
    	else 
    		return false;
    }
    public boolean num()
    {
    	int token_type=word_analyze.words.get(p).type;
    	if(token_type==23)
    	{
    		p++;
    		//System.out.println(word_analyze.words.get(p-1).name);
    		return true;
    	}
    	else 
    		return false;
    }
    public boolean datatype()
    {
    	int token_type=word_analyze.words.get(p).type;
    	if(token_type>=18&&token_type<=21)
    	{
    		p++;
    		//System.out.println(word_analyze.words.get(p-1).name);
    		return true;
    	}
    	else 
    		return false;
    }
    public boolean singleyinhao()
    {
    	int token_type=word_analyze.words.get(p).type;
    	if(token_type==16)
    	{
    		p++;
    		//System.out.println(word_analyze.words.get(p-1).name);
    		return true;
    	}
    	else 
    		return false;
    }
    public boolean idList()
    {
    	list=new ArrayList<String>();
    	if(id())
    	{
    		list.add(word_analyze.words.get(p-1).name);
    		while(match(",")&&id())
    		{
    			list.add(word_analyze.words.get(p-1).name);
    		}
    		return true;
    	}
    	else
    		return false;
    }
    public boolean day()
    {
    	int token_type=word_analyze.words.get(p).type;
    	if(token_type>=24)
    	{
    		p++;
    		//System.out.println(word_analyze.words.get(p-1).name);
    		return true;
    	}
    	else 
    		return false;
    }
    public boolean valueList()
    {
    	list=new ArrayList<String>();
    	if(num()||singleyinhao()||id())
    	{
    		list.add(word_analyze.words.get(p-1).name);
    		while(match(",")&&(id()||(match("'")&&(num()||id())&&match("'"))||num()||(match("to_date")&&match("(")&&match("'")&&day()&&match("'")&&match(",")&&match("'")&&id()&&match("'")&&match(")"))))
    		{
    			list.add(word_analyze.words.get(p-1).name);
    		}
    		return true;
    	}
    	else
    		return false;
    }
    public boolean column()
    {
    	int token_type1=word_analyze.words.get(p).type;
    	int token_type2=word_analyze.words.get(p+1).type;
    	if(token_type1==22&&token_type2>=18&&token_type2<=21)
    	{
    		p+=2;
    		System.out.println(word_analyze.words.get(p-2).name+" "+word_analyze.words.get(p-1).name);
    		return true;
    	}
    	else 
    		return false;
    }
    public boolean columnList()
    {
    	list=new ArrayList<String>();
    	if(id()&&datatype())
    	{
    		//System.out.println(word_analyze.words.get(p-2).name+" "+word_analyze.words.get(p-1).name);
    		list.add(word_analyze.words.get(p-2).name+" "+word_analyze.words.get(p-1).name);
    		while(match(",")&&id()&&datatype())
    		{
    			//System.out.println(word_analyze.words.get(p-2).name+" "+word_analyze.words.get(p-1).name);
    			list.add(word_analyze.words.get(p-2).name+" "+word_analyze.words.get(p-1).name);
    		}
    		return true;
    	}
    	else
    		return false;
    }
    public boolean setList()
    {
    	list=new ArrayList<String>();
    	if(id())
    	{
    		if(match("="))
    		{
    			if(id())
    			{
    				if(match("+")||match("-")||match("*")||match("/"))
    				{
    					if(num())
    					{
    						return true;
    					}
    					else
    						return false;
    				}
    				else
    					return false;
    			}
    			else 
    				return false;
    		}
    		else
    			return false;
    	}
    	else
    		return false;
    }
}
