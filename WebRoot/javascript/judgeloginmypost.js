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
						alert("您还没有登陆");
						location.replace("/html/login.html");
					} else {
						if (data.length < 8) {
							$("#toptop")
									.html(
											"<a ><span>Hi~,"
													+ data
													+ "</span> </a> <a href=\"/BookSystem/bookser?state=loginout\" ><span>注销 </span> </a><a href=\"../html/home.html\" ><span>主页</span></a><a  href =\"../html/posthome.html\" > <span>全部书贴</span></a> ");
						} else {
							$("#toptop")
									.html(
											"<a ><span>"
													+ data.substr(0, 3)
													+ "……</span> </a> <a href=\"/BookSystem/bookser?state=loginout\" ><span>注销 </span> </a><a href=\"../html/home.html\" ><span>主页</span></a><a  href =\"../html/posthome.html\" > <span>全部书贴</span></a> ");

						}
					}
				}
			});
});