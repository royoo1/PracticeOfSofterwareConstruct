package parttimejob.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import parttimejob.interfac.IGetMissionListByPersonId;
import model.MissionInfo;
import commonSocekt.ConnectServer;

public class GetMissionListByPersonId implements IGetMissionListByPersonId{

	ConnectServer cons;
	Socket socket;
	PrintStream out;
	DataInputStream in;
	public GetMissionListByPersonId(){
		cons=new ConnectServer();
		socket=cons.getSocket();
		out=cons.getPrintStream();
		in=cons.getDataInputStream();
	}
	
	public List<MissionInfo> getMissionListByPersonId(String workerid){
		URLDecoder ud = new URLDecoder();
		List<MissionInfo> missionList=new ArrayList<MissionInfo>();
		MissionInfo missionInfo;
		String get=null;
		out.println("GetMissionListByPersonId");
		try {
			out.println(URLEncoder.encode(workerid, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			get=ud.decode(in.readLine(), "utf-8");
			while(!get.equals("finish")){
				missionInfo=new MissionInfo();
				missionInfo.setJobid(get);
				missionInfo.setJobname(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setJobcontent(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setPushdate(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setDeadline(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setExcutedate(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setCash(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setAdress(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setPhone(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setUserid(ud.decode(in.readLine(), "utf-8"));
				missionInfo.setIfget(ud.decode(in.readLine(), "utf-8"));
				get=ud.decode(in.readLine(), "utf-8");
				missionList.add(missionInfo);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return missionList;
	}
	}















