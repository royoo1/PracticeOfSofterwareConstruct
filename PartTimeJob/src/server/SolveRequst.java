package server;
import java.io.DataInputStream;
import java.io.PrintStream;

import service.partTimeJobManage.implement.ConfirmApplyPersonId;
import service.partTimeJobManage.implement.GetApplyInfo;
import service.partTimeJobManage.implement.PushJob;
import service.partTimeJobManage.implement.SendJob;
import service.partTimeJobManage.implement.SetApplyInfo;
import service.usermanage.implement.GiveUserInfo;
import service.usermanage.implement.ModifyUserInfo;
import service.usermanage.implement.SolveLogInOut;
import service.usermanage.implement.SolveRegister;

public class SolveRequst {
	//信息和输出流
	public SolveRequst(){
	}
	
	//判断信息并回应
	public void judgeRequst(String msg,PrintStream out,DataInputStream in){
		
		switch(msg){
		case "registerinfo":
			SolveRegister re=new SolveRegister(out,in);
			re.judgeid();
			break;
		case "login":
			SolveLogInOut inout=new SolveLogInOut();
			inout.solveLogIn(out, in);
			break;
		case "logout":
			break;
		case "getjobinfo":
			SendJob sendjob=new SendJob(in,out);
			sendjob.SendByPage();
			break;
		case "getuserinfo":
			GiveUserInfo giveinfo=new GiveUserInfo(in,out);
			giveinfo.sendUserInfo();
		    break;
		case "pushjob":
			PushJob pushjob=new PushJob(in,out);
			pushjob.getJobInfo();
			break;
		case "setapplyinfo":
			SetApplyInfo sai=new SetApplyInfo(in,out);
			sai.setApplyInfo();
			break;
		case "getapplyinfo":
			GetApplyInfo gai=new GetApplyInfo(in,out);
			gai.getApplyInfo();
			break;
		case "setConfirmedPersonId":
			ConfirmApplyPersonId capi=new ConfirmApplyPersonId(in,out);
			capi.setConfrim();
			break;
		case "getConfirmedPersonId":
			ConfirmApplyPersonId capid=new ConfirmApplyPersonId(in,out);
			break;
		case "modifyUserInfo":
			ModifyUserInfo mui=new ModifyUserInfo(out,in);
			mui.modifyUserInfo();
			break;
		default:
			break;
		}
		
	}
}
