package com.cxh.stream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/*
 * 实现管道流的相连
 * 管道输入流一输入数据，就可以从管道输出流读取
 */
public class PipedSteamDemo{
	public static void main(String[] args) throws IOException{
		PipedOutputStream pos=new PipedOutputStream();//创建管道输出流对象
		PipedInputStream pis=new PipedInputStream(pos);////创建管道输入流对象，并和管道输出流相连接
		new Thread(new Read(pis)).start();//创建线程1，并开启
		new Thread(new Writer(pos)).start();//创建线程2，并开启
	}

}
//创建读类，把管道输出流写入的数据读出来
class Read implements Runnable	{
	//创建管道输入流的引用。方便输入流在线程中操作
	private PipedInputStream pis;
	Read(PipedInputStream pis){
		this.pis=pis;
	}
	@Override
	public void run() {
		byte[] b=new byte[1024];
		int len;
		try{
			len=pis.read(b);//将管道输入流的数据读取到数组中，该数据原本是管道输出流写入的数据
			System.out.println(new String(b,0,len));
		}
		catch(IOException e){
			throw new RuntimeException("读取管道输入流失败",e);
		}
		finally{
			try{
				if(pis!=null)
					pis.close();
			}
			catch(IOException e){
				throw new RuntimeException(e.getMessage());
			}
		}	
	}
	
}
//创建写类，把数据写入到管道输入流里面
class Writer implements Runnable  {
	//创建管道输入流引用
	private PipedOutputStream pos;
	Writer(PipedOutputStream pos){
		this.pos=pos;
	}
	public void run() {
		try{
			//将字节数据写入到管道输入流里面
			pos.write("进入管道咯".getBytes());
		} 
		catch (IOException e){
			throw new RuntimeException("写入管道输入流失败",e);
		}
		finally{
			try{
				if(pos!=null)
					pos.close();
			}
			catch(IOException e){
				throw new RuntimeException("关闭管道输入流失败",e);
			}
		}	
	}
	
}
