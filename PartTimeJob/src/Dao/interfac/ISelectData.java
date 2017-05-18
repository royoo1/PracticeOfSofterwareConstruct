package Dao.interfac;

import model.User;

public interface ISelectData {
	public boolean judgeId(String id);
	public int insertInfo(User user);
	public String getPassword(String id);
	public String[] getUserInfo(String id);
}
