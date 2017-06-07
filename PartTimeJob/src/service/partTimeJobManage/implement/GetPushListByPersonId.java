package service.partTimeJobManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import service.partTimeJobManage.interfac.IgGetPushListByPersonId;
import Dao.implement.GetJobInfo;
import model.Job;

public class GetPushListByPersonId implements IgGetPushListByPersonId{
	List<Job> joblist;
	DataInputStream in;
	PrintStream out;
	public GetPushListByPersonId(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	
	public void getPushListByPersonId(){
		URLDecoder ud = new URLDecoder();
		String bossid="";
		try {
			bossid = ud.decode(in.readLine(), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GetJobInfo getjobinfo=new GetJobInfo();
		joblist=getjobinfo.getJobInfoByPersonId(bossid);
		String[] jobinfo;
		
		try {
			out.println(URLEncoder.encode("start", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("进入循环");
		for(int i = 0;i < joblist.size(); i ++){
			jobinfo=(joblist.get(i)).toStringArray();
			System.out.println("开始传输");
            try {
				out.println(URLEncoder.encode(jobinfo[0], "utf-8"));
				System.out.println(jobinfo[0]);
				out.println(URLEncoder.encode(jobinfo[1], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[2], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[3], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[4], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[5], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[6], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[7], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[8], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[9], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[10], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[11], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[12], "utf-8"));
				out.println(URLEncoder.encode(jobinfo[13], "utf-8"));
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
