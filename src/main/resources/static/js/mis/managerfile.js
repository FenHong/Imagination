function loadMisManagerFileList (page) {
	// 获取表单中所有key-value，包含查询字段、列表id的字段、分页字段
	var t = $('#misManagerFileListPageForm').serializeArray();
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
	$('#misManagerFileList').load (basePath + 'managerfile_query', d, function () {
		pintuer();
	});
}

function loadMisManagerFileNextPage () {
	loadMisManagerFileList(1);
}

function loadMisManagerFileLastPage () {
	loadMisManagerFileList(-1);
}
function loadMisManagerFileDownloadForm (id) {
	window.open (basePath + 'toManagerFileDownload?id=' + id);
}