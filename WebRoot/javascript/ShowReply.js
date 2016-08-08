$(function() {
	// link,button

	var $button_huifu = $('#button_rep');

	$button_huifu.click(function() {
		$('#fabuhuitie').css({
			visibility : 'visible',
		});// 打开弹出层

		$('#bg').css({// 背景变灰
			height : $(document).height(),
			width : $(document).width(),
			position : 'absolute',
			top : 0,
			left : 0,
			zIndex : 10,
			opacity : 0.5,
			backgroundColor : 'gray'
		});
	});

	$('#fabuhuitie button').click(function() {// 提交后关闭弹出层
		$('#fabuhuitie').css({
			visibility : 'hidden',
		});

		$('#bg').css({
			height : 0,
			width : 0,
		});
	});

	$('#fabuhuitie a').click(function() {// 点击X后关闭弹出层
		$('#fabuhuitie').css({
			visibility : 'hidden',
		});

		$('#bg').css({
			height : 0,
			width : 0,
		});
		return false;
	});

	$('#fabuhuitie input').focus(function() {// 清空初始值
		if ($(this).val() == this.defaultValue) {
			$(this).val("");
		}
		;
	});

	$('#fabuhuitie input').blur(function() {// 离开时若为空，恢复初始值
		if ($(this).val() == "") {
			$(this).val(this.defaultValue);
		}
		;
	})

})// JavaScript Document
