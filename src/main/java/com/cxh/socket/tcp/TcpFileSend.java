package com.cxh.socket.tcp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

/**
 * 该程序可以用tcp协议来完成文本文件传输、二进制文件传输
 * @author Mr-hang
 *
 */
public class TcpFileSend {
	@Test
	public void  sendFileClient() throws Exception {
		Socket s =new Socket(InetAddress.getByName("127.0.0.1"),10000);
		System.out.println("已为127.0.0.1的服务器建立连接");
		System.out.println("请输入您要发送的文件路径");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "gbk"));
		String filePath = bufferedReader.readLine();
		//源文件读取流
		BufferedReader reader=new BufferedReader(new FileReader(filePath));
		//写到服务器的输出流
		PrintWriter printWriter=new PrintWriter(s.getOutputStream(),true);				
		//服务器反馈回来的输入流
		BufferedReader response=new BufferedReader(new InputStreamReader(s.getInputStream()));
		String line=null;
		while((line=reader.readLine())!=null){
			printWriter.println(line);
		}
		s.shutdownOutput();//此处应该加个流的结束标记,告诉服务端不用读取了
		String info=response.readLine();//读取服务端发的反馈
		System.out.println("服务端的响应为："+info);
		s.close();
		reader.close();
	}
	@Test
	public void tcpReciceFile() throws Exception{
		ServerSocket serverSocket=new ServerSocket(10000);
		System.out.println("已开启Socket连接，监听100000端口");
		Socket socket=serverSocket.accept();
		System.out.println("已得到客户端请求，文件传输即将开始");
		//写入文件流
		PrintWriter printWriter=new PrintWriter(new FileWriter("D:\\文件名.txt"),true);
		//Socket客户端的输入流
		BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),"gbk"));
		String line=null;
		while((line=reader.readLine())!=null)
		{
			printWriter.println(line);
		}
		//发送响应信息给客户端
		PrintWriter writer=new PrintWriter(socket.getOutputStream(),true);
		writer.println("上传成功");
		printWriter.close();
		socket.close();
		serverSocket.close();
	}
}
