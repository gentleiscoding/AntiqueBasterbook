package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import db.DBConn;

public class ShowDetail {
	public ShowDetail() {
	};

	public String result(int pagenumber, int pid) {
		String result = "";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int pagecount = 0;
		// 从数据库中取出回复总数
		try {
			pre = con
					.prepareStatement("Select replycount  from post where pid="
							+ pid);
			rs = pre.executeQuery();
			while (rs.next()) {
				System.out
						.println("thisisreplycount" + rs.getInt("replycount"));
				if (rs.getInt("replycount") % 4 == 0) {
					pagecount = rs.getInt("replycount") / 4;
				} else {
					pagecount = (rs.getInt("replycount") / 4) + 1;
				}
			}
			System.out.println("thisisreplypagecount" + pagecount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		// 取出帖子的详细信息
		try {
			pre = con
					.prepareStatement("Select username,subject,bookname,publishinghouse,content,createtime from post where pid="
							+ pid);
			rs = pre.executeQuery();
			while (rs.next()) {
				String user = "";
				if (rs.getString("username").length() < 8) {
					user = rs.getString("username");
				} else {
					user = rs.getString("username").substring(0, 3) + "……";
				}
				result = "<script src=\"../javascript/ShowReply.js\" type=\"text/javascript\"></script><div class=\"middle\" id=\"middle_id\"><div class=\"Note_Reply\"><p style=\"font-size:28px;\">"
						+ rs.getString("subject")
						+ "</p><button type=\"button\" id=\"button_rep\" class=\"button_rep\">回复</button><div class=\"Reply_one\"><div class=\"Reply_one_left\"><img src=\"../img/reply/user_picture.png\"><pre>"
						+ user
						+ "</pre></div><div class=\"Reply_one_right\"><p>书名："
						+ rs.getString("bookname")
						+ "</br>出版社："
						+ rs.getString("publishinghouse")
						+ "</br>楼主说："
						+ rs.getString("content")
						+ "</p></div></div><p style=\"clear:both;float:right;padding-right:110px;\">发帖时间："
						+ rs.getDate("createtime")
						+ " "
						+ rs.getTime("createtime") + "</p>";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		System.out.println(pagenumber);
		// 取出回复
		try {
			if (pagenumber == 1) {
				pre = con
						.prepareStatement("Select username,content,createtime from reply where pid= "
								+ pid + " order by createtime asc limit 4");
			}
			if (pagenumber > 1) {
				pre = con
						.prepareStatement("select username,content,createtime from(select username,content,createtime from(Select username,content,createtime from reply where pid ="
								+ pid
								+ " order by createtime asc limit "
								+ 4
								* pagenumber
								+ ") aa order by createtime desc limit 4) bb order by createtime asc  ");
			}
			if (pagecount > 0) {
				rs = pre.executeQuery();
				result = result
						+ "<script src=\"../javascript/showdetail2.js\" type=\"text/javascript\"></script>";
				while (rs.next()) {
					String user = "";
					if (rs.getString("username").length() < 8) {
						user = rs.getString("username");
					} else {
						user = rs.getString("username").substring(0, 3) + "……";
					}
					result = result
							+ "<div class=\"Reply_two\"><div class=\"Reply_two_left\"><img src=\"../img/reply/user_picture.png\"><pre>"
							+ user
							+ "</pre></div><div class=\"Reply_two_right\"><p>"
							+ rs.getString("content")
							+ "</p></div></div><p style=\"clear:both;float:right;padding-right:110px;\">回复时间："
							+ rs.getDate("createtime") + " "
							+ rs.getTime("createtime") + "</p>";
				}
				result = result
						+ "</div><div id=\"theme_holder_reply\" class=\"holder\"> <form id=\"selectpageform\">	 <select name=\"pagenumber\">";
				int i = 1;
				while (i <= pagecount) {
					result = result + "<option value=\"" + i + "\">第" + i
							+ "页</option>";
					i++;
				}
				result = result
						+ "</select><input id=\"selectpage\" type=\"button\" value=\"跳转\" onclick=\"showdetail2()\" /><input type=\"hidden\" name=\"pid\" value=\""
						+ pid
						+ "\"/><input type=\"hidden\" name=\"state\" value=\"showdetail\" /></form> </div> <div id=\"fabuhuitie\"> <span>发布回帖</span><a href=\"#\" class=\"right\">X</a> <script src=\"../javascript/doreply.js\" type=\"text/javascript\"></script> <form  id=\"replyform\"><!--<input type=\"text\" value=\"标题 *\" id=\"comment_title\"  onfocus=\"if (value=='标题 *'){value=''}\"  onblur=\"if(value==''){value='标题 *'}\"/ >--><textarea type=\"text\"  id=\"comment_add_content\" name=\"content\"></textarea> <button type=\"button\"  id=\"comment_add_btn\">提交</button><input type=\"hidden\" name=\"state\" value=\"doreply\"/><input type=\"hidden\" name=\"pid\" value=\""
						+ pid
						+ "\"/></form> </div><!-- 弹出层遮罩 --> <div id=\"bg\"> </div></div>";
				System.out.println(result);
				return result;
			} else {
				result = result
						+ "</div><div id=\"theme_holder_reply\" class=\"holder\">  <form id=\"selectpageform\">	 <select name=\"pagenumber\">";
				int i = 1;
				while (i <= pagecount) {
					result = result + "<option value=\"" + i + "\">第" + i
							+ "页</option>";
					i++;
				}
				result = result
						+ "</select><input id=\"selectpage\" type=\"button\" value=\"跳转\" onclick=\"showdetail2()\" /><input type=\"hidden\" name=\"pid\" value=\""
						+ pid
						+ "\"/><input type=\"hidden\" name=\"state\" value=\"showdetail\" /></form> </div> <div id=\"fabuhuitie\"> <span>发布回帖</span><a href=\"#\" class=\"right\">X</a> <script src=\"../javascript/doreply.js\" type=\"text/javascript\"></script> <form  id=\"replyform\"><!--<input type=\"text\" value=\"标题 *\" id=\"comment_title\"  onfocus=\"if (value=='标题 *'){value=''}\"  onblur=\"if(value==''){value='标题 *'}\"/ >--><textarea type=\"text\"  id=\"comment_add_content\" name=\"content\"></textarea> <button type=\"button\"  id=\"comment_add_btn\">提交</button><input type=\"hidden\" name=\"state\" value=\"doreply\"/><input type=\"hidden\" name=\"pid\" value=\""
						+ pid
						+ "\"/> </form> </div><!-- 弹出层遮罩 --> <div id=\"bg\"> </div></div>";
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
