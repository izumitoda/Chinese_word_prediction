package program;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sqlconnect {

//这里是SqlConnection 类

    /*
     *java连接mysql数据库
     *1、加载驱动程序
     *2、数据库连接字符串"jdbc:mysql://localhost:3306/数据库名?"
     *3、数据库登录名
     *3、数据库登录密码
     */

    private static final String URL = "jdbc:mysql://localhost:3306/ngram";//数据库连接字符串，这里的deom为数据库名
    private static final String NAME = "root";//登录名
    private static final String PASSWORD = "960930";//密码
    private static Connection conn;
    public Connection TheSqlConnection() {
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
            //添加一个println，如果加载驱动异常，检查是否添加驱动，或者添加驱动字符串是否错误
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("获取数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
            //添加一个println，如果连接失败，检查连接字符串或者登录名以及密码是否错误
            e.printStackTrace();
        }
        //数据库打开后就要关闭

        return conn;
    }

    public static String SearchNgram(String sqlquery) throws SQLException
    {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sqlquery);
        if(rs.next())
         return rs.getString(1);
        else
            return "";

    }

}
