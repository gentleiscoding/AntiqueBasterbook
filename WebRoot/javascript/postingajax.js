$(function() {
	$("#comment_add_btn").click(function() {
		$('#postingform').ajaxSubmit({
			type : 'POST',// 数据发送方式
			url : '/BookSystem/bookser',// 后台处理程序的页面路径
			data : $('#postingform').serialize(),
			dataType : 'text',// 返回的数据格式
			error : function(XMLHttpRequest) {
				alert("发帖失败");
			},
			success : function(data) {
				if (data == "请登陆后再发帖") {
					alert(data);
					location.replace("/BookSystem/html/login.html");
				}
				if (data == "发帖成功") {
					$('#fabuxintie').css({
					visibility: 'hidden',
				});
		
				$('#bg').css({
		    	height:0,
		    	width:0,
		    	});
		    	location.replace("/BookSystem/html/posthome.html");
				} else {
					alert(data);
				}
			}

		});
	});
});