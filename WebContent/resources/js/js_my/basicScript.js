String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, 'gm'), s2);
}
function table_addRow(otable) {
	var len = otable.rows.length;
	var newRow = otable.insertRow(len);
	var refRow = otable.rows[len - 1];
	var newCol;
	var obj;
	newRow.className = refRow.className;
	for ( var i = 0; i < refRow.cells.length; i++) {
		newCol = newRow.insertCell(i);
		copyProp_f(newCol, refRow.cells[i]);
		var $newCol = $(newCol);
		$newCol.html(refRow.cells[i].innerHTML.replaceAll('\\[.*\\]', '['
				+ (len - 1) + ']'));
		obj = newCol.firstChild;
	}
	return newRow;
}
function copyProp_f(oCol, refCol) {
	oCol.align = refCol.align;
	oCol.style.width = refCol.style.width;
	oCol.style.display = refCol.style.display;
	oCol.colSpan = refCol.colSpan;
}
function delRow(obj) {
	var row = obj.parentNode.parentNode;
	var tb = row.parentNode;
	var rowIndex = row.rowIndex;
	if (rowIndex > 1) {
		tb.deleteRow(rowIndex);
	} else {
		$.messager.alert('提示', '当前行不可删除!', 'info');
	}
}
var insertNotice = function() {
	$('#addNotice').form('submit', {
		url : "Notice_addNoticeDo",
		onSubmit : function() {
			if ($('#noticeTitle').val() == '') {
				$.messager.alert('提示', '标题不可为空,请填写标题', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '发布成功', 'info');
			} else {
				$.messager.alert('提示', '发布失败', 'info');
			}
		}
	});
};
var updateNotice = function() {
	$('#updateNotice').form('submit', {
		url : "Notice_updateNoticeDo",
		onSubmit : function() {
			if ($('#noticeTitle').val() == '') {
				$.messager.alert('提示', '标题不可为空,请填写标题', 'info');
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
};
var delNotice = function(nt_id, p) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Notice_deleteNotice", {
				"nt_id" : nt_id
			}, function(data) {
				if (data == 1) {
					location.href = "Notice_noticeList?p=" + p;
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var addabc = function(cl_id) {
	$.messager.confirm('提示', '确认提交该班级成绩吗?', function(r) {
		if (r) {
			$('#abc').form('submit', {
				url : "Gra_addGradeDo",
				onSubmit : function() {
				},
				success : function(data) {
					if (data == 1) {
						$.messager.alert('提示', '提交成功', 'info');
						location.href = "Gra_addGE?p=109";
					} else {
						$.messager.alert('提示', '提交失败', 'info');
					}
				}
			});
		}
	});
};
var daoru = function() {
	$.messager.confirm('提示', '确认数据准确无误，要正式导入数据吗?', function(r) {
		if (r) {
			$.post("Att_copydata", {
			}, function(data) {
				if (data == 1) {
					$.messager.alert('提示', '导入成功', 'info');
				} else {
					$.messager.alert('提示', '导入失败', 'info');
				}
			});
		}
	});
};
var ulsubmit = function(type) {
	if(type=="mc")
		$('#mc').form('submit', {
			url : "Att_upload?t=mx",
			onSubmit : function() {
				if ($('#claid').val() == '') {
					$.messager.alert('提示', '请先选择班级', 'info');
					return false;
				}
			},
			success : function(data) {
				$.messager.alert('提示',data, 'info');
				if (data == 1) {
					$.messager.alert('提示', '上传成功', 'info');
				} else {
					$.messager.alert('提示', '上传失败', 'info');
				}
			}
	});
	if(type=="hz")
		$('#hz').form('submit', {
			url : "Att_upload?t=hz",
			onSubmit : function() {
				if ($('#claid1').val() == '') {
					$.messager.alert('提示', '请先选择班级', 'info');
					return false;
				}
			},
			success : function(data) {
				if (data == 1) {
					$.messager.alert('提示', '上传成功', 'info');
				} else {
					$.messager.alert('提示', '上传失败', 'info');
				}
			}
	});
};
var delStuatt = function(att_id, p) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Att_delPresonAttByStuid", {
				"stuid" : att_id
			}, function(data) {
				if (data == 1) {
					$.messager.alert('提示', '删除成功', 'info');
					location.href = "Att_attClassList?p=" + p;
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var delclassatt1 = function(att_id, p) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Att_deleteAllAtt", {
				"classid" : att_id
			}, function(data) {
				if (data == 1) {
					$.messager.alert('提示', '删除成功', 'info');
					location.href = "Att_attClassList?p=" + p;
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var addDept = function(parent) {
	var node = $('#deptTrees').tree('getSelected');
	var url = "Sym_dept?p=" + parent + "&o=c&d=" + node.id;
	location.href = url;
};
var addDeptDo = function(parent) {
	var sort = $('#sortID').val();
	$('#addDept').form('submit', {
		url : "Sym_addDept",
		onSubmit : function() {
			if ($('#deptName').val() == '') {
				$.messager.alert('提示', '必填项不可为空', 'info');
				return false;
			}
			if (isNaN(sort)) {
				$.messager.alert('提示', '排序必须为数字', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '添加成功', 'info');
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var updateDept = function(parent) {
	var node = $('#deptTrees').tree('getSelected');
	var url = "Sym_dept?p=" + parent + "&o=u&d=" + node.id;
	location.href = url;
};
var updateDept2 = function(dept_id, parent) {
	var url = "Sym_dept?p=" + parent + "&o=u&d=" + dept_id;
	location.href = url;
};
var updateDeptNameDo = function(dept_id, parent) {
	var sort = $('#sortID').val();
	$('#updateDept').form('submit', {
		url : "Sym_updateDept",
		onSubmit : function() {
			if ($('#deptName2').val() == '') {
				$.messager.alert('提示', '必填项不可为空', 'info');
				return false;
			}
			if (isNaN(sort)) {
				$.messager.alert('提示', '排序必须为数字', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				var url = "Sym_dept?o=r&d=" + dept_id + "&p=" + parent;
				location.href = url;
			} else {
				$.messager.alert('提示', '修改失败', 'info');
			}
		}
	});
};
var delDept = function(p) {
	var node = $('#deptTrees').tree('getSelected');
	var rootNode = $('#deptTrees').tree('getRoot');
	if (node.id == rootNode.id) {
		$.messager.alert('提示', '根节点不可撤销', 'info');
		return;
	} else {
		$.messager.confirm('提示', '确认撤销吗?', function(r) {
			if (r) {
				$.post("Sym_delDept", {
					"d" : node.id
				}, function(data) {
					if (data == 1) {
						var url = "Sym_dept?o=r&d=101&p=" + p;
						location.href = url;
					} else {
						$.messager.alert('提示', '撤销失败或者您没有权限', 'info');
					}
				});
			}
		});
	}
};
var updateUsersDo = function() {
	$('#updateUsers').form('submit', {
		onSubmit : function() {
			if ($('#userName').val() == '') {
				$.messager.alert('提示', '用户名不可为空', 'info');
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
};
var delUser = function(userId) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Sym_delUsers", {
				"user_id" : userId
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var addUserView = function(p) {
	var node = $('#deptTrees2').tree('getSelected');
	var url = "Sym_user?p=" + p + "&d=" + node.id + "&o=c";
	location.href = url;
};
var addUsersDo = function() {
	$('#addUsers').form(
			'submit',
			{
				onSubmit : function() {
					if ($('#userName').val() == ''
							|| $('#pass').val() == '') {
						$.messager.alert('提示', '必选项不可为空', 'info');
						return false;
					}
				},
				success : function(data) {
					if (data == 1) {
						$.messager.alert('提示', '添加成功', 'info');
					} else {
						$.messager.alert('提示', '添加失败', 'info');
					}
				}
			});
};
var addRole = function(p) {
	var node = $('#roleTree').tree('getSelected');
	var url = "Sym_role?p=" + p + "&o=c&role_id=" + node.id;
	location.href = url;
};
var addRoleDo = function() {
	$('#addRoleForm').form('submit', {
		url : "Sym_addRoleDo",
		onSubmit : function() {
			if ($('#roleName').val() == '' || $('#roleSort').val() == '') {
				$.messager.alert('提示', '必填项不可为空', 'info');
				return false;
			}
			if (isNaN($('#roleSort').val())) {
				$.messager.alert('提示', '排序需要为数字', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '添加成功', 'info');
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var updateRoleView = function(p) {
	var node = $('#roleTree').tree('getSelected');
	var url = "Sym_role?p=" + p + "&o=u&role_id=" + node.id;
	location.href = url;
};
var updateRoleView2 = function(p, role_id) {
	var url = "Sym_role?p=" + p + "&o=u&role_id=" + role_id;
	location.href = url;
};
var updateRole = function(p, role_id) {
	$('#updateRoleForm').form('submit', {
		url : "Sym_updateRole",
		onSubmit : function() {
			if ($('#roleName').val() == '' || $('#roleSort').val() == '') {
				$.messager.alert('提示', '必填项不可为空', 'info');
				return false;
			}
			if (isNaN($('#roleSort').val())) {
				$.messager.alert('提示', '排序需要为数字', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				var url = "Sym_role?p=" + p + "&o=r&role_id=" + role_id;
				location.href = url;
			} else {
				$.messager.alert('提示', '修改失败', 'info');
			}
		}
	});
};
var delRoleDO = function() {
	var node = $('#roleTree').tree('getSelected');
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Sym_delRoleDo", {
				"role_id" : node.id
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
// 权限编辑
var permissionUpdate = function(p) {
	var node = $('#roleTree').tree('getSelected');
	var url = "Sym_role?p=" + p + "&o=q&role_id=" + node.id;
	location.href = url;
};
// 权限编辑do
var permissionUpdateDo = function() {
	$('#roleEd').form('submit', {
		onSubmit : function() {
			// alert($('.role:checked').length);
			var str = '';
			$('.role:checked').each(function() {
				str += $(this).val() + ',';
			});
			var manage = $('#roleDept').combobox('getValues');
			$('#permission').val(str);
			$('#manageScope').val(manage);
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '修改成功', 'info');
			} else {
				$.messager.alert('提示', '修改失败', 'info');
			}
		}
	});
};
var insertStu = function() {
	$('#addStu').form('submit', {
		url : "Stu_addStuDo",
		onSubmit : function() {
			if ($('#userName').val() == '' || $('#phone').val() == '') {
				$.messager.alert('提示', '必选项不可为空', 'info');
				return false;
			}
			if ($("#staValue").val() == 1) {
				$("#snoV").val("");
			} else {
				var aa = $("#snoB").val() + $("#snoV").val()
				$("#snoV").val(aa);
			}
		},
		success : function(data) {
			if (data == 1) {
				location.href = "Stu_seeStu?p=102&phone="+$('#phone').val();
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var updateStu = function() {
	$('#updateStu').form('submit', {
		url : "Stu_upStuDo",
		onSubmit : function() {
			if ($('#userName').val() == '' || $('#phone').val() == '') {
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
};
var delStu = function(rId) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Stu_delStu", {
				"s_id" : rId
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};

var insertTea = function() {
	$('#addTea').form('submit', {
		url : "Tea_insertTea",
		onSubmit : function() {
			if ($('#tnoV').val() == '' || $('#userName').val() == '') {
				$.messager.alert('提示', '必选项不可为空', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '添加成功', 'info');
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var updateTea = function() {
	$('#updateTea').form('submit', {
		url : "Tea_updateTea",
		onSubmit : function() {
			if ($('#userName').val() == '' || $('#tnoV').val() == '') {
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
};
var delTea = function(t_id) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Tea_delTea", {
				"t_id" : t_id
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var insertCla = function() {
	$('#addCla').form('submit', {
		url : "Cla_insertCla",
		onSubmit : function() {
			if ($('#cNo').val() == '') {
				$.messager.alert('提示', '必选项不可为空', 'info');
				return false;
			}
			var claHVal = $("#claHour").val();
			var claFVal = $("#claFee").val();
			if (claHVal != "" || claFVal != "") {
				if (isNaN(claHVal) || isNaN(claFVal)) {
					$.messager.alert('提示', '请输入数字', 'info');
					return false;
				}
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '添加成功', 'info');
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var upCla = function() {
	$('#uCla').form('submit', {
		url : "Cla_updateCla",
		onSubmit : function() {
			if ($('#cNo').val() == '') {
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
};
var delCla = function(c_id) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Cla_delCla", {
				"c_id" : c_id
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else if(data==0){
					$.messager.alert('提示', '删除失败', 'info');
				}else
					$.messager.alert('提示', '删除失败,可能该班级存在学生，请在缴费管理删除该班级缴费信息后重试！', 'info');
			});
		}
	});
};
var addTxt = function() {
	$('#addtxt').form('submit', {
		url : "Tx_saveT",
		onSubmit : function() {
			if ($('#txName').val() == '' || $('#number').val() == '') {
				$.messager.alert('提示', '必选项不可为空', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '添加成功', 'info');
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var updateTxt = function() {
	$('#updateTx').form('submit', {
		url : "Tx_updateTx",
		onSubmit : function() {
			if ($('#txName').val() == '' || $('#number').val() == '') {
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
};
var delTx = function(t_id) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Tx_delTx", {
				"t_id" : t_id
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var addFee = function() {
	$('#addFee').form('submit', {
		url : "Fe_addFee",
		onSubmit : function() {
			if ($('#uname').val() == '' || $('#uphone').val() == ''||$('#time1').val() == ''||$('#time2').val() == '') {
				$.messager.alert('提示', '必选项不可为空', 'info');
				return false;
			}
			var m = $("#money").val();
			var t = $("#tFee").val();
			if (isNaN(m) || isNaN(t)) {
				$.messager.alert('提示', '金额必须是数字', 'info');
				return false;
			}
		},
		success : function(data) {
			if (data!=0) {
				location.href = "Fe_seeFee?p=105&f_id="+data;
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var delFee = function(f_id) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Fe_delF", {
				"f_id" : f_id
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var uFee = function() {
	$('#uFee').form('submit', {
		url : "Fe_upFee",
		onSubmit : function() {
			var M = $("#Money").val();
			var T = $("#Tfee").val();
			if (isNaN(M) || isNaN(T)) {
				$.messager.alert('提示', '金额必须是数字', 'info');
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
};

var shenheFee = function() {
	$('#uFee').form('submit', {
		url : "Fe_shenheFee",
		onSubmit : function() {
		},
		success : function(data) {
			if (data!=0) {
				$.messager.alert('提示', '审核成功', 'info');
			} else {
				$.messager.alert('提示', '审核失败', 'info');
			}
		}
	});
};

var addGrade = function(a) {
	$('#form'+a).form('submit', {
		url : "Gra_addGradeDo",
		onSubmit : function() {
			if ($('#inp'+a).val() == '') {
				return false;
			}
		},
		success : function(data) {
		}
	});
};
var uGra = function() {
	$('#uGra').form('submit', {
		url : "Gra_uGdo",
		onSubmit : function() {
			var M = $("#gr").val();
			if (isNaN(M)) {
				$.messager.alert('提示', '成绩必须是数字', 'info');
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
};

var uPro = function() {
	$('#uPro').form('submit', {
		url : "Sym_DuPro",
		onSubmit : function() {
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '修改成功', 'info');
			} else {
				$.messager.alert('提示', '修改失败', 'info');
			}
		}
	});
};
var delPro = function(pro_id) {
	$.messager.confirm('提示', '确认删除吗?', function(r) {
		if (r) {
			$.post("Sym_delPro", {
				"pro_id" : pro_id
			}, function(data) {
				if (data == 1) {
					location.reload();
				} else {
					$.messager.alert('提示', '删除失败', 'info');
				}
			});
		}
	});
};
var addPro = function() {
	$('#addPro').form('submit', {
		url : "Sym_aProDo",
		onSubmit : function() {
		},
		success : function(data) {
			if (data == 1) {
				$.messager.alert('提示', '添加成功', 'info');
			} else {
				$.messager.alert('提示', '添加失败', 'info');
			}
		}
	});
};
var sub = function(a,classid) {
	$.messager.prompt('提示', '请输入新成绩', function(r) {
		$('#inpq'+a).val(r);
		$('#aqw'+a).text(r);
		if (r!=null) {
			$('#abcq'+a).form('submit', {
				url : "Gra_uGra",
				onSubmit : function() {
				},
				success : function(data1) {
					if (data1 == 1) {
						$.messager.alert('提示', '提交成功', 'info');
					} else {
						$.messager.alert('提示', '提交失败', 'info');
					}
				}
			});
		}
	});
};
var uPassN = function(a) {
	$.messager.confirm('提示', '确认进行此操作吗?', function(r) {
		if (r) {
			$('#abcqwe'+a).form('submit', {
				url : "Gra_uGra",
				onSubmit : function() {
				},
				success : function(data1) {
					if (data1 == 1) {
						 $.messager.alert('系统提示', '保存成功', 'info', function(){
								location.reload();});
					} else {
						$.messager.alert('提示', '提交失败', 'info');
					}
				}
			});
		}
	});
};
var uPassY = function(a) {
	$.messager.confirm('提示', '确认进行此操作吗?', function(r) {
		if (r) {
			$('#abcqw'+a).form('submit', {
				url : "Gra_uGra",
				onSubmit : function() {
				},
				success : function(data1) {
					if (data1 == 1) {
						 $.messager.alert('系统提示', '保存成功', 'info', function(){
						location.reload();});
					} else {
						$.messager.alert('提示', '提交失败', 'info');
					}
				}
			});
		}
	});
};
