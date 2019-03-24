package com.cxh.jdbc.Uitl;

import com.cxh.jdbc.enums.DataBaseType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 为拯救我那垃圾的代码，特此封装一些代码
 * @author Mr-hang
 *
 */
public class DBUitl {
	
	/**
	 * 获取sql连接对象
	 * @param userName 用户名
	 * @param password 密码
	 * @return 连接对象
	 * @throws SQLException  如果建立连接发生异常
	 */
	public static Connection getConncetion(String userName,String password) throws SQLException{
		//new此对象则自动向数据库连接大管家注册
		//new oracle.jdbc.driver.OracleDriver();
		//1521是oracle的监听器端口，orcl是实例名
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", userName,password);//如果这句程
	}
	public static Connection getConncetion(String userName, String password, DataBaseType type ){
		try {
			switch (type){
				case MYSQL:
					Class.forName(DataBaseType.MYSQL.getDriveClass()).newInstance();
					return DriverManager.getConnection(DataBaseType.MYSQL.getUrl(), userName,password);
				case ORACLE:
					Class.forName(DataBaseType.ORACLE.getDriveClass()).newInstance();
					return DriverManager.getConnection(DataBaseType.ORACLE.getUrl(), userName,password);
					default:
						throw new IllegalArgumentException("Not Support this DataBaseType!");
			}
		} catch (Exception e) {
			throw new RuntimeException("connection database Fail",e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param connection 连接对象
	 * @param preparedStatement 准备语句
	 * @param statement 陈述语句
	 * @param resultSet 结果集
	 */
	public static void  closeResource(Connection connection,PreparedStatement preparedStatement,Statement statement,ResultSet resultSet){
		//其实最后置为null没什么作用，只不过看起来比较严谨一点罢了
		try{
			if(resultSet!=null){
				resultSet.close();
				resultSet = null;
			}
			if(preparedStatement !=null){
				preparedStatement.close();
				preparedStatement = null;
			}
			if(statement!=null){
				statement.close();
				statement=null;
			}
			if(connection!=null){
				connection.close();
				connection=null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
