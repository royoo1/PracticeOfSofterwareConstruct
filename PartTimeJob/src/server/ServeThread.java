package server;
import java.net.Socket;
import java.io.PrintStream;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;

//每一个连接建立时，用于处理请求的线程
public class ServeThread extends Thread{
	//与客户端通信的套接字
	Socket client;
	public ServeThread(Socket c){
		client=c;
	}
	
	public void run(){
		try{
			 //获取用户的IP地址和端口号  
            String clientIP = client.getInetAddress().toString();  
            int clientPort = client.getPort();  
            //创建输出流对象  
            PrintStream out = new PrintStream(client.getOutputStream());  
            //创建输入流对象  
            DataInputStream in = new DataInputStream(client.getInputStream());  
            //读取提交的请求  
            SolveRequst solverequst=new SolveRequst();
            String msg = in.readLine();  
            while(!msg.equals("bye"))
            {
            	solverequst.judgeRequst(msg, out,in);
            	msg = in.readLine();
            }
            out.println("bye");
            client.close();  
		}catch(Exception e){
			
		}
	}

}

