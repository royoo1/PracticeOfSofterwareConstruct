package service.usermanage.implement;
import service.usermanage.interfac.IGiveUserInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import Dao.implement.SelectData;
public class GiveUserInfo implements IGiveUserInfo{
	DataInputStream in;
	PrintStream out;
	String id;
	public GiveUserInfo(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	public boolean judgeIdPass(){
		SelectData data=new SelectData();
		try {
			id=in.readLine();
			String password=in.readLine();
			if(data.judgeId(id)){
				if(data.getPassword(id).equals(password)){
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean sendUserInfo(){
		if(judgeIdPass()){
			SelectData data=new SelectData();
			String[] info=data.getUserInfo(id);
			for(int i=0;i<info.length;i++){
				out.println(URLEncoder.encode(info[i]));
			}
			out.println("finish");
			return true;
		}
		return false;
	}
}
