function loadMisTeacherSelectSubjectList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misTeacherSelectSubjectListPageForm').serializeArray();
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
	$('#misTeacherSelectSubjectList').load (basePath + 'teacherselectsubject_query', d, function () {
		pintuer();
	});
}

function loadMisTeacherSelectSubjectNextPage () {
	loadMisTeacherSelectSubjectList(1);
}

function loadMisTeacherSelectSubjectLastPage () {
	loadMisTeacherSelectSubjectList(-1);
}
function loadMisStudentScoreCreateForm (id) {
	window.open (basePath + 'tostudentscorecreate?id=' + id);
}
function submitMisStudentScoreCreate () {
	$.post(basePath + 'createstudentscore', $('#misStudentScoreCreateForm').serializeArray(), function(result) {
		if (result.success) {
		
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
}


