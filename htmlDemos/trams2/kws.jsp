<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.matrix.common.pbac.user.User"%>
<%@ page import="com.matrix.common.pbac.web.PBACRequestContext"%>
<%@ page import="com.matrix.common.pbac.web.imp.goal.WebPBACRequestContext"%>
<%
	User user = WebPBACRequestContext.getPBACRequestContext(request)
			.getRequestUser();
	String userid = user.getId();
	String username = user.getNames();
%>

<!DOCTYPE html>
<html lang="zh">
<head>
<title>知识库</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/TRAMS/js/dist/optiscroll.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui_old.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/font2/iconfont.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sweet-white-alert.css">
<script src="<%=request.getContextPath()%>/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/TRAMS/js/dist/jquery.optiscroll.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.easyui5.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/sweet-alert.min.js"></script>
<link rel="stylesheet" href="/TRAMS/js/layui/css/layui.css" media="all">
<script src="/TRAMS/js/layui/layui.js" charset="utf-8"></script>


<style>
html,body {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
	background: #EEF3FD;
}

h2 {
	font-size: 1.5em;
	font-weight: bold;
}

.main {
	width: 100%;
	height: calc(100% - 102px);;
	font-size: 14px;
	overflow: auto;
	padding:2px 8px;
}

.left {
	width: 19%;
	height: 100%;
	float: left;
	margin-right: 8px;
	overflow: auto;
}

.top {
	width: 100%;
	height: 80px;
    margin: 5px 0;
    text-align: center;
    background: #fff;
   /*  border-bottom:1px solid #ddd; */
}

.center {
	width: calc(62% - 32px);
	height: 100%;
	float: left;
	background: #fff;
}

.right {
	width: 19%;
	height: 100%;
	float: left;
	background: #fff;
	margin-left:8px;
}

.iconfont {
	font-size: 14px;
}

.layui-colla-title {
	background: #fff;
	border: 0px;
	border-top: 0px solid #ddd;
	padding: 0 15px 0 15px;
}

.layui-colla-item,.layui-collapse {
	border: 0px solid #fff;
}

.layui-colla-icon {
	left: 90%;
}

.left-baseinfo {
	margin-left: 15px;
	width: 100%;
	height: 38px;
	line-height: 38px;
	font-size: 14px;
}

.minesbox {
	width: 250px;
	float: right;
	height: 100%;
	margin-right: 10px;
}

.mines {
	margin: 10px 0;
}

.mines li {
	width: calc(100%/ 3);
	text-align: center;
	float: left;
	display: list-item;
	margin-bottom: 20px;
}

.mines a {
	font-size: 12px;
	transition-duration: .3s;
	max-width: 100%;
	display: inline-block;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	cursor: pointer;
}

.mines .num {
	font-size: 26px;
	display: block;
	transition-duration: .3s;
}

.mines .txt {
	line-height: 1;
	display: block;
}

.layui-form-checkbox {
	display: block;
}

.searchbox {
	width: 50%;
    float: left;
    padding: 15px 0;
    height: auto;
    margin-left: 25%;
}

.searchinput {
	width: calc(100% - 150px);
	float: left;
	margin-right: 0px;
	height: 50px;
}

.layui-btn {
	display: inline-block;
	height: 50px;
	line-height: 50px;
	background-color: #6ABBE8;
}

.datebox {
	padding: 10px 15px;
	padding-top: 20px;
}

.datelabel {
	color: #666;
	margin: 10px;
}

.dateselect {
	width: 95%;
	display: inline;
}

.layui-colla-content {
    background: #fff;
    margin-bottom:8px;
}

.layui-colla-content {
	padding-top: 0px;
}

.layui-form-pane .layui-form-checkbox {
	margin: 15px 0 15px 10px;
}

.center-title {
	height: 38px;
	line-height: 38px;
	padding-left: 20px;
	font-size: 12px;
	color: #666;
	margin-top: 10px;
	border-bottom: 1px solid #E6E6E6;
}
.center-content{
	height: calc(100% - 48px - 48px);
}
.center-order{
	float: right;
	padding-right: 5px;
}
.orderitems{
	display: none;
	border: 1px #e6e6e6 solid;
	padding: 0 15px;
	position: absolute;
    z-index: 1000;
    background: #fff;
}
.center-order:hover>.orderitems {
	display: block;
}
.orderitem{
	line-height: 24px;
	cursor: pointer;
}
.orders{
	width: 90px;
	text-align: center;
	cursor: pointer;
}
.center-footer{
	height: 48px;
	width: 100%;
	text-align: center;
}
#fream {
    width: 100%;
    position: fixed;
    right: 0px;
    height: 100%;
    z-index: 9999;
    display: none;
}
.kw{
	margin: 10px 20px;
    box-shadow: 1px 1px 4px #ccc;
    padding: 10px;
    background-color: white;
}
.kw-title{
	margin-bottom: 10px;
    font-size: 18px;
    background-color: #f4f4f4;
    line-height: 42px;
    padding-left: 20px;
    color: #0066CC;
    cursor: pointer;
}
.kw-title:hover {
    text-decoration-line: underline;
}
.kw-content{

}
.kw-footer{
	margin-top: 10px;
    color: #999999;
}
.kw-createuser{
	margin-right: 20px;
}
.kw-createtime{

}
.newest{

}
.hotest{

}
.kwtop{
	margin: 16px 20px;
	cursor: pointer;
	height: 20px;
	border-bottom: 1px #e6e6e6 solid;
}
.kwtop>div{
	display: inline-block;
}
.num{
	width: 20px;
	height: 16px;
	background-color: #8eb9f5;
	text-align: center;
	color: #fff;
	margin-right: 4px;
}
.kwtop1{
	background-color: #f54545;
}
.kwtop2{
	background-color: #ff8547;
}
.kwtop3{
	background-color: #ffac38;
}

.kwtop:hover>.kwtop-title{
	color: #2866bd;
}
.kwtop-createtime{
	float: right;
}

</style>
</head>

<body>
	<div class="animated bounceInRight" id="fream">
		<iframe id="ifrm" src="" width="100%" height="99.6%" scrolling="no" frameborder="0"></iframe>
	</div>
	<form id="layform" class="layui-form layui-form-pane" style="height:100%;">
		<div class="top">
			<div class="minesbox">
				<ul class="mines">
					<li>
						<a onclick="dosearch('uploaded')">
							<span class="txt">
								我的创建(
								<span id="uploaded">0</span>
								)
							</span>
						</a>
					</li>
					<li>
						<a onclick="dosearch('favorited')">
							<span class="txt">
								我的收藏(
								<span id="favorited">0</span>
								)
							</span>
						</a>
					</li>
					<li>
						<a onclick="dosearch('appended')">
							<span class="txt">
								我的补充(
								<span id="appended">0</span>
								)
							</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="searchbox">
				<img src="/TRAMS/images/knowledge.png" style="float: left;margin-right: 10px;opacity:0.5;" width="48px" height="35px">
				<div style="position: absolute;top:53px;left:24.7%;color: #6ABBE8;font-weight: bold;opacity:0.9">知识搜索</div>
				<input type="text" id="searchinput" placeholder="" autocomplete="off" class="layui-input searchinput">
				<button type="button" class="layui-btn" onclick="searcher()">
					<i class="iconfont icon-sousuo"></i>&nbsp;搜索
				</button>
			</div>
		</div>
		<div class="main">
			<div class="left">
				<div class="m-wrapper optiscroll" id="lefttreediv">
					<div class="layui-collapse">
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">时间筛选</h2>
							<div class="layui-colla-content layui-show">
								<div class="datebox">
									<input type="text" class="layui-input dateselect" id="dateselect" placeholder=" ~ ">
								</div>
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">重要等级</h2>
							<div class="layui-colla-content layui-show">
								<div id="levels-container"></div>
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">业务分类</h2>
							<div class="layui-colla-content layui-show">
								<div id="bussinesstype-container"></div>
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">服务种类</h2>
							<div class="layui-colla-content layui-show">
								<div id="servicetype-container"></div>
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">信息来源</h2>
							<div class="layui-colla-content layui-show">
								<div id="sourcetype-container"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</form>
			<div class="center">
				<div class="center-title">已为您找到<span id="kwnum">0</span>条相关结果
					<div class="center-order">
						<div class="orders"><i class="iconfont icon-shaixuan"></i><span id="orders-text">按时间降序</span></div>
						<div class="orderitems">
							<div class="orderitem" onclick="orderby('createtime','按时间降序')">按时间降序</div>
							<div class="orderitem" onclick="orderby('browertimes','按浏览数量')">按浏览数量</div>
						</div>
					</div>
				</div>
				<div class="center-content optiscroll">
					<div id="center-content"></div>
				</div>
				<div class="center-footer" id="footer"></div>
			</div>
			<div class="right">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					<ul class="layui-tab-title">
						<li class="layui-this">最新知识</li>
						<li>热门知识</li>
					</ul>
					<div class="layui-tab-content" style="height: 100%;">
						<div class="layui-tab-item layui-show" id="newest" class="newest">最新内容</div>
						<div class="layui-tab-item" id="hotest" class="hotest">最热内容</div>
					</div>
				</div>
			</div>
		</div>
		<div style="display: none;">
			<input id="mineselect" type="text">
			<input id="kwid" type="text">
			<form id="ff" class="easyui-form">
				<input type="text" id="daterange" name="daterange">
				<input type="text" id="levels" name="levels">
				<input type="text" id="bussinesstype" name="bussinesstype">
				<input type="text" id="servicetype" name="servicetype">
				<input type="text" id="sourcetype" name="sourcetype">
				<input type="text" id="condition" name="condition">
				<input type="text" id="order" name="order" value="createtime">
			</form>
		</div>

	<script>
		$("#lefttreediv").height('100%');
		$("#lefttreediv").optiscroll();

		$('.center-content').height('86%');
		$('.center-content').optiscroll();

		layui.use([ 'element', 'layer', 'form', 'laydate'], function() {
			var element = layui.element;
			var layer = layui.layer;
			var form = layui.form;
			var laydate = layui.laydate;

			form.on('checkbox(levels)', function(e){
				let ele = $('#levels-container>input:checked');
				if(ele.length > 0) {
					let val = '';
					$.each(ele, function(i, n) {
						val += ',' + n.value;
					});
					$('#levels').val(val.substring(1));
				} else {
					$('#levels').val('');
				}
		    	dosearch();
			});
			form.on('checkbox(bussinesstype)', function(e){
				let ele = $('#bussinesstype-container>input:checked');
				if(ele.length > 0) {
					let val = '';
					$.each(ele, function(i, n) {
						val += ',' + n.value;
					});
					$('#bussinesstype').val(val.substring(1));
				} else {
					$('#bussinesstype').val('');
				}
		    	dosearch();
			});
			form.on('checkbox(servicetype)', function(e){
				let ele = $('#servicetype-container>input:checked');
				if(ele.length > 0) {
					let val = '';
					$.each(ele, function(i, n) {
						val += ',' + n.value;
					});
					$('#servicetype').val(val.substring(1));
				} else {
					$('#servicetype').val('');
				}
		    	dosearch();
			});
			form.on('checkbox(sourcetype)', function(e){
				let ele = $('#sourcetype-container>input:checked');
				if(ele.length > 0) {
					let val = '';
					$.each(ele, function(i, n) {
						val += ',' + n.value;
					});
					$('#sourcetype').val(val.substring(1));
				} else {
					$('#sourcetype').val('');
				}
		    	dosearch();
			});

			laydate.render({
			    elem: '#dateselect',
			    range: '~',
			    done: function(value, date, endDate){
			    	if (value) {
				    	$('#daterange').val(value);
					} else{
						$('#daterange').val('')
					}
				    dosearch();
				}
			});
		});
		$.ajax({
			url : '<%=request.getContextPath()%>/knowledge?operateFlag=getparamdic',
			type : 'json',
			async:false,
			success : function(data) {
				loaddata(data.map.LEVEL, '#levels-container','levels');
				loaddata(data.map.BUSSINESSTYPE, '#bussinesstype-container','bussinesstype');
				loaddata(data.map.SERVICETYPE, '#servicetype-container','servicetype');
				loaddata(data.map.SOURCETYPE, '#sourcetype-container','sourcetype');
			}
		});

		$.ajax({
			url : '<%=request.getContextPath()%>/knowledge?operateFlag=getuserinfo&user='+encodeURI(encodeURI("<%="'" + userid + "','" + username + "'"%>")),
			type : 'post',
			success : function(data) {
				$('#uploaded').text(data.knowledgecount);
				$('#favorited').text(data.favoritecount);
				$('#appended').text(data.remarkcount);
			}
		});

		$.ajax({
			url : '<%=request.getContextPath()%>/knowledge?operateFlag=topnowledge',
			type : 'post',
			success : function(data) {
				toprender(data.newtop, '#newest', true);
				toprender(data.hottop, '#hotest', false);
			}
		});

		$(function(){
			$('#searchinput').keydown(function(event) {
				event = document.all ? window.event : event;
				if ((event.keyCode || event.which) == 13) {
					searcher();
				}
			});
			//dosearch();
		});

		function loaddata(data, selector, filter) {
			let	html = '';
			for (k in data) {
				html += '<input type="checkbox" title="' + data[k] + '" lay-skin="primary" value="' + k + '" lay-filter="' + filter + '">';
			}
			$(selector).html(html);
		}

		function searcher(){
			$('#condition').val($('#searchinput').val());
			dosearch();
		}

		function dosearch(value){
			$('#mineselect').val(value);
			if (value) {
				$.ajax({
					url : '<%=request.getContextPath()%>/knowledge?operateFlag=getall'+value+'&user='+encodeURI(encodeURI("<%="'" + userid + "','" + username + "'"%>"))+'&order='+$('#order').val(),
					type : 'post',
					success : function(data) {
						datarender(data.datalist);
					}
				});
			} else {
				let datas = 'searchPO.' + $('#ff').serialize().replace(/&/gm, '&searchPO.');
				$.ajax({
					url : '<%=request.getContextPath()%>/knowledge?operateFlag=searchknowledge',
					type : 'post',
					dataType : 'json',
					data : datas,
					success : function(data) {
						datarender(data.datalist);
					}
				});
			}
		}



		function openjsp(e) {
			$('#kwid').val(e.dataset.kwid);
			document.getElementById('fream').style.display = 'block';
			$('#ifrm').attr('src', 'knowledges.jsp');
		}

		function closejsp() {
			document.getElementById('fream').style.display = 'none';
			$('#kwid').val('');
		}

	</script>
</body>

</html>
