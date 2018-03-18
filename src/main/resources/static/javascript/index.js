/**
 * 页面加载的时候，就做的事情
 * 1. 将可用的货位的数据获取到
 * 2. 将表单中的默认submit行为都禁用，否则会刷新整个页面，达不到ajax的局部刷新效果
 */
$(function() {
	loadEmptyStorageUnitList();
	$('#search-form').submit(function (event){
		//stop submit the form, we will post it manually. otherwise the whole page will be submitted and refreshed. 
		event.preventDefault();
		getUnitByGoodsCode();
	});
	$('#in-goods-form').submit(function(event){
		event.preventDefault();
		addGoods2Unit();
	});
	$('#unit-add-form').submit(function(event){
		event.preventDefault();
		addUnit();
	});
});


/**
 * refer: https://www.mkyong.com/spring-boot/spring-boot-ajax-example/
 * 获取存储货物为空的货位列表.
 * 
 * @returns
 */
function loadEmptyStorageUnitList() {
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

function addUnit(){
	var unit = {};
	unit.channel=$("#channel").val();
	unit.layer=$('#layer').val();
	unit.slot=$('#slot').val();
	
	$.ajax({
		type : "POST",
		url : "/addUnit",
		contentType : "application/json",
		dataType : 'json',
//		data:unit,
		data:JSON.stringify(unit),
		cache : false,
		timeout : 600000,
		success : function(result) {
			console.log(result);
			loadEmptyStorageUnitList();
		},
		error : function(err) {
			console.log(err)
		}
	})
}

function addGoods2Unit() {
	
	var storageUnit = {};
	storageUnit.id=$("#availableStorageUnitList").find("option:selected").val();
	storageUnit.goodsCode=$('#inGoodsCode').val();
	storageUnit.goodsNum=$('#inGoodsNum').val();
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/addGoods2Unit",
		dataType : 'json',
		data:JSON.stringify(storageUnit),
		cache : false,
		timeout : 600000,
		success : function(result) {
			console.log(result);
			loadEmptyStorageUnitList();
		},
		error : function(err) {
			console.log(err)
		}
	})
}


function getUnitByGoodsCode(){
	
	var outGoodsCode = $('#outGoodsCodeSearch').val();
//	alert(outGoodsCode);
	$.ajax({
		type : "POST",
		url : "/getStorageUnitByGoodsCode",
		dataType : 'json',
//		contentType : "application/text",
		data:{'goodsCode':outGoodsCode},
		cache : false,
		timeout : 600000,
		success : function(result) {
			console.log('success - getUnitByGoodsCode');
//			alert('success - getUnitByGoodsCode');
			console.log(result);
			updateOutCandidateUnitTable(result.data);
		},
		error : function(err) {
			console.log('err - getUnitByGoodsCode');
//			alert('err - getUnitByGoodsCode');
//			alert(err);
			console.log(err)
		}
	})
}

function updateOutCandidateUnitTable(data){
	var tableBody = $('#outCandidateUnitTable').find('tbody');
	tableBody.empty();
	$.each(data, function(index, item) {
		var tr = $('<tr id='+item.id+'>');
		var indexCol = $('<td>'+(index+1)+'</td>');
		var goodsNameCol = $('<td>'+item.goodsCode+'</td>');
		var goodsNumCol = $('<td>'+item.goodsNum+'</td>');
		var unitLocationCol = $('<td>'+item.name+'</td>');
		var gmtCreatedCol = $('<td>'+item.gmtModified+'</td>');
		var recycleBtnCol = $('<td>');
		var recycleBtn = $('<input>').attr('type','submit').attr('onclick','recycleUnit(this)').val('出库');
		
		recycleBtnCol.append(recycleBtn);
		tr.append(indexCol);
		tr.append(goodsNameCol);
		tr.append(goodsNumCol);
		tr.append(unitLocationCol);
		tr.append(gmtCreatedCol);
		tr.append(recycleBtnCol);
		tableBody.append(tr);
//		alert('finished append data to table');
	});
}

function recycleUnit(obj){
	var unitId = $(obj).parents('tr').attr('id');
	
	$.ajax({
		type : "POST",
//		contentType : "application/json",
		url : "/recycleUnit",
		dataType : 'json',
		data:{'storageUnitId':unitId},
		cache : false,
		timeout : 600000,
		success : function(result) {
			console.log(result);
			$(obj).parents('tr').remove();
		},
		error : function(err) {
			console.log('err - recycleUnit');
			alert('err - recycleUnit');
			console.log(err)
		}
	})
}