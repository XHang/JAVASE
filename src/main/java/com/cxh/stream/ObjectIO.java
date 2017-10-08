package com.cxh.stream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.cxh.vo.Cat;
import com.cxh.vo.Person;

/**
 * 对象转IO流
 * @author Mr-hang
 *
 */
public class ObjectIO{
	public static void main(String[] args) throws Exception {
		//saveObj ();
		LoadObj ();
	}
	/**
	 * 保存对象到文件中
	 * @throws Exception
	 */
	public static void saveObj () throws Exception{
		//创建一个写对象的字节流，并与文件写入流相关联
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("1.Object"));
		oos.writeObject(new Cat("red",16));
		//写入对象，对象必须先实现Serializable
		oos.writeObject(new Person(18,"老王"));
		oos.close();
	}
	/**
	 * 从文件中读取对象，读取的时候是按照存的顺序读取的
	 * @throws Exception
	 */
	public static void LoadObj () throws Exception{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("1.Object"));//创建一个读取对象的字节流并与文件读取流相关联
		Cat cat=(Cat)ois.readObject();//读取第一个对象并强转
		Person person=(Person)ois.readObject();
		System.out.println(cat);//打印对象
		System.out.println(person);//调用对象的特有方法进行打印
		ois.close();
	}
}
