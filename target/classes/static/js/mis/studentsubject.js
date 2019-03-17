function loadMisStudentSubjectList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misStudentSubjectListPageForm').serializeArray();
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
	$('#misStudentSubjectList').load (basePath + 'studentsubject_query', d, function () {
		pintuer();
	});
}

function loadMisStudentSubjectNextPage () {
	loadMisStudentSubjectList(1);
}

function loadMisStudentSubjectLastPage () {
	loadMisStudentSubjectList(-1);
}


function loadMisStudentSubjectSelectForm (id) {
	window.open (basePath + 'tostudentsubjectselect?id=' + id);
}

//
// 修改表单js方法
// 修改表单提交按钮的事件
function submitMisStudentSubjectSelect () {
	// [{name:'loginName',value:'cai5'}, {}, {}]
	$.post(basePath + 'select_subject', $('#misStudentSubjectSelectForm').serializeArray(), function(result) {
		if (result.success) {
			window.opener.loadMisStudentSubjectList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
};
