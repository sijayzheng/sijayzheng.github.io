function setCookie(c_name,value,expiredays)
{
	var exdate=new Date();
	exdate.setDate(exdate.getDate()+expiredays);
	document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}
function getCookie(c_name)
{
	if (document.cookie.length>0)
	  {
	  c_start=document.cookie.indexOf(c_name + "=");
	  if (c_start!=-1)
	    { 
	    c_start=c_start + c_name.length+1 ;
	    c_end=document.cookie.indexOf(";",c_start);
	    if (c_end==-1) c_end=document.cookie.length;
	    return unescape(document.cookie.substring(c_start,c_end));
	    } 
	  }
	return "";
}
function getalarmnamebystatus(status){
	var names = "";
	if(status=="0"){
		names="无状态";
	}else if(status=="1"){
		names="正常";
	}else if(status=="2"){
		names="预警";
	}else if(status=="3"){
		names="报警";
	}else if(status=="4"){
		names="数据获取异常";
	}else if(status=="5"){
		names="网络中断";
	}
	return names;
	
}