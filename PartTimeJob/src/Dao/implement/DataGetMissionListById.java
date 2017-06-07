package Dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.interfac.IDataGetMissionListById;
import model.MissionInfo;

public class DataGetMissionListById implements IDataGetMissionListById{
	Connection conn;
	public DataGetMissionListById(){
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
	
	public List<MissionInfo> getMissionListById(String workerid){
		List<MissionInfo> missionList=new ArrayList<MissionInfo>();
		MissionInfo missionInfo;
		String sql="select g.jobid,jobname,jobcontent,pushdate,deadline,excutedate,cash,adress,contactphone,b.bossid,g.ifget from parttimejob p,pushjob b,getjob g where "
				+ "g.jobid=p.jobid and p.jobid=b.jobid and g.workerid=?";
		try {
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, workerid);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				missionInfo=new MissionInfo();
				missionInfo.setJobid(rs.getString(1));
				missionInfo.setJobname(rs.getString(2));
				missionInfo.setJobcontent(rs.getString(3));
				missionInfo.setPushdate(rs.getString(4));
				missionInfo.setDeadline(rs.getString(5));
				missionInfo.setExcutedate(rs.getString(6));
				missionInfo.setCash(rs.getString(7));
				missionInfo.setAdress(rs.getString(8));
				missionInfo.setPhone(rs.getString(9));
				missionInfo.setUserid(rs.getString(10));
				missionInfo.setIfget(rs.getString(11));
				missionList.add(missionInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return missionList;
	}
}
