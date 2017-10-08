package com.cxh.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP 浏览器可请求的服务器端
 * 浏览器只要输入127.0.0.1:10000就可以访问到该服务器
 * 只不过该服务器返回的信息被浏览器认为是无效的
 * @author Mr-hang
 *
 */
public class TCPWebServer {
	
	public void WebServer() throws Exception{
		ServerSocket ss=new ServerSocket(10000);
		Socket s=ss.accept();
		System.out.println("浏览器已连接到本服务器");
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		String info=null;
		//读取客户端发来的请求信息，这货发来的连接信息是保持存活，所以此循环结束不了。
		while((info=br.readLine())!=null){	
			System.out.println(info);
		}
		s.close();
		ss.close();
	}
}
