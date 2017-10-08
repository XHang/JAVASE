package com.cxh.awt;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 记事本小程序，要求实现保存，另存为，打开文件功能,ctrl+s保存。
 */
public class Notepad {
	public static void main(String[] args){
		new NotepadGui();
	}
}
class NotepadGui{
	private Frame frame;
	private TextArea ta;//文本区域对象
	private MenuBar mb;	//菜单栏对象引用
	private Menu file;	//一级菜单文件
	private MenuItem open,close,save,save_as;//二级菜单保存，关闭・，保存，另存为
	private FileDialog fdl;//文件打开对话框
	private FileDialog fds;//文件保存对话框
	private File fILE;	//文件对象引用
	BufferedReader reader=null;
	BufferedWriter writer=null;
	NotepadGui(){
		init();
	}
	public void init(){
		frame=new Frame("记事本");
		frame.setBounds(200, 340, 450, 400);
		ta=new TextArea();
		frame.add(ta);
		mb=new MenuBar();
		file=new Menu("文件");
		open=new MenuItem("打开");
		close=new MenuItem("关闭");
		save=new MenuItem("保存");
		save_as=new MenuItem("另存为");
		fdl=new FileDialog(frame,"打开文件",FileDialog.LOAD);
		fds=new FileDialog(frame,"保存文件",FileDialog.SAVE);
		file.add(open);
		file.add(save);
		file.add(save_as);
		file.add(close);
		mb.add(file);
		frame.setMenuBar(mb);
		event();	//加载组件事件
		frame.setVisible(true);
	}
	public void event(){
		//窗体关闭事件
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//打开菜单事件
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fdl.setVisible(true);
				String path=fdl.getDirectory();
				String FileName=fdl.getFile();
				//防止用户在文件打开对话框时点取消导致返回的文件名为null
				if(path==null || FileName==null)	
					return ;
				fILE=new File(path,FileName);
				//清空之前文本区域显示的数据
				ta.setText("");						
				try{
					BufferedReader br=new BufferedReader(new FileReader(fILE));
					String line=null;
					while((line=br.readLine())!=null){
						//将打开的文件信息显示到文本框里面
						ta.append(line+"\r\n");		
					}
				}
				catch(IOException exception){
					throw new RuntimeException("打开文件失败",exception);
				}
				finally{
					try{
						if(reader!=null)
							reader.close();
					}
					catch(IOException error){
						throw new RuntimeException("关闭文件流失败",error);
					}
				}
			}
			
		});
		//保存菜单事件
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(fILE==null)	//判断用户有没有打开新文件然后操作，如果有，则需要显示文件对话框
					save(1);	//需要显示文件对话框
				else
					save(2);	//不需要显示文件对话框
			}
		});
		//菜单事件-另存为
		save_as.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				save(1);	//另存为不管有没有新建文件，都要打开文件对话框
			}
		});
		//关闭菜单事件
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//文本区域键盘事件
		ta.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				//如果ctrl和s按下，则保存文件
				if(e.isControlDown()&& e.getKeyCode()==KeyEvent.VK_S){
					if(fILE==null)
						save(1);
					else
						save(2);
				}
			}
		});
	}
	//flag为1说明需要弹出对话框进行保存，不为1则把文本区域所有的内容覆盖源文件内容
	public void save(int flag){
		if(flag==1){
			fds.setVisible(true);
			String path=fds.getDirectory();
			String FileName=fds.getFile();
			if(path==null || FileName==null)
				return ;
			fILE=new File(path,FileName);
		}
		try{
			writer=new BufferedWriter(new FileWriter(fILE));
			String text=ta.getText();		//获取文本区域中的内容
			writer.write(text);				//保存
		}
		catch(IOException ioException){
			throw new RuntimeException("保存文件失败",ioException);
		}
		finally{
			try{
				if(writer!=null)
					writer.close();
			}
			catch(IOException ioe){
				throw new RuntimeException("关闭文件失败",ioe);
			}
		}
	}
}

