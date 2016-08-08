$(function() {
	$("#comment_add_btn").click(function() {
//		var reg = new RegExp("(^|&)" + "pid" + "=([^&]*)(&|$)", "i");
//		var r = window.location.search.substr(1).match(reg);
//		var pid = unescape(r[2]);
		$('#replyform').ajaxSubmit({
			type : 'post',// 数据发送方式
			url : '/BookSystem/bookser',
			data : $('#replyform').serialize(),
			dataType : 'text',
			error : function(XMLHttpRequest) {
				alert('错误信息:' + XMLHttpRequest);
			},
			success : function(data) {
				if (data == "发帖成功") {
					alert(data);
					location.replace(location.href);
				}else if(data=="请先登录") {
					alert(data);
					location.replace("/BookSystem/html/login.html");
				}
				else {
					alert(data);
				}
			}
		});
	});
});
