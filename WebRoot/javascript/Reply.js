$(function(){
	//link,button

	var $span_reply = $('#second');
	
	//taolun
	
	$span_reply.click(function(){
		$('#Reply').css({
			visibility: 'visible',
		});//打开弹出层

		$('#bg').css({//背景变灰
    	height: $(document).height(),
    	width: $(document).width(),
    	position : 'absolute',
    	top : 0,
    	left : 0,
    	zIndex : 10, 
    	opacity : 0.5,
    	backgroundColor: 'gray'
    	});
	});

	$('#Reply button').click(function(){//提交后关闭弹出层
		$('#Reply').css({
			visibility: 'hidden',
		});

		$('#bg').css({
    	height:0,
    	width:0,
    	});
	});

	$('#Reply a').click(function(){//点击X后关闭弹出层
		$('#Reply').css({
			visibility: 'hidden',
		});

		$('#bg').css({
    	height:0,
    	width:0,
    	});
    	return false;
	});

	$('#Reply input').focus(function(){//清空初始值
		if ($(this).val()==this.defaultValue) {
			$(this).val("");
		};
	});

	$('#Reply input').blur(function(){//离开时若为空，恢复初始值
		if ($(this).val()=="") {
			$(this).val(this.defaultValue);
		};
	})
	
})// JavaScript Document