package com.cxh.awt;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
 * 键盘事件
 */
public class KeyEventDemo{
	public static void main(String[] args){
		new KeyboardEvent();
	}
}
class KeyboardEvent{
	private Frame frame=null;	
	private Button but=null;
	private TextField tf=null; 
	public KeyboardEvent(){
		init();
	}
	//GUI初始化
	public void init()			{
		frame=new Frame("我的窗口");
		frame.setBounds(400, 300, 500, 300);
		frame.setLayout(new FlowLayout());
		but=new Button("我是按钮");
		frame.add(but);
		//创建文本框对象
		tf=new TextField(13);	
		frame.add(tf);	
		myEvent();
		frame.setVisible(true);
	}
	public void myEvent(){
		//窗口事件
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//按钮键盘事件
		but.addKeyListener(new KeyAdapter()	{
			public void keyPressed(KeyEvent e) {
				System.out.print("该键的字符是"+e.getKeyChar());
				System.out.println("该键所对应的字符是"+e.getKeyCode());
				System.out.println("该多字符的键是"+KeyEvent.getKeyText(e.getKeyCode()));
				if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_ENTER)
					System.out.println("ent+ctrl....run");
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
		});
		//文本框事件
		tf.addKeyListener(new KeyAdapter(){
			//当文本框有按钮按下时调用
			public void keyPressed(KeyEvent e){
				int num=e.getKeyCode();
				if(!(num>=KeyEvent.VK_0 && num<=KeyEvent.VK_9)){	
					System.out.println("输入非法");
					//输入非法时不处理该事件,不会以默认的方式处理
					e.consume();				
				}
			}
		});
	}
	
}