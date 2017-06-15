package model;

public class MissionInfo {
	String jobid;
	String jobname;
	String jobcontent;
	String pushdate;
	String deadline;
	String excutedate;
	String cash;
	String adress;
	String phone;
	String userid;
	String ifget;
	String iffinish;
	
	
	public String getIffinish() {
		return iffinish;
	}
	public void setIffinish(String iffinish) {
		this.iffinish = iffinish;
	}
	public String getIfget() {
		return ifget;
	}
	public void setIfget(String ifget) {
		this.ifget = ifget;
	}
	public String[] toStringArray(){
		String[] missionInfoArray=new String[12];
		missionInfoArray[0]=jobid;
		missionInfoArray[1]=jobname;
		missionInfoArray[2]=jobcontent;
		missionInfoArray[3]=pushdate;
		missionInfoArray[4]=deadline;
		missionInfoArray[5]=excutedate;
		missionInfoArray[6]=cash;
		missionInfoArray[7]=adress;
		missionInfoArray[8]=phone;
		missionInfoArray[9]=userid;
		missionInfoArray[10]=ifget;
		missionInfoArray[11]=ifget;
		return missionInfoArray;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
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
	
}
