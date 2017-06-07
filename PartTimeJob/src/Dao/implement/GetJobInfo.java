package Dao.implement;

import Dao.interfac.IGetJobInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Job;

public class GetJobInfo implements IGetJobInfo{
	Connection conn;
	public GetJobInfo(){
		String driver = "com.mysql.jdbc.Driver";
        String dbName = "job";
        String passwrod = "";
        String userName = "root";
        String url = "jdbc:mysql://localhost:3306/" + dbName+"?useUnicode=true&characterEncoding=utf-8";
        try{
        	
        	Class.forName(driver);
            conn = DriverManager.getConnection(url, userName,passwrod);          
        }catch(Exception e){	
        }
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Job> getJobByPage(int page,String type){
		List<Job> joblist=new ArrayList<Job>();
		switch(type){
		case "pushtime":
			joblist=sortByPushtime(page);
			break;
		case "excutetime":
			joblist=sortByExcutetime(page);
			break;
		case "cash":
			joblist=sortByCash(page);
			break;
		default:
			break;
		}
		return joblist;
	}
	
	public List<Job> sortByPushtime(int page){
		int start=(page-1)*20;
		Job job=null;
		List<Job> joblist=new ArrayList<Job>();
		String sql="select jobname,jobcontent,pushdate,deadline,excutedate,iffinish,cash,jobtype,sexrequire,adress,contactphone,bossid,personnumber,p.jobid from parttimejob p,pushjob q where p.jobid=q.jobid and p.iffinish=0 order by pushdate desc limit ?,20";
		try {
			PreparedStatement Stmt= conn.prepareStatement(sql);
			Stmt.setInt(1, start);
			ResultSet rs=Stmt.executeQuery();
			while(rs.next()){
				job=new Job();
				job.setJobname(rs.getString(1));
				System.out.println(rs.getString(1));
				job.setJobcontent(rs.getString(2));
				job.setPushdate((rs.getDate(3)).toString());				
				job.setDeadline((rs.getDate(4)).toString());				
				job.setExcutedate((rs.getDate(5)).toString());				
				job.setIffinish(String.valueOf((rs.getInt(6))));				
				job.setCash(String.valueOf((rs.getInt(7))));				
				job.setJobtype(rs.getString(8));
				job.setSexrequire(rs.getString(9));				
				job.setAdress(rs.getString(10));				
				job.setPhone(rs.getString(11));				
				job.setUserid(rs.getString(12));
				job.setPersonnumber(rs.getString(13));
				job.setJobid(String.valueOf(rs.getInt(14)));
				joblist.add(job);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
     return joblist;
	}
	
	public List<Job> sortByExcutetime(int page){
		int start=(page-1)*20;
		Job job=null;
		List<Job> joblist=new ArrayList<Job>();
		String sql="select jobname,jobcontent,pushdate,deadline,excutedate,iffinish,cash,jobtype,sexrequire,adress,contactphone,bossid,personnumber,jobid from parttimejob p,pushjob q where p.jobid=q.jobid and p.iffinish=0 order by excutedate desc limit ?,20";
		try {
			PreparedStatement Stmt= conn.prepareStatement(sql);
			Stmt.setInt(1, start);
			ResultSet rs=Stmt.executeQuery();
			while(rs.next()){
				job=new Job();
				job.setJobname(rs.getString(1));
				System.out.println(rs.getString(1));
				job.setJobcontent(rs.getString(2));
				job.setPushdate((rs.getDate(3)).toString());				
				job.setDeadline((rs.getDate(4)).toString());				
				job.setExcutedate((rs.getDate(5)).toString());				
				job.setIffinish(String.valueOf((rs.getInt(6))));				
				job.setCash(String.valueOf((rs.getInt(7))));				
				job.setJobtype(rs.getString(8));
				job.setSexrequire(rs.getString(9));				
				job.setAdress(rs.getString(10));				
				job.setPhone(rs.getString(11));				
				job.setUserid(rs.getString(12));
				job.setPersonnumber(rs.getString(13));
				job.setJobid(String.valueOf(rs.getInt(14)));
				joblist.add(job);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
     return joblist;
	}
	public List<Job> sortByCash(int page){
		int start=(page-1)*20;
		Job job=null;
		List<Job> joblist=new ArrayList<Job>();
		String sql="select jobname,jobcontent,pushdate,deadline,excutedate,iffinish,cash,jobtype,sexrequire,adress,contactphone,bossid,personnumber,jobid from parttimejob p,pushjob q where p.jobid=q.jobid and p.iffinish=0 order by cash desc limit ?,20";
		try {
			PreparedStatement Stmt= conn.prepareStatement(sql);
			Stmt.setInt(1, start);
			ResultSet rs=Stmt.executeQuery();
			while(rs.next()){
				job=new Job();
				job.setJobname(rs.getString(1));
				System.out.println(rs.getString(1));
				job.setJobcontent(rs.getString(2));
				job.setPushdate((rs.getDate(3)).toString());				
				job.setDeadline((rs.getDate(4)).toString());				
				job.setExcutedate((rs.getDate(5)).toString());				
				job.setIffinish(String.valueOf((rs.getInt(6))));				
				job.setCash(String.valueOf((rs.getInt(7))));				
				job.setJobtype(rs.getString(8));
				job.setSexrequire(rs.getString(9));				
				job.setAdress(rs.getString(10));				
				job.setPhone(rs.getString(11));				
				job.setUserid(rs.getString(12));
				job.setPersonnumber(rs.getString(13));
				job.setJobid(String.valueOf(rs.getInt(14)));
				joblist.add(job);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
     return joblist;
	}
	
	
	public List<Job> getJobInfoByPersonId(String bossid){
		Job job=null;
		List<Job> joblist=new ArrayList<Job>();
		String sql="select jobname,jobcontent,pushdate,deadline,excutedate,iffinish,cash,jobtype,sexrequire,adress,contactphone,bossid,personnumber,p.jobid from parttimejob p,pushjob b where b.jobid=p.jobid and b.bossid=?";
		try {
			PreparedStatement Stmt= conn.prepareStatement(sql);
			Stmt.setString(1, bossid);
			ResultSet rs=Stmt.executeQuery();
			while(rs.next()){
				job=new Job();
				job.setJobname(rs.getString(1));
				System.out.println(rs.getString(1));
				job.setJobcontent(rs.getString(2));
				job.setPushdate((rs.getDate(3)).toString());				
				job.setDeadline((rs.getDate(4)).toString());				
				job.setExcutedate((rs.getDate(5)).toString());				
				job.setIffinish(String.valueOf((rs.getInt(6))));				
				job.setCash(String.valueOf((rs.getInt(7))));				
				job.setJobtype(rs.getString(8));
				job.setSexrequire(rs.getString(9));				
				job.setAdress(rs.getString(10));				
				job.setPhone(rs.getString(11));				
				job.setUserid(rs.getString(12));
				job.setPersonnumber(rs.getString(13));
				job.setJobid(String.valueOf(rs.getInt(14)));
				joblist.add(job);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return joblist;
	}
}
