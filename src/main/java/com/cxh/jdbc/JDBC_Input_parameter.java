package com.cxh.jdbc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.cxh.jdbc.Uitl.DBUitl;

/*
 * 该程序实现控制台录入字段数据，然后添加数据库记录
 * 通过预处理陈述语句来完成
 */
public class JDBC_Input_parameter {

	public static void main(String[] args) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		try{
			int Deptno;
			String Dname,Loc;
			connection = DBUitl.getConncetion("scott", "tiger");
			String sql="insert into dept2 values (?,?,?)";
			pstmt=connection.prepareStatement(sql);					//获取陈述语句对象
			BufferedReader br=new BufferedReader( new InputStreamReader(System.in));
			System.out.println("请输入三个字段：部门编号，部门名称，地址");
			String line=null;
			while((line=br.readLine())!=null){
				if(line.equals("exit")){
					break;
				}
				String []arr =line.split(",");
				Deptno=Integer.parseInt(arr[0]);//设定第一个参数的值
				Dname=arr[1];
				Loc=arr[2];
				pstmt.setInt(1,Deptno);
				pstmt.setString(2, Dname);
				pstmt.setString(3, Loc);
				pstmt.executeUpdate();
				System.out.println("执行成功，请继续输入部门编号，部门名称，地址，或者exit退出");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			DBUitl.closeResource(connection, pstmt, null, null);
		}
		

	}

}
