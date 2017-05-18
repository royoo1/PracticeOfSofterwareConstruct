package userManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import userManage.interfac.IModifyUserInfo;
import commonSocekt.ConnectServer;

public class ModifyUserInfo implements IModifyUserInfo{
	ConnectServer cons;
	Socket socket;
	PrintStream out;
	DataInputStream in;
	public ModifyUserInfo(){
		cons=new ConnectServer();
		socket=cons.getSocket();
		out=cons.getPrintStream();
		in=cons.getDataInputStream();
	}
	
	public String changeNikename(Map<String, String> map,String userid){
		URLDecoder ud = new URLDecoder();
		String msg=null;
		out.println("modifyUserInfo");
        try {
			out.println(URLEncoder.encode(userid, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    for (Map.Entry<String, String> entry : map.entrySet()) {  
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
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			msg=ud.decode(in.readLine(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
}
