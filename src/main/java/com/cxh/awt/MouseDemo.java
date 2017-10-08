package com.cxh.awt;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 
 * 程序介绍：演示GUI触发事件中的鼠标事件
 * 测试程序
 */
public class MouseDemo{
	public static void main(String[] args) {
		new MouseGUI();
	}
}
/**
 * GUI主要程序
 * @author Mr-hang
 *
 */
class MouseGUI{
	Frame f=null;//定义窗口外部引用
	Button but=null;//定义按钮外部引用
	MouseGUI(){
		Init();
	}
	//用户窗口初始化
	public void Init(){
		f=new Frame("我的窗口");
		f.setSize(243,444);		//窗口大小
		f.setLocation(100, 200);//窗口位置
		f.setLayout(new FlowLayout());//流式布局
		but=new Button("点我");		//创建按钮对象
		f.add(but);					//添加按钮组件
		MyEvent();					//调用按钮和窗口的事件
		f.setVisible(true);			//窗口可视化
	}
	public void MyEvent(){
		//将窗口监听器注册到窗口中，并复写监听器里面的事件
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//将按钮监听器注册到按钮中，并实现里面的事件方法
		but.addActionListener(new ActionListener(){
			//当按钮活动时调用
			public void actionPerformed(ActionEvent e){
				System.out.println("按钮活动了诶");
			}
		});
		//将鼠标监听器注册到按钮中，并复写里面的方法
		but.addMouseListener(new MouseAdapter(){
			//当鼠标点击时调用
			public void mouseClicked(MouseEvent e){
				System.out.println("鼠标点了我一下，好疼~");
				if(e.getClickCount()==2)
					System.out.println("鼠标双击了我一下，疼死了~");
				if(e.getClickCount()==10){
					System.out.println("鼠标双击了我10下，不活了~~");
					but.setVisible(false);
				}
			}
			//当鼠标移入按钮时调用
			public void mouseEntered(MouseEvent e) {
				System.out.println("鼠标过来了，好可怕");
			}
			//当鼠标移出按钮时调用。
			public void mouseExited(MouseEvent e) {
				System.out.println("鼠标终于走了");
			}
		});
	}
}
