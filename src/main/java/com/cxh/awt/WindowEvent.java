package com.cxh.awt;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;

/**
 * 窗体事件-前置，后置事件
 * @author Mr-hang
 *
 */
public class  WindowEvent{
	public static void main(String[] args) {
		Frame frame = new Frame("my awt");
		frame.setSize(500,400);
		frame.setLocation(300,200);
		frame.setLayout(new FlowLayout());

		Button button = new Button("我是一个按钮");
		
		frame.add(button);
		
		//添加窗体事件
		frame.addWindowListener(new WindowAdapter(){
			//当窗口的关闭按钮被点击时
			public void windowClosing(WindowEvent e){
				System.out.println("窗体被关闭了");
				System.exit(0);
			}
			//当窗体前置时
			public void windowActivated(WindowEvent e) {
				System.out.println("窗体前置了");
			}
			
			//当窗体打开时
			public void windowOpened(WindowEvent e) {
				System.out.println("窗体被打开了");
			}
		});
		
		//设置窗体可见性
		frame.setVisible(true);
	}
}