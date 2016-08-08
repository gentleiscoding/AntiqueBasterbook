$(function() {
	var reg = new RegExp("(^|&)" + "type" + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	var type = unescape(r[2]);
	$.ajax({
		type : 'GET',// 数据发送方式
		url : '/BookSystem/bookser',
		data : 'state=sort&pagenumber=1&type=' + type,
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "暂时没有这个分类的相关信息") {
				alert("暂时没有这个分类的相关信息");
				location.replace("/html/posthome.html");
			}
			$("#main_Show").html(data);
		}
	});
});