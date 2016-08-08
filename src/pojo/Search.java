package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DBConn;

public class Search {
	public Search() {
	};

	public String searchresult(String type, String keyword, int pagenumber) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] keywords = keyword.split("\\s");
		int pagecount = 0;

		if (keyword.contains("'") || keyword.contains("\"")
				|| keyword.contains("&") || keyword.contains("|")
				|| keyword.contains("@") || keyword.contains("%")
				|| keyword.contains("*") || keyword.contains("(")
				|| keyword.contains(")") || keyword.contains("-")) {
			result = "请不要输入非法字符";
			return result;
		}

		try {
			if (type == null) {
				String statement = "Select count(pid) as count from post where ";
				for (int i = 0; i < keywords.length; i++) {
					if (i != 0) {
						statement = statement + " " + "or" + " ";
					}
					statement = statement + "subject like '%" + keywords[i]
							+ "%' or bookname like '%" + keywords[i] + "%'";
				}
				System.out.println(statement);
				pre = con.prepareStatement(statement);
			} else {
				String statement = "Select count(pid) as count from post where (label1='"
						+ type + "' or label2='" + type + "') and (";
				for (int i = 0; i < keywords.length; i++) {
					if (i != 0) {
						statement = statement + " " + "or" + " ";
					}
					statement = statement + "subject like '%" + keywords[i]
							+ "%' or bookname like '%" + keywords[i] + "%'";
				}
				statement = statement + ")";
				System.out.println(statement);
				pre = con.prepareStatement(statement);
			}

			rs = pre.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("count"));
				if (rs.getInt("count") % 4 == 0) {
					pagecount = rs.getInt("count") / 4;
				} else {
					pagecount = (rs.getInt("count") / 4) + 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		if (pagecount == 0) {
			result = "没有搜索到相关内容";
			return result;
		}

		try {
			if (pagenumber == 1) {
				if (type == null) {
					String statement = "Select pid,username,subject,bookname,updatetime,replycount from post where ";
					for (int i = 0; i < keywords.length; i++) {
						if (i != 0) {
							statement = statement + " " + "or" + " ";
						}
						statement = statement + "(subject like '%"
								+ keywords[i] + "%' or bookname like '%"
								+ keywords[i] + "%')";
					}
					pre = con.prepareStatement(statement
							+ "order by updatetime desc limit 4");

				} else {
					String statement = "Select pid,username,subject,bookname,updatetime,replycount from post where label1='"
							+ type + "' and (";
					for (int i = 0; i < keywords.length; i++) {
						if (i != 0) {
							statement = statement + " " + "or" + " ";
						}
						statement = statement + "subject like '%" + keywords[i]
								+ "%' or bookname like '%" + keywords[i] + "%'";
					}

					pre = con.prepareStatement(statement
							+ ") order by updatetime desc limit 4");
				}

			}
			if (pagenumber > 1) {
				if (type == null) {
					String statement = "Select pid,username,subject,bookname,updatetime,replycount from post where ";
					for (int i = 0; i < keywords.length; i++) {
						if (i != 0) {
							statement = statement + " " + "or" + " ";
						}
						statement = statement + "(subject like '%"
								+ keywords[i] + "%' or bookname like '%"
								+ keywords[i] + "%')";
					}
					pre = con
							.prepareStatement("select pid, username,subject,updatetime,replycount from (select pid, username,subject,updatetime,replycount from("
									+ statement
									+ "order by updatetime desc limit "
									+ 4
									* pagenumber
									+ ") aa order by updatetime asc limit 4) bb order by updatetime desc  ");
					System.out.println(pre);

				} else {
					String statement = "Select pid,username,subject,bookname,updatetime,replycount from post where label1='"
							+ type + "' ";
					for (int i = 0; i < keywords.length; i++) {
						if (i != 0) {
							statement = statement + " " + "or" + " ";
						}
						statement = statement + "(subject like '%"
								+ keywords[i] + "%' or bookname like '%"
								+ keywords[i] + "%')";
					}
					pre = con
							.prepareStatement("select pid, username,subject,updatetime,replycount from(select pid, username,subject,updatetime,replycount from("
									+ statement
									+ ")order by updatetime desc limit "
									+ 4
									* pagenumber
									+ ") aa order by updatetime asc limit 4) bb order by updatetime desc  ");
				}
			}
			System.out.println(pre);
			rs = pre.executeQuery();
			result = result
					+ "<script src=\"../javascript/showsearch2.js\" type=\"text/javascript\"></script><script src=\"../javascript/showdetail.js\" type=\"text/javascript\"></script><div class=\"mynote_list\">";

			// 显示
			while (rs.next()) {
				result = result
						+ "<div class=\"main_left\"> <a href=\"/BookSystem/html/reply.html?pid="
						+ rs.getInt("pid")
						+ "&state=showdetail\"  target=\"_blank\">"
						+ rs.getString("subject")
						+ "</a> <div class=\"left_downer\">"
						+ "<p style=\"padding-right:20px;\">"
						+ rs.getDate("updatetime") + " "
						+ rs.getTime("updatetime") + " </p> </div></div>";
			}
			result = result
					+ "</ul></div><div id=\"theme_holder\" class=\"holder\">  <form id=\"selectpageform\">	<div id=\"divselect_down\"> <select name=\"pagenumber\">";
			int i = 1;
			while (i <= pagecount) {
				result = result + "<option value=\"" + i + "\">第" + i
						+ "页</option>";
				i++;
			}
			result = result
					+ "</select></div><input id=\"selectpage\" type=\"button\" value=\"跳转\" onclick=\"showsearch2()\" /><input type=\"hidden\" name=\"state\" value=\"search\" /></form> </div>";
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
