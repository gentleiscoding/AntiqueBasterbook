$(function() {

	$("#searchsubmit").click(
			
			function() {
				var type = $("#xialakuang").val();
				var keyword1 = $("#keyword").val();
				if (keyword1 == ""&&type=="quanbu") {
					alert("请输入搜索关键词");
				} else {
					var keyword = escape(keyword1);
					location.replace("/BookSystem/html/search.html?type="
							+ type + "&keyword=" + keyword);
				}
			});
});