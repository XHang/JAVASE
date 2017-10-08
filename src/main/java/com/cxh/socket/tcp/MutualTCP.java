package com.cxh.socket.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

/**
 * 演示相互的TCP连接
 * 即Server端和客户端相互发送信息
 * @author Mr-hang
 *
 */

public class MutualTCP {
	
	@Test
	public void tcpServer()throws Exception{
		//创建服务端的Socket，并监听10000端口
		ServerSocket ss=new ServerSocket(10000);
		//获取连接的客户端的Socket对象
		System.out.println("已为本地创建Socket服务");
		Socket s=ss.accept();
		//读取数据
		InputStream in=s.getInputStream();
		byte []buf=new byte[1024];
		int leng=in.read(buf);
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+"已连接上");
		System.out.println("客户端说："+new String (buf,0,leng));
		//发送数据
		OutputStream os=s.getOutputStream();
		os.write("俺服务器已经收到，客户端你可知道".getBytes());
		System.out.println("服务端已完成使命");
		s.close();
		ss.close();
	}
	
	@Test
	public void tcpClient()throws Exception{
		//创建客户端的Socket服务
		Socket s=new Socket(InetAddress.getByName("127.0.0.1"),10000);
		//获取本次链接的输出流
		OutputStream os=s.getOutputStream();
		//写数据给服务端
		os.write("服务端，你好".getBytes());
		InputStream in=s.getInputStream();
		byte buf[]=new byte[1024];
		//等待服务端发来的数据。。。
		int leng=in.read(buf);
		System.out.println("服务端的反馈:"+new String(buf,0,leng));
		//全TM关了
		s.close();
	}
}
