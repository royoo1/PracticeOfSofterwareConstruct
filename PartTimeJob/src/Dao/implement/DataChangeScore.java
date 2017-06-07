package Dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import Dao.interfac.IDataChangeScore;

public class DataChangeScore implements IDataChangeScore{
	Connection conn;
	public DataChangeScore(){
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
	
	public String changeScore(String score,String personId){
		String sql="select commentstar from user where userid=?";
		String sql1="update user set commentstar=? where userid=?";
		String sql2="update user set comments=comments+1 where userid=?";
		PreparedStatement stmt;
		int retu=0;
		try {
			System.out.println("start");
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, personId);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			float cs=rs.getFloat(1);
			if(cs==0){
				cs=Float.parseFloat(score);
			}
			else{
				cs=(cs+Float.parseFloat(score))/2;
			}
			System.out.println(cs);
			stmt=conn.prepareStatement(sql1);
			stmt.setFloat(1, cs);
			stmt.setString(2, personId);
			retu=stmt.executeUpdate();
			
			stmt=conn.prepareStatement(sql2);
			stmt.setString(1, personId);
			stmt.executeUpdate();
			System.out.println("end");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		if(retu==0){
			return "false";
		}
		else{
			return "success";
		}
	}
	
	
	public String changeScore(Map<String,String> scoreMap){
		String sql="select * from user where userid=?";
		String sql1="update user set commentstar=? where userid=?";
		String sql2="update user set comments=comments+1 where userid=?";
		PreparedStatement stmt;
		int retu=0;
		
		for (Map.Entry<String, String> entry : scoreMap.entrySet()) { 
		try {
			System.out.println(entry.getKey());
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, entry.getKey());
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()){
			float cs=rs.getFloat(9);
			if(cs==0){
				cs=Float.parseFloat(entry.getValue());
			}
			else{
				cs=(cs+Float.parseFloat(entry.getValue()))/2;
			}
			System.out.println(cs);
			stmt=conn.prepareStatement(sql1);
			stmt.setFloat(1, cs);
			stmt.setString(2, entry.getKey());
			retu=stmt.executeUpdate();
			
			stmt=conn.prepareStatement(sql2);
			stmt.setString(1, entry.getKey());
			stmt.executeUpdate();
			System.out.println("end");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		close();
		
		if(retu==0){
			return "false";
		}
		else{
			return "success";
		}
	}
}
