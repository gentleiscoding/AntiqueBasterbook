package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConn;

public class Advice {
	public Advice() {

	};

	public String result(String advice) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		if (advice == null || advice.equals("")) {
			result = "请输入您的建议";
			return result;
		}
		// 当账号或密码长度超过12个字符时，提示错误
		if (advice.length() > 200) {
			result = "建议内容太长~不要超过200字嘛";
			return result;
		}

		try {

			con = conns.getConnection();
			pre = con.prepareStatement(" INSERT INTO  advice VALUES ('"
					+ advice + "')");

			pre.executeUpdate();
			System.out.println("aaa");
			System.out.println();
			result = "谢谢您的建议";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;
	}
}
