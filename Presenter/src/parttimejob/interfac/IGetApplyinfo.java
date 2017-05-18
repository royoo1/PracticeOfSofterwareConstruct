package parttimejob.interfac;

import java.util.List;

import model.ApplyInfo;

public interface IGetApplyinfo {
	public List<ApplyInfo> getApplyInfoByJobid(int jobid);
}
