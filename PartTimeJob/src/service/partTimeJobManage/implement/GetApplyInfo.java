package service.partTimeJobManage.implement;

import service.partTimeJobManage.interfac.IGetApplyInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import model.ApplyInfo;
import Dao.implement.DataApplyInfo;

public class GetApplyInfo implements IGetApplyInfo{
	DataInputStream in;
	PrintStream out;
	public GetApplyInfo(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	public void getApplyInfo(){
		String get=null;
		List<ApplyInfo> alist=null;
		ApplyInfo ai;
		URLDecoder ud = new URLDecoder();
		try {
			get=ud.decode(in.readLine(), "utf-8");
			DataApplyInfo dai=new DataApplyInfo();
			alist=dai.getApplyByJobid(Integer.parseInt(get));
			for(int i=0;i<alist.size();i++){
				ai=alist.get(i);
				out.println(URLEncoder.encode(ai.getJobid(), "utf-8"));
				out.println(URLEncoder.encode(ai.getWorkerid(), "utf-8"));
				out.println(URLEncoder.encode(ai.getContent(), "utf-8"));
				System.out.println(ai.getContent());
				out.println(URLEncoder.encode(ai.getIfget(), "utf-8"));
			}
			out.println(URLEncoder.encode("finish", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
