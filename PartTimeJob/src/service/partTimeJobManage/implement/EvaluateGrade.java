package service.partTimeJobManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.partTimeJobManage.interfac.IEvaluateGrade;
import Dao.implement.DataChangeScore;

public class EvaluateGrade implements IEvaluateGrade{
	DataInputStream in;
	PrintStream out;
	
	public EvaluateGrade(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	
	public void changeScore(){
		String score="";
		String personId="";
		URLDecoder ud=new URLDecoder();
		Map<String,String> scoreMap=new HashMap<String,String>();
		String key=null;
		String value=null;
		try {
			key=ud.decode(in.readLine(), "utf-8");
            while(!key.equals("finish")){
				
				value=ud.decode(in.readLine(), "utf-8");
				scoreMap.put(key, value);
				key=ud.decode(in.readLine(), "utf-8");
			}
			DataChangeScore dcs=new DataChangeScore();
			out.println(URLEncoder.encode(dcs.changeScore(scoreMap), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
