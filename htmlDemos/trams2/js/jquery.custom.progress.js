var options;
(function ($){
	var defaults = {
		title:null,				//上方的标题
		titleColor:'#999',
		titleFontSize:'13px',
		width:'100%',			//一共的宽度
		speed:1000,				//显示效果的时间（毫秒）
		hazard:80,				//预警值
		data:[],				//数据 json
		itemStyle:{
			fontSize:'12px',			//每条文字的大小
			bgColor:'#eeeeee',			//每条的背景颜色
			borderColor:'#eeeeee',		//边框线颜色
			normalBgColor:'#2AA515',	//正常 百分比颜色
			alarmBgColor:'#FB6037',	//报警百分比颜色
			waringBgColor:'#EFB336'		//预警值 百分比颜色
		}
	};
	
	$.fn.disk = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var total=0;
		$.each(dataObj.datalist,function (i,n){//统计总数
			total+=parseInt(n.values);
		});
		//alert(JSON.stringify($(this).find("<table>")));
		//$(this).find("<table>").remove();
		$(this).append("<table id='"+id+"' class='tb-disk-list' cellpadding='0' cellspacing='0' style='font-size:"+options.itemStyle.fontSize+";' width='"+defaults.width+"'></table>");//设置TABLE的长度
		
		var itemDiv="";
		var trs="<tr style='border-bottom:1px solid #eee'><td width='15%' height='40px' style='padding-left:3%'>文件名</td><td width='5%' height='40px' align='right'>类型</td><td width='10%' height='40px' align='right'>总大小[M]</td><td width='10%' height='40px' align='right'>已使用[M]</td><td width='10%' height='40px' align='right'>可使用[M]</td><td width='15%'>使用率(%)</td><td width='5%'></td><td width='20%' style='padding-left:25px'>挂载</td></tr>";
		$.each(dataObj.datalist,function (i,n){
		    var index=0;//当前个数，超过5个循环取
			var percentage = (n.values*1).toFixed(2);//取后两位百分比
			if(isNaN(percentage)){
				percentage=0;
			}
			var imgWidth = parseFloat(percentage);
				if(i>(options.itelTotal-1))
					index = i-(options.itelTotal-1);
				else{
					index = i;
					itemDiv="<div style='border:1px solid "+ options.itemStyle.borderColor+";background-color:"+options.itemStyle.bgColor+";font-size:"+options.itemStyle.fontSize+"'>";
					if(n.status=='3'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:20px;background-color:"+options.itemStyle.alarmBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.alarmBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.alarmBgColor+";'></div></div>";
					}else if(n.status=='2'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:20px;background-color:"+options.itemStyle.waringBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.waringBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.waringBgColor+";'></div></div>";
					}else if(n.status=='1'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:20px;background-color:"+options.itemStyle.normalBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.normalBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.normalBgColor+";'></div></div>";
					}
					itemDiv+="</div>";
					itemDiv+="</div>";
			
				}
			//"<tr></tr>"
			var tds="<td width='15%' height='40px' style='padding-left:3%'>"+n.name+"</td><td width='10%' height='40px' align='right'>"+n.n4+"</td><td width='10%' height='40px' align='right'>"+n.total+"</td><td width='10%' height='40px' align='right'>"+n.n1+"</td><td width='10%' height='40px' align='right'>"+n.n2+"</td><td width='15%' style='bgcolor:"+options.itemStyle.bgColor+";'>"+itemDiv+"</td><td width='5%'>"+percentage+"%</td><td width='20%' style='padding-left:25px'>"+n.n3+"</td>";
			if(i%1==0){
				trs+="<tr style='border-bottom:1px solid #eee'>"+tds;
			}else{
				trs+=tds+"</tr>";
			}
			
		});
		
		$("table",o).append(trs);
		$("div",o).each(function(i,n){
			if($(n).attr('divWidth'))
			{
				$(n).animate( { width: $(n).attr('divWidth')+'%'}, options.speed );
				$(n).removeAttr("divWidth");
			}
		});
		return this;
	};
	
	
	$.fn.loginindexdisk = function (id,options){
		
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		//alert(JSON.stringify($(this).find("<table>")));
		//$(this).find("<table>").remove();
		$(this).append("<table id='"+id+"' class='tb-disk-list' cellpadding='0' cellspacing='0' style='font-size:"+options.itemStyle.fontSize+";margin-top:5px' width='"+defaults.width+"'></table>");//设置TABLE的长度
		
		var itemDiv="";
		var trs="";
		$.each(dataObj.datalist,function (i,n){
		    var index=0;//当前个数，超过5个循环取
			var percentage = (n.perdisk*1).toFixed(1);//取后两位百分比
			if(isNaN(percentage)){
				percentage=0;
			}
			var imgWidth = parseFloat(percentage);
				if(i>(options.itelTotal-1))
					index = i-(options.itelTotal-1);
				else{
					index = i;
					itemDiv="<div style='border:1px solid "+ options.itemStyle.borderColor+";background-color:"+options.itemStyle.bgColor+";font-size:"+options.itemStyle.fontSize+"'>";
					if(n.status=='3'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:12px;background-color:"+options.itemStyle.alarmBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.alarmBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.alarmBgColor+";'></div></div>";
					}else if(n.status=='2'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:12px;background-color:"+options.itemStyle.waringBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.waringBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.waringBgColor+";'></div></div>";
					}else if(n.status=='1'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:12px;background-color:"+options.itemStyle.normalBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.normalBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.normalBgColor+";'></div></div>";
					}
					itemDiv+="</div>";
					itemDiv+="</div>";
			
				}
			//"<tr></tr>"
			var tds="<td width='30%' height='30px' style='padding-left:3%'>"+n.diskname+"</td><td width='60%' style='bgcolor:"+options.itemStyle.bgColor+";'>"+itemDiv+"</td><td width='5%'>&nbsp;&nbsp;"+percentage+"%</td>";
			if(i%1==0){
				trs+="<tr style='border-bottom:1px solid #2486BF'>"+tds;
			}else{
				trs+=tds+"</tr>";
			}
			
		});
		
		$("table",o).append(trs);
		$("div",o).each(function(i,n){
			if($(n).attr('divWidth'))
			{
				$(n).animate( { width: $(n).attr('divWidth')+'%'}, options.speed );
				$(n).removeAttr("divWidth");
			}
		});
		return this;
	};
	
	$.fn.serverstatusinfo = function (id,options){
		
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var itemDiv="";//设置TABLE的长度
		
		if(dataObj.redisstatus=="1"){
			itemDiv = itemDiv +"<span class='login-index-status-green'></span>";
		}else{
			itemDiv = itemDiv +"<span class='login-index-status-red'></span>";
		}
		itemDiv = itemDiv +"&nbsp;<span style='display:inline;'>缓存服务</span>&nbsp;&nbsp;";
		
		if(dataObj.matrixservicestatus=="1"){
			itemDiv = itemDiv +"<span class='login-index-status-green'></span>";
		}else{
			itemDiv = itemDiv +"<span class='login-index-status-red'></span>";
		}
		itemDiv = itemDiv +"&nbsp;<span style='display:inline;'>采集服务</span>&nbsp;&nbsp;";
		
		if(dataObj.diskstatus=="3"){
			itemDiv = itemDiv +"<span class='login-index-status-red'></span>";
		}else if(dataObj.diskstatus=="2"){
			itemDiv = itemDiv +"<span class='login-index-status-orange'></span>";
		}else{
			itemDiv = itemDiv +"<span class='login-index-status-green'></span>";
		}
		itemDiv = itemDiv +"&nbsp;<span style='display:inline;' onclick='showviewdisk()' id='disks'>磁盘信息</span>";
		
		$(this).append(itemDiv);
		
		return this;
	};
	
	
	$.fn.monitorlocaldisk = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var total=0;
		$.each(dataObj.datalist,function (i,n){//统计总数
			total+=parseInt(n.values);
		});
		//alert(JSON.stringify($(this).find("<table>")));
		//$(this).find("<table>").remove();
		$(this).append("<table id='"+id+"' class='tb-disk-list' cellpadding='0' cellspacing='0' style='font-size:"+options.itemStyle.fontSize+";' width='"+defaults.width+"'></table>");//设置TABLE的长度
		
		var itemDiv="";
		var trs="<tr style='border-bottom:1px solid #eee'><td width='15%' height='40px' style='padding-left:3%'>文件名</td><td width='15%' height='45px' align='right'>总大小</td><td width='15%' height='40px' align='right'>已使用</td><td width='15%' height='40px' align='right'>可使用</td><td width='20%'>使用率</td><td width='5%'></td><td width='20%' style='padding-left:45px'>磁盘类型</td></tr>";
		$.each(dataObj.datalist,function (i,n){
		    var index=0;//当前个数，超过5个循环取
			var percentage = (n.values*1).toFixed(0);//取后两位百分比
			if(isNaN(percentage)){
				percentage=0;
			}
			var imgWidth = parseFloat(percentage);
				if(i>(options.itelTotal-1))
					index = i-(options.itelTotal-1);
				else{
					index = i;
					itemDiv="<div style='border:1px solid "+ options.itemStyle.borderColor+";background-color:"+options.itemStyle.bgColor+";font-size:"+options.itemStyle.fontSize+"'>";
					if(n.status=='3'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:20px;background-color:"+options.itemStyle.alarmBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.alarmBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.alarmBgColor+";'></div></div>";
					}else if(n.status=='2'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:20px;background-color:"+options.itemStyle.waringBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.waringBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.waringBgColor+";'></div></div>";
					}else if(n.status=='1'){
						itemDiv+="<div divWidth='"+imgWidth+"' style='width:0%;height:20px;background-color:"+options.itemStyle.normalBgColor+";' class='poll_plan"+index+"' >";
						itemDiv+="<div class='plan_e' style='background-color:"+options.itemStyle.normalBgColor+";'><div class='plan_c'  style='background-color:"+options.itemStyle.normalBgColor+";'></div></div>";
					}
					itemDiv+="</div>";
					itemDiv+="</div>";
			
				}
			//"<tr></tr>"
			var tds="<td width='15%' height='45px' style='padding-left:3%'>"+n.name+"</td><td width='15%' height='45px' align='right'>"+n.total+"</td><td width='15%' height='45px' align='right'>"+n.n2+"</td><td width='15%' height='45px' align='right'>"+n.n1+"</td><td width='20%' style='bgcolor:"+options.itemStyle.bgColor+";'>"+itemDiv+"</td><td width='5%'>&nbsp;"+percentage+"%</td><td width='20%' style='padding-left:45px'>"+n.n3+"</td>";
			if(i%1==0){
				trs+="<tr style='border-bottom:1px solid #eee'>"+tds;
			}else{
				trs+=tds+"</tr>";
			}
			
		});
		
		$("table",o).append(trs);
		$("div",o).each(function(i,n){
			if($(n).attr('divWidth'))
			{
				$(n).animate( { width: $(n).attr('divWidth')+'%'}, options.speed );
				$(n).removeAttr("divWidth");
			}
		});
		return this;
	};
	
})(jQuery);