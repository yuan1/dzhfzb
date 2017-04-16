// 备份
function backDB(){
	$.post("Sym_backDB",function(data){
		if(data == 1){
			location.reload();
		}else {
			$.messager.alert('提示','备份失败','info');
		}
	});
}

//===============用户管理==========================
function updateUserMess() {
	$('#updateUserMess').form('submit', {
		onSubmit : function() {
			if ($('#username').val() == '') {
				$.messager.alert('提示', '必选项不可为空', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '修改成功', 'info');
			} else {
				$.messager.alert('提示', '修改失败', 'info');
			}
		}
	});
}

