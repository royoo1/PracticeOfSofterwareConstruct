/**
 *1. 此类判断注册信息是否符合要求
 * id ，学号不重复 否则显示已经注册
 * 2.若符合要求则将信息写入数据库
 *
 * 业务逻辑 1客户端发来注册信息，通过学号查找是否有该人
 * 2.有发送 fail 客户端显示已经注册
 * 没有，发送success 客户端显示注册成功 并将信息存入数据库
 */
package service.usermanage.implement;

import service.usermanage.interfac.ISolveRegister;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLDecoder;

import model.User;
import Dao.implement.SelectData;

public class SolveRegister implements ISolveRegister{
	PrintStream output;
	DataInputStream in;
	String[] reinfo=new String[7];
	public SolveRegister(PrintStream output,DataInputStream in){
		this.output=output;
		this.in=in;
	}
	public void judgeid(){
		User user=new User();
		try {
			String msg = in.readLine();
			URLDecoder ud = new URLDecoder();
			 for(int i=0;!msg.equals("finish");i++){
				 reinfo[i]=ud.decode(msg, "utf-8");
	             msg = in.readLine();
	            }
			 user.setUserid(reinfo[0]);
			 user.setUsername(reinfo[1]);
			 user.setNikename(reinfo[2]);
			 user.setSex(reinfo[3]);
			 user.setSchool(reinfo[4]);
			 user.setPassword(reinfo[5]);
			 user.setPhonenumber(reinfo[6]);
		
			SelectData selectdata=new SelectData();
			boolean idjudge=selectdata.judgeId(user.getUserid());
			if(idjudge){
				output.println("exitID");
				selectdata.test();
			}
			else{
				output.println("success");
				insertInfo(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int insertInfo(User user){
		SelectData selectdata=new SelectData();
		return selectdata.insertInfo(user);
	}
	
	}