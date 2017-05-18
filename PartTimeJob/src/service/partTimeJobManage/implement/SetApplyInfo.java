package service.partTimeJobManage.implement;

import service.partTimeJobManage.interfac.ISetApplyInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLDecoder;

import Dao.implement.DataApplyInfo;

public class SetApplyInfo implements ISetApplyInfo{
	DataInputStream in;
	PrintStream out;
	public SetApplyInfo(DataInputStream in,PrintStream out){
		this.in=in;
		this.out=out;
	}
	public void setApplyInfo(){
		URLDecoder ud = new URLDecoder();
		String[] ainfo=new String[3];
		try {
			String get=ud.decode(in.readLine(), "utf-8");
			for(int i=0;!get.equals("finish");i++){
				ainfo[i]=get;
				get=ud.decode(in.readLine(), "utf-8");
			}
			
			DataApplyInfo apply=new DataApplyInfo();
			apply.storeApply(ainfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
