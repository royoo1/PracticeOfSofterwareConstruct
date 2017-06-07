package Dao.implement;

import Dao.interfac.ISelectData;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
public class SelectData implements ISelectData{
	Connection conn;
	public SelectData(){
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
	
	public boolean judgeId(String id){
		
		String sql="select username from user where userid=?";
		try {
			PreparedStatement Stmt= conn.prepareStatement(sql);
			Stmt.setString(1, id);
			ResultSet rs=Stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}
	public int insertInfo(User user){
		String sql="insert into user(userid,username,nikename,sex,school,password,phonenumber,comments,commentstar,usercash)values(?,?,?,?,?,?,?,0,0,0)";
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getNikename());
			stmt.setString(4, user.getSex());
			stmt.setString(5, user.getSchool());
			stmt.setString(6, user.getPassword());
			stmt.setString(7, user.getPhonenumber());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getPassword(String id){
		String sql="select password from user where userid=?";
		try {
			PreparedStatement Stmt= conn.prepareStatement(sql);
			Stmt.setString(1, id);
			ResultSet rs=Stmt.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	
	public void test(){
		String sql="select username from user ";
		try {
			Statement Stmt=conn.createStatement();
			ResultSet rs=Stmt.executeQuery(sql);
		    rs.next();
		    String i=rs.getString(1);
		    System.out.println(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String[] getUserInfo(String id){
		String[] info=new String[10];
		String sql="select * from user where userid=?";
		PreparedStatement Stmt;
		try {
			Stmt = conn.prepareStatement(sql);
			Stmt.setString(1, id);
			ResultSet rs=Stmt.executeQuery();
			rs.next();
			info[0]=rs.getString(1);
			info[1]=rs.getString(2);
			info[2]=rs.getString(3);
			info[3]=rs.getString(4);
			info[4]=rs.getString(5);
			info[5]=rs.getString(6);
			info[6]=rs.getString(7);
			info[7]= rs.getString(8);
			info[8]=rs.getString(9);
			info[9]=rs.getString(10);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
}
	
