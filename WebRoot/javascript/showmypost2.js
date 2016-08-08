function showmypost2() {
	$.ajax({
		type : 'POST',
		url : '/BookSystem/bookser',
		data : $('#selectpageform').serialize(),
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#main_Show").html(data);
		}

	});
};
