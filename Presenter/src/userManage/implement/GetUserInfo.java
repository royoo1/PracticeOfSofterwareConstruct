package userManage.implement;


import java.net.Socket;
import java.net.URLDecoder;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

import userManage.interfac.IGetUserInfo;
import model.UserInfo;
import commonSocekt.ConnectServer;

public class GetUserInfo implements IGetUserInfo{
	ConnectServer cons;
	Socket socket;
	PrintStream out;
	DataInputStream in;
	public GetUserInfo(){
		cons=new ConnectServer();
		socket=cons.getSocket();
		out=cons.getPrintStream();
		in=cons.getDataInputStream();
		
	}
	public UserInfo getUserInfo(String id,String pass){
		UserInfo userinfo=new UserInfo();
		if(socket!=null){
			out.println("getuserinfo");
			out.println(id);
			out.println(pass);
			try {
				String[] info=new String[10];
				String read=in.readLine();
				URLDecoder ud = new URLDecoder();
				for(int i=0;!read.equals("finish");i++){
					info[i]=ud.decode(read, "utf-8");
					read=in.readLine();
				}
				userinfo.setUserid(info[0]);
				userinfo.setUsername(info[1]);
				userinfo.setNikename(info[2]);
				userinfo.setSex(info[3]);
				userinfo.setSchool(info[4]);
				userinfo.setPassword(info[5]);
				userinfo.setPhonenumber(info[6]);
				userinfo.setComments(info[7]);
				userinfo.setCommentstar(info[8]);
				userinfo.setUsercash(info[9]);
				out.println("bye");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userinfo;
	}
	
	public UserInfo getUserInfoById(String id){
		UserInfo userinfo=new UserInfo();
		URLDecoder ud = new URLDecoder();
		if(socket!=null){
			out.println("GetUserInfoById");
			out.println(id);
			System.out.println("start");
			try {
				String[] info=new String[10];
				String read=ud.decode(in.readLine(), "utf-8");
				
				for(int i=0;!read.equals("finish");i++){
					info[i]=read;
					read=ud.decode(in.readLine(), "utf-8");
					System.out.println(i);
				}
				System.out.println("end");
				userinfo.setUserid(info[0]);
				userinfo.setUsername(info[1]);
				userinfo.setNikename(info[2]);
				userinfo.setSex(info[3]);
				userinfo.setSchool(info[4]);
				userinfo.setPassword(info[5]);
				userinfo.setPhonenumber(info[6]);
				userinfo.setComments(info[7]);
				userinfo.setCommentstar(info[8]);
				userinfo.setUsercash(info[9]);
				out.println("bye");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userinfo;
	}
}
