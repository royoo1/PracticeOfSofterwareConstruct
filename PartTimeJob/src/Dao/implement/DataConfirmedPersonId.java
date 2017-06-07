package Dao.implement;

import Dao.interfac.IDataConfirmedPersonId;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataConfirmedPersonId implements IDataConfirmedPersonId{
	Connection conn;
	public DataConfirmedPersonId(){
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
	
	public void setConfirmedPersonId(String jobid,List<String> idlist){
		String sql="update getjob set ifget=1 where jobid=? and workerid=?";
		for(int i=0;i<idlist.size();i++){
			try {
				PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setInt(1,Integer.parseInt(jobid));
				stmt.setString(2,idlist.get(i));
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		deletePersonFail(jobid);
	}
	
	public List<String> getConfirmedPersonId(String jobid){
		List<String> idlist=new ArrayList<String>();
		String sql="select workerid from getjob where jobid=? and ifget=1";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,Integer.parseInt(jobid));
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()){
				idlist.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idlist;
	}
	
     public void deletePersonFail(String jobid){
    	 String sql="update getjob set ifget=2 where jobid=? and ifget=0";
    	 try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(jobid));
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
		
	}
}
