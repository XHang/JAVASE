package com.cxh.awt;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 该程序主要演示菜单的使用
 * @author Mr-hang
 *
 */

/**
 * 测试程序
 * @author Mr-hang
 *
 */
public class MenuDemo {
	public static void main(String[] args) {
		new MenuGui();
	}
}
/**
 * 主要的GUI类
 * @author Mr-hang
 *
 */
class MenuGui{
	private Frame frame;
	private MenuBar menubar;	//菜单栏
	private Menu m1,m1_1,m2,m3;	//菜单栏下的m1,m2,m3菜单及m1的子菜单m1_1
	private MenuItem m1_1_1;	//m1的子菜单m1_1的末尾菜单m1_1_1
	MenuGui(){
		init();
	}
	public void init(){
		frame=new Frame("演示菜单");
		frame.setBounds(300, 300, 400, 500);
		m1=new Menu("顶层菜单1");
		m2=new Menu("顶层菜单2");
		m3=new Menu("顶层菜单3");
		m1_1=new Menu("下拉菜单");
		m1_1_1=new MenuItem("末尾退出");
		m1_1.add(m1_1_1);//添加时要从底层菜单开始添加
		m1.add(m1_1);
		menubar=new MenuBar();
		menubar.add(m1);
		menubar.add(m2);
		menubar.add(m3);
		frame.setMenuBar(menubar);//为窗口添加菜单栏组件
		frame.setVisible(true);
		MyEvent();
	}
	public void MyEvent(){
		//加载窗口事件
		frame.addWindowListener( new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		//加载底层菜单退出命令
		m1_1_1.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
