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

function addUnit(){
	var unit = {};
	unit.channel=$("#channel").val();
	unit.layer=$('#layer').val();
	unit.slot=$('#slot').val();
	
	$.ajax({
		type : "POST",
		url : "/addUnit",
		dataType : 'json',
		data:unit,
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
//		contentType : "application/json",
		url : "/addGoods2Unit",
		dataType : 'json',
		data:storageUnit,
		cache : false,
		timeout : 600000,
		success : function(result) {
//			alert('success - addGoods2Unit');
			console.log(result);
//			loadEmptyStorageUnitList();
		},
		error : function(err) {
//			alert('err - addGoods2Unit');
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
//		contentType : "application/json",
		data:{'goodsCode':outGoodsCode},
//		cache : false,
//		timeout : 600000,
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
//		var tr = $('<tr id='+item.id+'>');
//		var indexCol = $('<td>'+index+'</td>');
//		var goodsNameCol = $('<td>'+item.goodsCode+'</td>');
//		var goodsNumCol = $('<td>'+item.goodsNum+'</td>');
//		var unitLocationCol = $('<td>'+item.name+'</td>');
//		var gmtCreatedCol = $('<td>'+item.gmtModified+'</td>');
//		var recycleBtnCol = $('<td>');
//		var recycleBtn = $('<input>').attr('type','submit').attr('onclick','recycleUnit(this)').val('出库');
//		
//		recycleBtnCol.append(recycleBtn);
//		tr.append(indexCol);
//		tr.append(goodsNameCol);
//		tr.append(goodsNumCol);
//		tr.append(unitLocationCol);
//		tr.append(gmtCreatedCol);
//		tr.append(recycleBtnCol);
//		tableBody.append(tr);
//		alert('finished append data to table');
	});
}

function recycleUnit(obj){
	var unitId = $(obj).parents('tr').id();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/recycleUnit",
		dataType : 'json',
		data:{'storageUnitId':unitId},
		cache : false,
		timeout : 600000,
		success : function(result) {
			console.log(result);
		},
		error : function(err) {
			console.log('err - recycleUnit');
			alert('err - recycleUnit');
			console.log(err)
		}
	})
}