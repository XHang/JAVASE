package com.cxh.jdbc;
/*
 * 该程序可以执行Oracler数据库的PLSQL程序
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import com.cxh.jdbc.Uitl.DBUitl;

/*
 * 该程序实现控制台录入字段数据，然后添加数据库记录
 * 目前来说，只能调用oracle的过程函数。
 * 手动创建好像不行，没试过
 */
public class JDBC_execPLSQL {

	public static void main(String[] args) {
		Connection connection=null;
		CallableStatement callstmt=null;
		try{
			connection = DBUitl.getConncetion("scott", "tiger");
			//获取回调语句.由于参数不确定，暂时用问号代替
			callstmt=connection.prepareCall("{call p(?,?,?,?)}");
			//指定参数中那些是输出参数，以及是什么类型
			callstmt.registerOutParameter(3, Types.INTEGER);
			callstmt.registerOutParameter(4, Types.INTEGER);
			//设置输入参数
			callstmt.setInt(1, 4);
			callstmt.setInt(2, 5);
			callstmt.setInt(4, 7);
			callstmt.execute();
			//取出输出参数
			System.out.println(callstmt.getInt(3));
			System.out.println(callstmt.getInt(4));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(callstmt!=null){
					callstmt.close();
				}
				if(connection!=null){
					connection.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}

