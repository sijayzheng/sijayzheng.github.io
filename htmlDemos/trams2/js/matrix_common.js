$.ajaxSetup({
	cache:false,
	error: function (xhr, status, e) {
		if(xhr.status==401){
			window.top.location.href='/TRAMS/';
		}else{
			//swal("系统消息","后台服务出现错误!",'error');
			//$.messager.alert("系统提示","","error");
		}		
	}
});
function loadFilter(data){
	if(data.message == "treegrid"){
		return data.data.tree;
	}
	if(data.status=="success"){		
		return data.data;
	}else{
		swal("系统消息",data.message,'error');
		//$.messager.alert("系统提示",data.message,"error");
		return {"total":0,"rows":[]};
	}
}

function dataGridFilter(data){
	if(data.status == "success")
		return data.data;
	else{
		swal("系统消息",data.message,'error');
		return {"total":0,"rows":[]};
	}
}


function treeLoadFilter(data){
   if(data.status){
		if(data.status == "success"){
		    if(data.data.tree==null){
		    return [];
		    }else{
			return data.data.tree;
			}
		}else if(data.status="error"){
			swal("系统消息",data.message,'error');
			return [];
		}
	}else{ return data;}
}
//下拉框加载数据
		function treeLoadFilter1(data){
			if(data.treedata) return data.treedata;
			else return data;
		}
function comboLoadFilter(data){
	if(data.status == "success"){
	    if(data.data.combo==null){
	    return [];
	    }else{
		return data.data.combo;
		}
	}else{
		swal("系统消息",data.message,'error');
		return [];
	}
}
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.combogrid('grid').datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var alertStr = "";
    for (j = ColArray.length - 1; j >= 0; j--) {
        PerTxt = "";
        tmpA = 1;
        tmpB = 0;

       for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
            }
            else {
                CurTxt = tTable.combogrid('grid').datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt == CurTxt) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;
                
                tTable.combogrid('grid').datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],
                    rowspan: tmpA,
                    colspan: null
                });
                tTable.combogrid('grid').datagrid("mergeCells", { //根据ColArray[j]进行合并
                    index: i - tmpA,
                    field: "Ideparture",
                    rowspan: tmpA,
                    colspan: null
                });
               
                tmpA = 1;
            }
            PerTxt = CurTxt;
        }
    }
}