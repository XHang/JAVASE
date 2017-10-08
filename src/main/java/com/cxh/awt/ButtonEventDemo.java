package com.cxh.awt;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 演示按钮事件触发
 * @author Mr-hang
 *
 */
public class ButtonEventDemo{
	//定义该图形中所需的组件的引用。
	private Button but;
	private Frame f;
	ButtonEventDemo(){
		init();
	}
	private void init(){
		f=new Frame();
		//对frame进行基本设置。
		f.setBounds(300, 200, 400, 600);
		f.setLayout(new FlowLayout());
		but=new Button("这是一个按钮");
		//将组件添加到frame中
		f.add(but);
		//加载窗体事件
		 MyEvent();
		 //显示窗体
		f.setVisible(true);
	}

	private void MyEvent(){
		//设置窗口关闭按钮的动作
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//设置按钮点击事件
		but.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("退出，按钮干的");
				but.setLabel("hhhhh");
				but.setVisible(false);
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		new ButtonEventDemo();
	}
}

