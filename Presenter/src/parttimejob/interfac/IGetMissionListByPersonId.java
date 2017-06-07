package parttimejob.interfac;

import java.util.List;

import model.MissionInfo;

public interface IGetMissionListByPersonId {
	public List<MissionInfo> getMissionListByPersonId(String workerid);
}
