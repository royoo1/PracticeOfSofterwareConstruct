package userManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

import userManage.interfac.IConfirmLogin;
import commonSocekt.ConnectServer;

public class ConfirmLogin implements IConfirmLogin{

	public String confirmLogin(String userid,String password){
		String read=null;
		ConnectServer cons=new ConnectServer();
		Socket socket=cons.getSocket();
		PrintStream out=cons.getPrintStream();
		DataInputStream in=cons.getDataInputStream();
		if(socket!=null){
			out.println("login");
			try {
				out.println(URLEncoder.encode(userid, "utf-8"));
				out.println(URLEncoder.encode(password, "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
	            URLDecoder ud = new URLDecoder();
				read=ud.decode(in.readLine(), "utf-8");
				if(read.equals("success")){
					System.out.println("success");
				}
				else{
					System.out.println(read);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return read;
	}
}
