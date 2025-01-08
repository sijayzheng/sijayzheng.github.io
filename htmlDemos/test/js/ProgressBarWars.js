$.fn.extend({ProgressBarWars: function(opciones) {
	var ProgressBarWars=this;
	var theidProgressBarWars=$(ProgressBarWars).attr("id");
	var styleUnique = Date.now();
    var StringStyle="";
	var winheight = window.innerHeight;
	if(winheight>800){
		defaults = {
				porcentaje:"100",
				tiempo:1000,
				color:"#7DE5BA",
				estilo:"yoda",
				tamanio:"150px",
				alto:"9px",
				name:'aaaaaaaa',
				num:'2'
				}	
		var opciones = $.extend({}, defaults, opciones);
		if(opciones.color!=''){StringStyle="<style>.color"+styleUnique+"{ border-radius: 2px;display: block; width: 0%;background-color: "+opciones.color+";}</style>";opciones.estilo="color"+styleUnique;}
		$(ProgressBarWars).before(StringStyle);
		$(ProgressBarWars).append('<span class="live-system">'+opciones.name+'</span>&nbsp;&nbsp;&nbsp;&nbsp;重要度&nbsp;&nbsp;&nbsp;&nbsp;<span class="live-system">'+(opciones.num==1?'一般':'重要')+'</span>&nbsp;&nbsp;&nbsp;&nbsp;健康度&nbsp;&nbsp;&nbsp;&nbsp;'+opciones.porcentaje+'%&nbsp;<span class="live-bilv"><span class="barControl" style="width:'+opciones.tamanio+';">&nbsp;&nbsp;<div class="barContro_space"><span class="'+opciones.estilo+'" style="height: '+opciones.alto+';width:150px"  id="bar'+theidProgressBarWars+'"></span></div></span></span>');
		//$(ProgressBarWars).append('<span class="live-system">'+opciones.name+'</span>&nbsp;&nbsp;&nbsp;健康度&nbsp;&nbsp;'+opciones.porcentaje+'%&nbsp;<span class="live-bilv"><span class="barControl" style="width:'+opciones.tamanio+';"><div class="barContro_space"><span class="'+opciones.estilo+'" style="height: '+opciones.alto+';width:95px"  id="bar'+theidProgressBarWars+'"></span></div></span></span>');
		$("#bar"+theidProgressBarWars).animate({width: opciones.porcentaje+"%"},opciones.tiempo);
		this.mover = function(ntamanio) {
			$("#bar"+$(this).attr("id")).animate({width:ntamanio+"%"},opciones.tiempo);
		};
		return this;	
	}else{
		//alert(winheight);
		defaults = {
				porcentaje:"100",
				tiempo:1000,
				color:"#7DE5BA",
				estilo:"yoda",
				tamanio:"80px",
				alto:"6px",
				name:'aaaaaaaa',
				num:'2'
				}
		var opciones = $.extend({}, defaults, opciones);
		if(opciones.color!=''){StringStyle="<style>.color"+styleUnique+"{ border-radius: 2px;display: block; width: 0%;background-color: "+opciones.color+";}</style>";opciones.estilo="color"+styleUnique;}
		$(ProgressBarWars).before(StringStyle);
		$(ProgressBarWars).append('<span class="live-system">'+opciones.name+'</span>&nbsp;&nbsp;&nbsp;&nbsp;重要度&nbsp;&nbsp;&nbsp;&nbsp;<span class="live-system">'+(opciones.num==1?'一般':'重要')+'</span>&nbsp;&nbsp;&nbsp;&nbsp;健康度&nbsp;&nbsp;&nbsp;&nbsp;'+opciones.porcentaje+'%&nbsp;<span class="live-bilv"><span class="barControl" style="width:'+opciones.tamanio+';">&nbsp;&nbsp;<div class="barContro_space"><span class="'+opciones.estilo+'" style="height: '+opciones.alto+';width:80px"  id="bar'+theidProgressBarWars+'"></span></div></span></span>');
		//$(ProgressBarWars).append('<span class="live-system">'+opciones.name+'</span>&nbsp;&nbsp;&nbsp;健康度&nbsp;&nbsp;'+opciones.porcentaje+'%&nbsp;<span class="live-bilv"><span class="barControl" style="width:'+opciones.tamanio+';"><div class="barContro_space"><span class="'+opciones.estilo+'" style="height: '+opciones.alto+';width:95px"  id="bar'+theidProgressBarWars+'"></span></div></span></span>');
		$("#bar"+theidProgressBarWars).animate({width: opciones.porcentaje+"%"},opciones.tiempo);
		this.mover = function(ntamanio) {
			$("#bar"+$(this).attr("id")).animate({width:ntamanio+"%"},opciones.tiempo);
		};
		return this;	
	}
	
			 
	}
});

$.fn.extend({ProgressBarWarsold: function(opciones) {
	var ProgressBarWars=this;
	var theidProgressBarWars=$(ProgressBarWars).attr("id");
	var styleUnique = Date.now();
    var StringStyle="";
	
	defaults = {
	porcentaje:"100",
	tiempo:1000,
	color:"",
	estilo:"yoda",
	tamanio:"30%",
	alto:"6px"
	}
	
	var opciones = $.extend({}, defaults, opciones);
	if(opciones.color!=''){StringStyle="<style>.color"+styleUnique+"{ border-radius: 2px;display: block; width: 0%;background-color: "+opciones.color+";}</style>";opciones.estilo="color"+styleUnique;}
	$(ProgressBarWars).before(StringStyle);
	$(ProgressBarWars).append('<span class="barControl" style="width:'+opciones.tamanio+';"><div class="barContro_space"><span class="'+opciones.estilo+'" style="height: '+opciones.alto+';"  id="bar'+theidProgressBarWars+'"></span></div></span>');
	$("#bar"+theidProgressBarWars).animate({width: opciones.porcentaje+"%"},opciones.tiempo);
	this.mover = function(ntamanio) {
	$("#bar"+$(this).attr("id")).animate({width:ntamanio+"%"},opciones.tiempo);
	};
return this;			 
}
});