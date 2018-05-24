function loadData(type, pageSize, currentPage) {
	$.ajax({
		url : "/web/info/list?type=" + type + "&pageSize=" + pageSize
				+ "&currentPage=" + currentPage,
		type : 'get',
		cache : false,
		contentType : false,
		processData : false,
		timeout : 2000,
		success : function(data, status) {
			console.log(data);
			//alert(data.result.totalCount);
			$("#PageCount").val(data.result.totalCount);
			createNewsList(data);
			loadpage(currentPage);
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});

}

$(function() {
	//初始化页面

	loadData(1, 10, 1);

});

function exeData(num, type) {
	loadData(1, 10, num);

}

function createNewsList(data) {

	if (data != null) {
        
		var items = data.result.items;
		var html = "";
		$("#news_lists").html("");
		for (var i = 0; i < items.length; i++) {

			html += '<a href="news.html">';
			html += '<div class="new-list">';
			html += '<div class="fl">';
			if(items[i].newsMainImg==null||items[i].newsMainImg==''){
				html += ' <img class="list-img" src="/images/new3.jpg" alt=""/>'
			}else{
				html += ' <img class="list-img" src="'+items[i].baseUrl+items[i].newsMainImg+'" alt=""/>'
			}
		
			html += ' </div>';
			html += '<div class="list-title">';
			html += ' <div class="list-title-t">'+items[i].title+'</div>';
			html += '<p class="list-title-p">'+items[i].declareContext+'</p>';
			html += '<div class="see new-see-list">';
			html += '<img src="/images/icon_view.png" alt=""/>';
			html += ' <span class="see-tu">'+items[i].viewTimes+' &nbsp;'+items[i].createTime+'</span>';
			html += '</div>';
			html += '</div>'
			html += '</div></a>';

		}
		
		$("#news_lists").html(html);

	}

}