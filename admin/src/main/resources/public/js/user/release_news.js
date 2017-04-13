$(function() {
	$("#add_new_submit").click(function(){
		if($("#add_news_title").val()==""){
			$("#add_news_title").focus();
			return;
		}
		if($("#add_news_declare").val()==""){
			$("#add_news_declare").focus();
			return;
		}
		editor.sync();
		if($("#add_news_context").val()==""){
			alert("请输入内容信息");
			return;
		}

		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url : "/news/create",
			type : 'post',
			data : {
				"title" : $("#add_news_title").val(),
				"type" : $("#add_news_type").val(),
				"declareContext" : $("#add_news_declare").val(),
				"context" : $("#add_news_context").val()
			},
			dataType : 'json',
			timeout : 1000,
			beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		    },
			success : function(data, status) {
				if (data.success) {
					
				}
			},
			fail : function(err, status) {
				alert("系统错误，请稍后");
			}
		});


		
	});
	
	
});

