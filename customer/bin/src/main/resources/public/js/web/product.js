
$(function() {
	
	init_product();
	init_hot_new();
});


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
						
						
		                       
		                        
		                        
		                    
		               
						
						productHtml +='<a href="/item/product?id='+list[i].id+'">  <div class="pros-list"> <img src="'+list[i].baseUrl+list[i].productImg+'" alt=""><div>'+list[i].name+'</div><p>'+list[i].productDesc+'</p></div> </a>'
						
						
						
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
