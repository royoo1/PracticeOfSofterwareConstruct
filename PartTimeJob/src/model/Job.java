package model;

public class Job {
	String jobid;
	String jobname;
	String jobcontent;
	String pushdate;
	String deadline;
	String excutedate;
	String cash;
	String jobtype;
	String sexrequire;
	String adress;
	String phone;
	String userid;
	String iffinish;
	String personnumber;
	
	
	
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}
	public String getIffinish() {
		return iffinish;
	}
	public void setIffinish(String iffinish) {
		this.iffinish = iffinish;
	}
	public String[] toStringArray(){
		String[] jobinfo=new String[14];
		jobinfo[0]=jobname;
		jobinfo[1]=jobcontent;
		jobinfo[2]=pushdate;
		jobinfo[3]=deadline;
		jobinfo[4]=excutedate;
		jobinfo[5]=iffinish;
		jobinfo[6]=cash;
		jobinfo[7]=jobtype;
		jobinfo[8]=sexrequire;
		jobinfo[9]=adress;
		jobinfo[10]=phone;
		jobinfo[11]=userid;
		jobinfo[12]=personnumber;
		jobinfo[13]=jobid;
		return jobinfo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public void setJobcontent(String jobcontent) {
		this.jobcontent = jobcontent;
	}
	public String getPushdate() {
		return pushdate;
	}
	public void setPushdate(String pushdate) {
		this.pushdate = pushdate;
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
	
}
