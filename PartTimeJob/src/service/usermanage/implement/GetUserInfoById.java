package service.usermanage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;

import service.usermanage.interfac.IGetUserInfoById;
import Dao.implement.SelectData;

public class GetUserInfoById implements IGetUserInfoById{
	DataInputStream in;
	PrintStream out;
	String id;
	public GetUserInfoById(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	
	public void sendUserInfo(){
		
		try {
			id=in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			SelectData data=new SelectData();
			System.out.println("read");
			String[] info=data.getUserInfo(id);
			System.out.println(info.length);
			for(int i=0;i<info.length;i++){
				out.println(URLEncoder.encode(info[i]));
				System.out.println(info[i]);
			}
			out.println(URLEncoder.encode("finish"));
			
	}
}
