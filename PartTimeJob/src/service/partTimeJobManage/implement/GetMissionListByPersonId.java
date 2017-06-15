package service.partTimeJobManage.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import service.partTimeJobManage.interfac.IGetMissionListByPersonId;
import Dao.implement.DataGetMissionListById;
import model.Job;
import model.MissionInfo;

public class GetMissionListByPersonId implements IGetMissionListByPersonId{
	List<MissionInfo> missionList;
	MissionInfo missionInfo;
	DataInputStream in;
	PrintStream out;
	String[] missionArray;
	public GetMissionListByPersonId(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	
	public void getMissionListByPersonId(){
		URLDecoder ud = new URLDecoder();
		try {
			String workerid=ud.decode(in.readLine(), "utf-8");
			DataGetMissionListById get=new DataGetMissionListById();
			missionList=get.getMissionListById(workerid);
			for(int i=0;i<missionList.size();i++){
				missionInfo=missionList.get(i);
				missionArray=missionInfo.toStringArray();
				out.println(URLEncoder.encode(missionArray[0], "utf-8"));
				out.println(URLEncoder.encode(missionArray[1], "utf-8"));
				out.println(URLEncoder.encode(missionArray[2], "utf-8"));
				out.println(URLEncoder.encode(missionArray[3], "utf-8"));
				out.println(URLEncoder.encode(missionArray[4], "utf-8"));
				out.println(URLEncoder.encode(missionArray[5], "utf-8"));
				out.println(URLEncoder.encode(missionArray[6], "utf-8"));
				out.println(URLEncoder.encode(missionArray[7], "utf-8"));
				out.println(URLEncoder.encode(missionArray[8], "utf-8"));
				out.println(URLEncoder.encode(missionArray[9], "utf-8"));
				out.println(URLEncoder.encode(missionArray[10], "utf-8"));
				out.println(URLEncoder.encode(missionArray[11], "utf-8"));
			}
			out.println(URLEncoder.encode("finish", "utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
