
function getUnitByGoodsCode(){
	
	var outGoodsCode = $('#outGoodsCodeSearch').val();
//	alert(outGoodsCode);
	$.ajax({
		type : "POST",
		url : "/getStorageUnitByGoodsCode",
		dataType : 'json',
//		contentType : "application/json",
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