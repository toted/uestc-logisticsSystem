package SQLP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class sente_pre
{    public static void pretreatment(String str)
    {
    	str=str.toLowerCase();
    	String[] sentence=str.split(";");
    	int i;
    	for(i=0;i<sentence.length;i++)
    	{
    		sentences.offer(sentence[i]);
    	}
    }
	public static Queue<String> sentences = new LinkedList<String>(); 
	public boolean judge(String sql){	
        ArrayList<Boolean> judges=new ArrayList<Boolean>();
		pretreatment(sql);
	    while(!sentences.isEmpty()){
	    	String x=sentences.poll();
	    	word_analyze wa=new word_analyze();
	    	wa.adddd();
	    	wa.p=0;
	    	wa.sentence=x;
	    	wa.analyze();
	    	sentence_ananlyze sa= new sentence_ananlyze();
	    	sa.p=0;
	    	boolean y=sa.checkSentence();
	    	judges.add(y);
	    }
	    int i;
	    for(i=0;i<judges.size();i++){
	    	if(!judges.get(i))
	    		break;
	    }
        if(i!=judges.size())
        	return false;
        else
        	return true;
	}

}