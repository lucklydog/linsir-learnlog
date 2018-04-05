
import java.io.*;
import java.net.*;
public class Server {
	public static final int port = 2342;//监听端口号
	public static final String host = "localhost";
	public static void main(String [] args) {
		System.out.println("Server start...");
		try {
			ServerSocket serversocket = new ServerSocket(port);
			//创建一个ServerSocket，可以指定请求的队列的长度
			//调用方法serverSocket(port, int a),表示超过a个后请求会被拒绝
			while(true) {
				Socket client = serversocket.accept();//client表示接受连接的一个客户端
				new HandlerThread(client);//为这个客户端创建一个新的线程

			}
		}catch(Exception e) {
			System.out.println("服务器异常："+e.getMessage());
		}
	}
	private static class HandlerThread implements Runnable{//继承Runnable,覆盖run方法
		private Socket socket;
		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}
		public void run(){
			try {
				//读取客户端的数据
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String ClientStr  = input.readLine();//处理客户端的数据
				System.out.println("客户端发来的数据是");
				//向客户端回复信息
				PrintStream out = new PrintStream(socket.getOutputStream());
				System.out.println("服务器端发送信息：\t");
				//提示发送一段回复
				String s = new BufferedReader(new InputStreamReader(System.in)).readLine();//将输入数据保存在str中以便读取

				out.println(s);//将数据传输到服务器端
				out.close();
				input.close();
			}catch(Exception e) {
				System.out.println("服务器端发生异常："+e.getMessage());
			}finally {
				if(socket != null) {
					try {
					socket.close();
					}catch(Exception e) {
						System.out.println("socket关闭失败"+e.getMessage());
					}
				}
			}
		}

	}
}
