package parttimejob.implement;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import commonSocekt.ConnectServer;

public class SetApplyPersonId {
	ConnectServer cons;
	DataInputStream in;
	PrintStream out;
	public SetApplyPersonId(){
		cons=new ConnectServer();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	
	public void confirmApplyPersonId(String jobid,List<String> idlist){
		out.println("setConfirmedPersonId");
		try {
			out.println(URLEncoder.encode(jobid, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
