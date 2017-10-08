package com.cxh.socket.udp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.junit.Test;

/*
 *UDP基本使用方法，发送端发送数据给另一端的UDPSocket接收
 */
public class UDPBaseUsage
{
	/**
	 * UDP发送数据端，使用键盘录入，如果录入了再见，则结束该程序
	 * @throws Exception
	 */
	@Test
	public void send() throws Exception{
		//创建Socket服务
		DatagramSocket datagramSocket=new DatagramSocket();
		//准备数据来源
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		byte buf[]=new byte[1024*64];	//准备数据的缓冲区
		System.out.println("你想对她说什么");
		while((line=reader.readLine())!=null){
			//如果键盘录入的数据是拜拜，则结束对话
			if(line.equals("再见"))	{	
				break;
			}
			//将键盘录入的该行数据转换成字节数据
			buf=line.getBytes();		
			//封成数据包
			DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getLocalHost(),10000);
			datagramSocket.send(dp);
			System.out.println("你的念想一定传到她心里的");
		}
		datagramSocket.close();//发送端不发送可以关闭资源
	}
	/**
	 * 接收另一端UDP主机发送过来的数据，使用死循环，一直能接收
	 * @throws Exception
	 */
	@Test
	public void receive() throws Exception{
		//创建UDP的Socket服务，并指定监听端口
		DatagramSocket datagramSocket=new DatagramSocket(10000);
		while(true){
			//创建接受数据的空数据包
			byte []buf=new byte[1024];
			DatagramPacket datagramPacket=new DatagramPacket(buf,1024);
			//接受数据包啦
			datagramSocket.receive(datagramPacket);
			InetAddress ip=datagramPacket.getAddress();//获取数据包的发送端ip地址对象
			System.out.println("源ip地址是"+ip.getHostAddress());
			System.out.println("发送端的端口号"+datagramPacket.getPort());//获取数据包的源端口号
			String info=new String (buf,0,datagramPacket.getLength());
			System.out.print("数据包携带的信息是"+info);
			//关闭资源
			//ds.close();
		}
	}

}
