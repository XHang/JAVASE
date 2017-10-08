package com.cxh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
 * 该程序可实现用JDBC展示树状结构
 */
public class JDBC_ByMysql_tree {
	static Connection conn=null;
	public static void main(String[] args) {
		try{
		//第一步，注册数据库大管家
		 new com.mysql.jdbc.Driver();
		 conn =DriverManager.getConnection("jdbc:mysql://localhost/bbs?user=root&password=tiger");//拿到连接对象
		 tree(0,1);
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(conn!=null){
					conn.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
		}

	}
	public static void tree(int pid,int level) throws SQLException{	//用于展示树状结构的递归函数
		PreparedStatement  pstmt=conn.prepareStatement("select * from article where pid=?");//一个陈述语句只能对应一个结果集
		 String KG=" ";
		for(int i=1;i<level;i++){
			KG=KG+"----";		//根据等级设定前缀
		}
		pstmt.setInt(1, pid);	//设置查询条件为pid=pid参数
		ResultSet rs=pstmt.executeQuery();//执行查询
		while(rs.next()){
			System.out.println(KG+rs.getString(5));//打印内容
			if(rs.getInt(7)==1){	//如果该条记录是非叶子节点
				tree(rs.getInt(1),level+1);
			}
		}
		rs.close();
		pstmt.close();
		
	}

}
