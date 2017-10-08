package com.cxh.jdbc;
/**
 * 用JDBC来完成多个SQl语句的同时执行，即批处理
 * 有两种方法
 * 第一种：用陈述语句
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.cxh.jdbc.Uitl.DBUitl;
/*
 * 该程序实现控制台录入字段数据，然后添加数据库记录
 */
public class JDBC_Batch {

	public static void main(String[] args) {
		Connection connection=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		try{
			connection= DBUitl.getConncetion("scott", "tiger");
			//获取陈述语句对象
//			stmt=conn.createStatement();					
//			stmt.addBatch("insert into dept2 values(52,'cctv','nanao')");
//			stmt.addBatch("insert into dept2 values(53,'cctv','nanao')");
//			stmt.executeBatch();
			pstmt =connection.prepareStatement("insert into dept2 values(?,?,?)");
			pstmt.setInt(1, 88);
			pstmt.setString(2, "批处理");
			pstmt.setString(3, "新日暮里");
			pstmt.addBatch();
			pstmt.setInt(1, 89);
			pstmt.setString(2, "批处理2");
			pstmt.setString(3, "基佬国");
			pstmt.addBatch();
			pstmt.executeBatch();
			System.out.println("批处理执行完毕，大吉大利，今晚吃鸡");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBUitl.closeResource(connection, pstmt, stmt, null);
		}
	}
}
