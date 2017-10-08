package com.cxh.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.junit.Test;

public class IOStreamExample {
	/**
	 * 拷贝文件
	 * @throws IOException 
	 */
	@Test
	public void baseCopyFile() throws IOException{
		//创建数据流读对象，与源文件关联起来
		InputStream input=this.getClass().getResourceAsStream("/src.txt");
		Reader reader = new InputStreamReader(input);
		//创建数据流写对象。该对象创建的文件既是复制后的文件
		FileWriter fw=new FileWriter("desc.txt");
		int ch=0;
		//判断是否读到结尾了
		System.out.println("now write file");
		while((ch=reader.read())!=-1){
			//将数据写入复制的文件里,虽然传入的int类型的数据，但写入的却是char类型。
			fw.write(ch);
		}
		//关闭资源
		System.out.println("write file  complete");
		fw.close();
		reader.close();
	}
	
	
	/**
	 * 拷贝文件增强版
	 */
	@Test
	public void advancedCopyFile(){
		Reader reader = null;
		FileWriter fw=null;
		try{
			System.out.println("advanced write file start");
			InputStream input=this.getClass().getResourceAsStream("/src.txt");
			reader = new InputStreamReader(input);
			//创建数据流写对象。该对象创建的文件既是复制后的文件
			fw=new FileWriter("desc.txt");
			//创建缓冲区，建议大小为1024，若数据少于1024个字符则一次循环就全部存入
			char []buf=new char[1024];	
			//定义取得的有效字符数
			int len=0;					
			while((len=reader.read(buf))!=-1){
				//把有效字符全都写入复制的为文件
				fw.write(buf,0,len);
			}
		}
		catch(IOException e){
			//如果有异常，则复制失败，且程序停止
			throw new RuntimeException("复制失败！",e);
		}
		finally{
			try{
				if(fw!=null){
					fw.close();
				}
				if(reader!=null){
					reader.close();
				}
				System.out.println("copy file complete");
			}
			catch(IOException e){
				throw new RuntimeException("关闭文件资源失败",e);
			}
		}
	}
	
	/**
	 * 通过缓冲区来高效的拷贝文件
	 */
	@Test
	public void copyFileByBuffer(){
		BufferedWriter writer=null;//创建字符写入流的缓存对象引用
		BufferedReader reader=null;//创建字符读取流的缓存对象引用
		try{
			writer=new BufferedWriter(new FileWriter("DESC.txt"));//创建字符写入流的对象并作为参数传进缓存对象的构造函数中
			reader=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/src.txt")));//创建字符读取流的对象并与文件关联，再把该对象作为参数传入缓冲对象的构造函数
			String line=null;
			System.out.println("copy file start");
			while((line=reader.readLine())!=null)	{
				writer.write(line);
				writer.newLine();				//写入分行符
				writer.flush();
			}
			System.out.println("copy file success");
		}
		catch(IOException e){
			throw new RuntimeException("copy file fail",e);
		}
		finally{
			try{
				if(writer!=null)
					writer.close();
				if(reader!=null)
					reader.close();
			}
			catch(IOException e){
				throw new RuntimeException("close Stream fail",e);
			}
		}
	}
	
	/**
	 * 续写
	 */
	@Test
	public void replenishWrite(){
		FileWriter writer=null;
		try{
			System.out.println("replenish write start!");
			URL filepath = IOStreamExample.class.getResource("/");
			writer=new FileWriter(filepath.getPath()+"/src.txt", true);//使用构造函数的重载形式，续写数据
			writer.write("\r\n续写");//\r\n转义字符换行处理
		}
		catch(IOException e){
			throw new RuntimeException("replenish write fail!",e);
		}
		finally{
				try{
					if(writer!=null){
					   writer.close();
					   System.out.println("replenish write success!");
					}
				}
				catch(IOException e){
					throw new RuntimeException("close Stream fail",e);
				}
			
			}
		}
	
	/**
	 * 拷贝Mp3
	 */
	@Test
	public void copyMp3File(){
		BufferedInputStream input=null;
		BufferedOutputStream output=null;
		try{
			System.out.println("copy mp3 file start");
			//创建字节读取缓冲流对象，并把字节读取流对象作为参数传进构造函数
			input=new BufferedInputStream(new FileInputStream("E:\\Music\\周杰伦-晴天.mp3"));
			output=new BufferedOutputStream(new FileOutputStream("D:\\Music.mp3"));
			int singleByte;
			//反正是在缓冲区内操作，那就一个一个读字节吧，再一个一个存吧
			while((singleByte=input.read())!=-1){
				output.write(singleByte);			//写入字节数组的有效数据
			}
		}
		catch(IOException e){
			throw new RuntimeException("copy MP3 fail",e);
		}
		finally{
			try{
				if(input!=null)
					input.close();//关闭资源
				if(output!=null)
					output.close();//关闭资源
				System.out.println("copy mp3 success");
			}
			catch(IOException e){
				throw new RuntimeException("close fileStream failure!",e);
			}
		}
	}
	
	/**
	 * 特别的文件复制能力，就是用标准输入流和标准输出流来复制文件
	 * @throws IOException 
	 */
	@Test
	public void speciallyCopyFile() throws IOException{
		System.out.println("这是一个特别的文件复制程序，使用标准输入输出流来复制文件");
		System.setIn(this.getClass().getResourceAsStream("/src.txt"));
		System.setOut(new PrintStream("desc.txt"));
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writerW=new BufferedWriter(new OutputStreamWriter(System.out));
		String line=null;
		while((line=reader.readLine())!=null)				//其实死循环也可，因为键盘读入是没有结束的
		{
			writerW.write(line);
			writerW.newLine();
			writerW.flush();
			//如果读到最后一行是over则停止复制
			if(line.equals("over"))
				break;				
		}
		reader.close();
	}

	/**
	 * 写入系统环境变量到文件中
	 * @throws FileNotFoundException 
	 */
	@Test
	public void writeSystemPropertiesToFile() throws FileNotFoundException{
		Properties properties=System.getProperties();
		PrintStream print=new PrintStream("SystemInfo.txt");
		//把properties里面的键值对数据写到print流中
		properties.list(print);
		print.close();
	}
	/**
	 * 打印系统属性
	 */
	private void printSystemProperties(){
		System.out.println("即将打印系统属性");
		Properties properties = System.getProperties();
		for(Entry<Object, Object> entry:properties.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		properties.clone();
	}
	
	/**
	 * 保存键值对信息到系统环境变量中-临时-虚拟机完蛋变量也随之完蛋
	 */
	@Test
	public void savePropertiesTOSystem(){
		Properties properties = System.getProperties();
		properties.setProperty("系统版本", "windows11");
		printSystemProperties();
		properties.clone();
	}

	
		
}
	

