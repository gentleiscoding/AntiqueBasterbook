function showsearch2() {
	var reg = new RegExp("(^|&)" + "type" + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	var type = unescape(r[2]);
	var reg = new RegExp("(^|&)" + "keyword" + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	var keyword = unescape(r[2]);
	keyword = unescape(keyword);
	$.ajax({
		type : 'get',
		url : '/BookSystem/bookser',
		data : $('#selectpageform').serialize() + "&type=" + type + "&keyword="
				+ keyword,
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "您还没有登陆哦") {
				alert("您还没有登陆哦");
				location.replace("/BookSystem/html/login.html");
			} else if (data == "请不要输入非法字符") {
				alert(data);
				location.replace(document.referrer);
			} else if (data == "没有搜索到相关内容") {
				alert(data);
				location.replace(document.referrer);
			} else {
				$("#main_Show").html(data);
			}
		}
	});
};