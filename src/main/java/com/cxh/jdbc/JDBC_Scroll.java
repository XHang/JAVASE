package com.cxh.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cxh.jdbc.Uitl.DBUitl;
import java.sql.DriverManager;

/**
 * 演示可以滚动的结果集
 * @author Mr-hang
 *
 */
public class JDBC_Scroll {

	public static void main(String[] args) {
		Connection connection=null;
		Statement statement=null;
		ResultSet rs =null;
		try{
			connection = DBUitl.getConncetion("scott", "tiger");
			//获取陈述句对象，第一个参数是设置结果集可滚动，第二个设置结果集只读
			statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			 rs=statement.executeQuery("select * from emp2 order by sal ");
			rs.next();
			System.out.println(rs.getInt(1));
			//跳转到最后一条记录
			rs.last();
			//判断是不是最后一行记录
			System.out.println(rs.isLast());
			//获取行号，也就是这个结果集有多少条
			System.out.println(rs.getRow());
			rs.next();
			//判断是不是最后一行的下一行记录啊
			System.out.println(rs.isAfterLast());
			//滚动至上一行，没错，就是最后一行
			rs.previous();
			//获取行号，也就是这个结果集有多少条
			System.out.println(rs.getRow());
			//滚动到第六行记录
			rs.absolute(6);
			System.out.print(rs.getRow());
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBUitl.closeResource(connection, null, statement, rs);
		}
		

	}

}
