package service.partTimeJobManage.implement;

import service.partTimeJobManage.interfac.IConfirmApplyPersonId;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import Dao.implement.DataConfirmedPersonId;

public class ConfirmApplyPersonId implements IConfirmApplyPersonId{
	DataInputStream in;
	PrintStream out;
	List<String> idlist=new ArrayList<String>();
	public ConfirmApplyPersonId(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	
	public void setConfrim(){
		String get=null;
		String jobid=null;
		URLDecoder ud = new URLDecoder();
		try {
			jobid=ud.decode(in.readLine(), "utf-8");
			get=ud.decode(in.readLine(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!(get).equals("finish")){
			idlist.add(get);
			try {
				get=ud.decode(in.readLine(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		DataConfirmedPersonId cpi=new DataConfirmedPersonId();
		cpi.setConfirmedPersonId(jobid, idlist);
	}
	
	public void getComfirmedPersonId(){
		String jobid=null;
		URLDecoder ud = new URLDecoder();
		try {
			jobid=ud.decode(in.readLine(), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataConfirmedPersonId dcpi =new DataConfirmedPersonId();
		idlist=dcpi.getConfirmedPersonId(jobid);
		
		for(int i=0;i<idlist.size();i++){
			try {
				out.println(URLEncoder.encode(idlist.get(i), "utf-8"));
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
		
	}
}
