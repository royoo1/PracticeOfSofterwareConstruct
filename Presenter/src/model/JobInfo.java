package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobInfo {
	String jobid;
	String jobname;
	String jobcontent;
	String pushdate;
	String deadline;
	String excutedate;
	String iffinish;
	String cash;
	String jobtype;
	String sexrequire;
	String adress;
	String phone;
	String userid;
    String personnumber;
    
    
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getIffinish() {
		return iffinish;
	}
	public void setIffinish(String iffinish) {
		this.iffinish = iffinish;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getJobcontent() {
		return jobcontent;
	}
	public void setJobcontent(String Jobcontent) {
		this.jobcontent=Jobcontent;
	}
	public String getPushdate() {
		return pushdate;
	}
	public void setPushdate(String pushdate) {
		this.pushdate=pushdate;
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//pushdate=df.format(new Date());// new Date()为获取当前系统时间
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getExcutedate() {
		return excutedate;
	}
	public void setExcutedate(String excutedate) {
		this.excutedate = excutedate;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public String getSexrequire() {
		return sexrequire;
	}
	public void setSexrequire(String sexrequire) {
		this.sexrequire = sexrequire;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	
}
