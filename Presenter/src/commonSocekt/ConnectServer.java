package commonSocekt;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class ConnectServer {
	Socket socket;
	PrintStream out;
	DataInputStream in;
	public ConnectServer(){
		try {
			socket=new Socket("172.29.117.17",12345);  
            out = new PrintStream(socket.getOutputStream());    
            in = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Socket getSocket(){
		return socket;
	}
	public DataInputStream getDataInputStream(){
		return in;
	}
	public PrintStream getPrintStream(){
		return out;
	}

}
