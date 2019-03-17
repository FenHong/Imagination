function loadMisStudentList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misStudentListPageForm').serializeArray();
	// 将上述表单中的key-value转化为json对象
	var d = {};
	$.each(t, function(i, field) {
		if (d[field.name]) {
			d[field.name] = d[field.name] + "," + field.value;
		} else {
			d[field.name] = field.value;
		}
	});
	if (page != 0) {
		d.page = parseInt (d.page) + page;
	}
	$('#misStudentList').load (basePath + 'student_query', d, function () {
		pintuer();
	});
}

function loadMisStudentNextPage () {
	loadMisStudentList(1);
}

function loadMisStudentLastPage () {
	loadMisStudentList(-1);
}


function loadMisStudentUpdateForm (id) {
	window.open (basePath + 'tostudentupdate?id=' + id);
}

// 创建表单js方法
// 创建表单提交按钮的事件
function submitMisStudentCreate () {
	$.post(basePath + 'createstudent', $('#misStudentCreateForm').serializeArray(), function(result) {
		if (result.success) {
			//window.opener.loadMisStudentList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
}
//
// 修改表单js方法
// 修改表单提交按钮的事件
function submitMisStudentUpdate () {
	// [{name:'loginName',value:'cai5'}, {}, {}]
	$.post(basePath + 'studentupdate', $('#misStudentUpdateForm').serializeArray(), function(result) {
		if (result.success) {
			window.opener.loadMisStudentList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
};

function deleteMisStudent () {
	var t = $('#misStudentListPageForm').serializeArray();
	console.log(t);
	if (t.length > 2 && confirm ('确定删除选择的数据吗？')) {
		$.post(basePath + 'studentdelete', t, function(result) {
	        if (result.success) {
	            alert(result.message);
	            loadMisStudentList(0);
	        } else {
	            alert(result.message);
	        }
	    });
	}
}
