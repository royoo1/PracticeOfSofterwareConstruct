package parttimejob.implement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

import commonSocekt.ConnectServer;

public class Jobfinish {

	ConnectServer cons;
	DataInputStream in;
	Socket socket;
	PrintStream out;
	public Jobfinish(){
		cons=new ConnectServer();
		socket=cons.getSocket();
		in=cons.getDataInputStream();
		out=cons.getPrintStream();
	}
	public String finishJob(String id){
		URLDecoder ud = new URLDecoder();
		out.println("Jobfinish");
		try {
			out.println(URLEncoder.encode(id, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String get=null;
		try {
			get = ud.decode(in.readLine(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return get;
	}
}
