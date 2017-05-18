package parttimejob.implement;

import java.util.Date;
import java.text.SimpleDateFormat;

import commonSocekt.ConnectServer;

import java.net.Socket;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import model.JobInfo;
public class SendJobInfo {
	ConnectServer cons;
	Socket socket;
	PrintStream out;
	public SendJobInfo(){
		cons=new ConnectServer();
		socket=cons.getSocket();
		out=cons.getPrintStream();
	}
	
	public void sendJobInfo(JobInfo jobinfo){
		out.println("pushjob");
		try {
			out.println(URLEncoder.encode(jobinfo.getJobname(), "utf-8"));
			out.println(URLEncoder.encode(jobinfo.getJobcontent(), "utf-8"));
			out.println(URLEncoder.encode(jobinfo.getPushdate(), "utf-8"));
			out.println(URLEncoder.encode(jobinfo.getDeadline(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getExcutedate(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getCash(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getJobtype(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getSexrequire(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getAdress(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getPhone(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getUserid(), "utf-8"));
		    out.println(URLEncoder.encode(jobinfo.getPersonnumber(), "utf-8"));
		    out.println("finish");
		    
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
