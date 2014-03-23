package org.masque.qq.demo.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	private DBConn() {
	}

	public static Connection getConn() {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/webqq_major?useUnicode=true&characterEncoding=UTF-8";
		String username = "root";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username,
					"");
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}

		return con;
	}
}
