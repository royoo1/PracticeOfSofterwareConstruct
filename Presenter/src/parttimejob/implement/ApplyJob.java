package parttimejob.implement;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import model.ApplyInfo;
import commonSocekt.ConnectServer;

public class ApplyJob {
	ConnectServer cons;
	PrintStream out;
	
	public ApplyJob(){
		cons=new ConnectServer();
		out=cons.getPrintStream();
	}
	
	public void sendApply(ApplyInfo ainfo){
		try {
			out.println(URLEncoder.encode("setapplyinfo", "utf-8"));
			out.println(URLEncoder.encode(ainfo.getJobid(), "utf-8"));
			out.println(URLEncoder.encode(ainfo.getWorkerid(), "utf-8"));
			out.println(URLEncoder.encode(ainfo.getContent(), "utf-8"));
			out.println(URLEncoder.encode("finish", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
