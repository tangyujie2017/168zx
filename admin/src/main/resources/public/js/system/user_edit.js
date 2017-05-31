$(function() {
	formValidate("editUserForm",{
		roles: {
			required: true
		},
		mobile: {
			required: true,
			mobile: true,
			remote:{
				url:'/user/check/mobile',
				data:{
					'id':function() {
						var id = $('#id').val();
						if(!isnull(id)){
							id = Number(id);
						}else{
							id = 0;
						}
						return id;
					}
				}
			}
		},
		realName: {
			required: true,
			maxlength: 10,
			minlength: 2
		}
	},
	{
		mobile : {
			remote : "该电话已存在"
		}
	});
});
function submitForm(){
	if($("#editUserForm").remoteCount()>0){
		alertInfo("正在远程校验中...");
		return false;
	}
	if($("#editUserForm").valid()){
		var url = $("#editUserForm").attr("action");
		var userUrl = url.substring(0,url.lastIndexOf("/")+1);
		mask();
		$("#editUserForm").ajaxSubmit(function(data){
			unmask();
			if(data.success){
				siMenu('user_','用户管理',userUrl);
				top.mainFrame.tab.close('user_edit');
			}else{
				alertInfo(data.message);
			}
		});
	}
	
}