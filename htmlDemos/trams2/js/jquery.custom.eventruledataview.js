var options;
(function ($){
	var defaults = {
		data:[],				//数据 json
	};
	$.fn.eventrulelist = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON

		var itemDiv="<div ><div class='normaltwolist-content-bucket-block'><div class='b-b-info-list' >";
		
		var listpo=dataObj.listpo;
		//console.log(listpo);
		
		//设置标题
		itemDiv=itemDiv+"<div class='b-b-info-item' id='title'>" +
				"<div class='b-b-info-label' style='width:10%'><span class='item-detail'><b>紧急程度&nbsp;</b></span></div>" +
				"<div class='b-b-info-label' style='width:25%'><span style='font-size: 12px;'><b>通知方式&nbsp;</b></span></div>" +
				"<div class='b-b-info-label' style='width:15%'><span style='font-size: 12px;'><b>假期处理&nbsp;</b></span></div>" +
				"<div class='b-b-info-label' style='width:12%'><span style='font-size: 12px;'><b>环节处理时限&nbsp;</b></span></div>" +
				"<div class='b-b-info-label' style='width:10%'><span style='font-size: 12px;'><b>工单处理时限&nbsp;</b></span></div>" +
				"<div class='b-b-info-label' style='width:20%'><span style='font-size: 12px;'><b>工作时间段处理&nbsp;</b></span></div>" +
				"</div>";
		$.each(listpo,function(i,n){
			itemDiv = itemDiv + "<div class='b-b-info-item' id='item"+n.levels+"'>";//一整行数据的div
			
			itemDiv = itemDiv + "<div class='b-b-info-label' style='width:10%;padding-top: 5px;'>"+
			"<span class='item-detail' style='width:80%' >&nbsp;"+n.name+"</span>"+"&nbsp;</div>";//紧急程度内容
			
			itemDiv = itemDiv + "<div class='b-b-info-label' style='width:25%;padding-top: 5px;'>";	//通知方式复选框
			if(n.noticeflag.indexOf('0')!=-1){
				itemDiv = itemDiv + "<input type='checkbox' name='"+n.levels+"mes' title='消息' value='0' checked >";
			}else{
				itemDiv = itemDiv + "<input type='checkbox' name='"+n.levels+"mes' title='消息' value='0' >";
			}
			if(n.noticeflag.indexOf('1')!=-1){
				itemDiv = itemDiv + "<input type='checkbox' name='"+n.levels+"sms' title='短信' value='1' checked >";
			}else{
				itemDiv = itemDiv + "<input type='checkbox' name='"+n.levels+"sms' title='短信' value='1' >";
			}
			if(n.noticeflag.indexOf('2')!=-1){
				itemDiv = itemDiv + "<input type='checkbox' name='"+n.levels+"email' title='邮件' value='2' checked >"+"&nbsp;</div>";
			}else{
				itemDiv = itemDiv + "<input type='checkbox' name='"+n.levels+"email' title='邮件' value='2' >"+"&nbsp;</div>";
			}
			
			itemDiv = itemDiv + "<div class='b-b-info-label' style='width:15%;padding-top: 4px;'> <div style='margin-left: 12%;'>";	//假期处理的单选框
			if(n.holidayflag=='1'){
				itemDiv = itemDiv + "<input type='radio'  name='"+n.levels+"holi' value='1' title='是' checked >"+
				"<input type='radio'  name='"+n.levels+"holi' value='0' title='否' >"+"&nbsp;</div>";
			}else{
				itemDiv = itemDiv + "<input type='radio'  name='"+n.levels+"holi' value='1' title='是'>"+
				"<input type='radio'  name='"+n.levels+"holi' value='0' title='否' checked >"+"&nbsp;</div>";
			}
			itemDiv=itemDiv+"</div>";
			
			itemDiv = itemDiv + "<div class='b-b-info-label' style='width:12%'>"+
			"<input style='width:40px;margin-top:5px;padding-left: 0px;text-align: center;display: block;margin-left: 37%;' lay-verify='required|num' " +
			"class='layui-input' type='text' name='"+n.levels+"roam' value='"+n.roamhour+"'/>"+"&nbsp;</div>";//环节处理时限
			
			itemDiv = itemDiv + "<div class='b-b-info-label' style='width:10%'>"+
			"<input style='width:40px;margin-top:5px;padding-left: 0px;text-align: center;display: block;margin-left: 34%;' lay-verify='required|num' " +
			"class='layui-input' type='text' name='"+n.levels+"exce' value='"+n.exceedhour+"'/>"+"&nbsp;</div>";//工单处理时限
			
			itemDiv = itemDiv + "<div class='b-b-info-label' style='width:20%;padding-top: 4px;'>  <div style='margin-left: 12%;'>";
			if(n.worktimeflag=='1'){
				itemDiv = itemDiv + "<input type='radio'  name='"+n.levels+"work' value='1' title='是' checked >"+
				"<input type='radio'  name='"+n.levels+"work' value='0' title='否' >"+"&nbsp;</div>";
			}else{
				itemDiv = itemDiv + "<input type='radio'  name='"+n.levels+"work' value='1' title='是'>"+
				"<input type='radio'  name='"+n.levels+"work' value='0' title='否' checked >"+"&nbsp;</div>";//工作时间段处理
			}
			itemDiv=itemDiv+"</div>";
			
			itemDiv = itemDiv + "<input type='hidden' name='"+n.levels+"id' value='"+n.id+"'/>";//主键
			itemDiv = itemDiv + "<input type='hidden' name='levels"+n.levels+"' value='"+n.levels+"'/>";//等级
			
			itemDiv = itemDiv + "</div>";//结束一行的div
		});
		itemDiv = itemDiv + "</div></div></div>";
		$(this).append(itemDiv);
		return this;
	};
})(jQuery);
