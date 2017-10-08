package com.cxh.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.cxh.jdbc.Uitl.DBUitl;
public class JDBC_DML {
	
	/**
	 * 该程序演示DML语句的使用
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection=null;
		Statement statement=null;
		try{
		connection=DBUitl.getConncetion("scott", "tiger");
		statement=connection.createStatement();
		//dept2是oracle里面的一个表，第一个字段时部门号，第二个是部门名，第三个是部门位置
		String sql="insert into dept2 values(98,'game','bj')";
		statement.executeUpdate(sql);
		System.out.println("恭喜，成功插入数据");
		}catch(SQLException e){
			e.printStackTrace();
		}
		//划重点，这是一般情况下的sql连接异常处理方式
		finally{
			DBUitl.closeResource(connection, null, statement, null);
		}
	}

}
