var type=parseInt(4*Math.random());



function init_hot_new(){
	
	if(type==null){
		alert("无效类型");
		return;
		
	}
	
	$.ajax({
		url : "/web/info/list?type="+type+"&pageSize=5&currentPage=1",
		type : 'get',
		cache : false,
		contentType : false,
		processData : false,
		timeout : 2000,
		success : function(data, status) {
			if (data.success) {
				$("#hot_news_ul").html("");
				var hotNewsHtml="";
				var list = data.result.items;
				if(list.length > 0){
					for(var i=0;i<list.length;i++){
						hotNewsHtml+= ' <li><a href="/item/page?type='+type+'&id='+list[i].id+'" style="text-decoration: none"><b></b><p>'+list[i].title+'</p></a></li>'
					}
					
				}
				$("#hot_news_ul").html(hotNewsHtml)
			}
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});
}
