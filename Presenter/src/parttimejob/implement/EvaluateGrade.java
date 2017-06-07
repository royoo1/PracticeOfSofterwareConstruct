package parttimejob.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parttimejob.interfac.IEvaluateGrade;
import commonSocekt.ConnectServer;

public class EvaluateGrade implements IEvaluateGrade{
	ConnectServer cons;
	DataInputStream in;
	PrintStream out;
	List<String> idlist=new ArrayList<String>();
	public EvaluateGrade(){
		cons=new ConnectServer();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	
	public String changeScore(String score,String personId){
		out.println("EvaluateGrade");
		URLDecoder ud = new URLDecoder();
		String get="";
		try {
			out.println(URLEncoder.encode(score, "utf-8"));
			out.println(URLEncoder.encode(personId, "utf-8"));
			
			try {
				get=ud.decode(in.readLine(), "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return get;
	}
	
	public String changeScore(Map<String,String> scoreMap){
		out.println("EvaluateGrade");
		URLDecoder ud = new URLDecoder();
		String get="";
		for (Map.Entry<String, String> entry : scoreMap.entrySet()) {  
			System.out.println(entry.getKey()+":"+entry.getValue());
		  try {
			out.println(URLEncoder.encode(entry.getKey(), "utf-8"));
			out.println(URLEncoder.encode(entry.getValue(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		}  
		try {
			out.println(URLEncoder.encode("finish", "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			get=ud.decode(in.readLine(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return get;
	}
	
	
	
	
	
}
