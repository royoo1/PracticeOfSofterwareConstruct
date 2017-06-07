package Dao.interfac;

import java.util.List;

import model.MissionInfo;

public interface IDataGetMissionListById {

	public List<MissionInfo> getMissionListById(String workerid);
	
}
