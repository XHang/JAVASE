package com.cxh.awt;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
/**
 * 小程序：查看文件树
 * @author Mr-hang
 *
 */
public class ShowFileList {
	public static void main(String[] args){
		new ShowFileListGui();
	}
}
class ShowFileListGui{
	private Frame  frame=null;
	private Button but=null;
	private TextField tf=null;//文本框对象引用
	private TextArea ta=null;//文本区域对象引用
	ShowFileListGui(){
		init();
	}
	public void init(){
		frame=new Frame("这是一首简单的小情歌");
		frame.setBounds(300, 200, 800, 500);//长800，高500
		frame.setLayout(new FlowLayout());
		tf=new TextField(20);//文本框设置列数为300
		frame.add(tf);//添加文本框,考虑是流式布局，所以要首先添加
		but=new Button("转到");
		frame.add(but);
		ta=new TextArea(100,100);
		frame.add(ta);
		MyEvent();
		frame.setVisible(true);
	}
	//添加事件方法
	public void MyEvent(){
		//添加窗口监听
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//添加文本框按键监听
		tf.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					showDir();
			}
		});
		//添加按钮监听
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				showDir();
			}
		});
	}
	public void showDir()			//按钮监听和文本框按键监听所处理的事务
	{
		ta.setText("");//按钮每激活一次，文本区域清空
		String path=tf.getText();//获取文本框里面的路径
		tf.setText("");//按钮每激活一次，文本框清空
		File file=new File(path);
		//判断这货是不是目录且是不是存在。
		if(file.exists()&& file.isDirectory()){
			String files[]=file.list();//取出文件目录里面的文件列表
			//遍历文件列表
			for(String str:files){
				ta.append(str+"\r\n");//将每一个文件名显示在文本区域中，并换行
			}
		}
		//如果错误输入则弹出错误提示
		else{
			final Dialog dialog=new Dialog(frame,"错误提示",true);
			dialog.setLayout(new FlowLayout());
			Button okBut=new Button("确定"); 
			Label info=new Label();
			info.setText("您输入的路径"+path+"有误，请重新输入");
			dialog.setBounds(200, 300,390,100);		//对话框位置和大小
			dialog.add(info);
			dialog.add(okBut);
			okBut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dialog.setVisible(false);
				}
			});
			dialog.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					dialog.setVisible(false);
				}
			});
			dialog.setVisible(true);
		}
	}
}
