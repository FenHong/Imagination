function loadMisAnnouncementList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misAnnouncementListPageForm').serializeArray();
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
	$('#misAnnouncementList').load (basePath + 'managerannouncement_query', d, function () {
		pintuer();
	});
}

function loadMisAnnouncementNextPage () {
	loadMisAnnouncementList(1);
}

function loadMisAnnouncementLastPage () {
	loadMisAnnouncementList(-1);
}


// 创建表单js方法
// 创建表单提交按钮的事件
function submitMisAnnouncementCreate () {
	$.post(basePath + 'createannouncement', $('#misAnnouncementCreateForm').serializeArray(), function(result) {
		if (result.success) {
			//window.opener.loadMisAnnouncementList(0);
			alert(result.message);
		} else {
			alert(result.message);
		}
	});
}
function loadMisAnnouncementForm(id) {
	window.open (basePath + 'toannouncementinformation?id=' + id);
}



