/********
树菜单展示
fuction：初始化树节点：
        json存放展示的节点数据，
        json[i][j].id对应数据表pbac_menu的主键id，
        json[i][i].parent_id对应数据表pbac_menu的字段parent_id，
        json[i][i].levels对应数据表pbac_menu的字段levels,节点深度，
        json[i][i].names对应数据表pbac_menu的字段names,
        json[i][i].isleaf对应数据表pbac_menu的字段isleaf，0不为叶子，1为叶子，
        json[i][i].display_index对应数据表pbac_menu的字段display_index,节点在同一级中的展示顺序，
        tree为配置库树对象，根据dTree.js所得
auther:
       fang
****************/

/**




******
循环递归菜单
json:在整个方法中json 都不变，恒等于jsonstr
mainMenu:主菜单数组
****************/

var lastTramsViewArray=new Array();
var longTramsViewArray='';
var longTramsViewStr='';
var oldcolorid='';
var oldfontcolorid='';
var prelongTrams='';//“更多”菜单前面第一个菜单
var preprelongTrams='';//“更多”菜单前面第二个菜单
var longTramsItem='';
 var islong=false;//没有“更多”菜单
  function addMenu(json){

	
    if(json){
        var z=0;
	    var mainMenu=new Array();//存放主菜单数组   
	    longTramsViewArray=new Array();//存放“更多”菜单展示的主菜单
		for(i in json){
			json[i].sort(AscSortMenu);
			json[i].sort(AscSort);
			if(islong){
	           for(m in json[i]){
	              if(longTramsItem==json[i][m].id){
	              longTramsItem=json[i][m];
	              break;
	              }
	           }
            }
			for(j in json[i] ){
				if(json[i][j].id==json[i][j].parent_id){//取得主菜单
					z=z+1;
					if((z*73+100)>screen.width){//菜单长度大于分辨率时在“更多”菜单下展示
						if(json[i][j].id!=longTramsItem.id){
							longTramsViewArray=longTramsViewArray.concat(json[i][j]);
						}
						islong=true;
					}else {
						mainMenu=mainMenu.concat(json[i][j]);//此主菜单没有子菜单，则放到数组mainMenu中
						if(!islong){
							preprelongTrams=prelongTrams;
							prelongTrams=  json[i][j]; 
						}else  if(json[i][j].id==preprelongTrams.id){
							mainMenu=mainMenu.concat(longTramsItem);
							z=z+1;
						}
					 }
				}  
			}
		}   
		// alert(mainMenu.length+150);
		
		mainMenu.sort(AscSort);//数值按照字段displayindex升序排列
		longTramsViewArray.sort(AscSort);
		returnStr=addMainItem(mainMenu,islong);
		longTramsViewStr=longTramsMailItem(longTramsViewArray);
	 }else{
       returnStr="";
     }
      return returnStr;
 }	
 
 
/********
鼠标滑过“更多”时展示的主菜单
****************/ 
function longTramsMailItem(longTramsViewArray){
var retStr='<table id=\"longTrams\"  >';
     
      for( var i=0;i<longTramsViewArray.length;i++){
              if(longTramsViewArray[i].url!=""&&longTramsViewArray[i].url!=null&&longTramsViewArray[i].url!=undefined){
						      	     if(longTramsViewArray[i].url.indexOf("?")>0){
								        retStr+=" <tr><td>";
								        retStr+="<td class=otherTabLeft style=\" display:none\" id=q"+longTramsViewArray[i].id+">xx</td>";
								        retStr+="<td class=otherTab style=\" display:none\"  id=s"+longTramsViewArray[i].id+">xx</td>";
								        retStr+="<td class=otherTabRight style=\" display:none\"  id=h"+longTramsViewArray[i].id+">xx</td>";
								   		retStr+="<td class=otherTabLink style=\" display:none\"  id=x"+longTramsViewArray[i].id+">xx</td>";
								        retStr+=" <A class=menuItem   ";
									    retStr+="    target=\"main_target\"  onclick=\"menuList('"+longTramsViewArray[i].id+"','"+longTramsViewArray[i].url+"'),shoushuo('Layer6')\" href=\""+longTramsViewArray[i].url+"&id="+longTramsViewArray[i].id+"\">"+longTramsViewArray[i].names+"</A>";
									 }else{
									 			      	    if(longTramsViewArray[i].url=="/ITSM/trams/monitorstate/index.jsp"||longTramsViewArray[i].url=="/ITSM/workflow/process/workflowindex.jsp"){
										    retStr+=" <tr>";
								        retStr+="<td class=otherTabLeft style=\" display:none\"  id=q"+longTramsViewArray[i].id+">xx</td>";
								        retStr+="<td class=otherTab style=\" display:none\"  id=s"+longTramsViewArray[i].id+">xx</td>";
								        retStr+="<td class=otherTabRight style=\" display:none\"  id=h"+longTramsViewArray[i].id+">xx</td>";
								    	retStr+="<td class=otherTabLink style=\" display:none\"  id=x"+longTramsViewArray[i].id+">xx</td>";
									    retStr+=" <td> <A class=menuItem ";
									    retStr+="   target=\"main_target\"   onclick=\"menuList('"+longTramsViewArray[i].id+"','"+longTramsViewArray[i].url+"'),yinchang11('Layer6')\" href=\""+longTramsViewArray[i].url+"?id="+longTramsViewArray[i].id+"\">"+longTramsViewArray[i].names+"</A>";
							 
									 }else {
									    retStr+=" <tr>";
								        retStr+="<td class=otherTabLeft style=\" display:none\"  id=q"+longTramsViewArray[i].id+">xx</td>";
								        retStr+="<td class=otherTab style=\" display:none\"  id=s"+longTramsViewArray[i].id+">xx</td>";
								        retStr+="<td class=otherTabRight style=\" display:none\"  id=h"+longTramsViewArray[i].id+">xx</td>";
								    	retStr+="<td class=otherTabLink style=\" display:none\"  id=x"+longTramsViewArray[i].id+">xx</td>";
									    retStr+=" <td> <A class=menuItem ";
									    retStr+="   target=\"main_target\"   onclick=\"menuList('"+longTramsViewArray[i].id+"','"+longTramsViewArray[i].url+"'),shoushuo('Layer6')\" href=\""+longTramsViewArray[i].url+"?id="+longTramsViewArray[i].id+"\">"+longTramsViewArray[i].names+"</A>";
								
						      	          }
						      	          }
			             }
   
      }
      retStr+='</table>';
       return retStr;
} 
/********
配置库首页、系统管理首页、权限管理首页、服务台首页
json:在整个方法中json 都不变，恒等于jsonstr
mainmenuid：首页节点的父级菜单
mainMenuIndex:首页链接页面显示的数据数组
****************/
  function addMenuIndex(json,mainmenuid){
  	if(json){  
  		var isIndex=false;//链接页面是否显示菜单
        var parentid="";
        var parenturl="";
        var mainMenuIndex=new Array();//存放首页链接页面显示的菜单  
		for(i in json){
			json[i].sort(LevelsSort);
			for(z in json[i]){
				//如果为主菜单
				if((json[i][z].id==mainmenuid)&&(json[i][z].id==json[i][z].parent_id)&&(json[i][z].url!="")){
					parenturl=json[i][z].url;
					parentid=json[i][z].id;
					isIndex=true;
					break;
				}else if((json[i][z].id==mainmenuid)&&(json[i][z].url!="")){//为子菜单首页
					parentid=json[i][z].parent_id;
					parenturl=json[i][z].url;
					continue;
				}
				if((parenturl==json[i][z].url)&&(parenturl!="")&&(json[i][z].id==json[i][z].parent_id)){//找到子菜单对应的上级菜单
					parentid=json[i][z].id;
					isIndex=true;
					break;
				}
			}
			if(isIndex){
				for(j in json[i] ){
					if((json[i][j].parent_id==parentid)&&(json[i][j].id!=json[i][j].parent_id)&&(json[i][j].url!=parenturl)){
						mainMenuIndex=mainMenuIndex.concat(json[i][j]);//取得显示页面中的菜单数值
					}  
				}
			}   
		}
		mainMenuIndex.sort(AscSort);
		returnStr=addMainItemIndex(mainMenuIndex);
	 }else{
       returnStr="";
     }
     return returnStr;
}	 
/********
配置库首页、系统管理首页、权限管理首页、服务台首页
json:在整个方法中json 都不变，恒等于jsonstr
mainmenuid：首页节点的父级菜单
mainMenuIndex:首页链接页面显示的数据数组
****************/
  function addMenuwindow(json,mainmenuid){
  	if(json){  
  		var isIndex=false;//链接页面是否显示菜单
        var parentid="";
        var parenturl="";
        var mainMenuIndex=new Array();//存放首页链接页面显示的菜单  
		for(i in json){
			json[i].sort(LevelsSort);
			for(z in json[i]){
				//如果为主菜单
				if((json[i][z].id==mainmenuid)&&(json[i][z].id==json[i][z].parent_id)&&(json[i][z].url!="")){
					parenturl=json[i][z].url;
					parentid=json[i][z].id;
					isIndex=true;
					break;
				}else if((json[i][z].id==mainmenuid)&&(json[i][z].url!="")){//为子菜单首页
					parentid=json[i][z].parent_id;
					parenturl=json[i][z].url;
					continue;
				}
				if((parenturl==json[i][z].url)&&(parenturl!="")&&(json[i][z].id==json[i][z].parent_id)){//找到子菜单对应的上级菜单
					parentid=json[i][z].id;
					isIndex=true;
					break;
				}
			}
			if(isIndex){
				for(j in json[i] ){
					if((json[i][j].parent_id==parentid)&&(json[i][j].id!=json[i][j].parent_id)&&(json[i][j].url!=parenturl)){
						mainMenuIndex=mainMenuIndex.concat(json[i][j]);//取得显示页面中的菜单数值
					}  
				}
			}   
		}
		mainMenuIndex.sort(AscSort);
		returnStr=addMainItemwindow(mainMenuIndex);
	 }else{
       returnStr="";
     }
     return returnStr;
}	 

/********
配置库首页、系统管理首页、权限管理首页、服务台首页展示
****************/
function addMainItemwindow(list){
	list.sort(AscSortMenu);
	list.sort(AscSort);
    var returnStr="";
    var html1="<ul class=\"toollist\">";
    var html2="<ul class=\"toollist\">";
    var html3="<ul class=\"toollist\">";
    var html4="<ul class=\"toollist\">";
    var html5="<ul class=\"toollist\">";
    for(var i=0;i<list.length;i++){
    	if((list[i].displayindex+"").substr(0,1)=="1"){
    		html1+=" <li><a href=\"javascript:window.parent.changeSrc('"+list[i].names+"','"+list[i].url+"')\"><img src=\"/TRAMS/images/loginimages/i0"+(i+1)+".png\" /></a><h2>"+list[i].names+"</h2></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="2"){
    		html2+=" <li><a href=\"javascript:window.parent.changeSrc('"+list[i].names+"','"+list[i].url+"')\"><img src=\"/TRAMS/images/loginimages/i0"+(i+1)+".png\" /></a><h2>"+list[i].names+"</h2></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="3"){
    		html3+=" <li><a href=\"javascript:window.parent.changeSrc('"+list[i].names+"','"+list[i].url+"')\"><img src=\"/TRAMS/images/loginimages/i0"+(i+1)+".png\" /></a><h2>"+list[i].names+"</h2></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="4"){
    		html4+=" <li><a href=\"javascript:window.parent.changeSrc('"+list[i].names+"','"+list[i].url+"')\"><img src=\"/TRAMS/images/loginimages/i0"+(i+1)+".png\" /></a><h2>"+list[i].names+"</h2></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="5"){
    		html5+=" <li><a href=\"javascript:window.parent.changeSrc('"+list[i].names+"','"+list[i].url+"')\"><img src=\"/TRAMS/images/loginimages/i0"+(i+1)+".png\" /></a><h2>"+list[i].names+"</h2></li>";
    	}
 	}
 	html1+="</ul>";
 	html2+="</ul>";
 	html3+="</ul>";
 	html4+="</ul>";
 	html5+="</ul>";
 	returnStr="html1@"+html1+";html2@"+html2+";html3@"+html3+";html4@"+html4+";html5@"+html5;
	return returnStr; 
}
  function addMenu(json,mainmenuid){
  	if(json){  
  		var isIndex=false;//链接页面是否显示菜单
        var parentid="";
        var parenturl="";
        var mainMenuIndex=new Array();//存放首页链接页面显示的菜单  
		for(i in json){
			json[i].sort(LevelsSort);
			for(z in json[i]){
				//如果为主菜单
				if((json[i][z].id==mainmenuid)&&(json[i][z].id==json[i][z].parent_id)&&(json[i][z].url!="")){
					parenturl=json[i][z].url;
					parentid=json[i][z].id;
					isIndex=true;
					break;
				}else if((json[i][z].id==mainmenuid)&&(json[i][z].url!="")){//为子菜单首页
					parentid=json[i][z].parent_id;
					parenturl=json[i][z].url;
					continue;
				}
				if((parenturl==json[i][z].url)&&(parenturl!="")&&(json[i][z].id==json[i][z].parent_id)){//找到子菜单对应的上级菜单
					parentid=json[i][z].id;
					isIndex=true;
					break;
				}
			}
			if(isIndex){
				for(j in json[i] ){
					if((json[i][j].parent_id==parentid)&&(json[i][j].id!=json[i][j].parent_id)&&(json[i][j].url!=parenturl)){
						mainMenuIndex=mainMenuIndex.concat(json[i][j]);//取得显示页面中的菜单数值
					}  
				}
			}   
		}
		mainMenuIndex.sort(AscSort);
  		if(mainmenuid=='00000001641'){
  			returnStr=addMainItem1(mainMenuIndex);
  		}else if(mainmenuid=='00000001741'){
  			returnStr=addMainItem2(mainMenuIndex);
  		}
		
	 }else{
       returnStr="";
     }
     return returnStr;
}	 

/********
配置库首页、系统管理首页、权限管理首页、服务台首页展示
****************/
function addMainItem1(list){
	list.sort(AscSortMenu);
	list.sort(AscSort);
    var returnStr="";
    var html1="";
    var html2="";
    var html3="";
    var html4="";
    var html5="";
    for(var i=0;i<list.length;i++){
    	if((list[i].displayindex+"").substr(0,1)=="1"){
    		html1+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="2"){
    		html2+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="3"){
    		html3+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="4"){
    		html4+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="5"){
    		html5+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}
 	}
 	if(html1.length>0){
 	   html1="<a href=\"javascript:void(0)\"  class=\"zygl parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">监控配置</li>"+html1+"</ul></span></a>";
 	}
 	if(html2.length>0){
 	   html2="<a href=\"javascript:void(0)\"  class=\"zypz parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">规则配置</li>"+html2+"</ul></span></a>";
 	}
 	if(html3.length>0){
 	   html3="<a href=\"javascript:void(0)\"  class=\"xtpz parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">模板管理</li>"+html3+"</ul></span></a>";
 	}
 	if(html4.length>0){
 	   html4="<a href=\"javascript:void(0)\"  class=\"qxpz parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">系统管理</li>"+html4+"</ul></span></a>";
 	}
 	if(html5.length>0){
 	   html5="<a href=\"javascript:void(0)\"  class=\"bfgl parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">权限配置</li>"+html5+"</ul></span></a>";
 	}
 	returnStr="html1@"+html1+";html2@"+html2+";html3@"+html3+";html4@"+html4+";html5@"+html5;
	return returnStr; 
}
/********
配置库首页、系统管理首页、权限管理首页、服务台首页展示
****************/
function addMainItem2(list){
	list.sort(AscSortMenu);
	list.sort(AscSort);
    var returnStr="";
    var html1="";
    var html2="";
    var html3="";
    var html4="";
    var html5="";
    for(var i=0;i<list.length;i++){
    	if((list[i].displayindex+"").substr(0,1)=="1"){
    		html1+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="2"){
    		html2+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="3"){
    		html3+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="4"){
    		html4+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="5"){
    		html5+=" <li><div rel=\""+list[i].url+"\">"+list[i].names+"</div></li>";
    	}
 	}
 	if(html1.length>0){
 	   html1="<a href=\"javascript:void(0)\"  class=\"bbtj parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">报表统计</li>"+html1+"</ul></span></a>";
 	}
 	if(html2.length>0){
 	   html2="<a href=\"javascript:void(0)\"  class=\"rzcx parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">统计查询</li>"+html2+"</ul></span></a>";
 	}
 	if(html3.length>0){
 	   html3="<a href=\"javascript:void(0)\"  class=\"tjfx parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">统计分析</li>"+html3+"</ul></span></a>";
 	}
 	if(html4.length>0){
 	   html4="<a href=\"javascript:void(0)\"  class=\"qxpz parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">权限配置</li>"+html4+"</ul></span></a>";
 	}
 	if(html5.length>0){
 	   html5="<a href=\"javascript:void(0)\"  class=\"bfgl parent\"><span><ul class='liveullist'><li class=\"leftmenu-ui-title\">备份清理</li>"+html5+"</ul></span></a>";
 	}
 	returnStr="html1@"+html1+";html2@"+html2+";html3@"+html3+";html4@"+html4+";html5@"+html5;
	return returnStr; 
}
/********
循环递归菜单
json:在整个方法中json 都不变，恒等于jsonstr
parentid 父级节点id
mainMenu:下级菜单数组
****************/
function addMenuChildren(json,parentid,tree,flag){

      if(json){
	  
		 for(i in json){	
		 		 for(z in json[i]){
		            if(flag=='1'){
					for(var n=0;n<menuTree.aNodes.length;n++){
		                  if(menuTree.aNodes[n].id==json[i][z].id){		//在menuTree.aNodes数组中删除此节点
		                     delArr(menuTree.aNodes,n);					//删除数组menuTree.aNodes数组中指定的元素n		                              
		                  }
                   }
                 }
		           }  
		           json[i].sort(AscSortMenu);	
		           json[i].sort(AscSort);		   		
				for(j in json[i]){
				
                 
				if(json[i][j].parent_id==parentid){	//父节点相同
				
							if(json[i][j].id==json[i][j].parent_id){//节点为根节点
								
							    if(json[i][j].isleaf==0){		 	//且不为叶子节点
							    
									tree.add(json[i][j].id,-1,json[i][j].names,json[i][j].url,'','','','',true,true,json[i][j].displayindex,json[i][j].views);
							    }else if(json[i][j].isleaf==1){	 	//为叶子节点
							    
									tree.add(json[i][j].id,-1,json[i][j].names,json[i][j].url,'','','','',true,false,json[i][j].displayindex,json[i][j].views);
							    }
							 }else {//不为根结点
							        if(json[i][j].isleaf==0){		//不为叶子节点
							         //   alert(json[i][j].id+json[i][j].names);
										tree.add(json[i][j].id,json[i][j].parent_id,json[i][j].names,json[i][j].url,'','','','',false,true,json[i][j].displayindex,json[i][j].views);							    
							            addMenuChildren(json,json[i][j].id,tree,'2');							            
							        }else if(json[i][j].isleaf==1){ //为叶子节点
							        
							            tree.add(json[i][j].id,json[i][j].parent_id,json[i][j].names,json[i][j].url,'','','','',true,false,json[i][j].displayindex,json[i][j].views);
							        }
					         }													   
				}				
			}
		 }   
			
	}else{
           returnStr="";
      }
  		return tree;
}	 


/***************
function:删除数组arr中指定的元素
arr:数组arr
val:指定删除元素的下标
********************/
function delArr(arr,val){
     for(var i=0;i<arr.length;i++){
         if(i==val){
               for(var j=i;j<arr.length;j++){
                 arr[j]=arr[j+1];
               }
                 arr.pop();
         }
     }
 
} 
/******
菜单在jsp中显示的字符串
list:存放的是主菜单
str:主菜单的字符串
**********/
function addMainItem(list)
{        
    var returnStr="";
        returnStr+="<ul class=\"nav\">"
        for(var i=0;i<list.length;i++){
		    if(i==0){
		    	returnStr+="<li><a href=\"javascript:changeSrc('"+list[i].url+"')\" id=\"one\" target=\"_parent\" class=\"selected\"><img src=\"/hwms/images/loginimages/i07.png\" title=\""+list[i].names+"\" height=\"40\"/><h2>"+list[i].names+"</h2></a></li>";
		    }else{
		    	returnStr+="<li><a href=\"javascript:changeSrc('"+list[i].url+"')\" id=\"one\" target=\"_parent\"><img src=\"/hwms/images/loginimages/icon0"+(i+1)+".png\" title=\""+list[i].names+"\" height=\"40\"/><h2>"+list[i].names+"</h2></a></li>";
		    }  
		}
		returnStr+="</ul>";
		return returnStr; 
}

/********
配置库首页、系统管理首页、权限管理首页、服务台首页展示
****************/
function addMainItemIndex(list){
	list.sort(AscSortMenu);
	list.sort(AscSort);
    var returnStr="";
    var html1="<ul class=\"menuson\">";
    var html2="<ul class=\"menuson\">";
    var html3="<ul class=\"menuson\">";
    var html4="<ul class=\"menuson\">";
    var html5="<ul class=\"menuson\">";
    for(var i=0;i<list.length;i++){
    	if((list[i].displayindex+"").substr(0,1)=="1"){
    		html1+="<li><cite></cite><a href=\"javascript:changeSrc('"+list[i].names+"','"+list[i].url+"')\" >"+list[i].names+"</a><i></i></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="2"){
    		html2+="<li><cite></cite><a href=\"javascript:changeSrc('"+list[i].names+"','"+list[i].url+"')\" >"+list[i].names+"</a><i></i></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="3"){
    		html3+="<li><cite></cite><a href=\"javascript:changeSrc('"+list[i].names+"','"+list[i].url+"')\" >"+list[i].names+"</a><i></i></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="4"){
    		html4+="<li><cite></cite><a href=\"javascript:changeSrc('"+list[i].names+"','"+list[i].url+"')\" >"+list[i].names+"</a><i></i></li>";
    	}else if((list[i].displayindex+"").substr(0,1)=="5"){
    		html5+="<li><cite></cite><a href=\"javascript:changeSrc('"+list[i].names+"','"+list[i].url+"')\" >"+list[i].names+"</a><i></i></li>";
    	}
 	}
 	html1+="</ul>";
 	html2+="</ul>";
 	html3+="</ul>";
 	html4+="</ul>";
 	html5+="</ul>";
 	returnStr="html1@"+html1+";html2@"+html2+";html3@"+html3+";html4@"+html4+";html5@"+html5;
	return returnStr; 
}


/******
菜单在jsp中显示的字符串
list:存放的是下级菜单
str:下级菜单的字符串
**********///zaici
function addItem(list)
{        
    var returnStr="";
        returnStr="  <table width=\"100%\"  border=\"0\" class=\"kjcd\" CellSpacing=\"0\"><tr><td class=\"kjcd_td\"><span class=\"STYLE1\">快捷方式</span></td></tr>\n";
         for(var i=0;i<list.length;i++){
 		  if(list[i].views!=""&&list[i].views!=null){
 		    if(list[i].url!=""&&list[i].url!=null&&list[i].url!=undefined){
 		       if(list[i].url.indexOf("?")>0){
 		          returnStr+=" <tr> <td class=\"kjcd_td\" onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\"><img src=\""+list[i].views+"\" width=\"14\" height=\"12\" /> <a target=\"main_target\" onclick=\"javascript:addTramsViewArray('1','"+list[i].id+"','"+list[i].views+"','"+list[i].url+"','"+list[i].names+"')\" href=\""+list[i].url+"&id="+list[i].id+"\">"+list[i].names+"</a></td></tr>\n";
 		       }else{
 		           returnStr+=" <tr> <td class=\"kjcd_td\" onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\"><img src=\""+list[i].views+"\" width=\"14\" height=\"12\" /> <a target=\"main_target\" onclick=\"javascript:addTramsViewArray('1','"+list[i].id+"','"+list[i].views+"','"+list[i].url+"','"+list[i].names+"')\" href=\""+list[i].url+"?id="+list[i].id+"\">"+list[i].names+"</a></td></tr>\n";
 		       }
 		    }else{
 		     returnStr+=" <tr> <td class=\"kjcd_td\" onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\"><img src=\""+list[i].views+"\" width=\"14\" height=\"12\" /> <a target=\"main_target\" onclick=\"javascript:addTramsViewArray('1','"+list[i].id+"','"+list[i].views+"','"+list[i].url+"','"+list[i].names+"')\" href=\"noUrlIndex.jsp\" >"+list[i].names+"</a></td></tr>\n";
 		    }
 		  }else{
 		      if(list[i].url!=""&&list[i].url!=null&&list[i].url!=undefined){
 		         if(list[i].url.indexOf("?")>0){
 		           returnStr+=" <tr> <td class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\"> <a target=\"main_target\"  onclick=\"javascript:addTramsViewArray('1','"+list[i].id+"','"+list[i].views+"','"+list[i].url+"','"+list[i].names+"')\" href=\""+list[i].url+"&id="+list[i].id+"\">"+list[i].names+"</a></td></tr>\n";
 		         }else{
 		          returnStr+=" <tr> <td class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\"> <a target=\"main_target\"  onclick=\"javascript:addTramsViewArray('1','"+list[i].id+"','"+list[i].views+"','"+list[i].url+"','"+list[i].names+"')\" href=\""+list[i].url+"?id="+list[i].id+"\">"+list[i].names+"</a></td></tr>\n";
 		         }
 		      }else{
 		      returnStr+=" <tr> <td class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\"> <a target=\"main_target\"  onclick=\"javascript:addTramsViewArray('1','"+list[i].id+"','"+list[i].views+"','"+list[i].url+"','"+list[i].names+"')\" href=\"noUrlIndex.jsp\">"+list[i].names+"</a></td></tr>\n";
 		      }
 		    
 		  }
		 }
		 returnStr+="</table>";
		return returnStr; 
}
/******
菜单在jsp中显示的访问历史
**********/
function addTramsViewArray(ind,id,views,url,names){

       		     var str="";
				 var last=0;
				 var lastexit=false;
				 if(ind==2){
				 lastTramsViewArray=window.parent.lastTramsViewArray;
				 }
				 var length=lastTramsViewArray.length-1;
				 var lastview=new lastviewObject(id,views,url,names);
				  if(lastTramsViewArray.length-5>0)
				    { last=lastTramsViewArray.length-5;
				    }
				for(var i=last;i<=length;i++){
				            if((lastTramsViewArray[i].id==id)||(lastTramsViewArray[i].names==names)){
				            lastexit=true;
				            }
					  	     str+= "  | ";
					  	    if(lastTramsViewArray[i].views!=""&&lastTramsViewArray[i].views!=null){
					  	          if(lastTramsViewArray[i].url.indexOf("?")>0){
					  	         	str+="  <img src=\""+lastTramsViewArray[i].views+"\" width=\"14\" height=\"12\" /><a class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\" target=\"main_target\" onclick=\"javascript:addTramsViewArray('"+lastTramsViewArray[i].id+"','"+lastTramsViewArray[i].views+"','"+lastTramsViewArray[i].url+"','"+lastTramsViewArray[i].names+"'),shoushuo('Layer6')\"  href=\""+lastTramsViewArray[i].url+"&id="+lastTramsViewArray[i].id+"\">"+lastTramsViewArray[i].names+"</a>";
					  	          }else{
					  	          	str+="  <img src=\""+lastTramsViewArray[i].views+"\" width=\"14\" height=\"12\" /><a class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\" target=\"main_target\" onclick=\"javascript:addTramsViewArray('"+lastTramsViewArray[i].id+"','"+lastTramsViewArray[i].views+"','"+lastTramsViewArray[i].url+"','"+lastTramsViewArray[i].names+"'),shoushuo('Layer6')\"  href=\""+lastTramsViewArray[i].url+"?id="+lastTramsViewArray[i].id+"\">"+lastTramsViewArray[i].names+"</a>";
					  	          }
		 		            }else{
		 		             if(lastTramsViewArray[i].url.indexOf("?")>0){
		 		             		str+="  <a class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\" target=\"main_target\" onclick=\"javascript:addTramsViewArray('"+lastTramsViewArray[i].id+"','"+lastTramsViewArray[i].views+"','"+lastTramsViewArray[i].url+"','"+lastTramsViewArray[i].names+"'),shoushuo('Layer6')\"   href=\""+lastTramsViewArray[i].url+"&id="+lastTramsViewArray[i].id+"\">"+lastTramsViewArray[i].names+"</a>";
		 		             }else{
		 		             		 str+="  <a class=\"kjcd_td\"  onmousemove=\"javascript:change_kuai(this)\" onmouseout=\"javascript:change_kuai_back(this)\" target=\"main_target\" onclick=\"javascript:addTramsViewArray('"+lastTramsViewArray[i].id+"','"+lastTramsViewArray[i].views+"','"+lastTramsViewArray[i].url+"','"+lastTramsViewArray[i].names+"'),shoushuo('Layer6')\"   href=\""+lastTramsViewArray[i].url+"?id="+lastTramsViewArray[i].id+"\">"+lastTramsViewArray[i].names+"</a>";
		 		             }
		 		          }
				 }
				  if(length>=0){
				      if((lastTramsViewArray[length].id!=id)&&!lastexit){
				          if(ind==1){//同一Frame中
				          lastTramsViewArray=lastTramsViewArray.concat(lastview);
				           }else if(ind==2) {//mainFrame中
				           window.parent.lastTramsViewArray=window.parent.lastTramsViewArray.concat(lastview);
				           }
				      }
				  }else{
				      if(ind==1){
				          lastTramsViewArray=lastTramsViewArray.concat(lastview);
				           }else if(ind==2) {
				           window.parent.lastTramsViewArray=window.parent.lastTramsViewArray.concat(lastview);
				           }
				   }
				if(ind==2){
				window.parent.document.getElementById("subMenu").innerHTML = str;//页面为首页手动链接
				}else if(ind==1){
			
				document.getElementById("subMenu").innerHTML = str;//页面为数据库取值的链接
				}
				 
}

/*************
对同一级的菜单按从小到大的顺序排列
**************/
function  AscSort(x, y) 
  {
   return  x.displayindex ==y.displayindex ? 0  : (x.displayindex  >  y.displayindex  ?   1  :  - 1 );
} 


function  AscSortMenu(x, y) 
  {
   return  x.id ==y.id ? 0  : (x.id  >  y.id  ?   1  :  - 1 );
} 
/*************
菜单按Levles降序排列
**************/
function  LevelsSort(x, y) 
  {
   return  x.levels ==y.levels ? 0  : (x.levels  <  y.levels  ?   1  :  -1 );
} 

function  over(obj){
 obj.className="imgtableover";
} 

function  out(obj){
 obj.className="imgtableout";
} 


function lastviewObject(id,views,url,names){
        this.id=id;
        this.views=views;
        this.url=url;
        this.names = names;
}

/*
   function  AscSort(x, y) 
{
    return  x.displayindex ==y.displayindex? 0  : (x.displayindex  >  y.displayindex ?   1  :  - 1 );
}
*/












