package parttimejob.interfac;

import java.util.List;
import model.JobInfo;
public interface IGetJobInfo {
	public List<JobInfo> getBytype(String type,int page);
}
