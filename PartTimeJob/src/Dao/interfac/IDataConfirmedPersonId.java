package Dao.interfac;

import java.util.List;

public interface IDataConfirmedPersonId {
	public void setConfirmedPersonId(String jobid,List<String> idlist);
	public List<String> getConfirmedPersonId(String jobid);
}
