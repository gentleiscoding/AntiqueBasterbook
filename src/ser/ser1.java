package ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Advice;
import pojo.Login;
import pojo.Posting;
import pojo.Reply;
import pojo.Search;
import pojo.ShowMyPost;
import pojo.ShowPost;
import pojo.ShowDetail;
import pojo.Signup;
import pojo.Sort;

public class ser1 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String state = (String) request.getParameter("state");
		System.out.println(state);
		PrintWriter out = response.getWriter();

		// 判断是否登陆的ser
		if (state.equals("judgelogin")) {
			String username = (String) request.getSession().getAttribute(
					"username");
			System.out.println(username);
			if (username == null) {
				out.write("no");
			} else {
				out.write(username);
			}
		}

		// 注册的ser
		if (state.equals("signup")) {
			String username = (String) request.getParameter("username");
			String password1 = (String) request.getParameter("password1");
			String password2 = (String) request.getParameter("password2");
			System.out.println(username);
			System.out.println(password1);
			System.out.println(password2);
			String email = (String) request.getParameter("email");
			String qq = (String) request.getParameter("qq");
			String pnumber = (String) request.getParameter("pnumber");
			String signupresult = "";
			Signup signup = new Signup();
			System.out.println("user" + username + "p1 " + password1 + "p2 "
					+ password2);
			signupresult = signup.result(username, password1, password2, email,
					qq, pnumber);

			if (signupresult.equals("注册成功")) {

				request.getSession().setAttribute("username", username);
			}
			System.out.println(signupresult);
			out.write(signupresult);
			System.out.println("finish");
			// if (signupresult.equals("用户名或密码长度不能超过12个字符")) {
			//
			// }
			// if (signupresult.equals("该用户已被注册")) {
			//
			// }
			// if (signupresult.equals("用户名，密码和确认密码不能为空")) {
			//
			// }
			// if (signupresult.equals("密码确认错误，请重新输入")) {
			//
			// }
			// if (signupresult.equals("注册成功")) {
			//
			// }

		}
		// 登陆的ser
		if (state.equals("loginin")) {
			// 判断账号密码是否正确
			System.out.println("bbb");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			Login login = new Login();
			String loginresult = login.result(username, password);
			if (loginresult.equals("登陆成功")) {

				request.getSession().setAttribute("username", username);
			}
			out.write(loginresult);
			// if (loginresult == "密码错误") {
			// System.out.println(loginresult);
			//
			// } else if (loginresult == "不存在该用户") {
			// System.out.println(loginresult);
			// } else if (loginresult == "密码正确") {
			// System.out.println(loginresult);
			// }
		}
		// 发帖的ser
		if (state.equals("doposting")) {
			String subject = (String) request.getParameter("subject");
			String username = (String) request.getSession().getAttribute(
					"username");
			System.out.println("thisisusername" + username);
			String bookname = (String) request.getParameter("bookname");
			String publishinghouse = (String) request
					.getParameter("publishinghouse");
			if (publishinghouse.equals("出版社")) {
				publishinghouse = "无";
			}
			String content = (String) request.getParameter("content");
			String label1 = (String) request.getParameter("label1");
			String label2 = (String) request.getParameter("label2");
			String intention = (String) request.getParameter("intention");
			System.out.println("thisisintention" + intention);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = df.format(new Date());
			Posting posting = new Posting(subject, username, bookname,
					publishinghouse, content, label1, label2, intention,
					createtime, createtime);
			String postingresult = posting.result();
			System.out.println(postingresult);
			out.write(postingresult);
		}
		// 主页显示帖子的ser
		if (state.equals("showpost")) {
			int pagenumber = Integer.parseInt(request
					.getParameter("pagenumber"));
			System.out.println("thishispagenumber "
					+ request.getParameter("pagenumber"));
			ShowPost showpost = new ShowPost();
			String result = "";
			result = showpost.result(pagenumber);
			System.out.println("alright");

			out.write(result);
		}
		// 回复的ser
		if (state.equals("doreply")) {
			String username = (String) request.getSession().getAttribute(
					"username");
			System.out.println("daozhelile");
			System.out.println("thisispp" + request.getParameter("pp"));
			int pid = Integer.parseInt(request.getParameter("pid"));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createtime = df.format(new Date());

			String content = (String) request.getParameter("content");
			System.out.println(content);
			Reply reply = new Reply(pid, username, content, createtime);
			String result = "";
			result = reply.result();
			out.write(result);
		}

		// 我的帖子
		if (state.equals("showmypost")) {
			int pagenumber = Integer.parseInt(request
					.getParameter("pagenumber"));
			System.out.println("thishispagenumber "
					+ request.getParameter("pagenumber"));
			String username = (String) request.getSession().getAttribute(
					"username");
			if (username == null || username.equals("username")) {
				String result = "您还没有登陆哦";
				out.write(result);
			} else {
				ShowMyPost showmypost = new ShowMyPost();
				String result = "";
				result = showmypost.result(pagenumber, username);
				System.out.println("alright");
				out.write(result);
			}
		}
		// 反馈的ser
		if (state.equals("advice")) {
			String advice = (String) request.getParameter("advice");
			Advice ad = new Advice();
			String result = ad.result(advice);
			out.write(result);

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("abc");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String state = (String) request.getParameter("state");
		System.out.println(state);
		PrintWriter out = response.getWriter();
		// 显示贴的具体页面
		if (state.equals("showdetail")) {
			int pid = Integer.parseInt(request.getParameter("pid"));
			System.out.println(pid);
			int pagenumber = Integer.parseInt(request
					.getParameter("pagenumber"));
			ShowDetail showdetail = new ShowDetail();
			String result = showdetail.result(pagenumber, pid);
			out.write(result);
		}

		if (state.equals("loginout")) {
			request.getSession().removeAttribute("username");
			System.out.println("afterloginout"
					+ (String) request.getSession().getAttribute("username"));
			out.write("注销成功");
			response.sendRedirect("/BookSystem/html/home.html");
			return;
		}

		// 帖子的分类搜索
		if (state.equals("sort")) {
			System.out.println("aaa");
			int pagenumber = Integer.parseInt(request
					.getParameter("pagenumber"));
			String type = (String) request.getParameter("type");

			System.out.println(type);
			Sort sort = new Sort();
			String result = sort.sortresult(pagenumber, type);
			out.write(result);
		}

		// ser的关键字搜索
		if (state.equals("search")) {
			String keyword = (String) request.getParameter("keyword");
			String type = (String) request.getParameter("type");
			int pagenumber = Integer.parseInt(request
					.getParameter("pagenumber"));
			if (type.equals("quanbu")) {
				type = null;
			}
			if ((keyword.equals("") || keyword == null) && type == null) {
				out.write("请输入搜索关键词");
				return;
			}
			if ((keyword.equals("") || keyword == null) && type != null) {
				Sort sort = new Sort();
				String result = sort.sortresult(pagenumber, type);
				out.write(result);
			}

			Search search = new Search();
			String result = search.searchresult(type, keyword, pagenumber);
			out.write(result);
		}
	}
}