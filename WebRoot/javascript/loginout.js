$(function() {

	$("#loginout").click(function() {
		$.ajax({
			type : 'POST',// 数据发送方式
			url : '/BookSystem/bookser',
			data : 'state=loginout',
			dataType : 'text',
			error : function(XMLHttpRequest) {
				alert('错误信息:' + XMLHttpRequest);
			},
			success : function(data) {
				if (data = "注销成功") {
					location.replace("/html/home.html");
				}

			}
		});
	})
});