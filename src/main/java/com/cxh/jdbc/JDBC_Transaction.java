package com.cxh.jdbc;
/*
 * 用JDBC实现事务处理
 * 别以为抛异常就自动回滚了。。
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.cxh.jdbc.Uitl.DBUitl;
import java.sql.DriverManager;
public class JDBC_Transaction {

	public static void main(String[] args) {
		Connection connection=null;
		Statement statement=null;
		try{
			connection = DBUitl.getConncetion("scott", "tiger");
			System.out.println("当前连接的事务处理是关的？"+connection.getAutoCommit());
			//将自动提交属性设置为false
			connection.setAutoCommit(false);
			PreparedStatement pstmt =connection.prepareStatement("insert into dept2 values(?,?,?)");
			pstmt.setInt(1, 88);
			pstmt.setString(2, "cctv");
			pstmt.setString(3, "nanjing");
			pstmt.addBatch();//将一条sql语句添加到批处理队列中
			pstmt.setInt(1, 89);
			pstmt.setString(2, "cctv");
			pstmt.setString(3, "nanjing");
			pstmt.addBatch();
			pstmt.executeBatch();//执行批处理
			connection.commit();//提交更改
			connection.setAutoCommit(true);//恢复属性
		}
		catch(SQLException e){
			e.printStackTrace();
			try{
				if(connection!=null){
					connection.rollback();//发生异常要先回滚，保证数据安全
					connection.setAutoCommit(true);//恢复原始属性
				}
			}
			catch(SQLException s){
				s.printStackTrace();
			}
			
		}
		finally{
			DBUitl.closeResource(connection, null, statement, null);
		}
	}
}
