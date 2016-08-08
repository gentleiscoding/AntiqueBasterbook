$(function() {
	var reg = new RegExp("(^|&)" + "pid" + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	var pid = unescape(r[2]);
	$.ajax({	
		type : 'GET',// 数据发送方式
		url : '/BookSystem/bookser',
		data : 'state=showdetail&pagenumber=1&pid='+pid,
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#intrc_note_id").html(data);
		}
	});
});
