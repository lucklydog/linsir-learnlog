
import java.io.*;
import java.net.*;
public class Client {
	public static final int port = 2342;
	public static final String host = "localhost";
	public  static void main(String [] args) {
		 System.out.println("Client start...");
		 while(true) {
			 Socket socket = null;//初始化socket，为null
			 try {
				 socket  = new Socket(host,port);//实例化一个socket
				 //读取服务器上的数据
				 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 //向服务器发送数据的输出流
				 PrintStream out = new PrintStream(socket.getOutputStream());
				 System.out.println("请输入：\t");
				 //输入的数据保存在Str中
				 String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
				 out.println(str);//发送到输出流，送到客户端
				 String ret = input.readLine();//从服务器端读取数据
				 System.out.println("从服务器端读取的数据是:"+ret);
				 if(ret.equals("bye")) {
					 System.out.println("通讯结束，即将关闭socket");
					 Thread.sleep(500);
					 break;
				 }
				 out.close();//关闭输出流
				 input.close();//关闭输入流

			 }
			 catch(Exception e) {
				 System.out.println("客户端出现异常"+e.getMessage());

			 }finally {
				 if(socket!= null) {
					 try {
						 socket.close();//如果socket未关闭，将其关闭
					 }catch(Exception e) {
						 e.printStackTrace();
					 }
				 }
			 }
		 }
	}
}
