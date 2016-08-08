$(function() {

	$("#button1").click(function() {
		$('#signupform').ajaxSubmit({
			type : 'POST',// 数据发送方式
			url : '/BookSystem/bookser',// 后台处理程序的页面路径
			data : $('#signupform').serialize(),
			dataType : 'text',// 返回的数据格式
			error : function(XMLHttpRequest) {
				alert('出错l ,错误信息:' + XMLHttpRequest);
			},
			success : function(data) {
				if (data == "注册成功") {
					alert(data);
					location.replace("/BookSystem/html/home.html");
				} else {
					alert(data);
				}
			}

		});
	});
});