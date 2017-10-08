package com.cxh.awt;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 演示文件对话框
 * @author Mr-hang
 *
 */
public class OpenFileDemo {
	public static void main(String[] args) {
		new OpenFileGUi();
	}
}
class OpenFileGUi{
	private Frame frame;
	private TextArea textArea;
	private MenuBar mb;
	private Menu file;
	private MenuItem openFile,exit;
	private FileDialog fdl;
	OpenFileGUi(){
		init();
	}
	public void init(){
		frame=new Frame("打开文件窗口");		
		frame.setBounds(200, 300, 400, 450);
		textArea=new TextArea();				//文本区域
		frame.add(textArea);
		file=new Menu("文件");			//菜单
		openFile=new MenuItem("打开");
		exit=new MenuItem("关闭");
		file.add(openFile);
		file.add(exit);
		mb=new MenuBar();
		mb.add(file);
		frame.setMenuBar(mb);
		fdl=new FileDialog(frame,"打开文件",FileDialog.LOAD);//文件对话框
		MyEvent();
		frame.setVisible(true);
	}
	public void MyEvent(){
		//窗体事件
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//退出菜单事件
		exit.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		//打开菜单事件
		openFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fdl.setVisible(true);				//文件对话框显示
				String path=fdl.getDirectory();		//获取用户点击的文件目录
				String fileName=fdl.getFile();		//获取用户点击的文件名
				System.out.println(path+"....."+fileName);
				if(path==null || fileName==null)	//如果用户没点击文件，则退出该函数
					return;
				File f=new File(path,fileName);		//将用户点击的文件封装成文件对象
				textArea.setText("");				//清空前一次文件显示的文本区域
				BufferedReader br=null;
				try{
					br=new BufferedReader(new FileReader(f));
					String line=null;
					while((line=br.readLine())!=null){
							textArea.append(line+"\r\n");
					}
				}
				catch(IOException exception){
					throw new RuntimeException("打开文件失败",exception);
				}
				finally{
					try {	
						if(br!=null)
							br.close();
					} 
					catch (IOException ioException){
						throw new RuntimeException("关闭文件失败",ioException);
					}
				}
			}
		});
	}
}
