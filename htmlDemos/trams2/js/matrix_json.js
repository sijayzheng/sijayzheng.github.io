/********************************************************************/
/*																	*/
/*function：															*/
/*	根据json对象递归调用处理json的每一个属性，							*/
/*	将其按照模板HTML的设计代码填入相应的div中							*/
/*parameter：														*/
/*	json待处理的json对象，											*/
/*	name为待处理json对象的名字，										*/
/*	template为待处理对象对应的html模板								*/
/*		（非空表示上层有递归调用；									*/
/*		  为空表示													*/
/*			1.上层没有模板递归调用									*/
/*			2.本层模板需要根据json对象名查找模板）					*/
/*return:															*/
/*	需要填入div.innerHTML的HTML代码									*/
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/
function filljson(json,name,objname,index,template){

	var returnStr = "";
	var isparameter=false;//不是select、radio或者checkbox标签
			if(template!=""&&template!=undefined){
				if((/<\/select>/).test(template)){ //如果模板不为空并且为select标签
				  template=parameterSelect(json,template);
				  isparameter=true;
		         }else if((/type='radio'/).test(template)){//如果模板不为空并且为radio标签
		           template=parameterRadio(json,template);
		           isparameter=true;
		         }else if((/type='checkbox'/).test(template)){//如果模板不为空并且为checkbox标签
		           template=parameterCheckbox(json,template);
		           isparameter=true;
		         }
          	}
	//如果json对象为空则不做任何操作
	if(!json&&json!=0){
		try{
			if(dojo.byId(objname).value!=undefined){//如果当前处理的json对象可以找到对应的空间的value值
				dojo.byId(objname).value="";
			}
		}catch(exception){
			returnStr = "";
		}
		if(isparameter){
		    returnStr = template;
		}else{
	       returnStr = "";
		}
	}else{
		//如果json对象的构造函数为空表示json对象为Global或Math，将json的值直接返回
		if(!json.constructor){
			returnStr = json;
		}else{//json对象可能为Array/Object/非Array或Object的Value
			if(template==""||template==undefined){//如果模板为空或没有定义则根据json的名字找到相应模板
				try{
					if(dojo.byId(objname).value!=undefined){//如果当前处理的json对象可以找到对应的空间的value值
						template = "";
					}else{
						template = eval(name);//根据json的名字找到模板
					}
				}catch(exception){
					template = "";
				}
			}
			//如果模板不为空则根据模板中的逻辑字符完成逻辑替换
			if(template!=""&&template!=undefined){
				while(template.indexOf("<!--if{")>=0){//循环递归处理逻辑单元
					template = logicDeal(json,name,index,template);
				}
			}
			
			//如果模板不为空则根据模板替换变量
			if(json.constructor==Array){//如果json对象是Array，循环递归操作其Value
				if(template!=""&&template!=undefined){//模板非空
					var iteratorStr = template;
					for(i in json){//循环过程组织模板中需要替换的部分
						var subtemplatestr = "";
						try{//在本层模板中找到第一个符合<!--A{*}-->的子串，取得其中字符串作为子模板传递给下层处理
							subtemplatestr = iteratorStr.substring(iteratorStr.indexOf("<!--A{")+6,iteratorStr.indexOf("}-->",iteratorStr.indexOf("<!--A{")+6));
							subtemplate = eval(subtemplatestr);
						}catch(exception){//如果子模板找不到将子模板置为空
							subtemplate = "";
						}
						if(i!=json.length-1){//如果当前循环非数组尾则将<!--A{*}-->子串向后复制实现循环填写模板
							iteratorStr=iteratorStr.replace("<!--A{"+subtemplatestr+"}-->","<!--A{"+subtemplatestr+"}--><!--A{"+subtemplatestr+"}-->");
						}
						//将本层模板中的第一个<!--N-->替换为name，第一个<!--A{*}-->子串替换为其下层处理结果,
						iteratorStr = iteratorStr.replace("<!--N-->",name).replace("<!--I-->",index).replace("<!--A{"+subtemplatestr+"}-->",filljson(json[i],name+"["+i+"]",name+"["+i+"]",i,subtemplate));
					}
					returnStr = iteratorStr;
				}else{//模板为空
					var iteratorStr = "";
					for(i in json){//循环过程组织模板中需要替换的部分
						iteratorStr += filljson(json[i],name+"["+i+"]",name+"["+i+"]",i,template);
					}
					returnStr = iteratorStr;
				}
			}else if(json.constructor==Object){//如果json对象是Object，循环递归操作其Value
			
				if(template!=""&&template!=undefined){//模板非空
					returnStr = template;
					var ind = 0;
					
					for(i in json){//循环过程组织模板中需要替换的部分
						var subtemplate;
						try{//在本层模板中找到第一个符合<!--O{i}-->的子串，取得其中字符串作为子模板传递给下层处
							subtemplate = eval(i);
						}catch(exception){//如果子模板找不到将子模板置为空
							subtemplate = "";
						}
						//将本层模板中的第一个<!--N-->替换为name，第一个<!--O{i}-->子串替换为其下层处理结果
						returnStr = returnStr.replace("<!--N-->",name).replace("<!--I-->",index).replace("<!--O{"+i+"}-->",filljson(json[i],i,name==""?i:(name+"."+i),ind++,subtemplate));
					}
				}else{//模板为空
					var returnStr = "";
					var ind = 0;
					for(i in json){
					   if(i=='resultMessage'){
					   
					      if(document.getElementById("fg_body")!=undefined&&document.getElementById('current_color_style').href=="/ITSM/pbac/css/colors.normal.css"){
					      document.getElementById("fg_body").style.top="40";
					      document.getElementById("fg_body").style.bottom="0";
					        if(document.getElementById("rmb_motai")!=undefined){
					      document.getElementById("rmb_motai").style.top="65";
					      document.getElementById("rmb_motai").style.height="99%";
					      }
					        if(document.getElementById("rmb_motai1")!=undefined){
					         document.getElementById("rmb_motai1").style.top="65";
					      document.getElementById("rmb_motai1").style.height="99%";
					        }
					      }
					    
					   }
						returnStr += filljson(json[i],i,name==""?i:(name+"."+i),ind++,template);
					}
				}
				
			}else{//如果json对象是非Array和Object的其他value,根据name处理页面对应变量的修改
				if(template!=""&&template!=undefined){//模板非空
					returnStr = template.replace("<!--N-->",name).replace("<!--V-->",json).replace("<!--I-->",index);
				}else{//模板为空
					returnStr = json;
				}
			}
			//如果可以在页面上找到对应json对象的div控件则将递归运行结果填入div的innerHTML中
			if(dojo.byId(objname)){
				try{
					dojo.byId(objname).innerHTML = returnStr;
				}catch(exception){
					dojo.byId(objname).value = returnStr;
				}
			}
		}
	}
	//nowHtmlHeight();
	return returnStr;
}
/********************************************************************/
/*																	*/
/*function：															*/
/*	处理json中的select标签											*/
/*																	*/
/*parameter：														*/
/*	value待处理的json对象，											*/
/*	name为待处理json对象的名字，										*/
/*return:															*/
/*	无																*/
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/
function selectjson(json,name,objname,index){
	//如果模板不为空则根据模板替换变量
	if(json.constructor==Array){//如果json对象是Array，循环递归操作其Value
		for(i in json){//循环过程组织模板中需要替换的部分
			selectjson(json[i],name+"["+i+"]",name+"["+i+"]",i);
		}
	}else if(json.constructor==Object){//如果json对象是Object，循环递归操作其Value
		var ind = 0;
		for(i in json){
			selectjson(json[i],i,name==""?i:(name+"."+i),index);
		}
	}else{//如果json对象是非Array和Object的其他value,根据name处理页面对应变量的修改
		try{
			if((/select/).test(""+dojo.byId(name).type)){
				if(document.getElementsByName(name).length>1){
					document.getElementsByName(name)[index].value=json;
				}else{
					dojo.byId(name).value=json;
				}
			}
		}catch(exception){
		}
	}
}
/********************************************************************/
/*																	*/
/*function：															*/
/*	根据if条件子句判断逻辑，组织template中需要逻辑处理的内容			*/
/*																	*/
/*parameter：														*/
/*	value待处理的json对象，											*/
/*	name为待处理json对象的名字，										*/
/*	template为待处理对象对应的html模板								*/
/*return:															*/
/*	逻辑处理之后的template											*/
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/
function logicDeal(value,name,index,template){
	var condition = template.substring(template.indexOf("<!--if{")+7,template.indexOf("}",template.indexOf("<!--if{")+7));
	var id = template.substring(template.indexOf("}",template.indexOf("<!--if{")+7)+1,template.indexOf("-->",template.indexOf("<!--if{")+7));
	if(template.indexOf("<!--else"+id+"-->")>=0){//如果模板中有else
		if(eval(condition)){//如果条件成立
			template = template.replace(new RegExp("<!--else"+id+"-->.*<!--endif"+id+"-->"),"").replace(new RegExp("<!--if{.*}"+id+"-->"),"");//去掉if头和else与endif中间的部分
		}else{//如果条件不成立
			template = template.replace(new RegExp("<!--if{.*}"+id+"-->.*<!--else"+id+"-->"),"").replace(new RegExp("<!--endif"+id+"-->"),"");//去掉if和else中间的部分与endif尾
		}
	}else{//如果模板中没有else
		if(eval(condition)){//如果条件成立
			template = template.replace(new RegExp("<!--endif"+id+"-->"),"").replace(new RegExp("<!--if{.*}"+id+"-->"),"");//去掉if的头为
		}else{//如果条件不成立
			template = template.replace(new RegExp("<!--if{.*}"+id+"-->.*<!--endif"+id+"-->"),"");//去掉整个包括内容的if体
		}
	}
	return template;
}

/********************************************************************/
/*																	*/
/*function：															*/
/* 在select标签中，                                                          */
/*	根据option标签value值是进行判断，组织template中需要逻辑处理的内容		*/
/*parameter：														*/
/*	value待处理的json对象，											*/
/*	template为待处理对象对应的html模板								    */
/*return:															*/
/*	逻辑处理之后的template											    */
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/
function parameterSelect(value,template){
  if(value!=null&&value!=undefined&&value!=""){
   template=template.replace(new RegExp("selected"),new RegExp(""));//取消标签中的默认选项
   template=template.replace(new RegExp("value='"+value+"'"),new RegExp("value='"+value+"'selected"));//若<option>标签的value值等于json对象，则此<option>标签选中
   }
   return template;
}

/********************************************************************/
/*																	*/
/*function：															*/
/*	根据radio标签value值是进行判断，组织template中需要逻辑处理的内容         */
/*																	*/
/*parameter：														*/
/*	value待处理的json对象，											*/
/*	template为待处理对象对应的html模板								    */
/*return:															*/
/*	逻辑处理之后的template											    */
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/
function parameterRadio(value,template){
  if(value!=null&&value!=undefined){
     template=template.replace(new RegExp("checked"),new RegExp(""));//取消标签中的默认选项
     template=template.replace(new RegExp("value='"+value+"'"),new RegExp("value='"+value+"' checked"));//若radio标签的value等于json对象的值，则此radio标签选中
   }
   
   return template;
}
/********************************************************************/
/*																	*/
/*function：															*/
/*	根据checkbox标签中的value值是进行判断，组织template中需要逻辑处理的内容   */
/*																	*/
/*parameter：														*/
/*	value待处理的json对象，											*/
/*	template为待处理对象对应的html模板								    */
/*return:															*/
/*	逻辑处理之后的template											    */
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/
function parameterCheckbox(json,template){
  if(json!=null&&json!=undefined){
     if(json.constructor==Array){//如果json对象是Array
	     for(i in json){
	       template=template.replace(new RegExp("checked"),new RegExp(""));//取消标签中的默认选项
	        template=template.replace(new RegExp("value='"+json[i]+"'"),new RegExp("value='"+json[i]+"' checked"));//若复选框的value值等于json对象的值，则此复选框选中
	     }
     }else{
      	     template=template.replace(new RegExp("checked"),new RegExp(""));//取消标签中的默认选项
      	     template=template.replace(new RegExp("value='"+json+"'"),new RegExp("value='"+json+"' checked"));//若复选框的value值等于json的值，则此复选框选中
     }
   }
   return template;
}


/********************************************************************/
/*																	*/
/*function：															*/
/*	checkbox标签的onclick()事件，即复选框全选功能的实现                      */
/*																	*/
/*parameter：														*/
/*	checkboxName为复选框列表的名字，								   */
/*	selectAllname为全选复选框的ID								        */
/*auther:															*/
/*	atom.w															*/
/*																	*/
/********************************************************************/

function   parameterCheckboxSelectAll(checkboxName,selectAllName){   
	var   all   =   document.getElementById(selectAllName);   
	var   els   =   document.getElementsByName(checkboxName);   
	for(i=0;i<els.length;i++){   
	els[i].checked   = all.checked; //选中所有复选框  
	}   
 } 
 
 /********************************************************************/
/*																	*/
/*function：															*/
/*	frame自适应高度                  */
/********************************************************************/
  
 function   nowHtmlHeight(){
if(document.getElementById("fg_body")!=undefined){
               if(document.getElementById("tree")==undefined){
                var bh=this.document.body.scrollHeight;
	      document.getElementById("fg_body").style.height=bh-5;
	      
	        if(document.getElementById("rmb_motai")!=undefined){
					      document.getElementById("rmb_motai").style.top="65";
					      document.getElementById("rmb_motai").style.height=bh-25;
					      }
					        if(document.getElementById("rmb_motai1")!=undefined){
					         document.getElementById("rmb_motai1").style.top="65";
					      document.getElementById("rmb_motai1").style.height=bh-25;
					        }
               }
          
	      }
  //如果父级frame存在
 /*if(parent.document.getElementById("main_targetid")!=undefined&&parent.document.getElementById("main_targetid")!=null&&document.getElementById("main_targetid")==undefined){
	 //如果现有高度小于父级frame高度
//	 	      	     	  	  alert(parent.document.body.offsetHeight-110+"aa"+this.document.body.scrollHeight);
	
	       if(document.getElementById("edit")==undefined){
	         parent.document.getElementById("main_targetid").style.height=100;
	               if(parent.document.body.offsetHeight-110>document.body.scrollHeight){
	                  parent.document.getElementById("main_targetid").style.height=parent.document.body.offsetHeight-110;
	                   
	               }else {
	               // alert("bbb"+this.document.body.scrollHeight);
	                   parent.document.getElementById("main_targetid").style.height=this.document.body.scrollHeight;
	               }
	       }else if(document.getElementById("edit").style.display=="none"){
	             parent.document.getElementById("main_targetid").style.height=100;
	               if(parent.document.body.offsetHeight-110>document.body.scrollHeight){
     	     	  parent.document.getElementById("main_targetid").style.height=parent.document.body.offsetHeight-110;
	               
	               }else {
	              
            	  	  parent.document.getElementById("main_targetid").style.height=this.document.body.scrollHeight;
	               }
	       }
	       //alert("aa");
	      // if(document.getElementById("fg_body")!=undefined){
	      // document.getElementById("fg_body").style.height="100%";
	      // }
	 
    }*/
    
  }
  
  
   function   nowmonitorHeight(){
   if(document.getElementById("fg_body")!=undefined){
           var bh=this.document.body.scrollHeight;
	      document.getElementById("fg_body").style.height=bh+5;
	      }
  //如果父级frame存在
  
 /*if(parent.parent.document.getElementById("main_targetid")!=undefined&&parent.parent.document.getElementById("main_targetid")!=null&&document.getElementById("main_targetid")==undefined){
	 //如果现有高度小于父级frame高度
	 	              parent.parent.document.getElementById("main_targetid").style.height=100;
	 
	  if(parent.parent.document.body.offsetHeight-110>document.body.scrollHeight){
	      
     	     	  parent.parent.document.getElementById("main_targetid").style.height=parent.parent.document.body.offsetHeight-50;
	  }else {//如果现有高度大于父级frame高度
	     
            	  	  parent.parent.document.getElementById("main_targetid").style.height=this.document.body.scrollHeight+200;
	   	    }   
	
    }*/
    
  }
  
  //监控状态曲线统计表滚动条最外显示
  function reportf(obj,h){

  obj.height=h;
   if(parent.document.getElementById("reportf")!=undefined){
   obj.height=100;
   obj.height=h;
   parent.document.getElementById("reportf").style.height=h;
  
    if(parent.parent.document.getElementById("main_targetid")!=undefined&&parent.parent.document.getElementById("main_targetid")!=null&&document.getElementById("main_targetid")==undefined){
	 //如果现有高度小于父级frame高度
	  parent.parent.document.getElementById("main_targetid").style.height=100;
	  if(parent.parent.document.body.offsetHeight-110>h){
	            //  parent.parent.document.getElementById("main_targetid").style.height=100;
     	     	  parent.parent.document.getElementById("main_targetid").style.height=parent.parent.document.body.offsetHeight-50;
	  }else {//如果现有高度大于父级frame高度
	   	   //  parent.parent.document.getElementById("main_targetid").style.height=100;
            	  	  parent.parent.document.getElementById("main_targetid").style.height=h+250;
	   	    }   
	
    }
    }
  }
  
  
     function   nowworkflowHeight(){
  //如果父级frame存在
  
 if(parent.parent.document.getElementById("main_targetid")!=undefined&&parent.parent.document.getElementById("main_targetid")!=null&&document.getElementById("main_targetid")==undefined){
	 //如果现有高度小于父级frame高度
	 	              parent.parent.document.getElementById("main_targetid").style.height=100;
	  if(parent.parent.document.body.offsetHeight-110>document.body.scrollHeight){
	           //   parent.parent.document.getElementById("main_targetid").style.height=100;
     	     	  parent.parent.document.getElementById("main_targetid").style.height=parent.parent.document.body.offsetHeight-50;
	  }else {//如果现有高度大于父级frame高度
	     
	   	   //  parent.parent.document.getElementById("main_targetid").style.height=100;
            	  	  parent.parent.document.getElementById("main_targetid").style.height=this.document.body.scrollHeight+320;
	   	    }   
	
    }
    
  }
  
  