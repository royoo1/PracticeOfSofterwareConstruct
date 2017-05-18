package Dao.implement;

import Dao.interfac.IDataJobInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Job;


public class DataJobInfo implements IDataJobInfo{
	Connection conn;
	public DataJobInfo(){
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
	
	public void pushJobInfo(Job job){
		String sql="insert into parttimejob(jobname,jobcontent,pushdate,deadline,excutedate,iffinish,cash,jobtype,sexrequire,adress,contactphone,personnumber)values(?,?,?,?,?,0,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, job.getJobname());
			stmt.setString(2, job.getJobcontent());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			
			
			java.util.Date utilDate1=null;
			try {
				utilDate1 = df.parse(job.getPushdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.util.Date utilDate2=null;
			try {
				utilDate2 = df.parse(job.getDeadline());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.util.Date utilDate3=null;
			try {
				utilDate3 = df.parse(job.getExcutedate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			java.sql.Date date1=new java.sql.Date(utilDate1.getTime());
			java.sql.Date date2=new java.sql.Date(utilDate2.getTime());
			java.sql.Date date3=new java.sql.Date(utilDate3.getTime());
			
			stmt.setDate(3, date1);
			stmt.setDate(4, date2);
			stmt.setDate(5, date3);
			stmt.setInt(6, Integer.parseInt(job.getCash()));
			stmt.setString(7,job.getJobtype());
			stmt.setString(8, job.getSexrequire());
			stmt.setString(9, job.getAdress());
			stmt.setString(10, job.getPhone());
			stmt.setInt(11,Integer.parseInt(job.getPersonnumber()));
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean confirmExist(Job job){
		String sql="select pushdate,contactphone from parttimejob where jobname=?";
		PreparedStatement stmt;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		java.util.Date utilDate1=null;
		
		try {
			utilDate1 = df.parse(job.getPushdate());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date date1=new java.sql.Date(utilDate1.getTime());
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, job.getJobname());
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()){
			java.sql.Date date=rs.getDate(1);
			String phone=rs.getString(2);
	        System.out.println(date.toString());
	        System.out.println(phone);
	        
		    if(phone.equals(job.getPhone())&&date.equals(date1)){
		    	System.out.println(phone);
		    	return true; 	
		    }
		    else{
		    	return false;
		    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("dddd");
			e.printStackTrace();
		}
		return false;
	}
	
	public int getJobid(Job job){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		java.util.Date utilDate1=null;
		
		try {
			utilDate1 = df.parse(job.getPushdate());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date date1=new java.sql.Date(utilDate1.getTime());
		String sql="select jobid from parttimejob where jobname=? and pushdate=?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, job.getJobname());
		    stmt.setDate(2, date1);
		    ResultSet rs=stmt.executeQuery();
		    if(rs.next()){
			    return rs.getInt(1);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void createRJobUser(Job job,int id){
		String sql="insert into pushjob(bossid,jobid)values(?,?)";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, job.getUserid());
		    stmt.setInt(2, id);
		    stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
