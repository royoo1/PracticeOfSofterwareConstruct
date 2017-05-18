package parttimejob.implement;

import parttimejob.interfac.IGetJobInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;





import model.JobInfo;
import commonSocekt.ConnectServer;

public class GetJobInfo implements IGetJobInfo{
	List<JobInfo> joblist=new ArrayList<JobInfo>();
	ConnectServer cons;
	DataInputStream in;
	PrintStream out;
	public GetJobInfo(){
		cons=new ConnectServer();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	@SuppressWarnings({ "static-access", "deprecation" })
	public List<JobInfo> getBytype(String type,int page){
		System.out.println("开始");
		out.println("getjobinfo");
		out.println(type);
		out.println(String.valueOf(page));
		System.out.println("传输");
		URLDecoder ud = new URLDecoder();
		String get;
		JobInfo job;
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
