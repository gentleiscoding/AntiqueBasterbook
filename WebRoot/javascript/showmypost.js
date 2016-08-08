$(function() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/BookSystem/bookser',
		data : 'state=showmypost&pagenumber=1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "您还没有登陆哦") {
				alert("您还没有登陆哦");
				location.replace("/BookSystem/html/login.html");
			}
			if (data == "你还没有发过帖子哦") {
				alert("你还没有发过帖子哦");
				location.replace("/BookSystem/html/posthome.html");
			} else {
				$("#main_Show").html(data);
			}
		}
	});
});