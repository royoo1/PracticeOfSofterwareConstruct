package Dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class DataModifyUserInfo {
	Connection conn;
	public DataModifyUserInfo(){
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
	
	public String modifyUserInfo(Map<String,String> map,String userid){
		String sql1="update user set ";
		String sql2="=? where userid=?";
		String sql3="";
		 for (Map.Entry<String, String> entry : map.entrySet()) {  
			 sql3=sql1+entry.getKey()+sql2;
			try {
				PreparedStatement stmt = conn.prepareStatement(sql3);
		        stmt.setString(1,entry.getValue());
		        stmt.setString(2, userid);
		        int re=stmt.executeUpdate();
		        if(re==0){
		        	return "updateFalse";
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		    }  
		 return "success";
	}
	
	
}
