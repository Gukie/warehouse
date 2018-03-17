
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