package Dao.interfac;

import java.util.List;

import model.ApplyInfo;

public interface IDataApplyInfo {
	public void close();
	public void storeApply(String[] ainfo);
	public List<ApplyInfo> getApplyByJobid(int id);
}
