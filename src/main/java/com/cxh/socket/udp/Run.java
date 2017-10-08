package com.cxh.socket.udp;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/*
 * 程序说明：这是一个聊天软件，使用UDP通信，兼顾发送和接受，可显示来源地ip，目前仅支持文字发送的说
 * 界面的下方是发送口，上方是接收口
 * 可手动指定ip地址
 * TODO 在本机测试的时候中文显示不出来
 * 经测试，改掉运行配置里面的公有配置的编码为gbk就行了，但是这是治标不治本
 */
 class ChatGui
{
	private Frame frame;
	private TextArea sendta,textArea;
	private Button sendbut,okbut;
	private DatagramSocket datagramSocket;
	private TextField textField;
	private String ip;
	ChatGui() throws Exception{
		//未处理gui前就建立发送和接受的Socket服务，绑定端口为10000
		datagramSocket=new DatagramSocket(10000);
		init();
	}
	void init(){
		frame=new Frame("聊天");
		frame.setBounds(200, 300, 300, 400);
		frame.setLayout(new FlowLayout());
		textArea=new TextArea(10,35);
		sendta=new TextArea(5,35);
		sendbut=new Button("发送");
		okbut=new Button("确定");
		textField=new TextField("请输入您要通信的ip地址",31);
		frame.add(textArea);
		frame.add(sendta);
		frame.add(sendbut);
		frame.add(textField);
		frame.add(okbut);
		Event();//加载组件事件
		Rec();//创建接受数据线程
		frame.setVisible(true);
	}
	void Event(){
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//按钮事件，处理发送线程
		sendbut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				byte []buf=new byte[1024*64];
				buf=sendta.getText().getBytes();
				try{
					DatagramPacket dp=
						new DatagramPacket(buf,buf.length,InetAddress.getByName(ip),10000);
					datagramSocket.send(dp);
					sendta.setText("");
				} 
				catch (Exception e1){
					final Dialog  d=new Dialog(frame,"出错咯",true);//创建一个出错对话框
					d.add(new Label("发送失败，请检查你的ip地址是否有误"));//添加文字标签
					d.setBounds(400, 300, 211,61);
					d.addWindowListener(new WindowAdapter(){
						public void windowClosing(WindowEvent e){//关闭出错提示框动作
							d.setVisible(false);
						}
					});
					d.setVisible(true);
				}
			}
		});
		okbut.addActionListener(new ActionListener(){//确认对话的ip地址
			public void actionPerformed(ActionEvent e){
				ip=textField.getText();//从文本框读取到ip地址，传给数据包发送
				textField.setText("ip地址输入成功！");
			}
		});
	}
	void Rec(){//接受端的方法设在另一线程中
		new Thread(new Runnable(){
			public void run(){
				byte[] buf=new byte[1024*64];
				DatagramPacket dp=new DatagramPacket(buf,buf.length);
				try {
					while(true){
						datagramSocket.receive(dp);
						String ip=dp.getAddress().getHostAddress();
						String data=new String(dp.getData(),0,dp.getLength());
						textArea.append("ip地址："+ip+"说:"+data+"\r\n");
					}
				} 
				catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
			
		}).start();
	}
}
 public class Run{
	public static void main(String[] args) throws Exception{
		new ChatGui();
	}
}

