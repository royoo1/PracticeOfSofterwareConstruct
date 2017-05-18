/**
修改
*/

package Dao.implement;

import Dao.interfac.IDataApplyInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ApplyInfo;

public class DataApplyInfo implements IDataApplyInfo{
	Connection conn;
	public DataApplyInfo(){
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
	public void storeApply(String[] ainfo){
		String sql="insert into getjob(jobid,workerid,content,ifget)values(?,?,?,0)";
		
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1,Integer.parseInt(ainfo[0]));
			stmt.setString(2, ainfo[1]);
			stmt.setString(3, ainfo[2]);
			stmt.executeUpdate();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ApplyInfo> getApplyByJobid(int id){
		String sql="select * from getjob where jobid=?";
		List<ApplyInfo> alist=new ArrayList<ApplyInfo>();
		ApplyInfo ainfo=null;
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				ainfo=new ApplyInfo();
				ainfo.setJobid(String.valueOf(rs.getInt(1)));
				ainfo.setWorkerid(rs.getString(2));
				ainfo.setContent(rs.getString(3));
				System.out.println(ainfo.getContent());
				ainfo.setIfget(String.valueOf(rs.getInt(4)));
				alist.add(ainfo);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;
	}

}
