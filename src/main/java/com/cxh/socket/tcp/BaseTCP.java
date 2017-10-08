package com.cxh.socket.tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.Test;

/*
 * 程序目的：演示TCP的服务器和客户端连接
 * 以及客户端向服务端发送信息
 */
public class BaseTCP
{

	@Test
	public void tcpClient() throws UnknownHostException, IOException{
		//第一步：创建TCP的Socket服务，指定服务器的ip地址和端口
		Socket s=new Socket(InetAddress.getByName("127.0.0.1"),10000);
		System.out.println("已和ip地址为127.0.0.1，端口为10000的服务器建立连接，");
		//获取此tcp连接的网络流对象
		OutputStream os=s.getOutputStream();
		//往该流对象写数据
		os.write("你好，再见".getBytes());
		System.out.println("发送信息成功");
		//关闭资源,只需关闭Socket服务资源，流资源依附于Socket服务也会因此而关闭
		s.close();
		System.out.println("客户端已停止");
	}
	@Test
	public void tcpServer()throws Exception{
		//第一步:建立服务端的Socket服务，ServerSocket():并监听一个端口，你不监听，怎么接收客户端的连接请求
		System.out.println("已在本机创建SocketTcp服务，监听10000端口");
		ServerSocket ss=new ServerSocket(10000);
		//第二步:取连接过来的客户端的Socket对象。通过accept（）；方法，如果客户端没连接，则进入等待，所以这个方法是阻塞性的
		Socket s=ss.accept();
		System.out.println("有一个连接对象进来了");
		//第三步:如果客户端发来数据，则通过客户端的Socket对象获取本次连接的输出流对象，并解析里面的数据
		InputStream inputStream=s.getInputStream();
		byte []buf=new byte[1024];
		int leng=inputStream.read(buf);
		String Data=new String (buf,0,leng);
		String ip=s.getInetAddress().getHostAddress();
		System.out.println("服务端的IP地址是:"+ip+"数据信息："+Data);
		//第四步: * 4：不要浪费服务端的连接资源，所以要关掉客户端的Socket服务。因为服务端仅服务一次，所以也可以考虑关掉服务端的Socket服务。
		s.close();
		ss.close();
	}

}
