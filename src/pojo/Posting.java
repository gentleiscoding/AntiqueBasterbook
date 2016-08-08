package pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConn;

public class Posting {
	private String Subject;
	private String Username;
	private String Bookname;
	private String PublishingHouse;
	private String Content;
	private String label1;
	private String label2;
	private String intention;
	private String CreateTime;
	private String UpdateTime;
	private int ReplyCount;

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getBookname() {
		return Bookname;
	}

	public void setBookname(String bookname) {
		Bookname = bookname;
	}

	public String getPublishingHouse() {
		return PublishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		PublishingHouse = publishingHouse;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Posting(String subject, String username, String bookname,
			String publishinghouse, String content, String label1,
			String label2, String intention, String createtime,
			String updatetime) {
		this.Subject = subject;
		this.Username = username;
		this.Bookname = bookname;
		this.PublishingHouse = publishinghouse;
		this.Content = content;
		this.label1 = label1;
		this.label2 = label2;
		this.intention = intention;
		this.CreateTime = createtime;
		this.UpdateTime = updatetime;
		this.ReplyCount = 0;
	};

	public String result() {
		String result = "";
		if (this.getUsername() == null) {
			result = "请登陆后再发帖";
			return result;
		}
		if (this.getBookname().equals("") || this.getBookname() == null) {
			result = "书名不能为空，请重新填写";
			return result;
		}
		if (this.getSubject().equals("") || this.getSubject() == null) {
			result = "主题不能为空，请重新填写";
			return result;
		}

		if (this.getContent().equals("") || this.getContent() == null) {
			result = "内容不能为空，请重新填写";
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

		if (this.Subject.contains("'") || this.Subject.contains("\"")
				|| this.Subject.contains("&") || this.Subject.contains("|")
				|| this.Subject.contains("@") || this.Subject.contains("%")
				|| this.Subject.contains("*") || this.Subject.contains("(")
				|| this.Subject.contains(")") || this.Subject.contains("-")) {
			result = "请不要输入非法字符";
			return result;
		}
		;
		if (this.Bookname.contains("'") || this.Bookname.contains("\"")
				|| this.Bookname.contains("&") || this.Bookname.contains("|")
				|| this.Bookname.contains("@") || this.Bookname.contains("%")
				|| this.Bookname.contains("*") || this.Bookname.contains("(")
				|| this.Bookname.contains(")") || this.Bookname.contains("-")) {
			result = "请不要输入非法字符";
			return result;
		}
		;
		if (this.PublishingHouse.contains("'")
				|| this.PublishingHouse.contains("\"")
				|| this.PublishingHouse.contains("&")
				|| this.PublishingHouse.contains("|")
				|| this.PublishingHouse.contains("@")
				|| this.PublishingHouse.contains("%")
				|| this.PublishingHouse.contains("*")
				|| this.PublishingHouse.contains("(")
				|| this.PublishingHouse.contains(")")
				|| this.PublishingHouse.contains("-")) {
			result = "请不要输入非法字符";
			return result;
		}
		;
		if (this.label1.equals("") || this.label1 == null) {
			result = "请至少选择一个分类";
			return result;
		}
		if (this.getIntention().equals("") || this.getIntention() == null) {
			result = "请选择意向";
			return result;
		}
		// if(this.getLabel1().equals("")||this.getLabel1()==null){
		// result="请至少填写一个标签";
		// return result;
		// }

		Connection con = null;
		PreparedStatement pre = null;
		DBConn conns = new DBConn();
		con = conns.getConnection();
		ResultSet rs;
		String pnumber = "";
		String qq = "";
		// 得到用户的联系方式
		try {
			System.out.println("daozheli");
			pre = con
					.prepareStatement("select pnumber, qq from user where username='"
							+ this.getUsername() + "'");
			rs = pre.executeQuery();

			if (rs.next()) {
				pnumber = rs.getString("pnumber");
				System.out.println(pnumber);
				qq = rs.getString("qq");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = "发帖失败";
		} finally {

		}

		try {
			if (pnumber != null && !pnumber.equals("")) {
				this.Content = this.Content + "</br>ps：楼主手机号：" + pnumber;
			}
			if (qq != null && !qq.equals("")) {
				this.Content = this.Content + "</br>qq：" + qq
						+ "\n么么哒(这句是系统自动生成的)";
			}
			System.out.println(this.getContent());
			pre = con
					.prepareStatement(" INSERT INTO  post (`subject`, `username`, `bookname`, `publishinghouse`, `content`,`label1`, `label2`,`intention`,`createtime`,`updatetime`,`replycount`) VALUES ('"
							+ this.getSubject()
							+ "','"
							+ this.getUsername()
							+ "','"
							+ this.getBookname()
							+ "','"
							+ this.getPublishingHouse()
							+ "','"
							+ this.getContent()
							+ "','"
							+ this.getLabel1()
							+ "','"
							+ this.getLabel2()
							+ "','"
							+ this.getIntention()
							+ "','"
							+ this.getCreateTime()
							+ "','"
							+ this.getUpdateTime() + "',0)");
			System.out
					.println(" INSERT INTO  post (`subject`, `username`, `bookname`, `publishinghouse`, `content`,`label1`, `label2`,`intention`,`createtime`,`updatetime`,`replycount`) VALUES ('"
							+ this.getSubject()
							+ "','"
							+ this.getUsername()
							+ "','"
							+ this.getBookname()
							+ "','"
							+ this.getPublishingHouse()
							+ "','"
							+ this.getContent()
							+ "','"
							+ this.getLabel1()
							+ "','"
							+ this.getLabel2()
							+ "','"
							+ this.getIntention()
							+ "','"
							+ this.getCreateTime()
							+ "','"
							+ this.getUpdateTime() + "',0)");
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

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public int getReplyCount() {
		return ReplyCount;
	}

	public void setReplyCount(int replyCount) {
		ReplyCount = replyCount;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
}
