function loadpiecolumn(title, subtitle, list, domid) {
	var myChart = echarts.init(document.getElementById(domid));
	myChart.off("click");
	myChart.on('click', function(params) {
		if (params.name == '报警') {
			openfavouriteifrm('favourite', '3', '报警');
		} else if (params.name == '预警') {
			openfavouriteifrm('favourite', '2', '预警');
		} else if (params.name == '正常') {
			openfavouriteifrm('favourite', '0,1', '正常');
		} else if (params.name == '其他') {
			openfavouriteifrm('favourite', '4,5', '其他');
		}
	});
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		color : [ '#FD987C', '#6BE5B2', '#FDCB6C', '#5DB8FD', "#6BE5B2" ],// 饼图颜色
		title : {
			text : title,
			subtext : subtitle,
			x : 'center',
			y : '50%',
			textStyle : {
				fontWeight : 'normal',
				fontSize : 12
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},

		toolbox : {
			show : false
		},
		calculable : false,
		series : [ {
			name : '占比',
			type : 'pie',
			radius : [ '50%', '70%' ], // 设置环形的空间大小
			itemStyle : {
				normal : {
					label : {
						show : false,
						textStyle : {
							fontSize : 12,
							fontWeight : "bold"
						},
						position : "center"
					},
					labelLine : {
						show : false
					}
				},
				emphasis : {
					label : {
						show : true,
						position : 'center',
						textStyle : {
							fontSize : '20',
							fontWeight : 'bold'
						}
					}
				}
			},
			data : []
		} ]
	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画
	var names = []; // 类别数组（用于存放饼图的类别）
	var brower = [];
	// 请求成功时执行该函数内容，result即为服务器返回的json对象
	$.each(list, function(index, item) {
		brower.push({
			value : item.value,
			name : item.name
		});
	});
	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		series : [ {
			data : brower
		} ]
	});
};

function loadbar(title, subtitle, list, domid) {
	var myChart = echarts.init(document.getElementById(domid));
	myChart.off("click");
	myChart.on('click', function(params) {
		changetologcmspage(params.data.id, domid);
	});
	// 显示标题，图例和空的坐标轴
	myChart
			.setOption({
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				grid : {
					top : '8%',
					left : '1%',
					right : '3%',
					bottom : '0',
					containLabel : true
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : false
						},
						dataView : {
							show : false,
							readOnly : false
						},
						magicType : {
							show : false,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : false
						},
						saveAsImage : {
							show : false
						}
					}
				},
				calculable : true,
				xAxis : {
					show : true,
					type : 'value',
					boundaryGap : [ 0, 0 ],
					interval : 10,
					min : 0,
					max : 100,
					axisLine : {
						lineStyle : {
							color : '#ccc'
						}
					},
					splitLine : {
						show : false
					}
				},
				yAxis : {
					type : 'category',
					boundaryGap : [ 0, 0.01 ],
					axisLine : {
						lineStyle : {
							color : '#ccc'
						}
					},
					splitLine : {
						show : false
					},
					axisLabel : {// 坐标轴刻度标签的相关设置。
						formatter : function(params) {
							var newParamsName = "";// 最终拼接成的字符串
							var paramsNameNumber = params.length;// 实际标签的个数
							var provideNumber = 8;// 每行能显示的字的个数
							var rowNumber = Math.ceil(paramsNameNumber
									/ provideNumber);// 换行的话，需要显示几行，向上取整
							/**
							 * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理
							 * 如果不大于，即等于或小于，就返回原标签
							 */
							// 条件等同于rowNumber>1
							if (paramsNameNumber > provideNumber) {
								/** 循环每一行,p表示行 */
								for (var p = 0; p < rowNumber; p++) {
									var tempStr = "";// 表示每一次截取的字符串
									var start = p * provideNumber;// 开始截取的位置
									var end = start + provideNumber;// 结束截取的位置
									// 此处特殊处理最后一行的索引值
									if (p == rowNumber - 1) {
										// 最后一次不换行
										tempStr = params.substring(start,
												paramsNameNumber);
									} else {
										// 每一次拼接字符串并换行
										tempStr = params.substring(start, end)
												+ "\n";
									}
									newParamsName += tempStr;// 最终拼成的字符串
								}

							} else {
								// 将旧标签的值赋给新标签
								newParamsName = params;
							}
							// 将最终的字符串返回
							return newParamsName
						}

					},
					data : []
				},
				series : [ {
					type : 'bar',
					itemStyle : {
						emphasis : {
							barBorderRadius : 30
						},
						normal : {
							// 柱形图圆角，初始化效果
							barBorderRadius : [ 10, 10, 10, 10 ],
							color : [ '#5AB1EF' ],
							label : {
								show : true,// 是否展示
								textStyle : {
									fontSize : '12',
									fontFamily : '微软雅黑',

								}
							}
						}

					},
					/*
					 * itemStyle: { normal: { color: function(params) { var
					 * colorList =
					 * ['#FE9E85','#FEDD7C','#FEDD7C','#FEDD7C','#7BE4BA','#7BE4BA' ];
					 * return colorList[params.dataIndex] }
					 *  } },
					 */
					data : []
				}

				]
			});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画
	var names = []; // 类别数组（用于存放饼图的类别）
	var brower = new Array();
	// 请求成功时执行该函数内容，result即为服务器返回的json对象
	$.each(list, function(index, item) {
		names.push(item.name); // 挨个取出类别并填入类别数组
		var obj = new Object();
		obj.value = item.values;
		obj.id = item.id;
		brower.push(obj);
	});
	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		series : [ {
			data : brower
		} ],
		yAxis : [ {
			data : names
		} ]
	});

};

function loadbigdataline(title, subtitle, date, data, domid) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		tooltip : {
			trigger : 'axis',
			position : function(pt) {
				return [ pt[0], '10%' ];
			}
		},
		title : {
			left : 'center',
			text : title,
			textStyle : {
				color : '#609ee9',
				fontSize : 14
			}
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : []
		},
		yAxis : {
			type : 'value',
			max : 100,
			min : 0,
			minInterval : 50
		},
		grid : {
			left : '3%',
			right : '3%',
			bottom : '40px',
			top : '40px'

		},
		series : [ {
			name : subtitle,
			type : 'line',
			smooth : true,
			symbol : 'none',
			sampling : 'average',
			itemStyle : {
				normal : {
					color : 'rgb(123,228,186)'
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgb(136,251,205)'
					}, {
						offset : 1,
						color : 'rgb(123,228,186)'
					} ])
				}
			},
			data : []
		} ]

	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			data : data
		} ]
	});
};

function loadbigdataline1(title, subtitle, date, data, domid) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		tooltip : {
			trigger : 'axis',
			position : function(pt) {
				return [ pt[0], '10%' ];
			}
		},
		title : {
			left : 'center',
			text : title,
			textStyle : {
				color : '#609ee9',
				fontSize : 14
			}
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : []
		},
		yAxis : {
			type : 'value',
			max : 100,
			min : 0,
			minInterval : 50
		},
		grid : {
			left : '3%',
			right : '3%',
			bottom : '20px',
			top : '10px'

		},
		series : [ {
			name : subtitle,
			type : 'line',
			smooth : true,
			symbol : 'none',
			sampling : 'average',
			itemStyle : {
				normal : {
					color : 'rgb(123,228,186)'
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgb(136,251,205)'
					}, {
						offset : 1,
						color : 'rgb(123,228,186)'
					} ])
				}
			},
			data : []
		} ]

	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			data : data
		} ]
	});
};

function loadcategory_two(title, subtitle, date, name1, data1, name2, data2,
		domid) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#57617B'
				}
			}
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '5%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			axisLine : {
				lineStyle : {
					color : '#ccc'
				}
			},
			data : []
		} ],
		yAxis : [ {
			type : 'value',
			name : '单位（%）',
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#ccc'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 12
				}
			},
			splitLine : {
				lineStyle : {
					color : '#ccc'
				}
			}
		} ],
		series : [ {
			name : '报警',
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(137,189,27)',
					borderColor : 'rgba(137,189,2,0.27)',
					borderWidth : 12

				}
			},
			data : []
		}, {
			name : '预警',
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			itemStyle : {
				normal : {
					color : 'rgb(0,136,212)',
					borderColor : 'rgba(0,136,212,0.2)',
					borderWidth : 12

				}
			},
			data : []
		}, ]
	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			name : name1,
			data : data1
		}, {
			name : name2,
			data : data2
		} ]
	});
};

function loadcategory_three(title, subtitle, date, name1, data1, name2, data2,
		name3, data3, domid) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#57617B'
				}
			}
		},
		legend : {
			itemGap : 13,
			data : [ name1, name2, name3 ],
			right : '4%',
		},
		grid : {
			top : '20%',
			left : '3%',
			right : '4%',
			bottom : '4%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			splitLine : {
				show : true,
				interval : 'auto',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			data : []
		} ],
		yAxis : [ {
			type : 'value',
			name : '单位（%）',
			axisTick : {
				show : false
			},
			splitLine : {
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
		} ],
		series : [ {
			name : '报警',
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			data : []
		}, {
			name : '预警',
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			data : []
		}, {
			name : '其他',
			type : 'line',
			smooth : true,
			symbol : 'circle',
			symbolSize : 5,
			showSymbol : false,
			lineStyle : {
				normal : {
					width : 1
				}
			},
			data : []
		}, ]
	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			name : name1,
			data : data1
		}, {
			name : name2,
			data : data2
		}, {
			name : name3,
			data : data3
		} ]
	});
};

function loadradialIndicator(domid, value, barBgColor, barColor) {
	document.getElementById(domid).innerHTML = "";
	$('#' + domid).radialIndicator({
		radius : 30,
		barColor : barColor,
		barWidth : 8,
		initValue : value,
		percentage : true,
		barBgColor : barBgColor,
		fontSize : '13'
	});
}

function loadline_two(title, subtitle, date, name1, data1, name2, data2, domid,
		size, top) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle,
			left : '50%',
			textAlign : 'center'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#ddd'
				}
			},
			backgroundColor : 'rgba(255,255,255,1)',
			padding : [ 5, 10 ],
			textStyle : {
				color : '#7588E4',
			},
			extraCssText : 'box-shadow: 0 0 5px rgba(0,0,0,0.3)'
		},
		legend : {
			right : 20,
			data : [ name1, name2 ]
		},
		grid : {
			top : top
		},
		xAxis : {
			type : 'category',
			data : [],
			boundaryGap : false,
			splitLine : {
				show : true,
				interval : 'auto',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			}
		},
		yAxis : {
			type : 'value',
			max : 100,
			min : 0,
			minInterval : 20,
			splitLine : {
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			}
		},
		series : [ {
			name : '',
			type : 'line',
			smooth : true,
			showSymbol : false,
			symbol : 'circle',
			symbolSize : 6,
			data : [],
			markPoint : {
				symbolSize : size, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize *
									// 2

				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(199, 237, 250,0.5)'
					}, {
						offset : 1,
						color : 'rgba(199, 237, 250,0.2)'
					} ], false)
				}
			},
			itemStyle : {
				normal : {
					color : '#f7b851'
				}
			},
			lineStyle : {
				normal : {
					width : 2
				}
			}
		}, {
			name : '',
			type : 'line',
			smooth : true,
			showSymbol : false,
			symbol : 'circle',
			symbolSize : 6,
			data : [],
			markPoint : {
				symbolSize : size, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize *
									// 2

				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(216, 244, 247,1)'
					}, {
						offset : 1,
						color : 'rgba(216, 244, 247,1)'
					} ], false)
				}
			},
			itemStyle : {
				normal : {
					color : '#58c8da'
				}
			},
			lineStyle : {
				normal : {
					width : 2
				}
			}
		} ]
	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			name : name1,
			data : data1
		}, {
			name : name2,
			data : data2
		} ]
	});
};

function loadline_three(title, subtitle, date, name1, data1, name2, data2,
		name3, data3, domid, size, top) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	myChart.off("click");
	myChart.on('click', function(params) {
		if (null != params.seriesType) {
			showalarmlog(params.name, params.seriesName);
		}
	});

	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ name1, name2, name3 ]
		},
		grid : {
			top : top,
			right : 25,
			bottom : 25,
			left : 60
		},
		toolbox : {
			right : 15,
			show : true,
			feature : {
				magicType : {
					type : [ 'bar', 'line' ]
				},
			}
		},
		xAxis : {
			type : 'category',
			data : [],
			boundaryGap : false,
			splitLine : {
				show : true,
				interval : 'auto',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			}
		},
		yAxis : {
			type : 'value',
			min : 0,
			minInterval : 10,
			splitLine : {
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			}
		},
		series : [ {
			name : name1,
			type : 'line',
			data : [],
			markPoint : {
				symbolSize : size,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		}, {
			name : name2,
			type : 'line',
			data : [],
			markPoint : {
				symbolSize : size,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		}, {
			name : name3,
			type : 'line',
			data : [],
			markPoint : {
				symbolSize : size,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		} ]
	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			name : name1,
			data : data1
		}, {
			name : name2,
			data : data2
		}, {
			name : name3,
			data : data3
		} ]
	});
};
function loadline_three1(title, subtitle, date, name1, data1, name2, data2,
		name3, data3, domid, size, top) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle,
			left : '50%',
			textAlign : 'center'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#ddd'
				}
			},
			backgroundColor : 'rgba(255,255,255,1)',
			padding : [ 5, 10 ],
			textStyle : {
				color : '#7588E4',
			},
			extraCssText : 'box-shadow: 0 0 5px rgba(0,0,0,0.3)'
		},
		legend : {
			position : "center",
			data : [ name1, name2, name3 ]
		},
		grid : {
			top : 50,
			left : 55,
			right : 40,
			bottom : 35
		},
		xAxis : {
			type : 'category',
			data : [],
			boundaryGap : false,
			splitLine : {
				show : true,
				interval : 'auto',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			}
		},
		yAxis : {
			type : 'value',
			max : 100,
			min : 0,
			minInterval : 20,
			splitLine : {
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#609ee9'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			}
		},
		series : [ {
			name : '',
			type : 'line',
			smooth : true,
			showSymbol : false,
			symbol : 'circle',
			symbolSize : 6,
			data : [],
			markPoint : {
				symbolSize : size, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize *
									// 2

				data : [ {
					type : 'max',
					name : '最大值'
				}, ]
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(199, 237, 250,0.5)'
					}, {
						offset : 1,
						color : 'rgba(199, 237, 250,0.2)'
					} ], false)
				}
			},
			itemStyle : {
				normal : {
					color : '#f7b851'
				}
			},
			lineStyle : {
				normal : {
					width : 2
				}
			}
		}, {
			name : '',
			type : 'line',
			smooth : true,
			showSymbol : false,
			symbol : 'circle',
			symbolSize : 6,
			data : [],
			markPoint : {
				symbolSize : size, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize *
									// 2

				data : [ {
					type : 'max',
					name : '最大值'
				}, ]
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(216, 244, 247,1)'
					}, {
						offset : 1,
						color : 'rgba(216, 244, 247,1)'
					} ], false)
				}
			},
			itemStyle : {
				normal : {
					color : '#58c8da'
				}
			},
			lineStyle : {
				normal : {
					width : 2
				}
			}
		}, {
			name : '',
			type : 'line',
			smooth : true,
			showSymbol : false,
			symbol : 'circle',
			symbolSize : 6,
			data : [],
			markPoint : {
				symbolSize : size, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize *
									// 2

				data : [ {
					type : 'max',
					name : '最大值'
				}, ]
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(38, 201, 80,0.4)'
					}, {
						offset : 1,
						color : 'rgba(38, 201, 80,0.2)'
					} ], false)
				}
			},
			itemStyle : {
				normal : {
					color : '#31E95F'
				}
			},
			lineStyle : {
				normal : {
					width : 2
				}
			}
		} ]
	});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画

	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		xAxis : {
			data : date
		},
		series : [ {
			name : name1,
			data : data1
		}, {
			name : name2,
			data : data2
		}, {
			name : name3,
			data : data3
		} ]
	});
};

function loadcircle(title, subtitle, domid, name, value, top, left) {
	var myChart = echarts.init(document.getElementById(domid));
	var num = 100 - value;
	// 显示标题，图例和空的坐标轴
	myChart
			.setOption({
				"title" : {
					"text" : title,
					"top" : '85%',
					"left" : '20%',
					"textStyle" : {
						"fontSize" : 14,
						"color" : "#7AB5F2"
					}
				},
				grid : {
					top : 10,
					bottom : 10
				},
				"series" : [
						{
							"name" : name,
							"center" : [ "50%", "50%" ],
							"radius" : [ "49%", "50%" ],
							"clockWise" : false,
							"hoverAnimation" : false,
							"type" : "pie",
							"data" : [
									{
										"value" : value,
										"name" : "",
										"label" : {
											"normal" : {
												"show" : true,
												"formatter" : '{d} %',
												"textStyle" : {
													"fontSize" : 14,
													"fontWeight" : "bold"
												},
												"position" : "center"
											}
										},
										"labelLine" : {
											"show" : false
										},
										"itemStyle" : {
											"normal" : {
												"color" : "#5886f0",
												"borderColor" : new echarts.graphic.LinearGradient(
														0, 0, 0, 1, [ {
															offset : 0,
															color : '#00a2ff'
														}, {
															offset : 1,
															color : '#70ffac'
														} ]),
												"borderWidth" : 5
											},
											"emphasis" : {
												"color" : "#5886f0",
												"borderColor" : new echarts.graphic.LinearGradient(
														0, 0, 0, 1, [ {
															offset : 0,
															color : '#85b6b2'
														}, {
															offset : 1,
															color : '#6d4f8d'
														} ]),
												"borderWidth" : 5
											}
										},
									},
									{
										"name" : " ",
										"value" : num,
										"itemStyle" : {
											"normal" : {
												"label" : {
													"show" : false
												},
												"labelLine" : {
													"show" : false
												},
												"color" : 'rgba(0,0,0,0)',
												"borderColor" : 'rgba(0,0,0,0)',
												"borderWidth" : 0
											},
											"emphasis" : {
												"color" : 'rgba(0,0,0,0)',
												"borderColor" : 'rgba(0,0,0,0)',
												"borderWidth" : 0
											}
										}
									} ]
						},
						{
							"name" : name,
							"center" : [ "50%", "50%" ],
							"radius" : [ "59%", "60%" ],
							"clockWise" : false,
							"hoverAnimation" : false,
							"type" : "pie",
							"data" : [
									{
										"value" : value,
										"name" : "",
										"label" : {
											"normal" : {
												"show" : true,
												"formatter" : '{d} %',
												"textStyle" : {
													"fontSize" : 14,
													"fontWeight" : "bold"
												},
												"position" : "center"
											}
										},
										"labelLine" : {
											"show" : false
										},
										"itemStyle" : {
											"normal" : {
												"color" : "#5886f0",
												"borderColor" : new echarts.graphic.LinearGradient(
														0, 0, 0, 1, [ {
															offset : 0,
															color : '#00a2ff'
														}, {
															offset : 1,
															color : '#70ffac'
														} ]),
												"borderWidth" : 1
											},
											"emphasis" : {
												"color" : "#5886f0",
												"borderColor" : new echarts.graphic.LinearGradient(
														0, 0, 0, 1, [ {
															offset : 0,
															color : '#85b6b2'
														}, {
															offset : 1,
															color : '#6d4f8d'
														} ]),
												"borderWidth" : 1
											}
										},
									},
									{
										"name" : " ",
										"value" : num,
										"itemStyle" : {
											"normal" : {
												"label" : {
													"show" : false
												},
												"labelLine" : {
													"show" : false
												},
												"color" : 'rgba(0,0,0,0)',
												"borderColor" : 'rgba(0,0,0,0)',
												"borderWidth" : 0
											},
											"emphasis" : {
												"color" : 'rgba(0,0,0,0)',
												"borderColor" : 'rgba(0,0,0,0)',
												"borderWidth" : 0
											}
										}
									} ]
						} ]
			});
};

function loadcomparebar(title, subtitle, domid, name1, value1, name2, value2,
		myData) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			x : 'center',
			textStyle : {

				fontSize : 13,
			},
		},
		legend : {
			data : [ name1, name2 ],
			bottom : 20,
			center : true,
			textStyle : {
				textAlign : 'center'
			},
			itemGap : 80,
			itemWidth : 0
		},
		grid : [ {
			show : false,
			left : '4%',
			top : 10,
			bottom : 50,
			containLabel : true,
			width : '46%'
		}, {
			show : false,
			left : '50.5%',
			top : 10,
			bottom : 50,
			width : '0%'
		}, {
			show : false,
			right : '4%',
			top : 10,
			bottom : 50,
			containLabel : true,
			width : '46%'
		}, ],
		xAxis : [ {
			splitNumber : 2,
			type : 'value',
			inverse : true,
			axisLine : {
				show : false,
			},
			axisTick : {
				show : false,
			},
			position : 'bottom',
			axisLabel : {
				show : true,
				textStyle : {

					fontSize : 12
				}
			},
			splitLine : {
				show : true,
				lineStyle : {
					color : '#57617f',
					width : 1,
					type : 'solid'
				}
			}
		}, {
			gridIndex : 1,
			show : false,
		}, {
			gridIndex : 2,
			type : 'value',
			axisLine : {
				show : false,
			},
			axisTick : {
				show : false,
			},
			position : 'bottom',
			axisLabel : {
				show : true,
				textStyle : {

					fontSize : 12,
				},
			},
			splitLine : {
				show : true,
				lineStyle : {
					color : '#57617f',
					width : 1,
					type : 'solid',
				},
			},
		}, ],
		yAxis : [ {
			type : 'category',
			inverse : true,
			position : 'right',
			axisLine : {
				show : false,
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				show : false,
			},
			data : [],

		}, {
			gridIndex : 1,
			type : 'category',
			inverse : true,
			position : 'left',
			axisLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				show : true,
			},
			data : myData.map(function(value) {
				return {
					value : value,
					textStyle : {
						align : 'center',

						fontSize : 12,

					}
				}
			}),
		}, {
			gridIndex : 2,
			type : 'category',
			inverse : true,
			position : 'left',
			axisLine : {
				show : false
			},
			axisTick : {
				show : false
			},
			axisLabel : {
				show : false,
			},
			data : [],
		}, ],
		series : [ {
			name : name1,
			type : 'bar',
			barGap : 20,
			barWidth : '80%',
			label : {
				normal : {
					show : true,
					color : 'red',
					position : 'insideLeft',
					textStyle : {
						color : '#ffffff',
					}

				},
				emphasis : {
					show : false,
				},
			},
			itemStyle : {
				normal : {
					color : '#36c5e7',
					barBorderRadius : [ 8, 0, 0, 8 ],
				},
				emphasis : {
					show : false,
				},
			},
			data : value1
		}, {
			name : name2,
			type : 'bar',
			barGap : 20,
			barWidth : '80%',
			xAxisIndex : 2,
			yAxisIndex : 2,
			label : {
				normal : {
					show : true,
					color : 'red',
					position : 'insideRight',
					textStyle : {
						color : '#ffffff',
					}

				},
			},
			itemStyle : {
				normal : {
					color : '#e68b55',
					barBorderRadius : [ 0, 8, 8, 0 ],

				},
				emphasis : {
					show : false,
				},
			},
			data : value2,
		} ]
	});
};

function loadbar_three(title, subtitle, date, name1, data1, name2, data2,
		name3, data3, domid, size, top) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	myChart.off("click");
	myChart.on('click', function(params) {
		if (null != params.seriesType) {
			showalarmlog(params.name, params.seriesName);
		}
	});

	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ name1, name2, name3 ]
		},
		grid : {
			top : 50,
			left : 45,
			right : 40,
			bottom : 30
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : false,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : false
				},
				saveAsImage : {
					show : false
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : date
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : name1,
			type : 'bar',
			data : data1,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		}, {
			name : name2,
			type : 'bar',
			data : data2,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		}, {
			name : name3,
			type : 'bar',
			data : data3,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		} ]
	});

};

function loadbar_one(title, subtitle, date, name1, data1, domid, size, top) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	/*
	 * myChart.off("click"); myChart.on('click', function (params) {
	 * if(null!=params.seriesType){ showalarmlog(params.name,params.seriesName); }
	 * });
	 */

	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ name1 ]
		},
		grid : {
			top : 40,
			left : 35,
			right : 30,
			bottom : 40
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : false,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : false
				},
				saveAsImage : {
					show : false
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : date
		} ],
		yAxis : [ {
			type : 'value',
			max : 100
		} ],
		series : [ {
			name : name1,
			type : 'bar',
			data : data1,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		} ]
	});

};

function screenline(domid) {
	var myChart = echarts.init(document.getElementById(domid));
	var now = new Date();
	var res = [];
	var len = 12;
	while (len--) {
		var time = now.toLocaleTimeString().replace(/^\D*/, '');
		time = time.substr(time.indexOf(":") + 1);
		res.unshift(time);
		now = new Date(now - 1000);
	}
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#ddd'
				}
			},
			backgroundColor : 'rgba(255,255,255,1)',
			padding : [ 5, 10 ],
			textStyle : {
				color : '#7588E4',
			},
			extraCssText : 'box-shadow: 0 0 5px rgba(0,0,0,0.3)'
		},
		legend : {
			right : 20,
			textStyle : {
				color : '#7588E4',
			},
			data : [ '今日', '昨日' ]
		},
		grid : {
			right : 15,
			bottom : 25,
			top : 20,
			left : 35
		},
		xAxis : {
			type : 'category',
			data : res,
			boundaryGap : false,
			splitLine : {
				show : false,
				interval : 'auto',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#03246F'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					color : '#999999',
					fontSize : 12
				}
			}
		},
		yAxis : {
			type : 'value',
			max : 100,
			min : 0,
			minInterval : 20,
			splitLine : {
				show : false,
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#021D5A'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					color : '#999999',
					fontSize : 12
				}
			}
		},
		series : [
				{
					name : '今日',
					type : 'line',
					smooth : true,
					showSymbol : false,
					symbol : 'circle',
					symbolSize : 6,
					data : [ '12', '24', '30', '24', '30', '42', '23', '18',
							'31', '20', '31', '13' ],
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(199, 237, 250,0.3)'
									}, {
										offset : 1,
										color : 'rgba(199, 237, 250,0.1)'
									} ], false)
						}
					},
					itemStyle : {
						normal : {
							color : '#f7b851'
						}
					},
					lineStyle : {
						normal : {
							width : 2
						}
					}
				},
				{
					name : '昨日',
					type : 'line',
					smooth : true,
					showSymbol : false,
					symbol : 'circle',
					symbolSize : 6,
					data : [ '72', '84', '68', '98', '86', '84', '66', '81',
							'75', '93', '79', '88' ],
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(216, 244, 247,0.4)'
									}, {
										offset : 1,
										color : 'rgba(216, 244, 247,0.2)'
									} ], false)
						}
					},
					itemStyle : {
						normal : {
							color : '#58c8da'
						}
					},
					lineStyle : {
						normal : {
							width : 2
						}
					}
				} ]
	});
};

function screenbar(domid, data) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		timeline : {
			data : [ 'CPU', '内存' ],
			axisType : 'category',
			show : true,
			autoPlay : true,
			playInterval : 6000,
			height : 35,
			padding : 0,
			left : 20,
			label : {
				textStyle : {
					color : '#609ee9'
				}
			},
			controlStyle : {
				itemSize : 15,
				itemGap : 5,
				normal : {
					color : '#333'
				},
				emphasis : {
					color : '#1e90ff'
				}
			},
			checkpointStyle : {
				itemSize : 15,
				itemGap : 5,
				color : '#04a5f1',
				borderColor : 'rgba(4, 165, 261, .5)'
			},
			itemStyle : {
				normal : {
					color : '#04a5f1'
				},
				emphasis : {
					color : '#04a5f1'
				}
			},
			lineStyle : {
				width : 1,
				color : '#04a5f1'
			},
		},
		options : [
				{
					title : {
						textStyle : {
							color : '#609ee9',
							fontSize : 13,
						},
						'text' : '内存',
					},
					calculable : true,
					grid : {
						'y' : 25,
						'y2' : 60,
						'x' : 20,
						right : 35,
					},
					xAxis : {
						type : 'category',
						data : data.xdata,
						boundaryGap : true,
						splitLine : {
							show : false,
							interval : '0',
							lineStyle : {
								color : [ '#D4DFF5' ]
							}
						},
						axisTick : {
							show : false
						},
						axisLine : {
							lineStyle : {
								color : '#03246F'
							}
						},
						axisLabel : {
							textStyle : {
								color : '#609ee9',
								fontSize : 12
							}
						}
					},
					yAxis : [ {
						'type' : 'value',
						'name' : '',
						'max' : 100
					}, {
						'type' : 'value',
						'axisLabel' : {
							textStyle : {
								color : '#609ee9',
								fontSize : 12
							}
						},
						min : 0,
						max : 100,
						splitLine : {
							show : false,
						},
					},

					],
					series : [ {
						'name' : '',
						'yAxisIndex' : 1,
						'type' : 'bar',
						'data' : data.bjdata,
						itemStyle : {
							normal : {
								color : new echarts.graphic.LinearGradient(0,
										1, 0, 0, [ {
											offset : 0,
											color : '#304D7D'
										}, {
											offset : 1,
											color : '#0B66FB'
										} ]),
								barBorderRadius : 2
							},
							emphasis : {
								color : new echarts.graphic.LinearGradient(0,
										1, 0, 0, [ {
											offset : 0,
											color : '#304D7D'
										}, {
											offset : 1,
											color : '#0B66FB'
										} ]),
								barBorderRadius : 2
							}
						},
					} ]
				}, {
					title : {
						textStyle : {
							color : '#609ee9',
							fontSize : 13,
						},
						'text' : '预警'
					},
					series : [ {
						'data' : data.yjdata
					} ]
				} ]
	});
};
var base = +new Date(2014, 9, 3);
var oneDay = 24 * 3600 * 1000;
var date = [];

var data = [ Math.random() * 150 ];
var now = new Date(base);

function addData(shift) {
	now = [ now.getFullYear(), now.getMonth() + 1, now.getDate() ].join('/');
	date.push(now);
	data.push((Math.random() - 0.4) * 10 + data[data.length - 1]);

	if (shift) {
		date.shift();
		data.shift();
	}

	now = new Date(+new Date(now) + oneDay);
}

function screenbigdataline(date1, data1, domid) {
	var myChart = echarts.init(document.getElementById(domid));

	for (var i = 1; i < 100; i++) {
		addData();
	}

	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : '当前状态',
			left : '50%',
			textAlign : 'center',
			textStyle : {
				color : '#609ee9',
				fontSize : 13
			}
		},
		grid : {
			left : '10%',
			right : '1%',
			bottom : '25px',
			top : '20px'

		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : date,
			splitLine : {
				show : false,
				interval : '0',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#021D5A'
				}
			},
			axisLabel : {
				textStyle : {
					color : '#ffffff',
					fontSize : 12
				}
			}
		},
		yAxis : {
			boundaryGap : [ 0, '50%' ],
			type : 'value',
			splitLine : {
				show : false,
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#021D5A'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					color : '#ffffff',
					fontSize : 12
				}
			}
		},
		series : [ {
			name : '成交',
			type : 'line',
			smooth : true,
			symbol : 'none',
			stack : 'a',
			itemStyle : {
				normal : {
					color : 'rgb(123,228,186)'
				}
			},
			areaStyle : {
				normal : {
					color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
						offset : 0,
						color : 'rgba(136,251,205,0.5)'
					}, {
						offset : 1,
						color : 'rgba(123,228,186,0.3)'
					} ])
				}
			},
			data : data
		} ]
	});
	setInterval(function() {
		addData(true);
		myChart.setOption({
			xAxis : {
				data : date
			},
			series : [ {
				name : '成交',
				data : data
			} ]
		});
	}, 500);
};

function screengraph(domid) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : ''
		},
		grid : {
			left : 0,
			right : 0,
			bottom : 10,
			top : 10

		},
		animationDurationUpdate : 1500,
		animationEasingUpdate : 'quinticInOut',
		label : {
			normal : {
				show : true,
				textStyle : {
					fontSize : 10
				},
			}
		},
		series : [

		{
			type : 'graph',
			layout : 'force',
			symbolSize : 56,
			focusNodeAdjacency : true,
			roam : true,
			categories : [ {
				name : '0',
				itemStyle : {
					normal : {
						color : "#009800",
					}
				}
			}, {
				name : '1',
				itemStyle : {
					normal : {
						color : "#4592FF",
					}
				}
			}, {
				name : '2',
				itemStyle : {
					normal : {
						color : "#F03806",
					}
				}
			} ],
			label : {
				normal : {
					show : true,
					textStyle : {
						fontSize : 12
					},
				}
			},
			force : {
				repulsion : 450
			},
			edgeSymbolSize : [ 5, 50 ],
			edgeLabel : {
				normal : {
					show : true,
					textStyle : {
						fontSize : 10
					},
					formatter : "{c}"
				}
			},
			data : [ {
				name : '资源100',
				category : 1,
				draggable : true,
			}, {
				name : '报警',
				category : 2,
				draggable : true,
			}, {
				name : '预警',
				category : 2,
				draggable : true,
			}, {
				name : '异常',
				category : 2,
				draggable : true,
			}, {
				name : '中断',
				category : 2,
				draggable : true,
			}, {
				name : '正常',
				category : 0,
				draggable : true,
			} ],
			links : [ {
				source : 0,
				target : 1,
				category : 0,
				value : '包括'
			}, {
				source : 0,
				target : 2,
				value : '包括'
			}, {
				source : 0,
				target : 3,
				value : '包括'
			}, {
				source : 0,
				target : 4,
				value : '包括'
			}, {
				source : 0,
				target : 5,
				value : '包括'
			} ],
			lineStyle : {
				normal : {
					opacity : 0.9,
					width : 1,
					curveness : 0
				}
			}
		} ]
	});
};

function screenpiebar(domid) {
	var myChart = echarts.init(document.getElementById(domid));
	var xvalue_24h = [ '00', '01', '02', '03', '04', '05', '06', '07', '08',
			'09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19',
			'20', '21', '22', '23', '24h' ];
	var yarn_data_obj = {
		"xValueList" : xvalue_24h,
		"queueNameList" : [ "billbd", "billqueue", "default", "dzqd" ],

		"2017-01-28" : {
			"billbd" : [ 0, 0.45, 0.43, 0.50, 0.55, 0.58, 0.62, 0.83, 0.39,
					0.56, 0.67, 0.50, 0.34, 0.50, 0.67, 0.58, 0.29, 0.46, 0.23,
					0.47, 0.46, 0.38, 0.56, 0.48, 0.36 ],
			"billqueue" : [ 0, 0.38, 0.31, 0.32, 0.32, 0.64, 0.66, 0.86, 0.47,
					0.52, 0.75, 0.52, 0.56, 0.54, 0.60, 0.46, 0.63, 0.54, 0.51,
					0.58, 0.64, 0.60, 0.45, 0.36, 0.67 ],
			"default" : [ 0, 0.46, 0.32, 0.53, 0.58, 0.86, 0.68, 0.85, 0.73,
					0.69, 0.71, 0.91, 0.74, 0.60, 0.50, 0.39, 0.67, 0.55, 0.49,
					0.65, 0.45, 0.64, 0.47, 0.63, 0.64 ],
			"dzqd" : [ 0, 0.60, 0.51, 0.52, 0.53, 0.64, 0.84, 0.65, 0.68, 0.63,
					0.47, 0.72, 0.60, 0.65, 0.74, 0.66, 0.65, 0.71, 0.59, 0.65,
					0.77, 0.52, 0.53, 0.58, 0.53 ],
		},
		"2017-01-29" : {
			"billbd" : [ 0, 14.1, 33.9, 36.0, 47.1, 39.0, 25.1, 14.9, 8.24, 0,
					0, 0, 0, 21.6, 1.36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
			"billqueue" : [ 0, 7.19, 8.23, 5.86, 6.54, 5.28, 3.56, 3.20, 4.13,
					4.75, 7.74, 7.92, 8.54, 8.15, 8.75, 8.31, 7.65, 8.59, 8.62,
					7.97, 8.99, 8.02, 9.61, 8.74, 7.44 ],
			"default" : [ 0, 0.01, 6.92, 9.11, 9.60, 9.44, 9.67, 9.72, 9.75,
					9.73, 9.78, 9.97, 9.96, 9.89, 9.78, 9.70, 10, 9.62, 9.24,
					9.28, 7.02, 9.99, 6.40, 5.39, 4.71 ],
		},
		"2017-01-30" : {
			"billqueue" : [ 0, 8.10, 7.36, 8.51, 7.51, 5.05, 3.06, 3.48, 3.74,
					5.16, 10.2, 12.5, 9.89, 9.52, 9.80, 10.1, 9.46, 9.35, 9.79,
					9.25, 8.81, 8.86, 9.60, 8.81, 8.69 ],
			"default" : [ 0, 2.73, 2.37, 2.38, 1.99, 1.76, 2.17, 4.59, 2.37,
					4.19, 6.31, 4.90, 5.30, 5.11, 5.26, 4.66, 4.92, 6.24, 5.95,
					5.33, 5.44, 5.04, 4.70, 4.94, 3.28 ],
		},
		"2017-01-31" : {
			"billbd" : [ 0, 0.45, 0.43, 0.50, 0.55, 0.58, 0.62, 0.83, 0.39,
					0.56, 0.67, 0.50, 0.34, 0.50, 0.67, 0.58, 0.29, 0.46, 0.23,
					0.47, 0.46, 0.38, 0.56, 0.48, 0.36 ],
			"default" : [ 0, 0.46, 0.32, 0.53, 0.58, 0.86, 0.68, 0.85, 0.73,
					0.69, 0.71, 0.91, 0.74, 0.60, 0.50, 0.39, 0.67, 0.55, 0.49,
					0.65, 0.45, 0.64, 0.47, 0.63, 0.64 ],
			"dzqd" : [ 0, 0.60, 0.51, 0.52, 0.53, 0.64, 0.84, 0.65, 0.68, 0.63,
					0.47, 0.72, 0.60, 0.65, 0.74, 0.66, 0.65, 0.71, 0.59, 0.65,
					0.77, 0.52, 0.53, 0.58, 0.53 ],
		},
		"2017-02-01" : {
			"billbd" : [ 0, 14.1, 33.9, 36.0, 47.1, 39.0, 25.1, 14.9, 8.24, 0,
					0, 0, 0, 21.6, 1.36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		},
		"2017-02-02" : {
			"billqueue" : [ 0, 8.10, 7.36, 8.51, 7.51, 5.05, 3.06, 3.48, 3.74,
					5.16, 10.2, 12.5, 9.89, 9.52, 9.80, 10.1, 9.46, 9.35, 9.79,
					9.25, 8.81, 8.86, 9.60, 8.81, 8.69 ],
			"default" : [ 0, 2.73, 2.37, 2.38, 1.99, 1.76, 2.17, 4.59, 2.37,
					4.19, 6.31, 4.90, 5.30, 5.11, 5.26, 4.66, 4.92, 6.24, 5.95,
					5.33, 5.44, 5.04, 4.70, 4.94, 3.28 ],
			"billbd" : [ 0, 0.45, 0.43, 0.50, 0.55, 0.58, 0.62, 0.83, 0.39,
					0.56, 0.67, 0.50, 0.34, 0.50, 0.67, 0.58, 0.29, 0.46, 0.23,
					0.47, 0.46, 0.38, 0.56, 0.48, 0.36 ],
			"dzqd" : [ 0, 0.60, 0.51, 0.52, 0.53, 0.64, 0.84, 0.65, 0.68, 0.63,
					0.47, 0.72, 0.60, 0.65, 0.74, 0.66, 0.65, 0.71, 0.59, 0.65,
					0.77, 0.52, 0.53, 0.58, 0.53 ],
		},
		"2017-02-03" : {
			"billqueue" : [ 0, 2.73, 21.3, 41.6, 17.2, 19.9, 1.60, 2.10, 2.54,
					2.78, 3.62, 4.41, 4.09, 3.83, 4.47, 4.20, 3.94, 3.80, 3.58,
					3.19, 4.30, 3.69, 3.52, 3.02, 3.30 ],
			"default" : [ 0, 1.51, 28.7, 0.94, 1.44, 18.6, 1.63, 1.56, 1.91,
					2.45, 3.87, 3.24, 4.90, 4.61, 4.10, 4.17, 3.85, 4.17, 3.46,
					3.46, 3.55, 3.50, 4.13, 2.58, 2.28 ],
			"billbd" : [ 0, 1.33, 4.68, 1.31, 1.10, 13.9, 1.10, 1.16, 1.67,
					2.64, 2.86, 3.00, 3.21, 4.14, 4.07, 3.68, 3.11, 3.41, 3.25,
					3.32, 3.07, 3.92, 3.05, 2.18, 3.24 ],
			"dzqd" : [ 0, 3.23, 3.15, 2.90, 1.81, 2.11, 2.43, 5.59, 3.09, 4.09,
					6.14, 5.33, 6.05, 5.71, 6.22, 6.56, 4.75, 5.27, 6.02, 5.22,
					5.77, 6.19, 5.68, 4.33, 5.48 ]
		},
	};
	var dateArr = [];
	for (key in yarn_data_obj) {
		if (key === "xValueList" || key === "queueNameList")
			continue;
		dateArr.push(key);
	}
	var colorConsArr_hdfs = [ "rgba(147,233,252,0.5)", "rgba(255,96,162,0.5)",
			"rgba(79,194,119,0.5)", "rgba(108,101,251,0.5)" ];
	var option = {
		baseOption : {
			backgroundColor : 'rgba(7,14,28,0)', // 设置背景色
			tooltip : {
				trigger : 'axis'
			},
			color : [], // ToDo
			legend : {
				itemGap : 12,
				right : '16%',
				top : '2%',
				// padding:[40,10,0,10],
				data : []
			// ToDo
			},
			grid : {
				left : '1.5%',
				right : '3%',
				bottom : '14%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : true,
				data : xvalue_24h, // Todo
				axisLine : {
					show : false
				},
				axisTick : {
					show : false
				},
				axisLabel : {
					textStyle : {
						color : "#999"
					},
					margin : 15
				},
				splitLine : {
					show : false,
					lineStyle : {
						color : "#1d203b"
					}
				}
			} ],
			yAxis : [ {
				name : '',
				nameGap : 20,
				nameTextStyle : {
					color : '#999',
					fontSize : 12,
				},
				type : 'value',

				axisLine : {
					show : true
				},
				axisTick : {
					show : false
				},
				axisLabel : {
					textStyle : {
						color : '#999'
					},
					margin : 10
				},
				splitLine : {
					show : false,
					lineStyle : {
						color : "#1d203b"
					}
				}
			} ],
			timeline : {
				axisType : 'category',
				autoPlay : true,
				playInterval : 3000,
				symbol : 'circle',
				inverse : false,
				rewind : true,
				symbolSize : 2,
				checkpointStyle : {
					symbol : 'circle',
					symbolSize : 5,
					color : '#aed2ff',
					borderColor : "#aed2ff"
				},
				left : "2%",
				width : '92%',
				height : '35',
				lineStyle : {
					color : "#454e72"
				},
				label : {
					normal : {
						textStyle : {
							color : "#999",
							fontSize : 12,
						}
					},
					position : 15,
					formatter : function(value, index) {
						return value.replace(/-/g, ".");
					}
				},
				controlStyle : {
					itemSize : 15,
					itemGap : 5,
					normal : {
						color : '#333'
					},
					emphasis : {
						color : '#1e90ff'
					}
				},
				data : dateArr
			},
			series : []
		// ToDo
		},
		options : []
	// Todo
	};
	option.baseOption.legend = (function() {
		var namelist = [];
		for (var i = 0; i < yarn_data_obj["queueNameList"].length; i++) {
			namelist.push({
				name : yarn_data_obj["queueNameList"][i],
				icon : 'circle',
				textStyle : {
					fontSize : '12px',
					color : '#999'
				}

			})
		}
		return namelist;
	})()
	for (var i = 0; i < dateArr.length; i++) {
		var options = [];
		for (var i = 0; i < dateArr.length; i++) {
			var item = [];
			for (var j = 0; j < (Object.keys(yarn_data_obj[dateArr[i]])).length; j++) {
				var dataItem = yarn_data_obj[dateArr[i]]
				var nameList = Object.keys(yarn_data_obj[dateArr[i]]);
				item.push({
					name : nameList[j],
					type : 'line',
					stack : '总量',
					areaStyle : {
						normal : {
							color : colorConsArr_hdfs[j],
							opacity : 1
						}
					},
					lineStyle : {
						normal : {
							color : colorConsArr_hdfs[j],
							width : 0
						}
					},
					symbol : 'none',
					smooth : true,
					data : dataItem[nameList[j]]
				})
			}
			option.options.push({
				series : item,

			});

		}
	}
	;
	myChart.setOption(option);
};

function loadscreenbar_three(title, subtitle, date, name1, data1, name2, data2,
		name3, data3, domid, size, top) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);

	// 显示标题，图例和空的坐标轴
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				lineStyle : {
					color : '#ddd'
				}
			},
			backgroundColor : 'rgba(255,255,255,1)',
			padding : [ 5, 10 ],
			textStyle : {
				color : '#7588E4',
			},
			extraCssText : 'box-shadow: 0 0 5px rgba(0,0,0,0.3)'
		},
		legend : {
			right : 20,
			textStyle : {
				color : '#7588E4',
			},
			data : [ name1, name2, name3 ]
		},
		grid : {
			right : 15,
			bottom : 25,
			top : 30,
			left : 25
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : false,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : false
				},
				saveAsImage : {
					show : false
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : date,
			boundaryGap : true,
			splitLine : {
				show : false,
				interval : '0',
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#03246F'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					color : '#999999',
					fontSize : 12
				}
			}
		} ],
		yAxis : [ {
			type : 'value',
			splitLine : {
				show : false,
				lineStyle : {
					color : [ '#D4DFF5' ]
				}
			},
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#021D5A'
				}
			},
			axisLabel : {
				margin : 10,
				textStyle : {
					color : '#999999',
					fontSize : 12
				}
			}
		} ],
		series : [ {
			name : name1,
			type : 'bar',
			data : data1,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		}, {
			name : name2,
			type : 'bar',
			data : data2,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		}, {
			name : name3,
			type : 'bar',
			data : data3,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		} ]
	});
};

function loadscreenbar(title, subtitle, list, domid) {
	var myChart = echarts.init(document.getElementById(domid));
	// 显示标题，图例和空的坐标轴
	myChart
			.setOption({
				tooltip : {
					trigger : 'axis',
					axisPointer : {
						type : 'shadow'
					}
				},
				grid : {
					top : '6%',
					left : '1%',
					right : '0',
					bottom : '1%',
					containLabel : true
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : false
						},
						dataView : {
							show : false,
							readOnly : false
						},
						magicType : {
							show : false,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : false
						},
						saveAsImage : {
							show : false
						}
					}
				},
				calculable : true,
				xAxis : {
					type : 'value',
					boundaryGap : [ 0, 0 ],
					interval : 10,
					min : 0,
					max : 100,
					axisLine : {
						lineStyle : {
							color : '#ccc'
						}
					},
					splitLine : {
						show : false
					}
				},
				yAxis : {
					type : 'category',
					boundaryGap : [ 0, 0.01 ],
					axisLine : {
						lineStyle : {
							color : '#ccc'
						}
					},
					splitLine : {
						show : false
					},
					axisLabel : {// 坐标轴刻度标签的相关设置。
						formatter : function(params) {
							var newParamsName = "";// 最终拼接成的字符串
							var paramsNameNumber = params.length;// 实际标签的个数
							var provideNumber = 8;// 每行能显示的字的个数
							var rowNumber = Math.ceil(paramsNameNumber
									/ provideNumber);// 换行的话，需要显示几行，向上取整
							/**
							 * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理
							 * 如果不大于，即等于或小于，就返回原标签
							 */
							// 条件等同于rowNumber>1
							if (paramsNameNumber > provideNumber) {
								/** 循环每一行,p表示行 */
								for (var p = 0; p < rowNumber; p++) {
									var tempStr = "";// 表示每一次截取的字符串
									var start = p * provideNumber;// 开始截取的位置
									var end = start + provideNumber;// 结束截取的位置
									// 此处特殊处理最后一行的索引值
									if (p == rowNumber - 1) {
										// 最后一次不换行
										tempStr = params.substring(start,
												paramsNameNumber);
									} else {
										// 每一次拼接字符串并换行
										tempStr = params.substring(start, end)
												+ "\n";
									}
									newParamsName += tempStr;// 最终拼成的字符串
								}

							} else {
								// 将旧标签的值赋给新标签
								newParamsName = params;
							}
							// 将最终的字符串返回
							return newParamsName
						}

					},
					data : []
				},
				series : [ {
					type : 'bar',
					barWidth : 25,
					itemStyle : {
						normal : {
							color : [ '#2EC7C9' ]
						}
					},
					/*
					 * itemStyle: { normal: { color: function(params) { var
					 * colorList =
					 * ['#FE9E85','#FEDD7C','#FEDD7C','#FEDD7C','#7BE4BA','#7BE4BA' ];
					 * return colorList[params.dataIndex] }
					 *  } },
					 */
					data : []
				}

				]
			});
	myChart.showLoading(); // 数据加载完之前先显示一段简单的loading动画
	var names = []; // 类别数组（用于存放饼图的类别）
	var brower = new Array();
	// 请求成功时执行该函数内容，result即为服务器返回的json对象
	$.each(list, function(index, item) {
		names.push(item.name); // 挨个取出类别并填入类别数组
		var obj = new Object();
		obj.value = item.values;
		obj.id = item.id;
		brower.push(obj);
	});
	myChart.hideLoading(); // 隐藏加载动画
	myChart.setOption({ // 加载数据图表
		series : [ {
			data : brower
		} ],
		yAxis : [ {
			data : names
		} ]
	});

};

function loadbar_alarmnum(title, subtitle, date, itemid, name1, data1, domid,itemnames) {
	var myChart = echarts.init(document.getElementById(domid), e_macarons);
	myChart.off('click');
	myChart.on('click', function(params) {
		if (null != params.seriesType) {
			var url = 'cmsalarmanalysis.jsp?date='+params.name+'&itemid='+itemid+'&itemnames='+encodeURI(encodeURI(itemnames));
			$('#alarmifrm').attr('src', url);
			$('#alarmscatter').css('display', 'block');
		}
	});
	myChart.setOption({
		title : {
			text : title,
			subtext : subtitle
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ name1 ]
		},
		grid : {
			top : 40,
			left : 40,
			right : 30,
			bottom : 40
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : false
				},
				magicType : {
					show : false,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : false
				},
				saveAsImage : {
					show : false
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : date
		} ],
		yAxis : [ {
			type : 'value',
			minInterval : 10
		} ],
		series : [ {
			name : name1,
			type : 'bar',
			data : data1,
			markPoint : {
				clickable : false,
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			}
		} ]
	});

};