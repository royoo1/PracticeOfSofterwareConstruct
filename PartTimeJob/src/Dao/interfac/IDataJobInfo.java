package Dao.interfac;

import model.Job;

public interface IDataJobInfo {
	public void pushJobInfo(Job job);
	public boolean confirmExist(Job job);
	public int getJobid(Job job);
	public void createRJobUser(Job job,int id);
	public void close();
}
