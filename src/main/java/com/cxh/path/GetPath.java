package com.cxh.path;

import java.net.URL;
import org.junit.Test;

/**
 * 获取各种各样的路径
 * @author Mr-hang
 *
 */
public class GetPath {
	
	
	/**
	 * 演示如何取得当前运行类的路径
	 * getResource方法参数带斜线的话，取得的是这个类的根路径（不包含包）
	 * getResource方法参数不带斜线的话，取得的是这个类的根路径（包含包）
	 * 另注：参数不带斜线的话，，可以用../../向上一层一层取上层文件夹
	 *		 参数带斜线的话,可以在后面补文件夹名字以进入下一层文件夹
	 *		但是无论怎么样，都不能取到根路径更上层的文件夹。。
	 *		有时候会取到test-class文件夹，不知道是什么bug
	 */
	@Test
	public void getRootPath(){
		URL path = this.getClass().getResource("../../../");
		URL slashPath1 = this.getClass().getResource("/");
		System.out.println("不带斜线的path"+path.getPath());
		System.out.println("带斜线的path"+slashPath1.getPath());
	}
}
