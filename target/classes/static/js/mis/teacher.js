function loadMisTeacherList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misTeacherListPageForm').serializeArray();
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
	$('#misTeacherList').load (basePath + 'teacher_query', d, function () {
		pintuer();
	});
}

function loadMisTeacherNextPage () {
	loadMisTeacherList(1);
}

function loadMisTeacherLastPage () {
	loadMisTeacherList(-1);
}


function loadMisTeacherUpdateForm (id) {
	window.open (basePath + 'toteacherupdate?id=' + id);
}

// 创建表单js方法
// 创建表单提交按钮的事件
function submitMisTeacherCreate() {
	$.post(basePath + 'createteacher', $('#misTeacherCreateForm').serializeArray(), function(result) {
		if (result.success) {
			//window.opener.loadMisTeacherList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
}

// 修改表单js方法
// 修改表单提交按钮的事件
function submitMisTeacherUpdate () {
	// [{name:'loginName',value:'cai5'}, {}, {}]
	$.post(basePath + 'teacherupdate', $('#misTeacherUpdateForm').serializeArray(), function(result) {
		if (result.success) {
			//window.opener.loadMisUserList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
};

function deleteMisTeacher () {
	var t = $('#misTeacherListPageForm').serializeArray();
	if (t.length > 2 && confirm ('确定删除选择的数据吗？')) {
		$.post(basePath + 'teacherdelete', t, function(result) {
	        if (result.success) {
	            alert(result.message);
	            loadMisTeacherList(0);
	        } else {
	            alert(result.message);
	        }
	    });
	}
}
