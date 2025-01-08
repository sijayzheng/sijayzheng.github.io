/**
 * 右键表头
 * @param params
 */
var createGridHeaderContextMenu = function(e, field) {  
    e.preventDefault();  
    var grid = $(this);/* grid本身 */  
    var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */  
    var okCls = 'tree-checkbox1';// 选中  
    var emptyCls = 'tree-checkbox0';// 取消  
    if (!headerContextMenu) {  
        var tmenu = $('<div style="width:150px;"></div>').appendTo('body');  
        var fields = grid.datagrid('getColumnFields');  
        for (var i = 0; i < fields.length; i++) {  
            var fildOption = grid.datagrid('getColumnOption', fields[i]);  
            if (!fildOption.hidden) {  
                $('<div iconCls="' + okCls + '" field="' + fields[i] + '"/>')  
                        .html(fildOption.title).appendTo(tmenu);  
            } else {  
                $('<div iconCls="' + emptyCls + '" field="' + fields[i] + '"/>')  
                        .html(fildOption.title).appendTo(tmenu);  
            }  
        }  
        headerContextMenu = this.headerContextMenu = tmenu.menu({  
            onClick : function(item) {  
                var field = $(item.target).attr('field');  
                if (item.iconCls == okCls) {  
                    grid.datagrid('hideColumn', field);  
                    $(this).menu('setIcon', {  
                        target : item.target,  
                        iconCls : emptyCls  
                    });  
                } else {  
                    grid.datagrid('showColumn', field);  
                    $(this).menu('setIcon', {  
                        target : item.target,  
                        iconCls : okCls  
                    });  
                }  
            }  
        });  
    }  
    headerContextMenu.menu('show', {  
        left : e.pageX,  
        top : e.pageY  
    });  
};  
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;