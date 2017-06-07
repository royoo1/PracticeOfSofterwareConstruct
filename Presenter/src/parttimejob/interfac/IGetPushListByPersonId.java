package parttimejob.interfac;

import java.util.List;

import model.JobInfo;

public interface IGetPushListByPersonId {
	public List<JobInfo> getPushListByPersonId(String personId);
}
