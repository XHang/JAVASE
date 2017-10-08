package com.cxh.jdbc;
/*
 * 此程序实现可更新的结果集，然并卵。实际oralce不支持
 */
/*
 * 实现可以滚动的结果集
 */
/*
 * 用JDBC实现事务处理
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cxh.jdbc.Uitl.DBUitl;
import java.sql.DriverManager;
public class JDBC_UpdataeRs {

	public static void main(String[] args) {
		Connection connection=null;
		Statement statementmt=null;
		ResultSet resultSets =null;
		try{
			connection = DBUitl.getConncetion("scott", "tiger");
			//获取陈述句对象时第二个参数要选择CONCUR_UPDATABLE，即可更新的
			statementmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSets=statementmt.executeQuery("select * from emp2 order by sal ");
			resultSets.next();
			//把当前游标所指的ename字段的值改为aaaaa
			resultSets.updateString("ename","aaaaaa" );
			//把变动更新到数据库中
			resultSets.rowUpdated();
			//把游标移动至新一行的空间
			resultSets.moveToInsertRow();
			//插入一条新纪录，第一个字段填充至999
			resultSets.updateInt(1, 999);
			//插入该行
			resultSets.insertRow();
			//让游标指向新的一行
			resultSets.moveToCurrentRow();
			//调到第六行
			resultSets.absolute(6);
			//删除该行
			resultSets.deleteRow();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBUitl.closeResource(connection, null, statementmt, resultSets);
		}
		

	}

}
