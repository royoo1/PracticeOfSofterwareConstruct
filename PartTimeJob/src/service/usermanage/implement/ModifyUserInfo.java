package service.usermanage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import Dao.implement.DataModifyUserInfo;
import service.usermanage.interfac.IModifyUserInfo;

public class ModifyUserInfo implements IModifyUserInfo{
	PrintStream output;
	DataInputStream in;
	String[] reinfo=new String[7];
	public ModifyUserInfo(PrintStream output,DataInputStream in){
		this.output=output;
		this.in=in;
	}
	
	public void modifyUserInfo(){
		URLDecoder ud = new URLDecoder();
		String key=null;
		String value=null;
		String userid=null;
		String msg=null;
		Map<String,String> map=new HashMap<String,String>();
		try {
			userid=ud.decode(in.readLine(), "utf-8");
			System.out.println(userid);
			key=ud.decode(in.readLine(), "utf-8");
			System.out.println(key);
			while(!key.equals("finish")){
				
				value=ud.decode(in.readLine(), "utf-8");
				map.put(key, value);
				key=ud.decode(in.readLine(), "utf-8");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataModifyUserInfo dmu=new DataModifyUserInfo();
		msg=dmu.modifyUserInfo(map, userid);
		try {
			output.println(URLEncoder.encode(msg, "utf-8"));
			output.println(URLEncoder.encode("finish", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
