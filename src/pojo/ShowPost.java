package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DBConn;

public class ShowPost {
	public ShowPost() {

	};

	public String result(int pagenumber) {
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
			pre = con.prepareStatement("Select count(pid) as count from post");
			rs = pre.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("count"));
				if (rs.getInt("count") % 10 == 0) {
					pagecount = rs.getInt("count") / 10;
				} else {
					pagecount = (rs.getInt("count") / 10) + 1;
				}
			}
			System.out.println(pagecount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		if (pagecount == 0) {
			result = "暂无帖子发布";
			return result;
		}

		System.out.println(pagenumber);
		try {
			if (pagenumber == 1) {
				pre = con
						.prepareStatement("Select pid,username,subject,updatetime,replycount, intention from post order by updatetime desc limit 10");
			}
			if (pagenumber > 1) {
				pre = con
						.prepareStatement("select pid, username,subject,updatetime,replycount,intention from(select pid, username,subject,content,updatetime,replycount,intention from(Select pid,username,subject,content,updatetime,replycount,intention from post order by updatetime desc limit "
								+ 10
								* pagenumber
								+ ") aa order by updatetime asc limit 10) bb order by updatetime desc  ");
			}
			// select username,subject,updatetime,replycount from(Select
			// username,subject,updatetime,replycount from post order by
			// updatetime desc limit 20) order by updatetime asc limit 10
			rs = pre.executeQuery();
			result = result
					+ "<link href=\"../css/NewNote.css\" rel=\"stylesheet\" type=\"text/css\"><script src=\"../javascript/showpost2.js\" type=\"text/javascript\"></script><script src=\"../javascript/showdetail2.js\" type=\"text/javascript\"></script><ul id=\"show_list\">";

			// 显示回复的具体内容
			while (rs.next()) {
				String img = "";
				if (rs.getString("intention") == null) {
					img = "";
				} else if (rs.getString("intention").equals("买书")) {
					img = "<img src=\"../img/posthome/buy_logo.png\">";
				} else if (rs.getString("intention").equals("卖书")) {
					img = "<img src=\"../img/posthome/sell_logo.png\">";
				} else if (rs.getString("intention").equals("借入")) {
					img = "<img src=\"../img/posthome/borrow_logo.png\">";
				} else if (rs.getString("intention").equals("借出")) {
					img = "<img src=\"../img/posthome/return_logo.png\">";
				} else {
					img = "";
				}
				String user = "";
				if (rs.getString("username").length() < 8) {
					user = rs.getString("username");
				} else {
					user = rs.getString("username").substring(0, 4) + "…";
				}
				result = result
						+ "<li class=\"single_list\"><div class=\"pic\">"
						+ img
						+ "</div><div class=\"goleft\"><pre style=\"font-family:'Segoe Script';font-size:18px;position:relative;bottom:10px;\">    "
				        + rs.getInt("replycount")
						+ "</pre></div><div class=\"goright\"> <span id=\"username\" style=\"padding-right:40px;\"  >"
						+ user
						+ "</span> <br/><a href=\"/BookSystem/html/reply.html?pid="
						+ rs.getInt("pid")
						+ "\" target=\"_blank\"><span id=\"third\">"
						+ rs.getString("subject")
						+ "</span></a><span id=\"date\" style=\"padding-top:35px;\">"
						+ rs.getDate("updatetime")
						+ " "
						+ rs.getTime("updatetime")
						+ "</span><div class=\"user_pic\"> <img src=\"../img/posthome/logo.png\"/ style=\"margin-top:-42px;margin-left:55px;\"></div></div></li><br/>";
			}
			result = result
					+ "</ul><div id=\"theme_holder\" class=\"holder\">  <form id=\"selectpageform\">	 <select name=\"pagenumber\">";
			int i = 1;
			while (i <= pagecount) {
				result = result + "<option value=\"" + i + "\">第" + i
						+ "页</option>";
				i++;
			}
			result = result
					+ "</select><input id=\"selectpage\" type=\"button\" value=\"跳转\" onclick=\"showpost2()\" /><input type=\"hidden\" name=\"state\" value=\"showpost\" /></form></div>";
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
