$(function() {
	$
			.ajax({
				type : 'POST',// 数据发送方式
				url : '/BookSystem/bookser',
				data : 'state=judgelogin',
				dataType : 'text',
				error : function(XMLHttpRequest) {
					alert('错误信息:' + XMLHttpRequest);
				},
				success : function(data) {
					if (data == "no") {
						$("#toptop")
								.html(
										"<script type=\"text/javascript\" src=\"../javascript/index.js\"></script><img src=\"../img/home/25.png\" id=\"leaf\"><li class=\"current\"><a href=\"../html/login.html\" >登录</a></li> <li class=\"current\"><a href=\"../html/signup.html\">注册</a></li> <li class=\"current\"><a href=\"http://www.sdu.edu.cn/\">山大主页</a></li> ");
					} else {
						if (data.length < 8) {
							$("#toptop")
									.html(
											"<script type=\"text/javascript\" src=\"../javascript/index.js\"></script><img src=\"../img/home/25.png\" id=\"leaf\"><li class=\"current\"><a>"
													+ data
													+ "</a></li>  <li><a id=\"loginout\" href=\"/BookSystem/bookser?state=loginout\"  >注销</a></li> <li><a href=\"../html/fankuiliuyan.html\" id=\"fankui\">反馈留言</a></li>");
						} else {
							$("#toptop")
									.html(
											"<script type=\"text/javascript\" src=\"../javascript/index.js\"></script><img src=\"../img/home/25.png\" id=\"leaf\"><li class=\"current\"><a>"
													+ data.substr(0, 3)
													+ "……</a></li>  <li><a id=\"loginout\" href=\"/BookSystem/bookser?state=loginout\"  >注销</a></li> <li><a href=\"../html/fankuiliuyan.html\" id=\"fankui\">反馈留言</a></li>");

						}
					}
				}
			});
});