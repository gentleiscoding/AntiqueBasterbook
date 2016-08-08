package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConn;

public class Signup {
	public Signup() {

	};

	public String result(String username, String password1, String password2,
			String email, String qq, String pnumber) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		// 用户名或密码为空
		if (username == null || password1 == null || password2 == null) {
			result = "用户名，密码和确认密码不能为空";
			return result;
		}
		// 当账号或密码长度超过12个字符时，提示错误
		if (username.length() > 12 || password1.length() > 12) {
			result = "用户名或密码长度不能超过12个字符";
			return result;
		}
		// 当账号或密码长度超过12个字符时，提示错误
		if (username.length() < 6 || password1.length() < 6) {
			result = "用户名或密码长度不能小于6个字符";
			return result;
		}
		// 判断用户名是否已被注册
		int a = 0;
		try {
			pre = con
					.prepareStatement(" Select count(*)  from user where username = '"
							+ username + "'");

			rs = pre.executeQuery();
			System.out.println("bbbb");
			while (rs.next()) {
				a = rs.getInt("count(*)");
			}
			System.out.println(a);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (a == 1) {
				result = "该用户已被注册";
				return result;
			}
		}
		// 密码1和密码2不等
		if (!password1.equals(password2)) {
			result = "密码确认错误，请重新输入";
			return result;
		}

		try {

			con = conns.getConnection();
			pre = con
					.prepareStatement(" INSERT INTO  user (`username`, `password`, `email`, `qq`, `pnumber`) VALUES ('"
							+ username
							+ "','"
							+ password1
							+ "','"
							+ email
							+ "','" + qq + "','" + pnumber + "')");

			pre.executeUpdate();
			System.out.println("aaa");
			System.out.println();
			result = "注册成功";

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
