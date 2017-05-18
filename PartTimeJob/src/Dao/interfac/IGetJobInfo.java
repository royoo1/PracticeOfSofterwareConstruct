package Dao.interfac;

import java.util.List;

import model.Job;

public interface IGetJobInfo {
	public void close();
	public List<Job> getJobByPage(int page,String type);
	public List<Job> sortByPushtime(int page);
	public List<Job> sortByExcutetime(int page);
	public List<Job> sortByCash(int page);
}
