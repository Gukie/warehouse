// 页面加载的时候，就做的事情
$(function() {
	loadEmptyStorageUnitList();
});

/**
 * refer: https://www.mkyong.com/spring-boot/spring-boot-ajax-example/
 * 获取存储货物为空的货位列表.
 * 
 * @returns
 */
function loadEmptyStorageUnitList() {
//	alert('starting to loadEmptyStorageUnitList');;
	$('#availableStorageUnitList').empty();
	$('#availableStorageUnitList').append('<option>请选择</option>');
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/getAvailableUnit",
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(result) {
			console.log(result);
			$.each(result.data, function(index, item) {
				var option = $('<option>').val(item.id).text(item.name);
				$('#availableStorageUnitList').append(option)
			});
		},
		error : function(err) {
			console.log(err)
		}
	})
}

