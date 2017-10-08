package com.cxh.jdk7;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 该代码是演示Switch对字符串的支持
 */
public class JDK7 {
	public static void main(String[] args) {
		try {
			try_With_resource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//switch的演示
	public static void SwitchDemo(){
		String sex="男";
		switch(sex){
		case "男":
				System.out.println("先生，你好");
				break;
		case "女":
				System.out.println("女孩，你好");
				break;
		default:
			System.out.println("扶她，你好");
		}
		List<String> list=new ArrayList<String>();
	}
	//演示多异常合并，该方法一般会报出角标越界异常，以及空指针异常
	public static void getElenemt(){
		try{
			int arr []=new int [3];
			int index=5;
				System.out.println(arr[index]);
			}catch(NullPointerException | ArrayIndexOutOfBoundsException e){
				System.out.println(e.toString());
			}	
	}
	//try内资源自动释放，注意 需要释放资源如流等要实现Closeable，即自动关闭接口
	public static void try_With_resource() throws IOException{
		try(FileReader fr=new FileReader("demo.txt");FileWriter fw=new FileWriter("demo2.txt")){
			int ch=fr.read();
			fw.write(ch);
			System.out.println(ch);
		}
	}
}
