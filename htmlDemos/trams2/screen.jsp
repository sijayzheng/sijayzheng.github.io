<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
	<head>
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title>IT资源监控运维平台</title>
		<link rel="shortcut icon" href="../matrix.ico" />
		<script src="http://127.0.0.1:8080/TRAMS/js/base-loading.js"></script>
		<link rel="stylesheet" type="text/css" href="css/screen.css">
		<link rel="stylesheet" type="text/css"
			href="font2/iconfont.css">
		<link rel="stylesheet" type="text/css"
			href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/jquery.custom.monitordataview.css"  type="text/css"/>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easing.min.js"></script>
		<script type="text/javascript" src="js/jquery.easy-ticker.min.js"></script>
		<script type="text/javascript" src="js/echarts.min.js"></script>
		<script type="text/javascript" src="js/echarts.custom.js"></script>
		<script type="text/javascript" src="js/macarons.js"></script>
		<script type="text/javascript" src="http://127.0.0.1:8080/TRAMS/js/jquery.custom.monitordataview.js"></script>

		<style type="text/css">
</style>
	</head>
	<body style="background-color: #011727; overflow: hidden; width: 100%; height: 100%" onload="initdatas()">
		<div class="body-index">
			<div class="bodystyle">
				<canvas id="canvas" width="1950px" height="800px"></canvas>
				<canvas id="canvasbg" width="1950px" height="800px"></canvas>
			</div>
			<div class="title-left-style">
				<div class="title-left-style-top">
					<div class="title-left-top-circles" style="margin-left: 0em">
						<div class="clock hours">
							<div class="left">
								<div></div>
							</div>
							<div class="right">
								<div></div>
							</div>
							<div class="progress">
								<span>0</span>
							</div>
						</div>
					</div>
					<div class="title-left-top-circles" style="margin-left: 6em">
						<div class="title-left-top-circles">
							<div class="clock hours1">
								<div class="left">
									<div></div>
								</div>
								<div class="right">
									<div></div>
								</div>
								<div class="progress">
									<span>0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="title-left-top-circles" style="margin-left: 12em">
						<div class="title-left-top-circles">
							<div class="clock hours2">
								<div class="left">
									<div></div>
								</div>
								<div class="right">
									<div></div>
								</div>
								<div class="progress">
									<span>0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="title-left-top-circles" style="margin-left: 18em">
						<div class="title-left-top-circles">
							<div class="clock hours3">
								<div class="left">
									<div></div>
								</div>
								<div class="right">
									<div></div>
								</div>
								<div class="progress">
									<span>0</span>
								</div>
							</div>
						</div>
					</div>

				</div>

			</div>
			<div class="title-center-style">
				<span class="center-title">IT资源运维监控平台</span>
				<span class="center-cog-big"><i class="fa fa-cog fa-spin"></i>
				</span>
				<span class="center-cog-small"><i class="fa fa-cog fa-spin"></i>
				</span>
			</div>
			<div class="title-right-style">
				<span class="title-right-style-top"> <span id="rightdate">2018年11月28日&nbsp;&nbsp;</span>
					<span id="rightweek">星期二&nbsp;&nbsp;</span> <span><span id="righthours">13</span><span
						class="flash">:</span><span id="rightminutes">28</span><span
						class="flash">:</span><span id="rightseconds">11</span>
				</span> </span>
				<span class="title-right-style-bottom"> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> <i
					class="fa fa-chevron-right " aia-hidden="true"></i> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> <i
					class="fa fa-chevron-right " aria-hidden="true"></i> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> <i
					class="fa fa-chevron-right " aria-hidden="true"></i> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> <i
					class="fa fa-chevron-right " aria-hidden="true"></i> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> <i
					class="fa fa-chevron-right " aria-hidden="true"></i> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> <i
					class="fa fa-chevron-right " aria-hidden="true"></i> <i
					class="fa fa-chevron-right flashi" aria-hidden="true"></i> </span>

			</div>
			<div class="bodys">
				<div class="body-left">
					<div class="body-top">
						<div class="body-top-left">
							<div class="iconlist">
								<div class="iconlist-icon">
									<i class="iconfont icon-notice " style="color: #75AFFF"></i>
								</div>
								<div class="iconlist-content">
									<div class="iconlist-content-title">
										健康度
									</div>
									<div id='totalhealth' class="iconlist-content-number"
										style="color: #75AFFF; font-weight: normal;">
										79分
									</div>
								</div>
							</div>
							<div class="iconlist">
								<div class="iconlist-icon">
									<i class="iconfont icon-notice"></i>
								</div>
								<div class="iconlist-content">
									<div class="iconlist-content-title">
										状态正常
									</div>
									<div class="iconlist-content-number">
										<span id='zcnum' style="color: #FDC31D; font-weight: normal;">79</span>/
										<span class='devicenum'
											style="color: #75AFFF; font-weight: normal;">100</span>
									</div>
								</div>
							</div>
							<div class="iconlist">
								<div class="iconlist-icon">
									<i class="iconfont icon-notice  "></i>
								</div>
								<div class="iconlist-content">
									<div class="iconlist-content-title">
										报警设备
									</div>
									<div class="iconlist-content-number">
										<span id='bjnum' style="color: #FDC31D; font-weight: normal;">11</span>/
										<span class='devicenum'
											style="color: #75AFFF; font-weight: normal;">100</span>
									</div>
								</div>
							</div>
							<div class="iconlist">
								<div class="iconlist-icon">
									<i class="iconfont icon-warn "></i>
								</div>
								<div class="iconlist-content">
									<div class="iconlist-content-title">
										预警设备
									</div>
									<div class="iconlist-content-number">
										<span id='yjnum' style="color: #FDC31D; font-weight: normal;">5</span>/
										<span class='devicenum'
											style="color: #75AFFF; font-weight: normal;">100</span>
									</div>
								</div>
							</div>
							<div class="iconlist">
								<div class="iconlist-icon">
									<i class="iconfont icon-xiangzhilusuoxudu"></i>
								</div>
								<div class="iconlist-content">
									<div class="iconlist-content-title">
										数据异常
									</div>
									<div class="iconlist-content-number">
										<span id='txzdnum'
											style="color: #FDC31D; font-weight: normal;">9</span>/
										<span class='devicenum'
											style="color: #75AFFF; font-weight: normal;">100</span>
									</div>
								</div>
							</div>
							<div class="iconlist">
								<div class="iconlist-icon">
									<i class="iconfont icon-zhixingzhongduan "></i>
								</div>
								<div class="iconlist-content">
									<div class="iconlist-content-title">
										网络中断
									</div>
									<div class="iconlist-content-number">
										<span id='wlzdnum'
											style="color: #FDC31D; font-weight: normal;">7</span>/
										<span class='devicenum'
											style="color: #75AFFF; font-weight: normal;">100</span>
									</div>
								</div>
							</div>
						</div>
						<div class="body-top-center">
							<div id="iconcircle"
								style="width: 185px; height: 185px; background: url('img/icon185.png') center center no-repeat;">
								<a><img src="img/html5icongreen.png">
								</a>
							</div>
							<div class="iconcircle-text">
								90
							</div>
							<div class="rectdetail">
								<div class="rectdetail-title">
									<i class="fa fa-cogs" aria-hidden="true"></i>&nbsp;<span id='bussiness'>网银前置系统</span>
								</div>
								<div class="rectdetail-content">
									<div class="rectdetail-content-li">
										<span id='itemnum'
											style="color: #028205; font-weight: bold; font-size: 24px; line-height: 0px;">256</span>
										<br>
										<span
											style="color: #028205; font-weight: bold; font-size: 13px; line-height: 0px;">正常</span>
									</div>
									<div class="rectdetail-content-li">
										<span id='alarmitemnum'
											style="color: #B12D05; font-weight: bold; font-size: 24px; line-height: 0px;">21</span>
										<br>
										<span
											style="color: #B12D05; font-weight: bold; font-size: 13px; line-height: 0px;">异常</span>
									</div>
								</div>
							</div>
							<div class="devicestatus-easyticker">
								<ul id='itemlist'>

								</ul>
							</div>
						</div>
						<div class="body-top-right">
						  <div style="height:10%;font-size:12px;color:#75AFFF">
						     <span style="">CPU/内存排行(当天)</span>
   						     <span id='cpubtn' onclick="changecharts('cpu')" style="float:right;color:#75AFFF;cursor:pointer;padding:5px 10px;background:#3D708A;border-radius: 6px;">CPU</span>
   						     <span  id='memeroybtn' onclick="changecharts('mermory')" style="float:right;color:#FDC31D;cursor:pointer;padding:5px 10px;background:#3D708A;border-radius: 6px;margin-right:5px;">内存</span>
						  </div>
						  <div style='height:90%;'>
							<div id="piebar0" style="width: 100%; height: 100%;"></div>
							</div>
						</div>
					</div>
					<div class="body-bottom">
						<!--  	<div class="body-bottom-left">
							<div id="alarmchart1" style="width: 100%;height: 99%;"></div>
						</div>
						<div class="body-bottom-right">
							<div id="businesschart0" style="width: 100%;height: 100%;"></div>
						</div>-->
						<div id="alarmchart1" style="width: 100%; height: 99%;"></div>
					</div>
				</div>
				<div class="body-right">
					<div class="righttopsvg">
						<svg width="300" height="300" version="1.1"
							xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink">
						<circle id="progress1" cx="170" cy="115" r="110"
							style="fill:none;stroke-width:5;stroke:#4B74B1;">
						</circle>
						<circle cx="170" cy="115" r="100"
							style="fill:none;stroke-width:2;stroke:#75AFFF;stroke-dasharray:0px;stroke-dashoffset:1">
						</circle>
						<circle cx="170" cy="115" r="90"
							style="fill:none;stroke-width:4;stroke:#314D7A"></circle>
						<circle id="progress" r="90" cy="115" cx="170" stroke-width="7"
							stroke="#75AFFF" fill="none" />
						</svg>
						<div class="contents">
							<div class="contents-left-total">
								<span  class="total-num devicenum">259</span>
								<br>
								<span class="total-text">资源总数</span>
							</div>
							<div id='contents-leftdiv' class="contents-left-text">


							</div>
						</div>
					</div>
					<div class="body-right-center">
						<!-- <div id="graph0" style="width: 100%;height: 100%;"></div> -->
						<div class="body-right-center-li li-green"></div>
						<div class="body-right-center-li li-red"></div>
						<div class="body-right-center-li li-orange"></div>
						<div class="body-right-center-li li-blue"></div>
					</div>
					<div id="p_alarmchart0" class="body-right-bottom">
					    <div class='left-pie' style='width: 50%;height: 80%;float: left;padding-top: 10px;'>
						<div id="alarmchart0" style="width: 100%; height: 100%;"></div>
						</div>
						<div class='left-list' style='width: 50%;height: 80%;float: left;padding-top: 10px;'>
						<div id="alarmlist" style="width: 100%; height: 100%;"></div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</body>
</html>
