package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DBConn;

public class ShowMyPost {
	public ShowMyPost() {

	};

	public String result(int pagenumber, String username) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int pagecount = 0;
		// 从数据库中取出帖子总数
		try {
			pre = con
					.prepareStatement("Select count(pid) as count from post where username='"
							+ username + "'");
			rs = pre.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("count"));
				if (rs.getInt("count") % 4 == 0) {
					pagecount = rs.getInt("count") / 4;
				} else {
					pagecount = (rs.getInt("count") / 4) + 1;
				}
			}
			System.out.println(pagecount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		if (pagecount == 0) {
			result = "你还没有发过帖子哦";
			return result;
		}

		System.out.println(pagenumber);
		try {
			if (pagenumber == 1) {
				pre = con
						.prepareStatement("Select pid,username,subject,content,updatetime,replycount from post where username='"
								+ username
								+ "'order by updatetime desc limit 4");
			}
			if (pagenumber > 1) {
				pre = con
						.prepareStatement("select pid, username,subject,content,updatetime,replycount from(select pid, username,subject,content,updatetime,replycount from(Select pid,username,subject,content,updatetime,replycount from post where username='"
								+ username
								+ "'order by updatetime desc limit "
								+ 4
								* pagenumber
								+ ") aa order by updatetime asc limit 4) bb order by updatetime desc  ");
			}
			// select username,subject,updatetime,replycount from(Select
			// username,subject,updatetime,replycount from post order by
			// updatetime desc limit 20) order by updatetime asc limit 10
			rs = pre.executeQuery();
			result = result
					+ "<script src=\"../javascript/showmypost2.js\" type=\"text/javascript\"></script><script src=\"../javascript/showdetail.js\" type=\"text/javascript\"></script><div class=\"mynote_list\">";

			// 显示
			while (rs.next()) {
				result = result
						+ "<div class=\"main_left\"> <a href=\"/BookSystem/html/reply.html?pid="
						+ rs.getInt("pid") + "&state=showdetail\"  target=\"_blank\">"
						+ rs.getString("subject")
						+ "</a> <div class=\"left_downer\">"
						+"<p style=\"padding-right:20px;\">"
						+ rs.getDate("updatetime") + " "
						+ rs.getTime("updatetime") + " </p> </div></div>";
			}
			result = result
					+ "</ul></div><div id=\"theme_holder\" class=\"holder\">  <form id=\"selectpageform\"><div id=\"divselect_down\">	 <select name=\"pagenumber\">";
			int i = 1;
			while (i <= pagecount) {
				result = result + "<option value=\"" + i + "\">第" + i
						+ "页</option>";
				i++;
			}
			result = result
					+ "</select></div><input id=\"selectpage\" type=\"button\" value=\"跳转\" onclick=\"showmypost2()\" /><input type=\"hidden\" name=\"state\" value=\"showmypost\" /> </form> </div>";
			System.out.println(result);
			return result;

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
