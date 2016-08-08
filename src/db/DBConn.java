package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

	// private String url; // 存储SQLSever连接路径
	//
	// private String serverName; // 存储机器的名称
	//
	// private String portNumber; // 存储端口名称
	//
	// private String databaseName; // 存储数据库名称
	//
	// private String userName; // 存储用户名称
	//
	// private String password; // 存储密码

	/* 设置连接数据库相关参数 */

	public DBConn() {
	}

	/* 获取Conncetion对象并返回 */

	public Connection getConnection() {

		Connection con = null;

		try {
			System.out.println("1");
			Class.forName("com.mysql.jdbc.Driver"); // 加载Jdbc驱动程序
			System.out.println("2");
			con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/booksystem?useUnicode=true&characterEncoding=utf8",
							"root", "");
			System.out.println("3");
		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("getConnection()内部跟踪错误:" + e.getMessage());
		}

		return con;

	}
}