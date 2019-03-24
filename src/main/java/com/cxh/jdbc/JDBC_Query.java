package com.cxh.jdbc;

import com.cxh.jdbc.Uitl.DBUitl;
import com.cxh.jdbc.enums.DataBaseType;

import java.sql.*;

public class JDBC_Query {

    public static void main(String [] args){

        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection= DBUitl.getConncetion("root", null, DataBaseType.MYSQL);
            statement=connection.prepareStatement("select * from cxh_char_test where id=?");
            statement.setInt(1,3);
            ResultSet resultSet= statement.executeQuery();

            while (resultSet.next()){
                String charString = resultSet.getString(1);
                String varCharString = resultSet.getString(2);
                System.out.println(String.format("得到的char是[%s]",charString));
                System.out.println(String.format("得到的varChar是[%s]",varCharString));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        //划重点，这是一般情况下的sql连接异常处理方式
        finally{
            DBUitl.closeResource(connection, null, statement, null);
        }
    }
}
