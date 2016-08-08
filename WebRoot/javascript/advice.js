$(function() {

	$("#btn_comment").click(function() {
		$('#adviceform').ajaxSubmit({
			type : 'POST',// 数据发送方式
			url : '/BookSystem/bookser',// 后台处理程序的页面路径
			data : $('#adviceform').serialize(),
			dataType : 'text',// 返回的数据格式
			error : function(XMLHttpRequest) {
				alert('出错l ,错误信息:' + XMLHttpRequest);
			},
			success : function(data) {
				alert(data);
				location.replace("/BookSystem/html/home.html");
			}

		});
	});
});