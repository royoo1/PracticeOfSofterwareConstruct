package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parttimejob.implement.ApplyJob;
import parttimejob.implement.GetApplyInfo;
import parttimejob.implement.GetJobInfo;
import parttimejob.implement.SendJobInfo;
import userManage.implement.ConfirmLogin;
import userManage.implement.GetUserInfo;
import userManage.implement.ModifyUserInfo;
import userManage.implement.SetUserInfo;
import model.ApplyInfo;
import model.JobInfo;
import model.UserInfo;


public class TestJob {
	public static void main(String[] args){
		//setuserinfo();
		//sendjobinfo();
		//getJobInfo();
		//setApply();
		//getApply();
		//testModifyUserInfo();
		confirmLogin();
	}
	public static void setuserinfo(){
		
		UserInfo setuserinfo=new UserInfo();
		setuserinfo.setUserid("14301042");
		setuserinfo.setUsername("卢海龙");
		setuserinfo.setNikename("royoo1");
		setuserinfo.setSex("男");
		setuserinfo.setSchool("软件学院");
		setuserinfo.setPassword("012345");
		setuserinfo.setPhonenumber("18401605971");
		SetUserInfo senduserinfo=new SetUserInfo();
		senduserinfo.sendInfo(setuserinfo);
		
		/**SetUserInfo setuserinfo=new SetUserInfo();
		setuserinfo.setUserid("14301041");
		setuserinfo.setUsername("刘琛");
		setuserinfo.setNikename("demien糖");
		setuserinfo.setSex("男");
		setuserinfo.setSchool("软件学院");
		setuserinfo.setPassword("012345");
		setuserinfo.setPhonenumber("13241828119");
		setuserinfo.sendInfo();**/
	}
	public static void getuserinfo(){
		GetUserInfo getuserinfo=new GetUserInfo();
		UserInfo userinfo=getuserinfo.getUserInfo("14301042","123456");
		System.out.println(userinfo.getUsername());
	}
	public static void sendjobinfo(){
		/**SendJobInfo send=new SendJobInfo();
		send.setAdress("16号公寓506宿舍");
		send.setCash("50");
		send.setDeadline("2017-4-19 12:00:00");
		send.setExcutedate("2017-4-18 12:00:00");
		send.setJobcontent("去学活下沉广场买一瓶洗衣液");
		send.setJobname("买洗衣液");
		send.setJobtype("买生活品");
		send.setPhone("18401605971");
		send.setPushdate();
		send.setSexrequire("男");
		send.setUserid("14301041");
		send.sendJobInfo();**/
		
		JobInfo send=new JobInfo();
		send.setAdress("16号公寓506宿舍");
		send.setCash("50");
		send.setDeadline("2017-4-24 12:00:00");
		send.setExcutedate("2017-4-23 12:00:00");
		send.setJobcontent("去学活下沉广场买一瓶洗衣液");
		send.setJobname("买洗衣液");
		send.setJobtype("生活");
		send.setPhone("18401605971");
		send.setPersonnumber("3");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		send.setPushdate(df.format(new Date()));
		send.setSexrequire("男");
		send.setUserid("14301042");
		SendJobInfo sendjob=new SendJobInfo();
		sendjob.sendJobInfo(send);
		
		/**JobInfo send=new JobInfo();
		send.setAdress("16号公寓506宿舍");
		send.setCash("10");
		send.setDeadline("2017-4-24 12:00:00");
		send.setExcutedate("2017-4-23 12:00:00");
		send.setJobcontent("去南门中通快递6号柜13号");
		send.setJobname("取快递");
		send.setJobtype("生活");
		send.setPhone("18401605971");
		send.setPersonnumber("2");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		send.setPushdate(df.format(new Date()));
		send.setSexrequire("男");
		send.setUserid("14301042");
		SendJobInfo sendjob=new SendJobInfo();
		sendjob.sendJobInfo(send);**/
	}
	
	
	public static void getJobInfo(){
		List<JobInfo> joblist=new ArrayList<JobInfo>();
		JobInfo job=new JobInfo();
		GetJobInfo getjobinfo=new GetJobInfo();
		joblist=getjobinfo.getBytype("pushtime", 1);
		for(int i=0;i<joblist.size();i++){
			job=joblist.get(i);
			System.out.println(job.getJobname());
			System.out.println(job.getPushdate());
			System.out.println(job.getAdress());
			System.out.println(job.getCash());
			System.out.println(job.getDeadline());
			System.out.println(job.getExcutedate());
			System.out.println(job.getJobcontent());
			System.out.println(job.getJobtype());
			System.out.println(job.getPhone());
			System.out.println(job.getSexrequire());
			System.out.println(job.getUserid());
			
			
		}
	}
	
	public static void setApply(){
		/**ApplyJob aj=new ApplyJob();
		aj.setJobid("15");
		aj.setWorkerid("14301041");
		aj.setContent("我很强");
		aj.sendApply();**/
		ApplyInfo aj=new ApplyInfo();
		aj.setJobid("15");
		aj.setWorkerid("14301042");
		aj.setContent("我更强");
		ApplyJob apj=new ApplyJob();
	}
	public static void getApply(){
		GetApplyInfo gai=new GetApplyInfo();
		List<ApplyInfo> ai=gai.getApplyInfoByJobid(15);
		ApplyInfo aj=null;
		for(int i=0;i<ai.size();i++){
			aj=ai.get(i);
			System.out.println(aj.getWorkerid());
		}
	}
	
	public static void testModifyUserInfo(){
		String msg=null;
		ModifyUserInfo mui=new ModifyUserInfo();
		Map<String,String> map=new HashMap<String,String>();
		map.put("phonenumber","15810803905");
		msg=mui.changeNikename(map, "14301049");
		System.out.println(msg);
	}
	
	public static void confirmLogin(){
		ConfirmLogin cl=new ConfirmLogin();
		System.out.println(cl.confirmLogin("14301044","123456"));
	}
}
