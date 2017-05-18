package userManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import userManage.interfac.ISetUserInfo;
import model.UserInfo;
import commonSocekt.ConnectServer;

public class SetUserInfo implements ISetUserInfo{
	ConnectServer cons;
	Socket socket;
	PrintStream out;
	DataInputStream in;
	public SetUserInfo(){
		cons=new ConnectServer();
		socket=cons.getSocket();
		out=cons.getPrintStream();
		in=cons.getDataInputStream();
	}
	public String sendInfo(UserInfo userinfo){
		String msg=null;
		try {
            out.println("registerinfo");
            out.println(URLEncoder.encode(userinfo.getUserid(), "utf-8"));
            out.println(URLEncoder.encode(userinfo.getUsername(), "utf-8"));
            out.println(URLEncoder.encode(userinfo.getNikename(), "utf-8"));
            out.println(URLEncoder.encode(userinfo.getSex(), "utf-8"));
            out.println(URLEncoder.encode(userinfo.getSchool(), "utf-8"));
            out.println(URLEncoder.encode(userinfo.getPassword(), "utf-8"));
            out.println(URLEncoder.encode(userinfo.getPhonenumber(), "utf-8"));
            out.println("finish");
           
            
            msg=in.readLine();
            
            out.println("bye");
            if((in.readLine()).equals("bye")){
            	
            	socket.close();
            }
            
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	public void login(){
		
	}
	
}
