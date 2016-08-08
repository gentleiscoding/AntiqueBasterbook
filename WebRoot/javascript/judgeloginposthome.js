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
										"<a href=\"../html/login.html\" ><span style=\"font-size:24px\">&nbsp;&nbsp;登陆</span> </a> <a href=\"../html/signup.html\" ><span style=\"font-size:24px\">注册 </span> </a><a href=\"../html/home.html\" ><span style=\"font-size:24px\">主页</span></a><a  href =\"../html/mypost.html\" > <span style=\"font-size:24px\">我的书帖</span></a> ");
					} else {
						if (data.length < 8) {
							$("#toptop")
									.html(
											"<a ><span style=\"font-size:24px\">Hi~,"
													+ data
													+ "</span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"/BookSystem/bookser?state=loginout\" ><span style=\"font-size:24px\">注销 </span> </a><a href=\"../html/home.html\" ><span style=\"font-size:24px\">主页</span></a><a  href =\"../html/mypost.html\" > <span style=\"font-size:24px\">我的书帖</span></a> ");
						} else {
							$("#toptop")
									.html(
											"<a ><span style=\"font-size:24px\">Hello~</span> </a>  &nbsp;&nbsp;&nbsp;&nbsp;<a href=\"/BookSystem/bookser?state=loginout\" ><span style=\"font-size:24px\">注销 </span> </a><a href=\"../html/home.html\" ><span style=\"font-size:24px\">主页</span></a><a  href =\"../html/mypost.html\" > <span style=\"font-size:24px\">我的书帖</span></a> ");

						}
					}
				}
			});
});