package parttimejob.implement;

import parttimejob.interfac.IGetApplyPersonId;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import commonSocekt.ConnectServer;

public class GetApplyPersonId implements IGetApplyPersonId{
	ConnectServer cons;
	DataInputStream in;
	PrintStream out;
	List<String> idlist=new ArrayList<String>();
	public GetApplyPersonId(){
		cons=new ConnectServer();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	
	public List<String> getconfirmedPersonId(String jobid){
		URLDecoder ud = new URLDecoder();
		out.println("getConfirmedPersonId");
		try {
			out.println(URLEncoder.encode(jobid, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String get=null;
		try {
			get=ud.decode(in.readLine(), "utf-8");
			while(!get.equals("finsih")){
				idlist.add(get);
				get=ud.decode(in.readLine(), "utf-8");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idlist;
		
	}
}
