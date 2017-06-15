package service.partTimeJobManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import Dao.implement.DataJobInfo;

public class Jobfinish {
	DataInputStream in;
	PrintStream out;
	public Jobfinish(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	
	public void finishJob(){
		String get=null;
		String send=null;
		URLDecoder ud = new URLDecoder();
		try {
			get=ud.decode(in.readLine(), "utf-8");
			DataJobInfo dji=new DataJobInfo();
			send=dji.finishJob(get);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.println(URLEncoder.encode(send, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
