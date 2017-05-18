package service.partTimeJobManage.implement;

import service.partTimeJobManage.interfac.IPushJob;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLDecoder;

import model.Job;
import Dao.implement.DataJobInfo;

public class PushJob implements IPushJob{
	DataInputStream in;
	PrintStream out;
	public PushJob(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	public void getJobInfo(){
		String[] jobinfo=new String[12];
		String getinfo=null;
		Job job=new Job();
		try {
			getinfo=in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		URLDecoder ud = new URLDecoder();
		for(int i=0;!getinfo.equals("finish");i++){
			try {
				jobinfo[i]=ud.decode(getinfo, "utf-8");
				getinfo=in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		job.setJobname(jobinfo[0]);
		job.setJobcontent(jobinfo[1]);
		job.setPushdate(jobinfo[2]);
		job.setDeadline(jobinfo[3]);
		job.setExcutedate(jobinfo[4]);
		job.setCash(jobinfo[5]);
		job.setJobtype(jobinfo[6]);
		job.setSexrequire(jobinfo[7]);
		job.setAdress(jobinfo[8]);
		job.setPhone(jobinfo[9]);
		job.setUserid(jobinfo[10]);
		job.setPersonnumber(jobinfo[11]);
		
	    DataJobInfo data=new DataJobInfo();
	    if(data.confirmExist(job)){
	    	
	    }else{
	    	data.pushJobInfo(job);
	    	int id=data.getJobid(job);
	    	data.createRJobUser(job,id);
	    }
	    data.close();
	}
}
