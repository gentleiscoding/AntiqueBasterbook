package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConn;

public class Reply {
	private int pid;
	private String Username;
	private String Content;
	private String CreateTime;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public Reply(int pid, String username, String content, String createtime) {
		this.pid = pid;
		this.Username = username;
		this.Content = content;
		this.CreateTime = createtime;
	};

	public String result() {
		int rss;
		String result = "";
		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();

		if (this.Username == null || this.Username.equals("")) {
			result = "请先登录";
			return result;
		}
		if (this.Content == null || this.Content.equals("")) {
			result = "回复内容不能为空";
			return result;
		}
		if (this.Content.length() > 100) {
			result = "回复内容应小于100字符";
			return result;
		}
		if (this.Content.contains("'") || this.Content.contains("\"")
				|| this.Content.contains("&") || this.Content.contains("|")
				|| this.Content.contains("@") || this.Content.contains("%")
				|| this.Content.contains("*") || this.Content.contains("(")
				|| this.Content.contains(")") || this.Content.contains("-")) {
			result = "请不要输入非法字符";
			return result;
		}
		try {
			pre = con
					.prepareStatement("INSERT INTO reply (`pid`, `username`, `content`, `createtime`) VALUES ("
							+ this.getPid()
							+ ",'"
							+ this.getUsername()
							+ "','"
							+ this.getContent()
							+ "','"
							+ this.getCreateTime()
							+ "')");
			pre.executeUpdate();
			System.out.println("hhh");
			pre = con
					.prepareStatement("update post set replycount=replycount+1 where pid="
							+ this.getPid());
			pre.executeUpdate();
			result = "发帖成功";
		} catch (SQLException e) {
			e.printStackTrace();
			result = "发帖失败";
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
