# warehouse
warehouse management


## Attention
- 录入数据的时候，下拉框中只会展示10条数据，而且是 FIFO(按创建时间来的)
- 按照货物条形码搜索的时候，也只会展现最先创建的10条数据


## Tips

- Form 的submit会提交整个页面，如果需要实现Ajax的局部刷新，需要禁用form的默认行文
HTML code:
```
	<form id="unit-add-form">
			<div class="input-group">
				<label for="channel">通道:</label>
				<input id="channel" name="channel" type="text"/>
			</div>
			<div class="input-group">
				<label for="layer">货层:</label>
				<input id="layer" name="layer" type="text" />
			</div>
			<div class="input-group">
				 <label for="layer">货槽:</label>
				 <input	id="slot" name="slot" type="text"  /> 
			</div>
			<input type="submit" value="添加货位" class="btn btn-primary"/>
		</form>
```

JS code
```
$('#unit-add-form').submit(function(event){
		//stop submit the form, we will post it manually. otherwise the whole page will be submitted and refreshed.
		event.preventDefault(); 
		addUnit();
	});
```

- Ajax的实现
1. Controller中
	1.1. 使用 @RestController来使用 @ResponseBody，返回数据
	1.2. 使用 @RequestBody 接受参数

@RestController
public class IndexController {
	@Resource(name = "warehouseService")
	private WarehouseService warehouseService;

	@RequestMapping("/addUnit")
	public ResponseResult addUnit(@RequestBody StorageUnitDTO storageUnitDTO) {
		int added = warehouseService.add(storageUnitDTO);
		return ResponseResult.success(added);
	}
	
2. JS中，将数据转换成 JSON格式
```
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
		data:JSON.stringify(unit), // convert data to json format
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
```
