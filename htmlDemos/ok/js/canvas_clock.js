day_arr=["周日", "周一", "周二","周三","周四","周五","周六"];
month_arr=["January","February","March","April","May","June","July","August","September","October","November","December"];

function clock_digital(size, cns, clockd)
{
  var now=new Date();
  var time_off=(clockd.hasOwnProperty("timeoffset"))?clockd["timeoffset"]:0;
  now.setTime(now.getTime()+time_off*1000);
  var milisec=now.getMilliseconds();
  var sec=now.getSeconds();
  var min=now.getMinutes();
  var hour=now.getHours();

  cns.clearRect(0,0,size,size);

  cns.beginPath();
  if(clockd.hasOwnProperty("bg_color")){cns.fillStyle=clockd["bg_color"];}else{cns.fillStyle="#ffffff";}
  cns.rect(0,0,size,size);
  cns.fill();
  cns.closePath();
  if(clockd.hasOwnProperty("bgLoaded") && clockd.bgLoaded==1){if(clockd.hasOwnProperty("bg_opacity")){cns.globalAlpha=clockd["bg_opacity"];cns.drawImage(clockd.bgImage,0,0,size,size);cns.globalAlpha=1;}}

  if(hour<10){hour="0"+hour;}
  if(min<10){min="0"+min;}
  if(sec<10){sec="0"+sec;}
  cns.fillStyle=(clockd.hasOwnProperty("dial1_color"))?clockd["dial1_color"]:"#333";
  cns.textBaseline="middle";
  cns.textAlign="center";
  cns.font=size/8+"px Arial";
  cns.fillText(hour+":"+min+":"+sec,(size/2),size/2.5);

  if(clockd.hasOwnProperty("date_add") && clockd.date_add)
  {
    date_add((size/2),size/5*3+size/10,size,cns, clockd);
  }

  clockd.timer=setTimeout(function(){clock_digital(size, cns, clockd)},50);
}


function date_add(x, y, size, cns, clockd)
{
  if(!clockd.hasOwnProperty("date_add"))
  {
    return;
  }
  
  var now=new Date();
  var time_off=(clockd.hasOwnProperty("timeoffset"))?clockd["timeoffset"]:0;
  now.setTime(now.getTime()+time_off*1000);
  var day=now.getDate();
  var year=now.getFullYear();
  var month=now.getMonth()+1;

  if((month)<10){month="0"+(month);}
  if(day<10){day="0"+day;}

  cns.lineWidth=1;
  cns.fillStyle=clockd["date_add_color"];
  cns.textBaseline="middle";
  cns.textAlign="center";
  cns.font=size/20+"px Arial";

  switch(parseInt(clockd["date_add"]))
  {
    case 1:
      cns.fillText(day+"/"+month+"/"+year,x,y);
    break;
    case 2:
      cns.fillText(month+"/"+day+"/"+year,x,y);
    break;
    case 3:
      day=now.getDay();
      cns.fillText(day_arr[day],x,y);
    break;
    case 4:
      month=now.getMonth();
      cns.fillText(month_arr[month]+" "+day,x,y);
    break;
    default:
      month=now.getMonth();
      cns.fillText(day+" "+month_arr[month],x,y);
  }
}