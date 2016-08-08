function showsort2() {
	alert($('#selectpageform').serialize());
	$.ajax({
		type : 'get',
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
