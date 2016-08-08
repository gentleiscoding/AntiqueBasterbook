$(function() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/BookSystem/bookser',
		data : 'state=showpost&pagenumber=1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#main_Show").html(data);
		}
	});
});