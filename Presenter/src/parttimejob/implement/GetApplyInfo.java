package parttimejob.implement;

import parttimejob.interfac.IGetApplyinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import model.ApplyInfo;
import commonSocekt.ConnectServer;

public class GetApplyInfo implements IGetApplyinfo{
	List<ApplyInfo> alist=new ArrayList<ApplyInfo>();
	ConnectServer cons;
	DataInputStream in;
	PrintStream out;
	public GetApplyInfo(){
		cons=new ConnectServer();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	public List<ApplyInfo> getApplyInfoByJobid(int jobid){
		out.println("getapplyinfo");
		out.println(String.valueOf(jobid));
		URLDecoder ud = new URLDecoder();
		String get;
		ApplyInfo ajob;
		try {
			get=ud.decode(in.readLine(), "utf-8");
			while(!get.equals("finish")){
				ajob=new ApplyInfo();
				ajob.setJobid(get);
				ajob.setWorkerid(ud.decode(in.readLine(), "utf-8"));
				ajob.setContent(ud.decode(in.readLine(), "utf-8"));
				ajob.setIfget((ud.decode(in.readLine(), "utf-8")));
				alist.add(ajob);
				get=ud.decode(in.readLine(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;
	}
}
