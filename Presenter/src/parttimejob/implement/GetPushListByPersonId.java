package parttimejob.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import parttimejob.interfac.IGetPushListByPersonId;
import model.JobInfo;
import commonSocekt.ConnectServer;

public class GetPushListByPersonId implements IGetPushListByPersonId{
	List<JobInfo> joblist=new ArrayList<JobInfo>();
	ConnectServer cons;
	DataInputStream in;
	PrintStream out;
	public GetPushListByPersonId(){
		cons=new ConnectServer();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	
	public List<JobInfo> getPushListByPersonId(String personId){
		URLDecoder ud = new URLDecoder();
		String get;
		JobInfo job;
		out.println("GetPushListByPersonId");
		try {
			out.println(URLEncoder.encode(personId, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			get = in.readLine();
			get=ud.decode(get, "utf-8");
			
			if(get.equals("start")){
			System.out.println(get);
			get=ud.decode(in.readLine(), "utf-8");
			while(!get.equals("finish")){
				job=new JobInfo();
				job.setJobname(get);
				job.setJobcontent(ud.decode(in.readLine(), "utf-8"));
				job.setPushdate(ud.decode(in.readLine(), "utf-8"));
				job.setDeadline(ud.decode(in.readLine(), "utf-8"));
				job.setExcutedate(ud.decode(in.readLine(), "utf-8"));
				job.setIffinish(ud.decode(in.readLine(), "utf-8"));
				job.setCash(ud.decode(in.readLine(), "utf-8"));
				job.setJobtype(ud.decode(in.readLine(), "utf-8"));
				job.setSexrequire(ud.decode(in.readLine(), "utf-8"));
				job.setAdress(ud.decode(in.readLine(), "utf-8"));
				job.setPhone(ud.decode(in.readLine(), "utf-8"));
				job.setUserid(ud.decode(in.readLine(), "utf-8"));
				job.setPersonnumber(ud.decode(in.readLine(), "utf-8"));
				job.setJobid(ud.decode(in.readLine(), "utf-8"));
				joblist.add(job);
				get=ud.decode(in.readLine(), "utf-8");
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("异常");
			e.printStackTrace();
		}
		return joblist;
	}
}
