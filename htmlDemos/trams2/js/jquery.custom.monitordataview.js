var options;
(function ($){
	var defaults = {
		useicon:'icon-dangqianjincheng',
		bottomborder:true,
		keyvaluetitle:null,				//上方的标题
		indexdashboardtype:'1',
		devicelistviewnames:'0',
		pagesort:false,
		sortdesc:true,
		sortcloum:'n0',
		clicks:false,
		cols:2,
		data:[],				//数据 json
		itemStyle:{
			fontSize:'13px',			//文字的大小
			titlefontColor:'#eeeeee',			//名称文字颜色
			valuefontColor:'#eeeeee',			//值文字颜色
			borderColor:'#cccccc',		//边框线颜色
			titleBgColor:'#26A0DA',	//名称背景颜色
			valueBgColor:'#F50000'		//值背景颜色
		},
		itemwidth:{}
	};
	
	$.fn.normalonelist_two = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<div id='"+id+"' class='normalonelist-center-center-content' ><div class='normalonelist-center-center-content-l'>";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			//alert(n.value2);
			if(i<dataObj.datalist.length-1){
				itemDiv = itemDiv + "<div class='normalonelist-center-center-content-list'>";
				itemDiv = itemDiv + "<lable class='lableleft'>"+n.name1+"</lable><span class='spanleft' >"+n.value1+"</span>";
				itemDiv = itemDiv + "<lable class='lableright'>"+n.name2+"</lable><span class='spanright' >"+n.value2+"</span>";
				itemDiv = itemDiv + "</div>";
			}else{
				//alert(n.name2==null);
				if(n.name2==null){
					n.name2="&nbsp;";
					n.value2="&nbsp;";
				}
				itemDiv = itemDiv + "<div class='normalonelist-center-center-content-list'>";
				itemDiv = itemDiv + "<lable class='lableleft' style='border-bottom:1px solid #E4E7EB;'>"+n.name1+"</lable><span style='border-bottom:1px solid #E4E7EB;' class='spanleft' >"+n.value1+"</span>";
				itemDiv = itemDiv + "<lable class='lableright' style='border-bottom:1px solid #E4E7EB;'>"+n.name2+"</lable><span style='border-bottom:1px solid #E4E7EB;' class='spanright' >"+n.value2+"</span>";
				itemDiv = itemDiv + "</div>";
			}
			
		});
		itemDiv = itemDiv + "</div></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.normalonelist_one = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<div id='"+id+"' class='normalonelist-center-center-content' ><div class='normalonelist-center-center-content-l'>";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			if(i<dataObj.datalist.length-1){
				itemDiv = itemDiv + "<div class='normalonelist-center-center-content-list'>";
				itemDiv = itemDiv + "<lable class='lableleftonly'>"+n.name1+"</lable><span class='spanleftonly'>"+n.value1+"</span>";
				itemDiv = itemDiv + "</div>";
			}else{
				itemDiv = itemDiv + "<div class='normalonelist-center-center-content-list'>";
				itemDiv = itemDiv + "<lable class='lableleftonly' style='border-bottom:1px solid #E4E7EB;'>"+n.name1+"</lable><span style='border-bottom:1px solid #E4E7EB;' class='spanleftonly'>"+n.value1+"</span>";
				itemDiv = itemDiv + "</div>";
			}
			
		});
		itemDiv = itemDiv + "</div></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.normaloneicon = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<div id='"+id+"' style='margin:5px 5px'>";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			if(dataObj.datalist.length>10){
				itemDiv = itemDiv + "<div class='normaloneicon-jincheng-li-"+n.status+"'>";
				itemDiv = itemDiv + "<p><i class='iconfont "+options.useicon+"'></i><p>";
				itemDiv = itemDiv + "<p style='margin-left:-8px'>"+n.name+"<p>";
				itemDiv = itemDiv + "</div>";
			}else{
				itemDiv = itemDiv + "<div class='normaloneicon-jincheng-li-"+n.status+"'>";
				itemDiv = itemDiv + "<p><i class='iconfont "+options.useicon+"'></i><p>";
				itemDiv = itemDiv + "<p style=''>"+n.name+"<p>";
				itemDiv = itemDiv + "</div>";
			}
		});
		itemDiv = itemDiv + "</div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.normaltwolist = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<div class='normaltwolist-center-center-content'><div class='normaltwolist-content-bucket-block'><div class='b-b-info-list' >";//设置TABLE的长度
		//alert(dataObj.datalist.length);
		var jsonStudents =  dataObj.datalist;
		var datan = jsonStudents[0];
		if(options.pagesort){
			//alert(jsonStudents.length);
			jsonStudents = jsonStudents.slice(1);
			var cloums= options.sortcloum;
			if(cloums=="n0"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n0.substring(a.n0.indexOf(">")+1,a.n0.indexOf("&nbsp;")),
				        value2 = b.n0.substring(b.n0.indexOf(">")+1,b.n0.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n1"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n1.substring(a.n1.indexOf(">")+1,a.n1.indexOf("&nbsp;")),
				        value2 = b.n1.substring(b.n1.indexOf(">")+1,b.n1.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n2"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n2.substring(a.n2.indexOf(">")+1,a.n2.indexOf("&nbsp;")),
				        value2 = b.n2.substring(b.n2.indexOf(">")+1,b.n2.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n3"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n3.substring(a.n3.indexOf(">")+1,a.n3.indexOf("&nbsp;")),
				        value2 = b.n3.substring(b.n3.indexOf(">")+1,b.n3.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n4"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n4.substring(a.n4.indexOf(">")+1,a.n4.indexOf("&nbsp;")),
				        value2 = b.n4.substring(b.n4.indexOf(">")+1,b.n4.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n5"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n5.substring(a.n5.indexOf(">")+1,a.n5.indexOf("&nbsp;")),
				        value2 = b.n5.substring(b.n5.indexOf(">")+1,b.n5.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n6"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n6.substring(a.n6.indexOf(">")+1,a.n6.indexOf("&nbsp;")),
				        value2 = b.n6.substring(b.n6.indexOf(">")+1,b.n6.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n7"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n7.substring(a.n7.indexOf(">")+1,a.n7.indexOf("&nbsp;")),
				        value2 = b.n7.substring(b.n7.indexOf(">")+1,b.n7.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n8"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n8.substring(a.n8.indexOf(">")+1,a.n8.indexOf("&nbsp;")),
				        value2 = b.n8.substring(b.n8.indexOf(">")+1,b.n8.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n9"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n9.substring(a.n9.indexOf(">")+1,a.n9.indexOf("&nbsp;")),
				        value2 = b.n9.substring(b.n9.indexOf(">")+1,b.n9.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="n10"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n10.substring(a.n10.indexOf(">")+1,a.n10.indexOf("&nbsp;")),
				        value2 = b.n10.substring(b.n10.indexOf(">")+1,b.n10.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="value1"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n10.substring(a.n10.indexOf(">")+1,a.n10.indexOf("&nbsp;")),
				        value2 = b.n10.substring(b.n10.indexOf(">")+1,b.n10.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="value2"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n10.substring(a.n10.indexOf(">")+1,a.n10.indexOf("&nbsp;")),
				        value2 = b.n10.substring(b.n10.indexOf(">")+1,b.n10.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}else if(cloums=="values"){
				jsonStudents.sort(function(a,b){
				    var value1 = a.n10.substring(a.n10.indexOf(">")+1,a.n10.indexOf("&nbsp;")),
				        value2 = b.n10.substring(b.n10.indexOf(">")+1,b.n10.indexOf("&nbsp;"));
				    getValue(options, value1, value2);
				});
			}
			
			if(options.bottomborder){
				if(options.clicks){
					itemDiv = itemDiv + "<div class='b-b-info-item' onclick='lookdetails(\""+datan.id+"\")'>";
				}else{
					itemDiv = itemDiv + "<div class='b-b-info-item'>";
				}
				
			}else{
				if(options.clicks){
					itemDiv = itemDiv + "<div class='b-b-info-item-last' onclick='lookdetails(\""+datan.id+"\")'>";
				}else{
					itemDiv = itemDiv + "<div class='b-b-info-item-last'>";
				}
			}
			
			if(datan.n0!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn0+"'>"+datan.n0+"&nbsp;</div>";
			}
			//alert(options.itemwidth.wn0);
			if(datan.n1!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn1+"'>"+datan.n1+"&nbsp;</div>";
			}
			if(datan.n2!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn2+"'>"+datan.n2+"&nbsp;</div>";
			}
			if(datan.n3!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn3+"'>"+datan.n3+"&nbsp;</div>";
			}
			if(datan.n4!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn4+"'>"+datan.n4+"&nbsp;</div>";
			}
			if(datan.n5!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn5+"'>"+datan.n5+"&nbsp;</div>";
			}
			if(datan.n6!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn6+"'>"+datan.n6+"&nbsp;</div>";
			}
			if(datan.n7!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn7+"'>"+datan.n7+"&nbsp;</div>";
			}
			if(datan.n8!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn8+"'>"+datan.n8+"&nbsp;</div>";
			}
			if(datan.n9!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn9+"'>"+datan.n9+"&nbsp;</div>";
			}
			if(datan.n10!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn10+"'>"+datan.n10+"&nbsp;</div>";
			}
			itemDiv = itemDiv + "</div>";
		}//end of if(options.pagesort)
		
		$.each(jsonStudents,function (i,n){
			if(options.bottomborder){
				if(options.clicks){
					itemDiv = itemDiv + "<div class='b-b-info-item' id='item"+n.id+"' onclick='lookdetails(\""+n.id+"\")'>";
				}else{
					itemDiv = itemDiv + "<div class='b-b-info-item' id='item"+n.id+"'>";
				}
			}else{
				if(options.clicks){
					itemDiv = itemDiv + "<div class='b-b-info-item-last' id='item"+n.id+"' onclick='lookdetails(\""+n.id+"\")'>";
				}else{
					itemDiv = itemDiv + "<div class='b-b-info-item-last' id='item"+n.id+"'>";
				}
			}
			if(n.n0!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn0+"'>"+n.n0+"&nbsp;</div>";
			}
			//alert(options.itemwidth.wn0);
			if(n.n1!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn1+"'>"+n.n1+"&nbsp;</div>";
			}
			if(n.n2!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn2+"'>"+n.n2+"&nbsp;</div>";
			}
			if(n.value1!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn11+"'>"+n.value1+"&nbsp;</div>";
			}
			if(n.n3!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn3+"'>"+n.n3+"&nbsp;</div>";
			}
			if(n.n4!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn4+"'>"+n.n4+"&nbsp;</div>";
			}
			if(n.n5!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn5+"'>"+n.n5+"&nbsp;</div>";
			}
			if(n.value2!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn12+"'>"+n.value2+"&nbsp;</div>";
			}
			if(n.n6!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn6+"'>"+n.n6+"&nbsp;</div>";
			}
			if(n.n7!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn7+"'>"+n.n7+"&nbsp;</div>";
			}
			if(n.n8!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn8+"'>"+n.n8+"&nbsp;</div>";
			}
			if(n.values!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn13+"'>"+n.values+"&nbsp;</div>";
			}
			if(n.n9!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn9+"'>"+n.n9+"&nbsp;</div>";
			}
			if(n.n10!=null){
				itemDiv = itemDiv + "<div class='b-b-info-label' style='width:"+options.itemwidth.wn10+"'>"+n.n10+"&nbsp;</div>";
			}
			itemDiv = itemDiv + "</div>";
		});//end of $.each
		itemDiv = itemDiv + "</div></div></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	function getValue(options, value1, value2){
		if(options.sortdesc){
		    if(!isNumber(value1) && !isNumber(value2)){return value1.localeCompare(value2);};
		    if(isNumber(value1) && !isNumber(value2)){return 1;};
		    if(!isNumber(value1) && isNumber(value2)){return -1;};
		    if(isNumber(value1) && isNumber(value2)){return value2 - value1;}
	    }else{
	    	if(!isNumber(value1) && !isNumber(value2)){return value2.localeCompare(value1);};
		    if(isNumber(value1) && !isNumber(value2)){return 1;};
		    if(!isNumber(value1) && isNumber(value2)){return -1;};
		    if(isNumber(value1) && isNumber(value2)){return value1 - value2;}
	    }
	}

	
	$.fn.smallnormal = function (id,options){
		var basedataflag=false;
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<div class='monitorview-smallnormal'>";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			if(n.name=='系统信息'||n.name=='基本信息'){
				basedataflag=true;
				document.getElementById("basedata").src="monitordataview/basedata.jsp?itemid="+n.id;
			}else{
				if(n.status=="1"||n.status=="0"){
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-module-"+n.status+"'  onclick='opendetails(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.value1+"\")'>";
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-module-title-"+n.status+"' title='"+n.name+"'>"+n.name+"</div>";
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-module-content-"+n.status+"'>当前运行状态正常…</div>";
					itemDiv = itemDiv + "</div>";
				}else{
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-module-"+n.status+"' onclick='opendetails(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.value1+"\")'>";
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-module-title-"+n.status+"' title='"+n.name+"'>"+n.name+"</div>";
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-module-content-"+n.status+"'>"+n.content+"</div>";
					itemDiv = itemDiv + "</div>";
				}
			}
			
		});
		if(!basedataflag){
			$("#content-basedatas").css("display","none");
		}
		itemDiv = itemDiv + "</div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.smallnormal_m = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			if(n.name=='系统信息'||n.name=='基本信息'){
				document.getElementById("basedata").src="monitordataview/basedata.jsp?itemid="+n.id;
			}else{
				if(n.status=="1"||n.status=="0"){
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-suoxiao-"+n.status+"' onclick='opendetails(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.value1+"\")'>";
					itemDiv = itemDiv + n.name+"</div>";
				}else{
					itemDiv = itemDiv + "<div class='monitorview-smallnormal-suoxiao-"+n.status+"' onclick='opendetails(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.value1+"\")'>";
					itemDiv = itemDiv + n.name+"</div>";
				}
			}
			
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.bignormal = function (id,options){
		var basedataflag=false;
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv1="";
		$.each(dataObj.datalist,function (i,n){
			if(n.name!='系统信息'){
				itemDiv1 = itemDiv1 + "<div class='monitorview-center-center'>";
				itemDiv1 = itemDiv1 + "<div class='monitorview-center-top-title-"+n.status+"'><i class='iconfont "+n.titleicon+"'></i><label>"+n.name+"";
				if(n.values=='1'){
					itemDiv1 = itemDiv1 +"(正在处理中……处理结束时间："+n.value1+")</label>";
					itemDiv1 = itemDiv1 + "<span><a data-id='"+n.id+"' href='javascript:;' class='deal_to_care' onclick='openinspectdeal(\""+n.id+"\",\"2\")'>结束处理</a><a href='javascript:;' id='sctitle"+n.id+"' title='加入收藏' onclick='addfavourite(\""+n.id+"\",\""+n.name+"\")'><i id='"+n.id+"' class='iconfont icon-shoucang3'></i></a>" +
					"<a href='javascript:;' title='阈值设置' onclick='openformula(\""+n.id+"\",\""+n.name+"\")'><i class='iconfont icon-shezhi'></i></a><a href='javascript:;'  onclick='reflashdaras(\""+n.id+"\",\""+n.iframesrc+"\",\""+n.name+"\")' title='刷新'><i class='iconfont icon-shuaxin'></i></a><a href='javascript:;' onclick='opendetail(\""+n.id+"\",\""+n.name+"\")' title='更多详细'><i class='iconfont icon-chakan'></i></a></span></div>";
				}else{
					itemDiv1 = itemDiv1 + "</label><span><a data-id='"+n.id+"' href='javascript:;' class='deal_to_care' onclick='openinspectdeal(\""+n.id+"\",\"1\")'>处理</a><a href='javascript:;' id='sctitle"+n.id+"' title='加入收藏' onclick='addfavourite(\""+n.id+"\",\""+n.name+"\")'><i id='"+n.id+"' class='iconfont icon-shoucang3'></i></a>" +
					"<a href='javascript:;' title='阈值设置' onclick='openformula(\""+n.id+"\",\""+n.name+"\")'><i class='iconfont icon-shezhi'></i></a><a href='javascript:;'  onclick='reflashdaras(\""+n.id+"\",\""+n.iframesrc+"\",\""+n.name+"\")' title='刷新'><i class='iconfont icon-shuaxin'></i></a><a href='javascript:;' onclick='opendetail(\""+n.id+"\",\""+n.name+"\")' title='更多详细'><i class='iconfont icon-chakan'></i></a></span></div>";
				}
				
				if(n.name=='文件系统'){
					itemDiv1 = itemDiv1 + "<div class='monitorview-center-center-content'><iframe  id='file' name='file' width='100%' height='200px' src='monitordataview/"+n.iframesrc+"' frameborder='0' scrolling='yes' resizable='yes'></iframe></div>";
				}else if((n.name).indexOf('CPU')!=-1){
					itemDiv1 = itemDiv1 + "<div class='monitorview-center-center-content'><iframe  id='cpu' name='cpu' width='100%' height='300px' src='monitordataview/"+n.iframesrc+"' frameborder='0' scrolling='yes' resizable='yes'></iframe></div>";
				}else{
					itemDiv1 = itemDiv1 + "<div class='monitorview-center-center-content'><iframe  id='id"+n.id+"' name='name"+n.id+"' width='100%' height='200px' src='monitordataview/"+n.iframesrc+"' frameborder='0' scrolling='yes' resizable='yes'></iframe></div>";
				}
				itemDiv1 = itemDiv1 +"</div>"
			}else{
				basedataflag=true;
				document.getElementById("basedata").src="monitordataview/basedata.jsp?itemid="+n.id;
			}
		});
		if(!basedataflag){
			$("#content-basedatas").css("display","none");
		}
		$(this).append(itemDiv1);
		return this;
	};
	
	$.fn.dzbignormal = function (id,options){
		var basedataflag=false;
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";
		var itemDiv1="";
		var flag=false;
		var itemDiv2="<div class='monitorview-center-center'>" +
				"<div class='monitorview-center-top-title'>" +
				"<i class='iconfont icon-xitongyunzhuanqingkuang'></i>其他监控项</div>" +
				"<div class='monitorview-smallnormal'>";
		$.each(dataObj.datalist,function (i,n){
			//alert(n.values);
			if(n.name!='系统信息'){
				if(n.titleicon==null||n.titleicon==''){
					flag = true;
					if(n.status=="1"||n.status=="0"){
						itemDiv2 = itemDiv2 + "<div class='monitorview-smallnormal-module-"+n.status+"' onclick='opendetails(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.value1+"\")'>";
						itemDiv2 = itemDiv2 + "<div class='monitorview-smallnormal-module-title-"+n.status+"' title='"+n.name+"'>"+n.name+"</div>";
						itemDiv2 = itemDiv2 + "<div class='monitorview-smallnormal-module-content-"+n.status+"'>当前运行状态正常…</div>";
						itemDiv2 = itemDiv2 + "</div>";
					}else{
						itemDiv2 = itemDiv2 + "<div class='monitorview-smallnormal-module-"+n.status+"' onclick='opendetails(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.value1+"\")'>";
						itemDiv2 = itemDiv2 + "<div class='monitorview-smallnormal-module-title-"+n.status+"' title='"+n.name+"'>"+n.name+"</div>";
						itemDiv2 = itemDiv2 + "<div class='monitorview-smallnormal-module-content-"+n.status+"'>"+n.content+"</div>";
						itemDiv2 = itemDiv2 + "</div>";
					}
				}else{
					//alert(n.status);
					itemDiv1 = itemDiv1 + "<div class='monitorview-center-center'>";
					itemDiv1 = itemDiv1 + "<div class='monitorview-center-top-title-"+n.status+"'><i class='iconfont "+n.titleicon+"'></i><label>"+n.name+"";
					if(n.values=='1'){
						itemDiv1 = itemDiv1 +"(正在处理中……处理结束时间："+n.value1+")</label>";
						itemDiv1 = itemDiv1 + "<span><a data-id='"+n.id+"' href='javascript:;' class='deal_to_care' onclick='openinspectdeal(\""+n.id+"\",\"2\")'>结束处理</a><a href='javascript:;' id='sctitle"+n.id+"' title='加入收藏' onclick='addfavourite(\""+n.id+"\",\""+n.name+"\")'><i id='"+n.id+"' class='iconfont icon-shoucang3'></i></a>" +
						"<a href='javascript:;' title='阈值设置' onclick='openformula(\""+n.id+"\",\""+n.name+"\")'><i class='iconfont icon-shezhi'></i></a><a href='javascript:;'  onclick='reflashdaras(\""+n.id+"\",\""+n.iframesrc+"\",\""+n.name+"\")' title='刷新'><i class='iconfont icon-shuaxin'></i></a><a href='javascript:;' onclick='opendetail(\""+n.id+"\",\""+n.name+"\")' title='更多详细'><i class='iconfont icon-chakan'></i></a></span></div>";
					}else{
						itemDiv1 = itemDiv1 + "</label><span><a data-id='"+n.id+"' href='javascript:;' class='deal_to_care' onclick='openinspectdeal(\""+n.id+"\",\"1\")'>处理</a><a href='javascript:;' id='sctitle"+n.id+"' title='加入收藏' onclick='addfavourite(\""+n.id+"\",\""+n.name+"\")'><i id='"+n.id+"' class='iconfont icon-shoucang3'></i></a>" +
						"<a href='javascript:;' title='阈值设置' onclick='openformula(\""+n.id+"\",\""+n.name+"\")'><i class='iconfont icon-shezhi'></i></a><a href='javascript:;'  onclick='reflashdaras(\""+n.id+"\",\""+n.iframesrc+"\",\""+n.name+"\")' title='刷新'><i class='iconfont icon-shuaxin'></i></a><a href='javascript:;' onclick='opendetail(\""+n.id+"\",\""+n.name+"\")' title='更多详细'><i class='iconfont icon-chakan'></i></a></span></div>";
					}
					
					if((n.name1).indexOf('FILE')!=-1){
						itemDiv1 = itemDiv1 + "<div class='monitorview-center-center-content'><iframe  id='file' name='file' width='100%' height='200px' src='monitordataview/"+n.iframesrc+"' frameborder='0' scrolling='yes' resizable='yes'></iframe></div>";
					}else if((n.name1).indexOf('CMS')!=-1){
						itemDiv1 = itemDiv1 + "<div class='monitorview-center-center-content'><iframe  id='cpu' name='cpu' width='100%' height='300px' src='monitordataview/"+n.iframesrc+"' frameborder='0' scrolling='yes' resizable='yes'></iframe></div>";
					}else{
						itemDiv1 = itemDiv1 + "<div class='monitorview-center-center-content'><iframe  id='id"+n.id+"' name='name"+n.id+"' width='100%' height='300px' src='monitordataview/"+n.iframesrc+"' frameborder='0' scrolling='yes' resizable='yes'></iframe></div>";
					}
					itemDiv1 = itemDiv1 +"</div>"
				}
			}else{
				basedataflag=true;
				document.getElementById("basedata").src="monitordataview/basedata.jsp?itemid="+n.id;
			}
			
		});
		itemDiv2 = itemDiv2 + "<div class='clear'></div></div></div>";
		if(flag){
			itemDiv = itemDiv + itemDiv1 + itemDiv2;
		}else{
			itemDiv = itemDiv + itemDiv1;
		}
		if(!basedataflag){
			$("#content-basedatas").css("display","none");
		}
		$(this).append(itemDiv);
		return this;
	};
	$.fn.keyvaluelist = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<div class='keyvaluelist-block'>";//设置TABLE的长度
		//是否显示标题
		if(null != options.keyvaluetitle){
			itemDiv = itemDiv + "<div class='keyvaluelist-block-title'>" +
					"<span class='keyvaluelist-block-title-text' style='font-size: 14px'><i style='font-size: 16px' class='iconfont icon-zhuyi'></i>"+options.keyvaluetitle+"</span>" +
							"</div>";
		} 
		itemDiv = itemDiv + "<div class='keyvaluelist-info-list' >";
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='keyvaluelist-info-item' >";
			itemDiv = itemDiv + "<div class='keyvaluelist-info-label' style='width:"+options.itemwidth.wn0+"'>"+n.name+"</div>";
			if(n.values==null||n.values==''){
				itemDiv = itemDiv + "<div class='keyvaluelist-info-value1'>"+n.values+"</div>";
			}else{
				itemDiv = itemDiv + "<div class='keyvaluelist-info-value' style='width:"+options.itemwidth.wn1+"'>"+n.values+"</div>";
			}
			
			itemDiv = itemDiv + "</div>";
		});
		itemDiv = itemDiv + "</div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.groupdevice = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			var datalistobj = n;
			itemDiv = itemDiv + "<div class='monitorview-center-center'>" +
					"<div class='monitorview-center-top-title'><" +
					"i class='iconfont icon-items'></i>"+n.names+"</div>" +
					"<div style='height:auto;min-height: 100px;'>";
			
			$.each(datalistobj.polist,function (j,m){
				itemDiv = itemDiv + "<div class='normaloneicon-jincheng-li-"+m.status+"' onclick='opendetails(\""+m.id+"\",\""+m.name+"\")'>";
				itemDiv = itemDiv + "<p class='groupdevice-span-"+m.status+"'><i style='color:#fff;font-size: 32px;' class='iconfont icon-device-"+m.deviceicon+"'></i></p>";
				itemDiv = itemDiv + "<p style='margin-left:-4px;margin-top:8px;'>"+m.name+"<p><p style='margin-left:0px;margin-top:3px;'>"+m.ip+"<p>";
				itemDiv = itemDiv + "</div>";
				
			});
			itemDiv = itemDiv + "<div class='clear'></div> </div></div>";
		});
		
		$(this).append(itemDiv);
		return this;
	};
	$.fn.groupdevice2 = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			var datalistobj = n;
			itemDiv = itemDiv + "<div class='monitorview-center-center' style='border:0.2px solid #eeeeee'>" +
					"<div class='monitorview-center-top-title' style='font-size: 9px;'><" +
					"i style='font-size: 11px;' class='iconfont icon-items'></i>"+n.names+"</div>" +
					"<div style='height:auto;min-height: 100px;'>";
			
			$.each(datalistobj.polist,function (j,m){
				itemDiv = itemDiv + "<div class='normaloneicon-jincheng-list-"+m.status+"' onclick='opendetails(\""+m.id+"\",\""+m.name+"\")'>";
				itemDiv = itemDiv + "<p class='devicelist-span-"+m.status+"'><i style='color:#fff;font-size: 20px;' class='iconfont icon-device-"+m.deviceicon+"'></i></p>";
				itemDiv = itemDiv + "<p style='margin-left:0px;margin-top:8px;font-size:7px'>"+m.name+"<p><p style='margin-left:0px;margin-top:3px;font-size:7px'>"+m.ip+"<p>";
				itemDiv = itemDiv + "</div>";
				
			});
			itemDiv = itemDiv + "<div class='clear'></div> </div></div>";
		});
		
		$(this).append(itemDiv);
		return this;
	};
	$.fn.devicelist = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			var datalistobj = n;
			
			$.each(datalistobj.polist,function (j,m){
				itemDiv = itemDiv + "<div class='normaloneicon-jincheng-list-"+m.status+"' onclick='opendetails(\""+m.id+"\",\""+m.name+"\")'>";
				itemDiv = itemDiv + "<p class='devicelist-span-"+m.status+"'><i style='color:#fff;font-size: 20px;' class='iconfont icon-device-"+m.deviceicon+"'></i></p>";
				if(options.devicelistviewnames=='0'){
					itemDiv = itemDiv + "<p style='margin-left:0px;margin-top:8px;display:none;'>"+m.name+"<p>";
				}else{
					itemDiv = itemDiv + "<p style='margin-left:0px;margin-top:8px;font-size:8px'>"+m.name+"<p>";
				}
				itemDiv = itemDiv + "</div>";
				
			});
			
		});
		itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	
	$.fn.dashboard = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "";
		$.each(dataObj.datalist,function (i,n){
			//alert(n.status);
			
			if(n.value1=='1'){
				itemDiv = itemDiv + "<div name='dashboarditem' id='"+n.id+"' class='ant-col-lg-4 uy-zone-item' onmouseover='viewdealbutton(\""+n.id+"\")' onmouseout='viewicon(\""+n.id+"\")' >" +
				"<div class='uy-item-content monitor-status-x' >" +
				"<div class='uy-content-detail' title='"+n.name+"--"+n.values+"("+n.content+")' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.ip+"\",\""+n.itemdisplay+"\")'><div class='uy-detail-item' >" +
				"<span>"+n.name+"</span></div>" +
						"<div class='uy-detail-item1' >" +
				"<span title='"+n.content+"' >"+n.values+"</span>";
				itemDiv = itemDiv  +"<span class='uy-detail-isdealflag'><i class='iconfont icon-repairfill'></i></span></div>"; 
			}else{
				itemDiv = itemDiv + "<div name='dashboarditem' id='"+n.id+"' class='ant-col-lg-4 uy-zone-item' onmouseover='viewdealbutton(\""+n.id+"\")' onmouseout='viewicon(\""+n.id+"\")' >" +
				"<div class='uy-item-content monitor-status-"+n.status+"' >" +
				"<div class='uy-content-detail' title='"+n.name+"--"+n.values+"("+n.content+")' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.values+"\",\""+n.ip+"\",\""+n.itemdisplay+"\")'><div class='uy-detail-item' >" +
				"<span>"+n.name+"</span></div>" +
						"<div class='uy-detail-item1' >" +
				"<span title='"+n.content+"' >"+n.values+"</span>";
				itemDiv = itemDiv  +"</div>"; 
			}
			itemDiv = itemDiv + "<div class='uy-detail-item-type' ><span >"+n.times+"</span></div></div>";
			if(n.status==0||n.status==1){
				itemDiv = itemDiv + "<div class='uy-content-icon' id='icon"+n.id+"'><i class='iconfont icon-repairfill'></i></div>"+n.n4;
			}else if(n.status==2){
				itemDiv = itemDiv + "<div class='uy-content-icon' id='icon"+n.id+"'><i class='iconfont icon-warn'></i></div>"+n.n4;
			}else if(n.status==3){
				itemDiv = itemDiv + "<div class='uy-content-icon' id='icon"+n.id+"'><i class='iconfont icon-notice'></i></div>"+n.n4;
			}else if(n.status==4){
				itemDiv = itemDiv + "<div class='uy-content-icon' id='icon"+n.id+"'><i class='iconfont icon-xiangzhilusuoxudu'></i></div>"+n.n4;
			}else if(n.status==5){
				itemDiv = itemDiv + "<div class='uy-content-icon' id='icon"+n.id+"'><i class='iconfont icon-zhixingzhongduan'></i></div>"+n.n4;
			}
			itemDiv = itemDiv + "</div></div>";
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.timeline = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "<ul class='time-line'>";
		$.each(dataObj.datalist,function (i,n){
			if(i<dataObj.datalist.length-1){
				itemDiv = itemDiv + "<li class='time-line-item' style='opacity: 1; visibility: visible; transform: translateY(0px);'>" +
				"<div class='time-line-date'><p >"+n.times+"</p></div><div class='time-line-tail'></div>" +
				"<div class='time-line-head'><i class='iconfont icon-yun'></i></div>" +
				"<div class='time-line-content'><div class='critical status'><i class='status-icon'></i>";
				itemDiv = itemDiv + "<p class='status-url'><a class='res-name' href='#' >"+n.values+"</a>";
				itemDiv = itemDiv + "<span class='events-about-content'>"+n.content+"</span></p>";
				itemDiv = itemDiv + "</div></div></li>";
			}else{
				itemDiv = itemDiv + "<li class='time-line-item' style='opacity: 1; visibility: visible; transform: translateY(0px);'>" +
				"<div class='time-line-date'><p >"+n.times+"</p></div>" +
				"<div class='time-line-head'><i class='iconfont icon-yun'></i></div>" +
				"<div class='time-line-content'><div class='critical status'><i class='status-icon'></i>";
				itemDiv = itemDiv + "<p class='status-url'><a class='res-name' href='#' >"+n.values+"</a>";
				itemDiv = itemDiv + "<span class='events-about-content'>"+n.content+"</span></p>";
				itemDiv = itemDiv + "</div></div></li>";
			}
			
		});
		$(this).append(itemDiv);
		return this;
	};
		$.fn.timeline1 = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "<ul class='time-line'>";
		$.each(dataObj.datalist,function (i,n){
			if(i<dataObj.datalist.length-1){
				itemDiv = itemDiv + "<li class='time-line-item' style='opacity: 1; visibility: visible; transform: translateY(0px);'>" +
				"<div class='time-line-tail'></div>" +
				"<div class='time-line-head'><i class='iconfont icon-yun'></i></div>" +
				"<div class='time-line-content' style='padding-left:15px;'>"+
				"<div class='critical status'>"
				"<i class='status-icon'></i>";
				itemDiv = itemDiv + "<p class='status-url'><span class='events-about-content'>"+n.n0+"&nbsp;"+n.times+"</span><a class='res-name' href='#' >"+n.values+"</a>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:10px;width:100%;float:left;text-overflow:ellipsis;white-space: nowrap;overflow: hidden;' title='"+n.content+"'>"+n.content+"</div></p>";
				itemDiv = itemDiv + "</div></div></li>";
			}else{
				itemDiv = itemDiv + "<li class='time-line-item' style='opacity: 1; visibility: visible; transform: translateY(0px);'>" +
				"<div class='time-line-head'><i class='iconfont icon-yun'></i></div>" +
				"<div class='time-line-content' style='padding-left:15px;'><div class='critical status'><i class='status-icon'></i>";
				itemDiv = itemDiv + "<p class='status-url'><span class='events-about-content'>"+n.n0+"&nbsp;"+n.times+"</span><a class='res-name' href='#' >"+n.values+"</a>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:10px;width:100%;float:left;text-overflow:ellipsis;white-space: nowrap;overflow: hidden;' title='"+n.content+"'>"+n.content+"</div></p>";
				itemDiv = itemDiv + "</div></div></li>";
			}
			
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.timeline2 = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "<ul class='time-line'>";
		$.each(dataObj.datalist,function (i,n){
			if(i<dataObj.datalist.length-1){
				itemDiv = itemDiv + "<li class='time-line-item' style='opacity: 1; visibility: visible; transform: translateY(0px);'>" +
				"<div class='time-line-tail'></div>" +
				"<div class='time-line-head'><i class='iconfont icon-yun'></i></div>" +
				"<div class='time-line-content' style='padding-left:15px;'>"+
				"<div class='critical status'>"
				"<i class='status-icon'></i>";
				itemDiv = itemDiv + "<p class='status-url'><span class='events-about-content'>"+n.n0+"&nbsp;"+n.times+"</span><a class='res-name' href='#' >"+n.values+"</a>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:5px;width:100%;float:left;'>"+n.n2+"</div></p>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:5px;width:100%;float:left;text-overflow:ellipsis;white-space: nowrap;overflow: hidden;' title='"+n.n1+"'>"+n.n1+"</div></p>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:5px;width:100%;float:left;text-overflow:ellipsis;white-space: nowrap;overflow: hidden;' title='"+n.content+"'>"+n.content+"</div></p>";
				itemDiv = itemDiv + "</div></div></li>";
			}else{
				itemDiv = itemDiv + "<li class='time-line-item' style='opacity: 1; visibility: visible; transform: translateY(0px);'>" +
				"<div class='time-line-head'><i class='iconfont icon-yun'></i></div>" +
				"<div class='time-line-content' style='padding-left:15px;'><div class='critical status'><i class='status-icon'></i>";
				itemDiv = itemDiv + "<p class='status-url'><span class='events-about-content'>"+n.n0+"&nbsp;"+n.times+"</span><a class='res-name' href='#' >"+n.values+"</a>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:5px;width:100%;float:left;'>"+n.n2+"</div></p>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:5px;width:100%;float:left;text-overflow:ellipsis;white-space: nowrap;overflow: hidden;' title='"+n.n1+"'>"+n.n1+"</div></p>";
				itemDiv = itemDiv + "<div class='events-about-content' style='margin-top:5px;width:100%;float:left;text-overflow:ellipsis;white-space: nowrap;overflow: hidden;' title='"+n.content+"'>"+n.content+"</div></p>";
				itemDiv = itemDiv + "</div></div></li>";
			}
			
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.gaugestable = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "";
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='alarmdetail-center-center-content-lable'>" +
			"<div class='content-lable-title-"+n.status+"'><i class='iconfont icon-zhuyi'></i>"+n.name+"</div>" +
			"<div class='content-lable-c'><div class='content-lable-c-left'><div id='"+n.id+"' style='width:100%; height: 100%;padding-top:5px'></div></div>" +
			"<div class='content-lable-c-right'><div class='content-lable-c-right-c'>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-span'><span>"+n.n0+"</span></div>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-span'><span>"+n.n1+"</span></div>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-span'><span>"+n.n2+"</span></div>";
			itemDiv = itemDiv + "</div>";
			//itemDiv = itemDiv + "<div class='content-lable-c-right-c'><div class='content-lable-c-right-span1'><span>"+n.n3+"</span></div></div>";
			itemDiv = itemDiv + "</div></div></div>";
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.progressbar = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "";
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='alarmdetail-center-center-content-lable'>" +
			"<div class='content-lable-title-"+n.status+"'><i class='iconfont icon-zhuyi'></i>"+n.name+"</div>" +
			"<div class='content-lable-c'><div class='content-lable-c-left'><div id='"+n.id+"' style='width:100%; height: 100%;'></div></div>" +
			"<div class='content-lable-c-right'><div class='content-lable-c-right-c'>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-span'><span>"+n.n0+"</span></div>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-span'><span>"+n.n1+"</span></div>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-span'><span>"+n.n2+"</span></div>";
			itemDiv = itemDiv + "</div>";
			itemDiv = itemDiv + "<div class='content-lable-c-right-c'><div class='content-lable-c-right-span1'><span>"+n.n3+"</span></div></div>";
			itemDiv = itemDiv + "</div></div></div>";
		});
		$(this).append(itemDiv);
		return this;
	};
	
	
	$.fn.listli = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "<div class='monitorview-center-top-content'>";
		var w = 45/options.cols;
		var w1=(w-8);
		var w2=w+8;
		//alert(w1+"!!!!!!!!!!"+w2);
		$.each(dataObj.datalist,function (i,n){
			if((i+1)%options.cols==0){
				itemDiv = itemDiv + "<lable style='width:"+w1+"%'><i class='iconfont icon-info'></i>&nbsp;&nbsp;"+n.name+"：</lable><span title='"+n.values+"' class='easyui-tooltip' style='width:"+w2+"%'>"+n.values+"</span>";
				if(i==dataObj.datalist.length-1){
					itemDiv = itemDiv + "<div class='clearfloat'></div></div>";
				}else{
					itemDiv = itemDiv + "<div class='clearfloat'></div></div><div class='monitorview-center-top-content'>";
				}
			}else{
				itemDiv = itemDiv + "<lable style='width:"+w1+"%'><i class='iconfont icon-info'></i>&nbsp;&nbsp;"+n.name+"：</lable><span title='"+n.values+"' class='easyui-tooltip' style='width:"+w2+"%'>"+n.values+"</span>";
			}
		});
		//alert(itemDiv);
		$(this).append(itemDiv+"<div class='clearfloat'></div>");
		return this;
	};
	
	$.fn.indexdashboard = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='device-dashbord-1' onclick='viewdevicelist(\""+n.id+"\",\""+n.name+"\")'><div class='device-dashbord-top'>";
			itemDiv = itemDiv + "<span class='devicetypeimg'><i style='font-size: 38px;' class='iconfont icon-"+n.titleicon+"'></i></span>"
			itemDiv = itemDiv + "<span class='devicetypenum'>"+n.total+"<br>";
			itemDiv = itemDiv + "<span class='devicetypename'>"+n.name+"</span></span></div>";
			itemDiv = itemDiv + "<div class='device-dashbord-foot'>";
			itemDiv = itemDiv + "<div class='footimg-2'><i style='font-size: 15px;' class='iconfont icon-noticefill'></i>"+n.n2+"</div>";
			itemDiv = itemDiv + "<div class='footimg-3'><i style='font-size: 15px;' class='iconfont icon-noticefill'></i>"+n.n3+"</div>";
			itemDiv = itemDiv + "<div class='footimg-4'><i style='font-size: 15px;' class='iconfont icon-noticefill'></i>"+n.n4+"</div>";
			itemDiv = itemDiv + "</div></div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	$.fn.indexdashboard_buss = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			if(n.total>70){
				itemDiv = itemDiv + "<div class='animated bounceIn hvr-grow-shadow device-dashbord-2' onclick='viewdevicelist(\""+n.id+"\",\""+n.name+"\")'>";
				itemDiv = itemDiv + "<div class='device-dashbord-title'>"+n.name+"</div>";
				itemDiv = itemDiv + "<div class='device-dashbord-content'>";
				itemDiv = itemDiv + "<div class='device-dashbord-content-left'><i style='font-size: 38px;' class='iconfont icon-nice'></i></div>"
				itemDiv = itemDiv + "<p><span class='device-dashbord-content-score'>"+n.total+"</span><span>&nbsp;<font color='#f9f9f9'>分</font></span></p>";
				itemDiv = itemDiv + "</div></div>";
			}else{
				itemDiv = itemDiv + "<div class='animated bounceIn hvr-grow-shadow device-dashbord2-alarm' onclick='viewdevicelist(\""+n.id+"\",\""+n.name+"\")'>";
				itemDiv = itemDiv + "<div class='device-dashbord-title-alarm'>"+n.name+"</div>";
				itemDiv = itemDiv + "<div class='device-dashbord-content'>";
				itemDiv = itemDiv + "<div class='device-dashbord-content-left'><i style='font-size: 38px;' class='iconfont icon-badreview'></i></div>"
				itemDiv = itemDiv + "<p><span class='device-dashbord-content-score'>"+n.total+"</span><span>&nbsp;<font color='#f9f9f9'>分</font></span></p>";
				itemDiv = itemDiv + "</div></div>";
			}
			
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.mempermomtop = function (id,options){//CMS变化TOP5
		$(this).children().remove();
		options = $.extend(defaults,options);
		var datalist = options.data;//数据JSON
		//alert(datalist);
		var o = this;
		var itemDiv="";//
		$.each(datalist,function (i,n){
			itemDiv = itemDiv + "<div class='b-b-info-item' title='点击查看趋势分析' onclick='mpertrend(\""+n[0]+"\",\""+n[1]+"\")'>";
			itemDiv = itemDiv + "<div title='"+n[1]+"' class='b-b-info-label space'>"+n[1]+"</div>";
			if(parseFloat(n[3])>0.0){
				itemDiv = itemDiv + "<div class='b-b-info-value-middle'><span class='b-b-info-value-red'>"+n[2]+"%&nbsp;"+"比上月↑&nbsp;"+n[3]+"%</span></div>";
			}else{
				itemDiv = itemDiv + "<div class='b-b-info-value-middle'><span class='b-b-info-value-blue'>"+n[2]+"%&nbsp;"+"比上月↓&nbsp;"+n[3]+"%</span></div>";
			}
			itemDiv = itemDiv + "<div class='b-b-info-value'><span class='b-b-info-value-normal'>趋势<img alt='点击查看趋势分析' onclick='mpertrend(\""+n[0]+"\",\""+n[1]+"\")' src='/TRAMS/images/qushi.png' width='50px' height='40px'></span></div>"
			itemDiv = itemDiv + "</div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.diskfilemomtop = function (id,options){//文件系统变化TOP5
		$(this).children().remove();
		options = $.extend(defaults,options);
		var datalist = options.data;//数据JSON
		//alert(datalist);
		var o = this;
		var itemDiv="";//
		$.each(datalist,function (i,n){
			itemDiv = itemDiv + "<div class='b-b-info-item' title='点击查看趋势分析' onclick='diskpertrend(\""+n[0]+"\",\""+n[2]+"\",\""+n[1]+"\")'>";
			itemDiv = itemDiv + "<div title='"+n[1]+"("+n[2]+")' class='b-b-info-label space'>"+n[1]+"("+n[2]+")</div>";
			itemDiv = itemDiv + "<div class='b-b-info-value-middle'><span class='b-b-info-value-normal'><span class='b-b-info-value-normal'>昨日"+n[3]+"%"+"&nbsp;&nbsp;</span><div style='width: 55px;margin-top: 0px;' class='progress' id='"+i+"1FILEPER' title='"+n[3]+"'></div></span></div>";
			if(parseFloat(n[4])>0){
				itemDiv = itemDiv + "<div class='b-b-info-value'><span class='b-b-info-value-red'>"+"比上月↑&nbsp;"+n[4]+"%</span></div>";
			}else{
				itemDiv = itemDiv + "<div class='b-b-info-value'><span class='b-b-info-value-blue'>"+"比上月↓&nbsp;"+n[4]+"%</span></div>";
			}
			itemDiv = itemDiv + "</div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.tablespacemomtop = function (id,options){//表空间变化top5
		$(this).children().remove();
		options = $.extend(defaults,options);
		var datalist = options.data;//数据JSON
		//alert(datalist);
		var o = this;
		var itemDiv="";//
		$.each(datalist,function (i,n){
			itemDiv = itemDiv + "<div class='b-b-info-item' title='点击查看趋势分析' onclick='tablespacepertrend(\""+n[0]+"\",\""+n[2]+"\",\""+n[1]+"\")'>";
			itemDiv = itemDiv + "<div title='"+n[1]+"("+n[2]+")' class='b-b-info-label space'>"+n[1]+"("+n[2]+")</div>";
			itemDiv = itemDiv + "<div class='b-b-info-value-middle'><span class='b-b-info-value-normal'><span class='b-b-info-value-normal'>昨日"+n[3]+"%"+"&nbsp;&nbsp;</span><div style='width: 55px;margin-top: 0px;' class='progress' id='"+i+"1SPACEPER' title='"+n[3]+"'></div></span></div>";
			if(parseFloat(n[4])>0){
				itemDiv = itemDiv + "<div class='b-b-info-value'><span class='b-b-info-value-red'>"+"比上月↑&nbsp;"+n[4]+"%</span></div>";
			}else{
				itemDiv = itemDiv + "<div class='b-b-info-value'><span class='b-b-info-value-blue'>"+"比上月↓&nbsp;"+n[4]+"%</span></div>";
			}
			itemDiv = itemDiv + "</div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.indexdashboard_alarmbak = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='animated bounceIn hvr-shadow-radial device-dashbord-3' onclick='viewdevicelist(\""+n.id+"\",\""+n.name+"\")'>";
			itemDiv = itemDiv + "<div class='device-dashbord-title1'>"+n.name+"</div>";
			itemDiv = itemDiv + "<div class='device-dashbord-content-top'>";
			itemDiv = itemDiv + "<div class='device-dashbord-content-top-left'><i style='font-size: 28px;' class='iconfont icon-"+n.titleicon+"'></i></div>"
			itemDiv = itemDiv + "<div class='device-dashbord-content-top-right'><span>"+n.n1+"</span></div>";
			itemDiv = itemDiv + "</div>";
			itemDiv = itemDiv + "<div class='device-dashbord-content-bottom'>";
			itemDiv = itemDiv + "<div class='device-dashbord-content-top-left'><div class='device-dashbord-num-1'>"+n.total+"</div><div class='device-dashbord-name'>监控项总数</div></div>";
			itemDiv = itemDiv + "<div class='device-dashbord-content-top-right'><div class='device-dashbord-num-2'>"+n.value+"</div><div class='device-dashbord-name'>报警监控项</div></div>";
			itemDiv = itemDiv + "</div>";
			itemDiv = itemDiv + "</div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.indexdashboard_alarm = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='animated bounceIn hvr-shadow-radial device-dashbord-4-"+n.n1+"' onclick='viewdevicelist(\""+n.id+"\",\""+n.name+"\")'>";
			itemDiv = itemDiv + "<div class='device-dashbord-4-left'>";
			itemDiv = itemDiv + "<i style='font-size: 36px;' class='iconfont icon-"+n.titleicon+"'></i></div>";
			itemDiv = itemDiv + "<div class='device-dashbord-4-right'>"
			itemDiv = itemDiv + "<div class='device-dashbord-4-righttop'>"+n.name+"</div>";
			itemDiv = itemDiv + "<div class='device-dashbord-4-rightbottom'>"+n.values+"</div>";
			itemDiv = itemDiv + "</div>";
			itemDiv = itemDiv + "</div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.jciconlist = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='jc-iconlist-block'><div class='jc-block-left'>";
			itemDiv = itemDiv + "<div class='block-left-icon-"+n.status+"'>";
			if(n.status==2){
				itemDiv = itemDiv + "<span class='jc-monitor-status-"+n.status+"'><i style='font-size: 20px;' class='iconfont icon-warn'></i></span>";
			}else if(n.status==3){
				itemDiv = itemDiv + "<span class='jc-monitor-status-"+n.status+"'><i style='font-size: 20px;' class='iconfont icon-notice'></i></span>";
			}else if(n.status==4){
				itemDiv = itemDiv + "<span class='jc-monitor-status-"+n.status+"'><i style='font-size: 20px;' class='iconfont icon-xiangzhilusuoxudu'></i></span>";
			}else if(n.status==5){
				itemDiv = itemDiv + "<span class='jc-monitor-status-"+n.status+"'><i style='font-size: 20px;' class='iconfont icon-zhixingzhongduan'></i></span>";
			}
			
			itemDiv = itemDiv + "</div></div><div class='jc-block-center'>"
			itemDiv = itemDiv + "<div class='jc-block-center-topli'>"+n.name+"</div>";
			itemDiv = itemDiv + "</div><div class='jc-block-right'><span>"+n.times+"&nbsp;</span>";
			itemDiv = itemDiv + "<span><i style='font-size: 16px;' class='iconfont icon-arrow-r'></i></span>";
			itemDiv = itemDiv + "</div></div>";
		});
		//alert(itemDiv);
		//itemDiv = itemDiv + "<div class='clear'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.inspecticonli = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div id='"+n.id+"' class='inspect-instance-li' onclick='lookitemlist(\""+n.id+"\")'>";
			itemDiv = itemDiv + "<div class='loader loader-4' style='display:none'> </div>";
			itemDiv = itemDiv + "<div class='instamcelist-span'>";
			itemDiv = itemDiv + "<i style='color:#fff;font-size: 24px;' class='iconfont icon-device-"+n.deviceicon+"'></i>";
			itemDiv = itemDiv + "</div>";
			itemDiv = itemDiv + "<div style='font-size: 12px;color: #000;width: 100px;text-align: center;white-space: normal;line-height: 18px;' >"+n.name+"</div>";
			itemDiv = itemDiv + "</div>";
		});
		$(this).append(itemDiv);
		return this;
	};
	$.fn.inspectitemli = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="<ol>";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div id='"+n.id+"' class='neirongdiv'>";
			itemDiv = itemDiv + "<li class='inspect-colorx'><i class='iconfont icon-infor-o'></i>"+n.name+"<span id='alarmmsg"+n.id+"'></span></li>";
			itemDiv = itemDiv + "<i class='fa fa-square fa-1x inspect-color' style='font-size:20px'></i>";
			itemDiv = itemDiv + "</div>";
		});
		itemDiv = itemDiv + "</ol>";
		$(this).append(itemDiv);
		return this;
	};
	$.fn.inspectdashboard = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='inspect-block'>";
			itemDiv = itemDiv + "<div class='inspect-block-title'><i class='fa fa-edit' onclick='openalarmdetailjsp(\"editinspect.jsp?id="+n.id+"\")'></i>&nbsp;&nbsp;<i class='fa fa-trash' onclick='remove(\""+n.id+"\")'></i></div>";
			if(n.status=="1"){
				itemDiv = itemDiv + "<div class='inspect-block-content'><div class='inspect-block-content-left'><i class='fa fa-cog fa-spin'></i></div>";
				itemDiv = itemDiv + "<div class='inspect-block-content-right'><div class='inspect-block-content-right-title'>"+n.name+"</div>" +
				"<div class='inspect-block-content-right-content'>监控实例总数：<span>"+n.value1+"</span>&nbsp;&nbsp;监控项总数：<span>"+n.value2+"</span></div>" +
				"<div class='kaishixunjian1' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.value1+"\",\""+n.value2+"\")'>巡检中,点击进入…</div></div></div>";
				itemDiv = itemDiv + "<div class='inspect-block-foot'><div class='inspect-block-foot-note'>"+n.n1+"</div>" +
				"<div class='inspect-block-foot-time'>上次巡检时间&nbsp;"+n.times+"</div></div></div>";
			}else{
				if(n.n0=="1"){
					itemDiv = itemDiv + "<div class='inspect-block-content'><div class='inspect-block-content-left'><i class='fa fa-cogs'></i></div>";
				}else{
					itemDiv = itemDiv + "<div class='inspect-block-content'><div class='inspect-block-content-left'><i class='fa fa-user-circle'></i></div>";
				}
				itemDiv = itemDiv + "<div class='inspect-block-content-right'><div class='inspect-block-content-right-title'>"+n.name+"</div>" +
				"<div class='inspect-block-content-right-content'>监控实例总数：<span>"+n.value1+"</span>&nbsp;&nbsp;监控项总数：<span>"+n.value2+"</span></div>" +
				"<div class='kaishixunjian' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.value1+"\",\""+n.value2+"\")'>立即巡检</div>" +
						"<div onclick='openinspecthistorylist(\""+n.id+"\",\""+n.name+"\",\""+n.value1+"\",\""+n.value2+"\")' style='font-size:14px;color:#eee;margin-left:5px;float:left;height:60px;line-height:60px;cursor:pointer;'><a>巡检历史</a></div></div></div>";
				itemDiv = itemDiv + "<div class='inspect-block-foot'><div class='inspect-block-foot-note'>"+n.n1+"</div>" +
				"<div class='inspect-block-foot-time'>上次巡检时间&nbsp;"+n.times+"</div></div></div>";
			}
			
			
			
		});
		$(this).append(itemDiv);
		return this;
	};
	
	
	$.fn.inspectdashboard_o = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='inspect-block'>";
			itemDiv = itemDiv + "<div class='inspect-block-title'></div>";
			if(n.status=="1"){
				itemDiv = itemDiv + "<div class='inspect-block-content'><div class='inspect-block-content-left'><i class='fa fa-cog fa-spin'></i></div>";
				itemDiv = itemDiv + "<div class='inspect-block-content-right'><div class='inspect-block-content-right-title'>"+n.name+"</div>" +
				"<div class='inspect-block-content-right-content'>监控实例总数：<span>"+n.value1+"</span>&nbsp;&nbsp;监控项总数：<span>"+n.value2+"</span></div>" +
				"<div class='kaishixunjian1' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.value1+"\",\""+n.value2+"\")'>巡检中,点击进入…</div></div></div>";
				itemDiv = itemDiv + "<div class='inspect-block-foot'><div class='inspect-block-foot-note'>"+n.n1+"</div>" +
				"<div class='inspect-block-foot-time'>上次巡检时间&nbsp;"+n.times+"</div></div></div>";
			}else{
				if(n.n0=="1"){
					itemDiv = itemDiv + "<div class='inspect-block-content'><div class='inspect-block-content-left'><i class='fa fa-cogs'></i></div>";
				}else{
					itemDiv = itemDiv + "<div class='inspect-block-content'><div class='inspect-block-content-left'><i class='fa fa-user-circle'></i></div>";
				}
				itemDiv = itemDiv + "<div class='inspect-block-content-right'><div class='inspect-block-content-right-title'>"+n.name+"</div>" +
				"<div class='inspect-block-content-right-content'>监控实例总数：<span>"+n.value1+"</span>&nbsp;&nbsp;监控项总数：<span>"+n.value2+"</span></div>" +
				"<div class='kaishixunjian' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.value1+"\",\""+n.value2+"\")'>立即巡检</div>" +
						"<div onclick='openinspecthistorylist(\""+n.id+"\",\""+n.name+"\",\""+n.value1+"\",\""+n.value2+"\")' style='font-size:14px;color:#eee;margin-left:5px;float:left;height:60px;line-height:60px;cursor:pointer;'><a>巡检历史</a></div></div></div>";
				itemDiv = itemDiv + "<div class='inspect-block-foot'><div class='inspect-block-foot-note'>"+n.n1+"</div>" +
				"<div class='inspect-block-foot-time'>上次巡检时间&nbsp;"+n.times+"</div></div></div>";
			}
			
			
			
		});
		$(this).append(itemDiv);
		return this;
	};
	
	
	$.fn.devicetypedashboard = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		var flagn1="0";
		var flagn2="0";
		var flagn3="0";
		var temphtml="<div onclick='viewstatusdevicelist(\"1\",\"正常\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-1'>正常</div>";
		$.each(dataObj.datalist,function (i,n){
			if(n.n2>0){
				flagn1="1";
			}
			if(n.n3>0){
				flagn2="1";
			}
			if(n.n4>0){
				flagn3="1";
			}
			var nums= Number(n.n2)+Number(n.n3)+Number(n.n4);
			itemDiv = itemDiv + "<div class='hvr-overline-from-center monitor-inspect-devicetype-dashboard' onclick='viewdevicelist(\""+n.id+"\",\""+n.name+"\",\""+nums+"\",\""+n.total+"\")'>";
			itemDiv = itemDiv + "<div class='monitor-inspect-devicetype-dashboard-icon'>";
			itemDiv = itemDiv + "<i class='iconfont icon-"+n.titleicon+"'></i>";
			itemDiv = itemDiv + "</div><div class='monitor-inspect-devicetype-dashboard-content'>" +
					"<div class='monitor-inspect-devicetype-dashboard-alarmnumber'>"+nums+"/"+n.total+
					"</div>"+n.name+"</div></div>";
		});
		
		if(flagn1=="1"){
			temphtml = temphtml + "<div onclick='viewstatusdevicelist(\"3\",\"报警\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-3'>报警<span class='newdoinet'>&nbsp;</span></div>";
		}else{
			temphtml = temphtml + "<div onclick='viewstatusdevicelist(\"3\",\"报警\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-3'>报警</div>";
		}
		if(flagn2=="1"){
			temphtml = temphtml + "<div onclick='viewstatusdevicelist(\"2\",\"预警\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-2'>预警<span class='newdoinet'>&nbsp;</span></div>";
		}else{
			temphtml = temphtml + "<div onclick='viewstatusdevicelist(\"2\",\"预警\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-2'>预警</div>";
		}
		if(flagn3=="1"){
			temphtml = temphtml + "<div onclick='viewstatusdevicelist(\"4\",\"其他\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-4'>其他<span class='newdoinet'>&nbsp;</span></div>";
		}else{
			temphtml = temphtml + "<div onclick='viewstatusdevicelist(\"4\",\"其他\")' class='monitor-inspect-devicetype-typelist devicetype-typelist-4'>其他</div>";
		}
		$(this).append(temphtml+itemDiv);
		return this;
	};
	
	$.fn.timelines = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			var strs = n.n0.split("_");
			//alert(strs[0]);
			if(i>=dataObj.datalist.length-2){
				if(n.n1>0){
					if(n.n2=='4'||n.n2=='5'){
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail2(\""+n.n0+"\",\"1\")' class='lines-point-3 hvr-grow'><div class='points'></div><div class='line-text1'>"+strs[0]+"</div><div class='line-text2'>..</div><div class='line-text'>"+strs[1]+"</div></li>";
					}else{
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail2(\""+n.n0+"\",\"1\")' class='lines-point-"+n.n2+" hvr-grow'><div class='points'></div><div class='line-text1'>"+strs[0]+"</div><div class='line-text2'>..</div><div class='line-text'>"+strs[1]+"</div></li>";
					}
				}else{
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail2(\""+n.n0+"\",\"1\")' class='lines-point-1 hvr-grow'><div class='points'></div><div class='line-text1'>"+strs[0]+"</div><div class='line-text2'>..</div><div class='line-text'>"+strs[1]+"</div></li>";
				}
			}else{
				if(n.n1>0){
					if(n.n2=='4'||n.n2=='5'){
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail2(\""+n.n0+"\",\"1\")' class='lines-point-3 hvr-grow'><div class='points'></div><div class='line-text1'>"+strs[0]+"</div><div class='line-text2'>..</div><div class='line-text'>"+strs[1]+"</div></li>";
					}else{
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail2(\""+n.n0+"\",\"1\")' class='lines-point-"+n.n2+" hvr-grow'><div class='points'></div><div class='line-text1'>"+strs[0]+"</div><div class='line-text2'>..</div><div class='line-text'>"+strs[1]+"</div></li>";
					}
				}else{
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail2(\""+n.n0+"\",\"0\")' class='lines-point-1 hvr-grow'><div class='points'></div><div class='line-text1'>"+strs[0]+"</div><div class='line-text2'>..</div><div class='line-text'>"+strs[1]+"</div></li>";
				}
			}
		});
		$(this).append(itemDiv);
		return this;
	};
	
	
	$.fn.daytimelines = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		var myDate = new Date();
		$.each(dataObj.datalist,function (i,n){
			if(n.n0==myDate.getHours()){
				if(n.n1>0){
					if(n.n2=='4'||n.n2=='5'){
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='linesselected lines-day-point-3 hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}else{
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='linesselected lines-day-point-"+n.n2+" hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}
				}else{
					if(n.n2=='0'){
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='linesselected lines-day-point-1 hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}else{
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='linesselected lines-day-point-1 hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}
				}
			}else{
				if(n.n1>0){
					if(n.n2=='4'||n.n2=='5'){
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='lines-day-point-3 hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}else{
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='lines-day-point-"+n.n2+" hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}
				}else{
					if(n.n2=='0'){
						itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail(\""+n.n0+"\")' class='lines-day-point-1 hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}else{
						itemDiv = itemDiv + "<li id='"+n.n0+"' class='lines-day-point-1 hvr-grow'><div class='day-points'></div><div class='day-line-text1'>"+n.n0+"</div></li>";
					}
				}
			}
			
			
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.minutetimelines = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.mdatalist,function (i,n){
			var strs = n.n0.split("_");
			//alert(strs[0]);
			if(n.n1>0){
				if(n.n2=='4'||n.n2=='5'){
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail1(\""+n.n0+"\",\"1\")' class='lines-minute-point-3 hvr-grow'><div class='minute-points'></div><div class='minute-line-text1'>"+strs[1]+"</div></li>";
				}else{
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail1(\""+n.n0+"\",\"1\")' class='lines-minute-point-"+n.n2+" hvr-grow'><div class='minute-points'></div><div class='minute-line-text1'>"+strs[1]+"</div></li>";
				}
			}else{
				if(n.n2=='0'){
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail1(\""+n.n0+"\",\"0\")' class='lines-minute-point-1 hvr-grow'><div class='minute-points'></div><div class='minute-line-text1'>"+strs[1]+"</div></li>";
				}else{
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloadminutedetail1(\""+n.n0+"\",\"0\")' class='lines-minute-point-1 hvr-grow'><div class='minute-points'></div><div class='minute-line-text1'>"+strs[1]+"</div></li>";
				}
				
			}
		});
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.monthtimelines = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.mdatalist,function (i,n){
			//alert(n.n0);
			var newdate = n.n0.substring(5,n.n0.length);
			if(n.n1>0){
				var nums = n.n1;
				if(n.n1>99){
					nums=99+"+";
				}
				if(n.n2=='4'||n.n2=='5'){
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloaddaydetail(\""+n.n0+"\")' class='lines-month-point-3 hvr-grow'><div class='month-line-text1'>"+newdate+"</div><div class='month-line-text2'>"+nums+"</div></li>";
				}else{
					itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloaddaydetail(\""+n.n0+"\")' class='lines-month-point-"+n.n2+" hvr-grow'><div class='month-line-text1'>"+newdate+"</div><div class='month-line-text2'>"+nums+"</div></li>";
				}
			}else{
				itemDiv = itemDiv + "<li id='"+n.n0+"' onclick='reloaddaydetail(\""+n.n0+"\")' class='lines-month-point-1 hvr-grow'><div class='month-line-text1'>"+newdate+"</div><div class='month-line-text2'>"+n.n1+"</div></li>";
				
			}
		});
		$(this).append(itemDiv);
		return this;
	};
	$.fn.typegroupdevice = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		itemDiv = itemDiv + "<div class='monitorview-center-center' style='border:0px'> " +
			"<div style='height:auto;min-height: 100px;'>";
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='normaloneicon-jincheng-li-"+n.status+"' onclick='opendetails(\""+n.id+"\",\""+n.name+"\")'>";
			itemDiv = itemDiv + "<p class='groupdevice-span-"+n.status+"'><i style='color:#fff;font-size: 32px;' class='iconfont icon-device-"+n.deviceicon+"'></i></p>";
			itemDiv = itemDiv + "<p style='margin-left:0px;margin-top:8px;'>"+n.name+"<p><p style='margin-left:0px;margin-top:5px;'>"+n.ip+"<p>";
			itemDiv = itemDiv + "</div>";
		});
		itemDiv = itemDiv + "<div class='clear'></div> </div></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	
	$.fn.custommonitors_dashboard = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		var itemDiv2="";//设置TABLE的长度
		var itemDiv3="";//设置TABLE的长度
		var itemDiv4="";//设置TABLE的长度
		var itemDiv1="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			//alert(n.status);
			if(n.status=='1'||n.status=='0'){
				itemDiv1 = itemDiv1 + "<div class='custommonitor-dashboard'><div class='custommonitor-dashboard-title' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>&nbsp;<i style='color:#fff;font-size: 15px;' class='iconfont icon-device-"+n.titleicon+"'></i>" +
						"&nbsp;"+n.name2+"-"+n.name1+"</div>" +
						"<div class='custommonitor-dashboard-content'><div class='custommonitor-dashboard-content-top' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-top-img'>" +
						"<i style='color:#fff;font-size: 30px;' class='iconfont icon-device-"+n.titleicon+"'></i></div>" +
						"<div class='custommonitor-dashboard-content-top-ip'><p  title='"+n.name+"'>"+n.name+"</p><p>"+n.ip+"</p></div></div>" +
						"<div class='custommonitor-dashboard-content-center' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class=''iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label1'>" +
						"<i style='color:#5DB8FD;font-size: 15px;' class='iconfont icon-gengduo1'></i></div></div>";
				itemDiv1 = itemDiv1 + "<div class='custommonitor-dashboard-content-bottom'>";
				var contents = (n.content).split(";");
				var sslag=0;
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"&&ss[1]!=null){
							itemDiv1 = itemDiv1 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
							"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#5FBBEB;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
							"<div class='custommonitor-dashboard-item-label1' style='float: right'>正常</div></div>";
							sslag++;
						}
					}
				}
				if(contents.length>=3){
					itemDiv1 = itemDiv1 +
					"<div style='text-align: center;margin-top:3px;' onclick='openitemdetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
					"<i style='color:#5DB8FD;font-size: 14px;' class='iconfont icon-xiayibu'></i></div></div></div></div>";
				}else{
					itemDiv1 = itemDiv1 +
					"</div></div></div>";
				}
				
			}else if(n.status=='2'){
				
				itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard"+n.status+"'><div class='custommonitor-dashboard-title"+n.status+"' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>&nbsp;<i style='color:#fff;font-size: 15px;' class='iconfont icon-device-"+n.titleicon+"'></i>" +
						"&nbsp;"+n.name2+"-"+n.name1+"</div>" +
						"<div class='custommonitor-dashboard-content' ><div class='custommonitor-dashboard-content-top' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-top-img"+n.status+"'>" +
						"<i style='color:#fff;font-size: 30px;' class='iconfont icon-device-"+n.titleicon+"'></i></div>" +
						"<div class='custommonitor-dashboard-content-top-ip'><p  title='"+n.name+"'>"+n.name+"</p><p>"+n.ip+"</p></div></div>" +
						"<div class='custommonitor-dashboard-content-center' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class=''iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div></div>";
				itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard-content-bottom'>";
				var contents = (n.content).split(";");
				var sslag=0;
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"&&ss[1]!=null){
							if(ss[2]>1){
								if(ss[2]==2){
									itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#FDCB6C;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>预警</div></div>";
									sslag++;
								}else if(ss[2]==3){
									itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#FC7475;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>报警</div></div>";
									sslag++;
								}else if(ss[2]==4){
									itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#82D642;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>网络中断</div></div>";
									sslag++;
								}else if(ss[2]==5){
									itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#82D642;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>数据异常</div></div>";
									sslag++;
								}
							}
							
						}
					}
				}
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"){
							if(ss[2]==1||ss[2]==0){
								itemDiv2 = itemDiv2 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
								"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#5FBBEB;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
								"<div class='custommonitor-dashboard-item-label1' style='float: right'>正常</div></div>";
								sslag++;
							}
							
						}
					}
				}
				if(contents.length>=3){
					itemDiv2 = itemDiv2 +
					"<div style='text-align: center;margin-top:3px;' class='custommonitor-dashboard-item-label"+n.status+"' onclick='openitemdetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
					"<i style='font-size: 14px;' class='iconfont icon-xiayibu'></i></div></div></div></div>";
				}else{
					itemDiv2 = itemDiv2 +
					"</div></div></div>";
				}
			}else if(n.status=='3'){
				
				itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard"+n.status+"'><div class='custommonitor-dashboard-title"+n.status+"' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>&nbsp;<i style='color:#fff;font-size: 15px;' class='iconfont icon-device-"+n.titleicon+"'></i>" +
						"&nbsp;"+n.name2+"-"+n.name1+"</div>" +
						"<div class='custommonitor-dashboard-content' ><div class='custommonitor-dashboard-content-top' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-top-img"+n.status+"'>" +
						"<i style='color:#fff;font-size: 30px;' class='iconfont icon-device-"+n.titleicon+"'></i></div>" +
						"<div class='custommonitor-dashboard-content-top-ip'><p  title='"+n.name+"'>"+n.name+"</p><p>"+n.ip+"</p></div></div>" +
						"<div class='custommonitor-dashboard-content-center' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class=''iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div></div>";
				itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard-content-bottom'>";
				var contents = (n.content).split(";");
				var sslag=0;
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"&&ss[1]!=null){
							if(ss[2]>1){
								if(ss[2]==2){
									itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#FDCB6C;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>预警</div></div>";
									sslag++;
								}else if(ss[2]==3){
									itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#FC7475;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>报警</div></div>";
									sslag++;
								}else if(ss[2]==4){
									itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#82D642;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>网络中断</div></div>";
									sslag++;
								}else if(ss[2]==5){
									itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#82D642;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>数据异常</div></div>";
									sslag++;
								}
							}
							
						}
					}
				}
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"){
							if(ss[2]==1||ss[2]==0){
								itemDiv3 = itemDiv3 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
								"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#5FBBEB;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
								"<div class='custommonitor-dashboard-item-label1' style='float: right'>正常</div></div>";
								sslag++;
							}
							
						}
					}
				}
				if(contents.length>=3){
					itemDiv3 = itemDiv3 +
					"<div style='text-align: center;margin-top:3px;' class='custommonitor-dashboard-item-label"+n.status+"' onclick='openitemdetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
					"<i style='font-size: 14px;' class='iconfont icon-xiayibu'></i></div></div></div></div>";
				}else{
					itemDiv3 = itemDiv3 +
					"</div></div></div>";
				}
			}else if(n.status=='4'||n.status=='5'){
				
				itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard"+n.status+"'><div class='custommonitor-dashboard-title"+n.status+"' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>&nbsp;<i style='color:#fff;font-size: 15px;' class='iconfont icon-device-"+n.titleicon+"'></i>" +
						"&nbsp;"+n.name2+"-"+n.name1+"</div>" +
						"<div class='custommonitor-dashboard-content' ><div class='custommonitor-dashboard-content-top' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-top-img"+n.status+"'>" +
						"<i style='color:#fff;font-size: 30px;' class='iconfont icon-device-"+n.titleicon+"'></i></div>" +
						"<div class='custommonitor-dashboard-content-top-ip'><p  title='"+n.name+"'>"+n.name+"</p><p>"+n.ip+"</p></div></div>" +
						"<div class='custommonitor-dashboard-content-center' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'><div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class=''iconfont icon-gengduo1'></i></div>" +
						"<div class='custommonitor-dashboard-content-center-li custommonitor-dashboard-item-label"+n.status+"'>" +
						"<i style='font-size: 15px;' class='iconfont icon-gengduo1'></i></div></div>";
				itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard-content-bottom'>";
				var contents = (n.content).split(";");
				var sslag=0;
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"&&ss[1]!=null){
							if(ss[2]>1){
								if(ss[2]==2){
									itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#FDCB6C;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>预警</div></div>";
									sslag++;
								}else if(ss[2]==3){
									itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#FC7475;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>报警</div></div>";
									sslag++;
								}else if(ss[2]==4){
									itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#82D642;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>网络中断</div></div>";
									sslag++;
								}else if(ss[2]==5){
									itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
									"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#82D642;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
									"<div class='custommonitor-dashboard-item-label"+ss[2]+"' style='float: right'>数据异常</div></div>";
									sslag++;
								}
							}
							
						}
					}
				}
				for(var x=0;x<contents.length;x++){
					if(sslag==3){
						break;
					}
					else{
						var ss = contents[x].split("|");
						//alert(ss.length);
						if(ss[1]!="基本信息"&&ss[1]!="系统信息"){
							if(ss[2]==1||ss[2]==0){
								itemDiv4 = itemDiv4 + "<div class='custommonitor-dashboard-content-bottom-li' onclick='opendetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
								"<div style='float: left;font-size: 12px;'>&nbsp;<i style='color:#5FBBEB;font-size: 14px;' class='iconfont icon-11'></i>&nbsp;"+ss[1]+" </div>" +
								"<div class='custommonitor-dashboard-item-label1' style='float: right'>正常</div></div>";
								sslag++;
							}
							
						}
					}
				}
				if(contents.length>=3){
					itemDiv4 = itemDiv4 +
					"<div style='text-align: center;margin-top:3px;' class='custommonitor-dashboard-item-label"+n.status+"' onclick='openitemdetail(\""+n.id+"\",\""+n.name+"\",\""+n.content+"\",\""+n.ip+"\")'>" +
					"<i style='font-size: 14px;' class='iconfont icon-xiayibu'></i></div></div></div></div>";
				}else{
					itemDiv4 = itemDiv4 +
					"</div></div></div>";
				}
			}
			
		});
		itemDiv = itemDiv3+ itemDiv2+ itemDiv4 + itemDiv1+"<div style='clear: both;'></div>";
		$(this).append(itemDiv);
		return this;
	};
	
	$.fn.ipmi_dashboard = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var listmap={};
		var itemdivtotal="";
		var itemDiv="";//设置TABLE的长度
		var monitoritemlables="";
		try{
			$.each(dataObj.list, function(i, n) {
				if (!((n.n0.split('@!')[0] == "PowerSupply" || n.n0.split('@!')[0] == "DriveBay") && n.n3.split('@!')[0] == 8000) && n.n2.split('@!')[0] == "OK") {
					monitoritemlables = monitoritemlables + "[" + n.n1.split('@!')[0] + "]";
				}
				if (listmap[n.n0] != null) {
					itemDiv = listmap[n.n0];
					if (n.n0.indexOf('Temperature') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont0'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-wenduji ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
								+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('Voltage') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-dianyabiao ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
								+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('Fan') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont0'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-fengshan ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa fa-spin'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
								+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('Processor') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-cpu1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
						if (n.n2.split('@!')[1] > 1) {
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv + n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					} else if (n.n0.indexOf('Memory') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-neicun1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
						if (n.n2.split('@!')[1] > 1) {
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv +  n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					} else if (n.n0.indexOf('DriveBay') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont1'>";
						if (n.n2.split('@!')[0] == 'OK' && n.n3.split('@!')[0] != 8000) {
							itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-yingpan1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa  '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
							itemDiv = itemDiv +  n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-yingpan1 ipmiboard-content-li-invaild fa  '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-invaild'>";
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					} else if (n.n0.indexOf('PowerSupply') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-dianyuan1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
							+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('PowerUnit') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-dianyuan1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
						if (n.n2.split('@!')[1] > 1) {
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv +  n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					}
					listmap[n.n0] = itemDiv;
				} else {
					itemDiv = "";
					if (n.n0.indexOf('Temperature') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont0'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-wenduji ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
								+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('Voltage') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-dianyabiao ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
								+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('Fan') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont0'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-fengshan ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa fa-spin'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
								+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('Processor') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-cpu1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";

						if (n.n2.split('@!')[1] > 1) {
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv + n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					} else if (n.n0.indexOf('Memory') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-neicun1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
						if (n.n2.split('@!')[1] > 1) {
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv +  n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					} else if (n.n0.indexOf('DriveBay') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li0 lifont1'>";
						if (n.n2.split('@!')[0] == 'OK' && n.n3.split('@!')[0] != 8000) {
							itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-yingpan1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa  '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
							itemDiv = itemDiv +  n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-yingpan1 ipmiboard-content-li-invaild fa  '></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-invaild'>";
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					} else if (n.n0.indexOf('PowerSupply') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-dianyuan1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>"
							+ n.n3.split('@!')[0] + "(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
					} else if (n.n0.indexOf('PowerUnit') != -1) {
						itemDiv = itemDiv + "<div class='ipmiboard-content-li lifont1'>";
						itemDiv = itemDiv + "<div class='ipmiboard-content-li-img'><i class='iconfont icon-dianyuan1 ipmiboard-content-li-" + n.n2.split('@!')[1] + " fa'></i></div>" + "<div class='ipmiboard-content-li-text'><p class='ipmiboard-content-li-" + n.n2.split('@!')[1] + "'>";
						if (n.n2.split('@!')[1] > 1) {
							itemDiv = itemDiv + "不可用(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						} else {
							itemDiv = itemDiv +  n.n3.split('@!')[0] + " 正常(" + n.n2.split('@!')[0] + ")</p><p>" + n.n1.split('@!')[0] + "</p></div></div>";
						}
					}
					if (itemDiv != "") {
						listmap[n.n0] = itemDiv;
					}
				}
			});
			for ( var key in listmap) {
				if (key.indexOf('Temperature') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-wenduji'></i>&nbsp;温度(Temperature)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('Voltage') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-dianyabiao'></i>&nbsp;电压(Voltage)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('Fan') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-fengshan'></i>&nbsp;风扇(Fan)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('Processor') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-cpu1'></i>&nbsp;CPU(Processor)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('Memory') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-neicun1'></i>&nbsp;内存(Memory)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('DriveBay') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-yingpan1'></i>&nbsp;硬盘(DriveBay)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('PowerSupply') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-dianyuan1'></i>&nbsp;电源供电(PowerSupply)</div>" + listmap[key] + "</div>";
				} else if (key.indexOf('PowerUnit') != -1) {
					itemdivtotal = itemdivtotal + "<div class='ipmiboard'><div class='ipmiboard-title'><i class='iconfont icon-dianyuan1'></i>&nbsp;电源模块(PowerUnit)</div>" + listmap[key] + "</div>";
				}
			}
			$(this).append(itemdivtotal + "<div style='clear: both;'></div>");
		}catch(e){}
		try{
			window.parent.$("#monitoritemlables").html(monitoritemlables);
		}catch(e){}
		try{
			window.parent.parent.$("#monitoritemlables").html(monitoritemlables);
		}catch(e){}
		return this;
	};
	

	$.fn.monitortables = function(id, options) {
		$(this).children().remove();
		options = $.extend(defaults, options);
		var dataObj = options.data; // 数据JSON
		var o = this;
		$(this).append("<table id='" + id + "' class='tb-disk-list' cellpadding='0' cellspacing='0' style='font-size:14px;' width='100%'></table>"); // 设置TABLE的长度
		var itemDiv = "";
		var trs = "<tr style='border-bottom:1px solid #eee;color:#666'><td width='15%' height='40px' style='padding-left:3%'><B>指标名称</B></td><td width='15%' height='45px' align='left'><B>A相(AB)</B></td><td width='15%' height='40px' align='left'><B>B相(BC)</B></td><td width='15%' height='40px' align='left'><B>C相(CA)</B></td></tr>";
		$.each(dataObj.datalist, function(i, n) {
			// "<tr></tr>"
			var tds = "<td width='15%' height='45px' style='padding-left:3%'>" + n.names + "</td><td width='15%' height='45px' align='left'>" + n.Adata
					+ "</td><td width='15%' height='45px' align='left'>" + n.Bdata + "</td><td width='15%' height='45px' align='left'>" + n.Cdata + "</td>";
			if (i % 1 == 0) {
				trs += "<tr style='border-bottom:1px solid #eee'>" + tds;
			} else {
				trs += tds + "</tr>";
			}
		});
		$("table", o).append(trs);
		return this;
	};

	$.fn.normaloneicon_con = function(id, options) {
		$(this).children().remove();
		options = $.extend(defaults, options);
		var dataObj = options.data; // 数据JSON
		var o = this;
		var itemDiv = "<div style='margin:15px 5px'>"; // 设置TABLE的长度
		$.each(dataObj.datalist, function(i, n) {
			itemDiv = itemDiv + "<div class='con-main-dashboards'> <div class='con-main-dashboard'><span  class='con_fontsize " + n.status + "'><i class='fa fa-toggle-" + n.value1 + "'></i></span>";
			itemDiv = itemDiv + "<div class='" + n.status + "'>" + n.values + "</div></div>";
			itemDiv = itemDiv + "<div class='con-main-dashboards-text'>" + n.name + "</div>";
			itemDiv = itemDiv + "</div>";
		});
		itemDiv = itemDiv + "</div>";
		$(this).append(itemDiv);
		return this;
	};

	$.fn.yunxingstatus = function (id,options){
		$(this).children().remove();
		options = $.extend(defaults,options);
		var dataObj = options.data;//数据JSON
		var o = this;
		var itemDiv="";//设置TABLE的长度
		$.each(dataObj.datalist,function (i,n){
			itemDiv = itemDiv + "<div class='air_main_index_left_bottom_content_left '><i class='iconfont icon-yuan current-status-"+n.status+"'></i>&nbsp;&nbsp;&nbsp;&nbsp;"+n.names+"</div>" ;
		});
		
		$(this).append(itemDiv);
		return this;
	};
})(jQuery);


function isNumber(val){

    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)){
        return true;
    }else{
        return false;
    }

}