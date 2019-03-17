function loadMisStudentSelectSubjectList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misStudentSelectSubjectListPageForm').serializeArray();
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
	$('#misStudentSelectSubjectList').load (basePath + 'studentselectsubject_query', d, function () {
		pintuer();
	});
}

function loadMisStudentSelectSubjectNextPage () {
	loadMisStudentSelectSubjectList(1);
}

function loadMisStudentSelectSubjectLastPage () {
	loadMisStudentSelectSubjectList(-1);
}


function deleteMisStudentSelectSubject () {
	var t = $('#misStudentSelectSubjectListPageForm').serializeArray();
	console.log(t);
	if (t.length > 0 && confirm ('确定删除数据吗？')) {
		$.post(basePath + 'studentselectsubjectdelete', t, function(result) {
	        if (result.success) {
	            alert(result.message);
	            loadMisStudentSelectSubjectList(0);
	        } else {
	            alert(result.message);
	        }
	    });
	}
}
