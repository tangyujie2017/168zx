var type=parseInt(4*Math.random());

$(function() {
	initSlider(type);
	init_hot_new(type);
	init_zqyw(1);
	init_agzj(2);
	init_agzj(4);
	init_product();
});



function initSlider(type){
	if(type==null){
		alert("无效类型");
		return;
		
	}
	$.ajax({
		url : "/web/slider/list?type="+type,
		type : 'get',
		cache : false,
		contentType : false,
		processData : false,
		timeout : 2000,
		success : function(data, status) {
			if (data.success) {
				$("#slider_ul").html("");
				var sliderHtml="";
				var list = data.result;
				if(list.length > 0){
					for(var i=0;i<list.length;i++){
						sliderHtml+= '<li><img src="'+list[i].baseimgUrl+list[i].imgPath+'" alt="1" style="width: 729px;height: 365px"/></li>'
					}
					
				}
				$("#slider_ul").html(sliderHtml)
			}
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});
}


function init_hot_new(type){
	
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
						hotNewsHtml+= ' <li><a href="hot.html" style="text-decoration: none"><b></b><p>'+list[i].title+'</p></a></li>'
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



function init_zqyw(type){
	
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
				$("#zqyw_ui").html("");
				var zqywHtml="";
				var list = data.result.items;
				if(list.length > 0){
					for(var i=0;i<list.length;i++){
						zqywHtml+= '<li class="ov"><a href="news.html"><div class="img fl"><img src="'+list[i].baseUrl+list[i].newsMainImg+'" alt=""/></div><div class="lists-title fl"><p class="lists-title-ti">'+list[i].title+'</p><div class="see-tu" style="margin-top: 10px"><img src="/images/icon_view.png" alt=""/><span class="see-tu">'+list[i].viewTimes+'</span></div></div></a></li>'
					}
					
				}
				$("#zqyw_ui").html(zqywHtml)
			}
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});
}


function init_agzj(type){
	
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
				$("#agzj_ul").html("");
				var agzjHtml="";
				var list = data.result.items;
				if(list.length > 0){
					for(var i=0;i<list.length;i++){
						agzjHtml+= '<li class="ov"><a href="news.html"><div class="img fl"><img src="'+list[i].baseUrl+list[i].newsMainImg+'" alt=""/></div><div class="lists-title fl"><p class="lists-title-ti">'+list[i].title+'</p><div class="see-tu" style="margin-top: 10px"><img src="/images/icon_view.png" alt=""/><span class="see-tu">'+list[i].viewTimes+'</span></div></div></a></li>'
					}
					
				}
				$("#agzj_ul").html(agzjHtml)
			}
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});
}





function init_hmc(type){
	
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
				$("#hmc_ul").html("");
				var hgclHtml="";
				var list = data.result.items;
				if(list.length > 0){
					for(var i=0;i<list.length;i++){
						hmcHtml+= '<li class="ov"><a href="news.html"><div class="img fl"><img src="'+list[i].baseUrl+list[i].newsMainImg+'" alt=""/></div><div class="lists-title fl"><p class="lists-title-ti">'+list[i].title+'</p><div class="see-tu" style="margin-top: 10px"><img src="/images/icon_view.png" alt=""/><span class="see-tu">'+list[i].viewTimes+'</span></div></div></a></li>'
					}
					
				}
				$("#hmc_ul").html(hmcHtml)
			}
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});
}



function init_product(){
	
	$.ajax({
		url : "/web/product/list?&pageSize=5&currentPage=1",
		type : 'get',
		cache : false,
		contentType : false,
		processData : false,
		timeout : 2000,
		success : function(data, status) {
			if (data.success) {
				$("#product_div").html("");
				var productHtml="";
				var list = data.result.items;
				if(list.length > 0){
					
					for(var i=0;i<list.length;i++){
						productHtml +=' <div class="pro-list"><a href="probasin.html" style="text-decoration: none"><img src="'+list[i].baseUrl+list[i].productImg+'" alt=""/><div class="pro-title">'+list[i].name+'</div><p class="p-de">'+list[i].productDesc+'</p></a></div>';
					}
					
				}
				$("#product_div").html(productHtml)
			}
		},
		fail : function(err, status) {
			alert("系统错误，请稍后");
		}
	});
}