function loadMisTeacherSubjectList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misTeacherSubjectListPageForm').serializeArray();
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
	$('#misTeacherSubjectList').load (basePath + 'teachersubject_query', d, function () {
		pintuer();
	});
}

function loadMisTeacherSubjectNextPage () {
	loadMisTeacherSubjectList(1);
}

function loadMisTeacherSubjectLastPage () {
	loadMisTeacherSubjectList(-1);
}


function loadMisTeacherSubjectUpdateForm (id) {
	window.open (basePath + 'toteachersubjectupdate?id=' + id);
}

// 创建表单js方法
// 创建表单提交按钮的事件
function submitMisTeacherSubjectCreate () {
	$.post(basePath + 'createteachersubject', $('#misTeacherSubjectCreateForm').serializeArray(), function(result) {
		if (result.success) {
			//window.opener.loadMisTeacherSubjectList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
}
//
// 修改表单js方法
// 修改表单提交按钮的事件
function submitMisTeacherSubjectUpdate () {
	// [{name:'loginName',value:'cai5'}, {}, {}]
	$.post(basePath + 'teachersubjectupdate', $('#misTeacherSubjectUpdateForm').serializeArray(), function(result) {
		if (result.success) {
			window.opener.loadMisTeacherSubjectList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
};

function deleteMisTeacherSubject () {
	var t = $('#misTeacherSubjectListPageForm').serializeArray();
	console.log(t);
	if (t.length > 0 && confirm ('确定删除数据吗？')) {
		$.post(basePath + 'teachersubjectdelete', t, function(result) {
	        if (result.success) {
	            alert(result.message);
	            loadMisTeacherSubjectList(0);
	        } else {
	            alert(result.message);
	        }
	    });
	}
}
function releaseMisTeacherSubject () {
	var t = $('#misTeacherSubjectListPageForm').serializeArray();
	console.log(t);
	if (t.length > 0 && confirm ('确定发布课题吗？')) {
		$.post(basePath + 'teachersubjectrelease', t, function(result) {
	        if (result.success) {
	            alert(result.message);
	            loadMisTeacherSubjectList(0);
	        } else {
	            alert(result.message);
	        }
	    });
	}
}
