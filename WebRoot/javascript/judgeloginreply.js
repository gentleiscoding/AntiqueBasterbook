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
										"<img src=\"../img/user_picture.png\" /><a href=\"/BookSystem/html/login.html\" id=\"loginhref\"  >登陆</a><a href=\"/BookSystem/html/signup.html\" id=\"signuphref\">注册</a>");
					} else {
						if (data.length < 8) {
							$("#toptop")
									.html(
											"<img src=\"../img/user_picture.png\" / ><p id=\"hi\">Hi~"
													+ data
													+ "</p><a href=\"/BookSystem/bookser?state=loginout\" id=\"loginouthref\">注销</a>");
						} else {

							$("#toptop")
									.html(
											"<img src=\"../img/user_picture.png\" / ><p id=\"hi\">Hello~</p><a href=\"/BookSystem/bookser?state=loginout\" id=\"loginouthref\">注销</a>");

						}
					}
				}
			});
});