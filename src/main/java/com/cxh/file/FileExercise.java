package com.cxh.file;

import java.io.File;
import org.junit.Test;

/**
 * file对象的练习和使用
 * @author Mr-hang
 *
 */
public class FileExercise {
	
	/**
	 * 递归打印文件列表
	 */
	@Test
	public void recursivelyFileList(){
		recursivelyFileList("D:\\BaiduYunDownload",0);
	}
	
	/**
	 * 递归打印文件列表的函数
	 * @param dir 文件夹对象
	 * @param Level 等级
	 */
	private void recursivelyFileList(File dir,int Level){
		//打印一下递归调用时传入的目录
		System.out.println(getLevel(Level)+dir.getName());
		//目录级别加1，因为是打印目录下的东西了
		Level++;
		//把目录下的文件列表全部存成File类型的数组
		File FileList[]=dir.listFiles();
		//遍历文件列表
		for(File file:FileList){
			//如果遍历到文件夹，则再次调用本函数来递归文件夹的内容，否则打印文件名
			if(file.isDirectory()){
				recursivelyFileList(file,Level);
			}
			else{
				System.out.println(getLevel(Level)+file.getName());//显示文件
			}
		}
	}
	/**
	 * 递归函数重载，提供更方便的接口供调用
	 * @param dir
	 * @param Level
	 */
	private void recursivelyFileList(String dir,int Level){
		File fileDir = new File(dir);
		recursivelyFileList(fileDir,Level);
	}
	/**
	 * 根据传进来的等级打印等级前缀
	 * @param Level
	 * @return
	 */
	private  static String getLevel(int Level){
		StringBuilder sb=new StringBuilder();
		//每个目录或文件都要有这个标记"|--"
		sb.append("|--");	
		for(int i=0;i<Level;i++){
			sb.insert(i,"  ");
		}
		return sb.toString();
	}
}
