package com.cxh.socket.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.Test;

/**
 * 演示Tcp发送二进制文件，比如说图片
 * @author Mr-hang
 *
 */
public class TcpSendBinaryFile {
	@Test
	public static void sendBinaryClient() throws Exception{
		Socket socket=new Socket("127.0.0.1",10000);
		System.out.println("已经和服务器建立连接，立即开始传输文件");
		InputStream fileInputStream=TcpSendBinaryFile.class.getResourceAsStream("/sendPic.jpg");
		if(fileInputStream ==null){
			socket.close();
			throw new Exception("文件找不到！");
		}
		//InputStream fileInputStream = new FileInputStream("E:\\JAVAWork\\JAVASE\\src\\main\\resources\\sendPic.jpg");
		OutputStream request=socket.getOutputStream();
		InputStream response=socket.getInputStream();
		byte Data[]=new byte[1024];
		int leng=0;
		//将图片文件数据传输至服务端
		while((leng=fileInputStream.read(Data))!=-1){
			request.write(Data, 0, leng);
		}
		//给输出流加一个结束标记
		socket.shutdownOutput();	
		//接受反馈信息
		leng=response.read(Data);		
		System.out.println("服务器反馈："+new String (Data,0,leng));
		socket.close();
		fileInputStream.close();
	}
	@Test
	@SuppressWarnings("resource")
	public void receive() throws IOException{
		ServerSocket serverSocket=new ServerSocket(10000);
		System.out.println("图片服务器已启动");
		//开这个循环，当客户端连进来时，就为其单独开一个线程服务，然后主线程可以继续等下一个客户端的连接，实现并发
		while(true)	{
			Socket sockets=serverSocket.accept();
			new Thread(new ClientThread(sockets)).start();
		}
	}
	
	//专门处理单个客户端连进来的线程
	class ClientThread implements Runnable{
		private Socket sockets;
		ClientThread(Socket sockets){
			this.sockets=sockets;
		}
		public void run() {
			System.out.println("已接收到客户端请求，正在处理数据");
			int flag=0;
			//获取客户端的ip，作为上传的文件名
			String ip=sockets.getInetAddress().getHostAddress();	
			File file=new File(ip+".jpg");
			//如果服务端中该ip命名的文件已经存在，则加一个标志位，重复判断，直至有一个文件名不重复
			while(file.exists()){
				file=new File(ip+"("+flag+++")"+".jpg");
			}
			try{
				FileOutputStream fileOutputStream=new FileOutputStream(file);
				InputStream inputStream=sockets.getInputStream();
				OutputStream outputStream=sockets.getOutputStream();
				byte[]Data=new byte[1024];
				int leng=0;
				while((leng=inputStream.read(Data))!=-1)	//从输入流读取图片数据，写入到文件流中
				{
					fileOutputStream.write(Data,0,leng);
				}
				outputStream.write("上传成功".getBytes());
				fileOutputStream.close();
				sockets.close();
			}
			catch(Exception e){
				System.out.println(ip+"上传文件失败！原因是+"+e.getMessage());
			}
		}
		
	}
	public static void main(String[] args) throws Exception {
		sendBinaryClient();
	}
}
