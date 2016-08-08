package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConn;

public class Login {
	public Login() {

	};

	public String result(String username, String password) {

		String result = "";
		if (username.contains("'") || username.contains("\"")
				|| username.contains("&") || username.contains("|")
				|| username.contains("@") || username.contains("%")
				|| username.contains("*") || username.contains("(")
				|| username.contains(")") || username.contains("-")) {
			result = "请不要输入非法字符";
			return result;
			
		}
		System.out.println("here");
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		try {
			pre = con
					.prepareStatement("Select password from user WHERE username='"
							+ username + "'");
			rs = pre.executeQuery();
			String passw = "";
			while (rs.next()) {
				passw = rs.getString("password");

			}
			if (passw.equals("")) {
				result = "请输入正确用户名";
				return result;

			}
			if (!passw.equals(password)) {
				System.out.println(passw);
				System.out.println(password);
				result = "密码错误";
				return result;
			}
			if (passw.equals(password)) {
				result = "登陆成功";
				return result;
			}

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
