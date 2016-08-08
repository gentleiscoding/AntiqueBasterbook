$("#downdrop")
		.blur(
				function() {
					var select = $("#downdrop").val();
					if (select == "kecheng") {
						$("#downdrop2")
								.html(
										"<option id=\"choose\" value=\" 无\">第二选择</option><option id=\"choose\" value=\" 四六级\">四六级</option><option id=\"choose\" value=\“xiaoyu\”>小语种</option><option id=\"choose\" value=\“kaoyan\”>考研用书</option><option id=\"choose\" value=\“kewai\”>课外读物</option><option id=\"choose\" value=\“biji\”>笔记资料</option>");
					} else if (select == "siliu") {
						$("#downdrop2")
								.html(
										"<option id=\"choose\" value=\" 无\">第二选择</option><option id=\"choose\" value=\“kecheng\”>课程用书</option><option id=\"choose\" value=\“xiaoyu\”>小语种</option><option id=\"choose\" value=\“kaoyan\”>考研用书</option><option id=\"choose\" value=\“kewai\”>课外读物</option><option id=\"choose\" value=\“biji\”>笔记资料</option>");
					} else if (select == "xiaoyu") {
						$("#downdrop2")
								.html(
										"<option id=\"choose\" value=\" 无\">第二选择</option><option id=\"choose\" value=\“kecheng\”>课程用书</option><option id=\"choose\" value=\“siliu\”>四六级</option><option id=\"choose\" value=\“kaoyan\”>考研用书</option><option id=\"choose\" value=\“kewai\”>课外读物</option><option id=\"choose\" value=\“biji\”>笔记资料</option>");
					}
					if (select == "kaoyan") {
						$("#downdrop2")
								.html(
										"<option id=\"choose\" value=\" 无\">第二选择</option><option id=\"choose\" value=\“kecheng\”>课程用书</option><option id=\"choose\" value=\“siliu\”>四六级</option><option id=\"choose\" value=\“xiaoyu\”>小语种</option><option id=\"choose\" value=\“kewai\”>课外读物</option><option id=\"choose\" value=\“biji\”>笔记资料</option>");
					} else if (select == "kewai") {
						$("#downdrop2")
								.html(
										"<option id=\"choose\" value=\" 无\">第二选择</option><option id=\"choose\" value=\“kecheng\”>课程用书</option><option id=\"choose\" value=\“siliu\”>四六级</option><option id=\"choose\" value=\“xiaoyu\”>小语种</option><option id=\"choose\" value=\“kaoyan\”>考研用书</option><option id=\"choose\" value=\“biji\”>笔记资料</option>");
					} else if (select == "biji") {
						$("#downdrop2")
								.html(
										"<option id=\"choose\" value=\" 无\">第二选择</option><option id=\"choose\" value=\“kecheng\”>课程用书</option><option id=\"choose\" value=\“siliu\”>四六级</option><option id=\"choose\" value=\“xiaoyu\”>小语种</option><option id=\"choose\" value=\“kaoyan\”>考研用书</option><option id=\"choose\" value=\“kewai\”>课外读物</option>");
					} else if (select == "请选择分类") {
						alert("请至少选择一个分类");
					}
				});