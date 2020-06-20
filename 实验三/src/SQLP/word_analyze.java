package SQLP;

import java.util.ArrayList;

public class word_analyze 
{
    public static ArrayList<word> tables=new ArrayList<word>();//编码表

    word x1=new word("select",1);
    word x2=new word("from",2);
    word x3=new word("insert",3);
    word x4=new word("into",4);
    word x5=new word("values",5);
    word x6=new word("update",6);
    word x7=new word("set",7);
    word x8=new word("where",8);
    word x9=new word("create",9);
    word x10=new word("table",10);
    word x11=new word("=",11);
    word x12=new word(",",12);
    word x13=new word(";",13);
    word x14=new word("(",14);
    word x15=new word(")",15);
    word x16=new word("'",16);
    word x17=new word("*",17);
    word x18=new word("date",18);
    word x19=new word("varchar",19);
    word x20=new word("number",20);
    word x21=new word("char",21);
    word x22=new word("id",22);
    word x23=new word("num",23);
    word x24=new word("day",24);
    word x25=new word("to_date",25);
    word x26=new word("+",26);
    word x27=new word("-",27);
    word x28=new word("/",28);
    word x29=new word("order",29);
    word x30=new word("by",30);
    public void adddd()
    {
    	tables.add(x1);
    	tables.add(x2);
    	tables.add(x3);
    	tables.add(x4);
    	tables.add(x5);
    	tables.add(x6);
    	tables.add(x7);
    	tables.add(x8);
    	tables.add(x9);
    	tables.add(x10);
    	tables.add(x11);
    	tables.add(x12);
    	tables.add(x13);
    	tables.add(x14);
    	tables.add(x15);
    	tables.add(x16);
    	tables.add(x17);
    	tables.add(x18);
    	tables.add(x19);
    	tables.add(x20);
    	tables.add(x21);
    	tables.add(x22);
    	tables.add(x23);
    	tables.add(x24);
    	tables.add(x25);
    	tables.add(x26);
    	tables.add(x27);
    	tables.add(x28);
    	tables.add(x29);
    	tables.add(x30);
    }
    

    
    
	/*String x=sentences.poll();
	word_analyze wa=new word_analyze();
	word_analyze.p=0;
	word_analyze.sentence=x;
	wa.analyze();*/
	
	public static ArrayList<word> words=new ArrayList<word>();
	//words.add(x);
	public int p;
	public String sentence;
	public char u;
	public void getChar()
	{
		if(p<sentence.length())
		{
		u=sentence.charAt(p);
		//System.out.println("char:"+u);
		p++;
		}
	}
	public boolean isLetter()
	{
		if(u>='a'&&u<='z')
			return true;
		else
			return false;
	}
	public boolean isDigit()
	{
		if(u>='0'&&u<='9')
			return true;
		else
			return false;
	}
	public void skipSpace()
	{
		
		int s=p-1;
		while(sentence.charAt(s)==' '&&s<sentence.length()-1)
			s++;
		u=sentence.charAt(s);
		p=s+1;
		//System.out.println("char:"+u);
		//System.out.println("p:"+p+",s:"+s);
	}
	public String token="";
	public void link()
	{
		token=token+u;
		//System.out.println(token);
	}
	public void back()
	{
		if(p==sentence.length())
			return;
		else
		    p=p-1;
		//System.out.println("back to:"+p);
	}
	public boolean isKeyWord()
	{
		if(token.equals("select")||token.equals("from")||token.equals("insert")||token.equals("into")||token.equals("values")||token.equals("update")||token.equals("set")||token.equals("where")||token.equals("create")||token.equals("table")||token.equals("order")||token.equals("by"))
		{
			return true;          
		}
		else
			return false;
	}
	public void insertKeyWords()
	{
		word xw=new word();
		xw.name=token;
		for(int i=0;i<tables.size();i++)
		{
			if(token.equals(tables.get(i).name))
			{
				xw.type=tables.get(i).type;
				break;
			}
		}
		//xw.type=1;
		words.add(xw);
		//System.out.println("kw:"+words.get(words.size()-1).name+","+words.get(words.size()-1).type);	
	}
	public boolean isDataType()
	{
		if(token.equals("date")||token.contains("varchar")||token.contains("number")||token.contains("char"))
		{
			return true;
            
		}
		else
			return false;
	}
	public void insertDataType()
	{
		word xw=new word();
		xw.name=token;
		for(int i=0;i<tables.size();i++)
		{
			if(token.equals(tables.get(i).name))
			{
				xw.type=tables.get(i).type;
				break;
			}
		}
		words.add(xw);
		//System.out.println("dt:"+words.get(words.size()-1).name);	
	}
	public void insertId()
	{
		//System.out.println(token);
		word xw=new word();
		xw.name=token;
		xw.type=22;
		//System.out.println(xw.name);
		words.add(xw);
		//System.out.println("id:"+words.get(words.size()-1).name);	
	}
	public void insertConst()
	{
		word xw=new word();
		xw.name=token;
		xw.type=23;
		words.add(xw);
		//System.out.println(words.get(words.size()-1).name);
	}
	public boolean isStar()
	{
		if(u=='*')
			return true;
		else
			return false;
	}
	public boolean isSeparator()
	{
		if(u=='('||u==')'||u==','||u==';'||u=='\'')
			return true;
		else
			return false;
	}
	public boolean isEqual()
	{
		if(u=='=')
			return true;
		else
			return false;
	}
	public boolean isLinked()
	{
		if(u=='_'||u=='-')
		{
			//System.out.println("xxxe");
			return true;
			
		}
			
		else
			return false;
	}
	public boolean isPoint()
	{
		if(u=='.')
		{
			//System.out.println("xxxe");
			return true;
			
		}
			
		else
			return false;
	}
	public boolean isCalculate()
	{
		if(u=='+'||u=='-'||u=='/')
		{
			//System.out.println("xxxe");
			return true;
			
		}
			
		else
			return false;
	}
	public void insertStar()
	{
		String x=u+"";
		//System.out.println(x);
		word w=new word();
		w.name=x;
		w.type=17;
		//System.out.println(w.name);
		words.add(w);
		//System.out.println(words.get(words.size()-1).name);
	}
	public void insertEqual()
	{
		String x=u+"";
		word w=new word();
		w.name=x;
		w.type=11;
		words.add(w);
		//System.out.println(words.get(words.size()-1).name);
	}
	public void insertSeparator()
	{
		String x=u+"";
		word w=new word();
		w.name=x;
		for(int i=0;i<tables.size();i++)
		{
			if(x.equals(tables.get(i).name))
			{
				w.type=tables.get(i).type;
				break;
			}
		}
		words.add(w);
		//System.out.println(words.get(words.size()-1).name);
	}
	public void insertDate()
	{
		//String x=u+"";
		word w=new word();
		w.name=token;
        w.type=24;
		words.add(w);
		//System.out.println(words.get(words.size()-1).name);
	}
	public void insertCalculate()
	{
		String x=u+"";
		word w=new word();
		w.name=x;
		for(int i=0;i<tables.size();i++)
		{
			if(x.equals(tables.get(i).name))
			{
				w.type=tables.get(i).type;
				break;
			}
		}
		words.add(w);
		//System.out.println(words.get(words.size()-1).name);
	}
    public void analyze()
    {
    	System.out.println("输入的sql语句为：");
    	System.out.println(sentence);
    	System.out.println("\n");
    	//sentence=sentence.trim();
    	while(p<sentence.length())
    	{
    		getChar();
    		skipSpace();
    		if(isLetter())
    		{
    			while(isLetter()||isDigit()||isLinked())
    			{
    				link();
    				if(p==sentence.length())
    					break;
    				getChar();
    			}
    			//System.out.println(token);
    			back();
    			if(isKeyWord())
    				insertKeyWords();
    			else if(isDataType())
    				insertDataType();
    			else
    				insertId();
    			token="";
    		}
    		else if(isDigit())
    		{
    			while(isDigit()||isLinked()||isPoint())
    			{
    				link();
    				if(p==sentence.length())
    					break;
    				getChar();
    			}
    			back();
    			if(token.contains("-")||token.contains("_"))
    				insertDate();
    			else
    			    insertConst();
    			token="";
    		}
    		else if(isStar())
    		{
    			insertStar();
    		}
    		else if(isEqual())
    		{
    			insertEqual();
    		}
    		else if(isSeparator())
    		{
    			insertSeparator();
    		}
    		else if(isCalculate())
    		{
    			insertCalculate();
    		}
    	}
    	if(!isLetter()&&!isDigit())
    	{
    		if(isStar())
    		{
    			insertStar();
    		}
    		else if(isEqual())
    		{
    			insertEqual();
    		}
    		else if(isSeparator())
    		{
    			insertSeparator();
    		}
    	}
    	System.out.println("------词法分析如下------");
        for(int i=0;i<words.size();i++)
        {
        	System.out.println("["+words.get(i).name+","+words.get(i).type+"]");
        }
        System.out.println("------词法分析完毕------");
        System.out.println("\n");
        System.out.println("------进入语法分析------");
        System.out.println("\n");
    }

}
