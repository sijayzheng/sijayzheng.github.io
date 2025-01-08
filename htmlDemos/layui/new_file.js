/*! layer-v3.5.1 Web 通用弹出层组件 MIT License */ !(function(e, t) {
	'use strict';
	var i;
	var n;
	var a = e.layui && layui.define;
	var o = {
		getPath: (function() {
			var t = document.currentScript ? document.currentScript.src : (function() {
				for (var e, t = document.scripts, i = t.length - 1, n = i; n > 0; n--)
					if (t[n].readyState === 'interactive') {
						e = t[n].src;
						break
					} return e || t[i].src
			}());
			var i = e.LAYUI_GLOBAL || {};
			return i.layer_dir || t.substring(0, t.lastIndexOf('/') + 1)
		}()),
		config: {},
		end: {},
		minIndex: 0,
		minLeft: [],
		btn: ['&#x786E;&#x5B9A;', '&#x53D6;&#x6D88;'],
		type: ['dialog', 'page', 'iframe', 'loading', 'tips'],
		getStyle: function(t, i) {
			var n = t.currentStyle ? t.currentStyle : e.getComputedStyle(t, null);
			return n[n.getPropertyValue ? 'getPropertyValue' : 'getAttribute'](i)
		},
		link: function(t, i, n) {
			if (r.path) {
				var a = document.getElementsByTagName('head')[0];
				var s = document.createElement('link');
				typeof i === 'string' && (n = i);
				var l = (n || t).replace(/\.|\//g, '');
				var f = 'layuicss-' + l;
				var c = 'creating';
				var u = 0;
				s.rel = 'stylesheet', s.href = r.path + t, s.id = f, document.getElementById(f) || a
					.appendChild(s), typeof i === 'function' && !(function d(t) {
						var n = 100;
						var a = document.getElementById(f);
						return ++u > 1e4 / n ? e.console && console.error(l + '.css: Invalid') : void(
							parseInt(o.getStyle(a, 'width')) === 1989 ? (t === c && a
								.removeAttribute('lay-status'), a.getAttribute('lay-status') === c ?
								setTimeout(d, n) : i()) : (a.setAttribute('lay-status', c),
								setTimeout(function() {
									d(c)
								}, n)))
					}())
			}
		}
	};
	var r = {
		v: '3.5.1',
		ie: (function() {
			var t = navigator.userAgent.toLowerCase();
			return !!(e.ActiveXObject || 'ActiveXObject' in e) && ((t.match(/msie\s(\d+)/) || [])[1] ||
				'11')
		}()),
		index: e.layer && e.layer.v ? 1e5 : 0,
		path: o.getPath,
		config: function(e, t) {
			return e = e || {}, r.cache = o.config = i.extend({}, o.config, e), r.path = o.config.path || r
				.path, typeof e.extend === 'string' && (e.extend = [e.extend]), o.config.path && r.ready(),
				e.extend ? (a ? layui.addcss('modules/layer/' + e.extend) : o.link('theme/' + e.extend),
					this) : this
		},
		ready: function(e) {
			var t = 'layer';
			var i = '';
			var n = (a ? 'modules/layer/' : 'theme/') + 'default/layer.css?v=' + r.v + i;
			return a ? layui.addcss(n, e, t) : o.link(n, e, t), this
		},
		alert: function(e, t, n) {
			var a = typeof t === 'function';
			return a && (n = t), r.open(i.extend({
				content: e,
				yes: n
			}, a ? {} : t))
		},
		confirm: function(e, t, n, a) {
			var s = typeof t === 'function';
			return s && (a = n, n = t), r.open(i.extend({
				content: e,
				btn: o.btn,
				yes: n,
				btn2: a
			}, s ? {} : t))
		},
		msg: function(e, n, a) {
			var s = typeof n === 'function';
			var f = o.config.skin;
			var c = (f ? f + ' ' + f + '-msg' : '') || 'layui-layer-msg';
			var u = l.anim.length - 1;
			return s && (a = n), r.open(i.extend({
				content: e,
				time: 3e3,
				shade: !1,
				skin: c,
				title: !1,
				closeBtn: !1,
				btn: !1,
				resize: !1,
				end: a
			}, s && !o.config.skin ? {
				skin: c + ' layui-layer-hui',
				anim: u
			} : (function() {
				return n = n || {}, (n.icon === -1 || n.icon === t && !o.config.skin) && (n
					.skin = c + ' ' + (n.skin || 'layui-layer-hui')), n
			}())))
		},
		load: function(e, t) {
			return r.open(i.extend({
				type: 3,
				icon: e || 0,
				resize: !1,
				shade: 0.01
			}, t))
		},
		tips: function(e, t, n) {
			return r.open(i.extend({
				type: 4,
				content: [e, t],
				closeBtn: !1,
				time: 3e3,
				shade: !1,
				resize: !1,
				fixed: !1,
				maxWidth: 260
			}, n))
		}
	};
	var s = function(e) {
		var t = this;
		var a = function() {
			t.creat()
		};
		t.index = ++r.index, t.config.maxWidth = i(n).width() - 30, t.config = i.extend({}, t.config, o.config,
			e), document.body ? a() : setTimeout(function() {
			a()
		}, 30)
	};
	s.pt = s.prototype;
	var l = ['layui-layer', '.layui-layer-title', '.layui-layer-main', '.layui-layer-dialog', 'layui-layer-iframe',
		'layui-layer-content', 'layui-layer-btn', 'layui-layer-close'
	];
	l.anim = ['layer-anim-00', 'layer-anim-01', 'layer-anim-02', 'layer-anim-03', 'layer-anim-04', 'layer-anim-05',
		'layer-anim-06'
	], l.SHADE = 'layui-layer-shade', l.MOVE = 'layui-layer-move', s.pt.config = {
		type: 0,
		shade: 0.3,
		fixed: !0,
		move: l[1],
		title: '&#x4FE1;&#x606F;',
		offset: 'auto',
		area: 'auto',
		closeBtn: 1,
		time: 0,
		zIndex: 19891014,
		maxWidth: 360,
		anim: 0,
		isOutAnim: !0,
		minStack: !0,
		icon: -1,
		moveType: 1,
		resize: !0,
		scrollbar: !0,
		tips: 2
	}, s.pt.vessel = function(e, t) {
		var n = this;
		var a = n.index;
		var r = n.config;
		var s = r.zIndex + a;
		var f = typeof r.title === 'object';
		var c = r.maxmin && (r.type === 1 || r.type === 2);
		var u = r.title ? '<div class="layui-layer-title" style="' + (f ? r.title[1] : '') + '">' + (f ? r
			.title[0] : r.title) + '</div>' : '';
		return r.zIndex = s, t([r.shade ? '<div class="' + l.SHADE + '" id="' + l.SHADE + a + '" times="' + a +
			'" style="' + ('z-index:' + (s - 1) + '; ') + '"></div>' : '', '<div class="' + l[0] + (
				' layui-layer-' + o.type[r.type]) + (r.type != 0 && r.type != 2 || r.shade ? '' :
				' layui-layer-border') + ' ' + (r.skin || '') + '" id="' + l[0] + a + '" type="' + o
			.type[r.type] + '" times="' + a + '" showtime="' + r.time + '" conType="' + (e ? 'object' :
				'string') + '" style="z-index: ' + s + '; width:' + r.area[0] + ';height:' + r.area[1] +
			';position:' + (r.fixed ? 'fixed;' : 'absolute;') + '">' + (e && r.type != 2 ? '' : u) +
			'<div id="' + (r.id || '') + '" class="layui-layer-content' + (r.type == 0 && r.icon !== -
				1 ? ' layui-layer-padding' : '') + (r.type == 3 ? ' layui-layer-loading' + r.icon :
			'') + '">' + (r.type == 0 && r.icon !== -1 ? '<i class="layui-layer-ico layui-layer-ico' + r
				.icon + '"></i>' : '') + (r.type == 1 && e ? '' : r.content || '') +
			'</div><span class="layui-layer-setwin">' + (function() {
				var e = c ?
					'<a class="layui-layer-min" href="javascript:;"><cite></cite></a><a class="layui-layer-ico layui-layer-max" href="javascript:;"></a>' :
					'';
				return r.closeBtn && (e += '<a class="layui-layer-ico ' + l[7] + ' ' + l[7] + (r
						.title ? r.closeBtn : r.type == 4 ? '1' : '2') +
					'" href="javascript:;"></a>'), e
			}()) + '</span>' + (r.btn ? (function() {
				var e = '';
				typeof r.btn === 'string' && (r.btn = [r.btn]);
				for (var t = 0, i = r.btn.length; t < i; t++) e += '<a class="' + l[6] + t +
					'">' + r.btn[t] + '</a>';
				return '<div class="' + l[6] + ' layui-layer-btn-' + (r.btnAlign || '') + '">' +
					e + '</div>'
			}()) : '') + (r.resize ? '<span class="layui-layer-resize"></span>' : '') + '</div>'
		], u, i('<div class="' + l.MOVE + '" id="' + l.MOVE + '"></div>')), n
	}, s.pt.creat = function() {
		var e = this;
		var t = e.config;
		var a = e.index;
		var s = t.content;
		var f = typeof s === 'object';
		var c = i('body');
		if (!t.id || !i('#' + t.id)[0]) {
			switch (typeof t.area === 'string' && (t.area = t.area === 'auto' ? ['', ''] : [t.area, '']), t
				.shift && (t.anim = t.shift), r.ie == 6 && (t.fixed = !1), t.type) {
				case 0:
					t.btn = 'btn' in t ? t.btn : o.btn[0], r.closeAll('dialog');
					break;
				case 2:
					var s = t.content = f ? t.content : [t.content || '', 'auto'];
					t.content = '<iframe scrolling="' + (t.content[1] || 'auto') +
						'" allowtransparency="true" id="' + l[4] + a + '" name="' + l[4] + a +
						'" onload="this.className=\'\';" class="layui-layer-load" frameborder="0" src="' + t
						.content[0] + '"></iframe>';
					break;
				case 3:
					delete t.title, delete t.closeBtn, t.icon === -1 && t.icon === 0, r.closeAll('loading');
					break;
				case 4:
					f || (t.content = [t.content, 'body']), t.follow = t.content[1], t.content = t.content[0] +
						'<i class="layui-layer-TipsG"></i>', delete t.title, t.tips = typeof t.tips ===
						'object' ? t.tips : [t.tips, !0], t.tipsMore || r.closeAll('tips')
			}
			if (e.vessel(f, function(n, r, u) {
					c.append(n[0]), f ? (function() {
						t.type == 2 || t.type == 4 ? (function() {
							i('body').append(n[1])
						}()) : (function() {
							s.parents('.' + l[0])[0] || (s.data('display', s.css('display'))
								.show().addClass('layui-layer-wrap').wrap(n[1]), i('#' +
									l[0] + a).find('.' + l[5]).before(r))
						}())
					}()) : c.append(n[1]), i('#' + l.MOVE)[0] || c.append(o.moveElem = u), e.layero = i(
						'#' + l[0] + a), e.shadeo = i('#' + l.SHADE + a), t.scrollbar || l.html.css(
						'overflow', 'hidden').attr('layer-full', a)
				}).auto(a), e.shadeo.css({
					'background-color': t.shade[1] || '#000',
					opacity: t.shade[0] || t.shade
				}), t.type == 2 && r.ie == 6 && e.layero.find('iframe').attr('src', s[0]), t.type == 4 ? e
				.tips() : (function() {
					e.offset(), parseInt(o.getStyle(document.getElementById(l.MOVE), 'z-index')) || (
						function() {
							e.layero.css('visibility', 'hidden'), r.ready(function() {
								e.offset(), e.layero.css('visibility', 'visible')
							})
						}())
				}()), t.fixed && n.on('resize', function() {
					e.offset(), (/^\d+%$/.test(t.area[0]) || /^\d+%$/.test(t.area[1])) && e.auto(a), t
						.type == 4 && e.tips()
				}), t.time <= 0 || setTimeout(function() {
					r.close(e.index)
				}, t.time), e.move().callback(), l.anim[t.anim]) {
				var u = 'layer-anim ' + l.anim[t.anim];
				e.layero.addClass(u).one(
					'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
					function() {
						i(this).removeClass(u)
					})
			}
			t.isOutAnim && e.layero.data('isOutAnim', !0)
		}
	}, s.pt.auto = function(e) {
		var t = this;
		var a = t.config;
		var o = i('#' + l[0] + e);
		a.area[0] === '' && a.maxWidth > 0 && (r.ie && r.ie < 8 && a.btn && o.width(o.innerWidth()), o
			.outerWidth() > a.maxWidth && o.width(a.maxWidth));
		var s = [o.innerWidth(), o.innerHeight()];
		var f = o.find(l[1]).outerHeight() || 0;
		var c = o.find('.' + l[6]).outerHeight() || 0;
		var u = function(e) {
			e = o.find(e), e.height(s[1] - f - c - 2 * (0 | parseFloat(e.css('padding-top'))))
		};
		switch (a.type) {
			case 2:
				u('iframe');
				break;
			default:
				a.area[1] === '' ? a.maxHeight > 0 && o.outerHeight() > a.maxHeight ? (s[1] = a.maxHeight, u(
					'.' + l[5])) : a.fixed && s[1] >= n.height() && (s[1] = n.height(), u('.' + l[5])) : u(
					'.' + l[5])
		}
		return t
	}, s.pt.offset = function() {
		var e = this;
		var t = e.config;
		var i = e.layero;
		var a = [i.outerWidth(), i.outerHeight()];
		var o = typeof t.offset === 'object';
		e.offsetTop = (n.height() - a[1]) / 2, e.offsetLeft = (n.width() - a[0]) / 2, o ? (e.offsetTop = t
				.offset[0], e.offsetLeft = t.offset[1] || e.offsetLeft) : t.offset !== 'auto' && (t.offset ===
				't' ? e.offsetTop = 0 : t.offset === 'r' ? e.offsetLeft = n.width() - a[0] : t.offset === 'b' ?
				e.offsetTop = n.height() - a[1] : t.offset === 'l' ? e.offsetLeft = 0 : t.offset === 'lt' ? (e
					.offsetTop = 0, e.offsetLeft = 0) : t.offset === 'lb' ? (e.offsetTop = n.height() - a[1], e
					.offsetLeft = 0) : t.offset === 'rt' ? (e.offsetTop = 0, e.offsetLeft = n.width() - a[0]) :
				t.offset === 'rb' ? (e.offsetTop = n.height() - a[1], e.offsetLeft = n.width() - a[0]) : e
				.offsetTop = t.offset), t.fixed || (e.offsetTop = /%$/.test(e.offsetTop) ? n.height() *
				parseFloat(e.offsetTop) / 100 : parseFloat(e.offsetTop), e.offsetLeft = /%$/.test(e
				.offsetLeft) ? n.width() * parseFloat(e.offsetLeft) / 100 : parseFloat(e.offsetLeft), e
				.offsetTop += n.scrollTop(), e.offsetLeft += n.scrollLeft()), i.attr('minLeft') && (e
				.offsetTop = n.height() - (i.find(l[1]).outerHeight() || 0), e.offsetLeft = i.css('left')), i
			.css({
				top: e.offsetTop,
				left: e.offsetLeft
			})
	}, s.pt.tips = function() {
		var e = this;
		var t = e.config;
		var a = e.layero;
		var o = [a.outerWidth(), a.outerHeight()];
		var r = i(t.follow);
		r[0] || (r = i('body'));
		var s = {
			width: r.outerWidth(),
			height: r.outerHeight(),
			top: r.offset().top,
			left: r.offset().left
		};
		var f = a.find('.layui-layer-TipsG');
		var c = t.tips[0];
		t.tips[1] || f.remove(), s.autoLeft = function() {
				s.left + o[0] - n.width() > 0 ? (s.tipLeft = s.left + s.width - o[0], f.css({
					right: 12,
					left: 'auto'
				})) : s.tipLeft = s.left
			}, s.where = [function() {
				s.autoLeft(), s.tipTop = s.top - o[1] - 10, f.removeClass('layui-layer-TipsB').addClass(
					'layui-layer-TipsT').css('border-right-color', t.tips[1])
			}, function() {
				s.tipLeft = s.left + s.width + 10, s.tipTop = s.top, f.removeClass('layui-layer-TipsL')
					.addClass('layui-layer-TipsR').css('border-bottom-color', t.tips[1])
			}, function() {
				s.autoLeft(), s.tipTop = s.top + s.height + 10, f.removeClass('layui-layer-TipsT').addClass(
					'layui-layer-TipsB').css('border-right-color', t.tips[1])
			}, function() {
				s.tipLeft = s.left - o[0] - 10, s.tipTop = s.top, f.removeClass('layui-layer-TipsR')
					.addClass('layui-layer-TipsL').css('border-bottom-color', t.tips[1])
			}], s.where[c - 1](), c === 1 ? s.top - (n.scrollTop() + o[1] + 16) < 0 && s.where[2]() : c === 2 ?
			n.width() - (s.left + s.width + o[0] + 16) > 0 || s.where[3]() : c === 3 ? s.top - n.scrollTop() + s
			.height + o[1] + 16 - n.height() > 0 && s.where[0]() : c === 4 && o[0] + 16 - s.left > 0 && s.where[
				1](), a.find('.' + l[5]).css({
				'background-color': t.tips[1],
				'padding-right': t.closeBtn ? '30px' : ''
			}), a.css({
				left: s.tipLeft - (t.fixed ? n.scrollLeft() : 0),
				top: s.tipTop - (t.fixed ? n.scrollTop() : 0)
			})
	}, s.pt.move = function() {
		var e = this;
		var t = e.config;
		var a = i(document);
		var s = e.layero;
		var l = s.find(t.move);
		var f = s.find('.layui-layer-resize');
		var c = {};
		return t.move && l.css('cursor', 'move'), l.on('mousedown', function(e) {
			e.preventDefault(), t.move && (c.moveStart = !0, c.offset = [e.clientX - parseFloat(s.css(
				'left')), e.clientY - parseFloat(s.css('top'))], o.moveElem.css('cursor',
				'move').show())
		}), f.on('mousedown', function(e) {
			e.preventDefault(), c.resizeStart = !0, c.offset = [e.clientX, e.clientY], c.area = [s
				.outerWidth(), s.outerHeight()
			], o.moveElem.css('cursor', 'se-resize').show()
		}), a.on('mousemove', function(i) {
			if (c.moveStart) {
				var a = i.clientX - c.offset[0];
				var o = i.clientY - c.offset[1];
				var l = s.css('position') === 'fixed';
				if (i.preventDefault(), c.stX = l ? 0 : n.scrollLeft(), c.stY = l ? 0 : n.scrollTop(), !
					t.moveOut) {
					var f = n.width() - s.outerWidth() + c.stX;
					var u = n.height() - s.outerHeight() + c.stY;
					a < c.stX && (a = c.stX), a > f && (a = f), o < c.stY && (o = c.stY), o > u && (o =
						u)
				}
				s.css({
					left: a,
					top: o
				})
			}
			if (t.resize && c.resizeStart) {
				var a = i.clientX - c.offset[0];
				var o = i.clientY - c.offset[1];
				i.preventDefault(), r.style(e.index, {
					width: c.area[0] + a,
					height: c.area[1] + o
				}), c.isResize = !0, t.resizing && t.resizing(s)
			}
		}).on('mouseup', function(e) {
			c.moveStart && (delete c.moveStart, o.moveElem.hide(), t.moveEnd && t.moveEnd(s)), c
				.resizeStart && (delete c.resizeStart, o.moveElem.hide())
		}), e
	}, s.pt.callback = function() {
		function e() {
			var e = a.cancel && a.cancel(t.index, n);
			e === !1 || r.close(t.index)
		}
		var t = this;
		var n = t.layero;
		var a = t.config;
		t.openLayer(), a.success && (a.type == 2 ? n.find('iframe').on('load', function() {
			a.success(n, t.index)
		}) : a.success(n, t.index)), r.ie == 6 && t.IE6(n), n.find('.' + l[6]).children('a').on('click',
			function() {
				var e = i(this).index();
				if (e === 0) a.yes ? a.yes(t.index, n) : a.btn1 ? a.btn1(t.index, n) : r.close(t.index);
				else {
					var o = a['btn' + (e + 1)] && a['btn' + (e + 1)](t.index, n);
					o === !1 || r.close(t.index)
				}
			}), n.find('.' + l[7]).on('click', e), a.shadeClose && t.shadeo.on('click', function() {
			r.close(t.index)
		}), n.find('.layui-layer-min').on('click', function() {
			var e = a.min && a.min(n, t.index);
			e === !1 || r.min(t.index, a)
		}), n.find('.layui-layer-max').on('click', function() {
			i(this).hasClass('layui-layer-maxmin') ? (r.restore(t.index), a.restore && a.restore(n, t
				.index)) : (r.full(t.index, a), setTimeout(function() {
				a.full && a.full(n, t.index)
			}, 100))
		}), a.end && (o.end[t.index] = a.end)
	}, o.reselect = function() {
		i.each(i('select'), function(e, t) {
			var n = i(this);
			n.parents('.' + l[0])[0] || n.attr('layer') == 1 && i('.' + l[0]).length < 1 && n
				.removeAttr('layer').show(), n = null
		})
	}, s.pt.IE6 = function(e) {
		i('select').each(function(e, t) {
			var n = i(this);
			n.parents('.' + l[0])[0] || n.css('display') === 'none' || n.attr({
				layer: '1'
			}).hide(), n = null
		})
	}, s.pt.openLayer = function() {
		var e = this;
		r.zIndex = e.config.zIndex, r.setTop = function(e) {
			var t = function() {
				r.zIndex++, e.css('z-index', r.zIndex + 1)
			};
			return r.zIndex = parseInt(e[0].style.zIndex), e.on('mousedown', t), r.zIndex
		}
	}, o.record = function(e) {
		var t = [e.width(), e.height(), e.position().top, e.position().left + parseFloat(e.css('margin-left'))];
		e.find('.layui-layer-max').addClass('layui-layer-maxmin'), e.attr({
			area: t
		})
	}, o.rescollbar = function(e) {
		l.html.attr('layer-full') == e && (l.html[0].style.removeProperty ? l.html[0].style.removeProperty(
			'overflow') : l.html[0].style.removeAttribute('overflow'), l.html.removeAttr('layer-full'))
	}, e.layer = r, r.getChildFrame = function(e, t) {
		return t = t || i('.' + l[4]).attr('times'), i('#' + l[0] + t).find('iframe').contents().find(e)
	}, r.getFrameIndex = function(e) {
		return i('#' + e).parents('.' + l[4]).attr('times')
	}, r.iframeAuto = function(e) {
		if (e) {
			var t = r.getChildFrame('html', e).outerHeight();
			var n = i('#' + l[0] + e);
			var a = n.find(l[1]).outerHeight() || 0;
			var o = n.find('.' + l[6]).outerHeight() || 0;
			n.css({
				height: t + a + o
			}), n.find('iframe').css({
				height: t
			})
		}
	}, r.iframeSrc = function(e, t) {
		i('#' + l[0] + e).find('iframe').attr('src', t)
	}, r.style = function(e, t, n) {
		var a = i('#' + l[0] + e);
		var r = a.find('.layui-layer-content');
		var s = a.attr('type');
		var f = a.find(l[1]).outerHeight() || 0;
		var c = a.find('.' + l[6]).outerHeight() || 0;
		a.attr('minLeft');
		s !== o.type[3] && s !== o.type[4] && (n || (parseFloat(t.width) <= 260 && (t.width = 260), parseFloat(t
				.height) - f - c <= 64 && (t.height = 64 + f + c)), a.css(t), c = a.find('.' + l[6])
			.outerHeight(), s === o.type[2] ? a.find('iframe').css({
				height: parseFloat(t.height) - f - c
			}) : r.css({
				height: parseFloat(t.height) - f - c - parseFloat(r.css('padding-top')) - parseFloat(r
					.css('padding-bottom'))
			}))
	}, r.min = function(e, t) {
		t = t || {};
		var a = i('#' + l[0] + e);
		var s = i('#' + l.SHADE + e);
		var f = a.find(l[1]).outerHeight() || 0;
		var c = a.attr('minLeft') || 181 * o.minIndex + 'px';
		var u = a.css('position');
		var d = {
			width: 180,
			height: f,
			position: 'fixed',
			overflow: 'hidden'
		};
		o.record(a), o.minLeft[0] && (c = o.minLeft[0], o.minLeft.shift()), t.minStack && (d.left = c, d.top = n
				.height() - f, a.attr('minLeft') || o.minIndex++, a.attr('minLeft', c)), a.attr('position', u),
			r.style(e, d, !0), a.find('.layui-layer-min').hide(), a.attr('type') === 'page' && a.find(l[4])
			.hide(), o.rescollbar(e), s.hide()
	}, r.restore = function(e) {
		var t = i('#' + l[0] + e);
		var n = i('#' + l.SHADE + e);
		var a = t.attr('area').split(',');
		t.attr('type');
		r.style(e, {
				width: parseFloat(a[0]),
				height: parseFloat(a[1]),
				top: parseFloat(a[2]),
				left: parseFloat(a[3]),
				position: t.attr('position'),
				overflow: 'visible'
			}, !0), t.find('.layui-layer-max').removeClass('layui-layer-maxmin'), t.find('.layui-layer-min')
			.show(), t.attr('type') === 'page' && t.find(l[4]).show(), o.rescollbar(e), n.show()
	}, r.full = function(e) {
		var t;
		var a = i('#' + l[0] + e);
		o.record(a), l.html.attr('layer-full') || l.html.css('overflow', 'hidden').attr('layer-full', e),
			clearTimeout(t), t = setTimeout(function() {
				var t = a.css('position') === 'fixed';
				r.style(e, {
					top: t ? 0 : n.scrollTop(),
					left: t ? 0 : n.scrollLeft(),
					width: n.width(),
					height: n.height()
				}, !0), a.find('.layui-layer-min').hide()
			}, 100)
	}, r.title = function(e, t) {
		var n = i('#' + l[0] + (t || r.index)).find(l[1]);
		n.html(e)
	}, r.close = function(e, t) {
		var n = i('#' + l[0] + e);
		var a = n.attr('type');
		var s = 'layer-anim-close';
		if (n[0]) {
			var f = 'layui-layer-wrap';
			var c = function() {
				if (a === o.type[1] && n.attr('conType') === 'object') {
					n.children(':not(.' + l[5] + ')').remove();
					for (var r = n.find('.' + f), s = 0; s < 2; s++) r.unwrap();
					r.css('display', r.data('display')).removeClass(f)
				} else {
					if (a === o.type[2]) try {
						var c = i('#' + l[4] + e)[0];
						c.contentWindow.document.write(''), c.contentWindow.close(), n.find('.' + l[5])[
							0].removeChild(c)
					} catch (u) {}
					n[0].innerHTML = '', n.remove()
				}
				typeof o.end[e] === 'function' && o.end[e](), delete o.end[e], typeof t === 'function' &&
				t()
			};
			n.data('isOutAnim') && n.addClass('layer-anim ' + s), i('#layui-layer-moves, #' + l.SHADE + e)
				.remove(), r.ie == 6 && o.reselect(), o.rescollbar(e), n.attr('minLeft') && (o.minIndex--, o
					.minLeft.push(n.attr('minLeft'))), r.ie && r.ie < 10 || !n.data('isOutAnim') ? c() :
				setTimeout(function() {
					c()
				}, 200)
		}
	}, r.closeAll = function(e, t) {
		typeof e === 'function' && (t = e, e = null);
		var n = i('.' + l[0]);
		i.each(n, function(a) {
			var o = i(this);
			var s = e ? o.attr('type') === e : 1;
			s && r.close(o.attr('times'), a === n.length - 1 ? t : null), s = null
		}), n.length === 0 && typeof t === 'function' && t()
	};
	var f = r.cache || {};
	var c = function(e) {
		return f.skin ? ' ' + f.skin + ' ' + f.skin + '-' + e : ''
	};
	r.prompt = function(e, t) {
		var a = '';
		if (e = e || {}, typeof e === 'function' && (t = e), e.area) {
			var o = e.area;
			a = 'style="width: ' + o[0] + '; height: ' + o[1] + ';"', delete e.area
		}
		var s;
		var l = e.formType == 2 ? '<textarea class="layui-layer-input"' + a + '></textarea>' : (function() {
			return '<input type="' + (e.formType == 1 ? 'password' : 'text') +
				'" class="layui-layer-input">'
		}());
		var f = e.success;
		return delete e.success, r.open(i.extend({
			type: 1,
			btn: ['&#x786E;&#x5B9A;', '&#x53D6;&#x6D88;'],
			content: l,
			skin: 'layui-layer-prompt' + c('prompt'),
			maxWidth: n.width(),
			success: function(t) {
				s = t.find('.layui-layer-input'), s.val(e.value || '').focus(), typeof f ===
					'function' && f(t)
			},
			resize: !1,
			yes: function(i) {
				var n = s.val();
				n === '' ? s.focus() : n.length > (e.maxlength || 500) ? r.tips(
					'&#x6700;&#x591A;&#x8F93;&#x5165;' + (e.maxlength || 500) +
					'&#x4E2A;&#x5B57;&#x6570;', s, {
						tips: 1
					}) : t && t(n, i, s)
			}
		}, e))
	}, r.tab = function(e) {
		e = e || {};
		var t = e.tab || {};
		var n = 'layui-this';
		var a = e.success;
		return delete e.success, r.open(i.extend({
			type: 1,
			skin: 'layui-layer-tab' + c('tab'),
			resize: !1,
			title: (function() {
				var e = t.length;
				var i = 1;
				var a = '';
				if (e > 0)
					for (a = '<span class="' + n + '">' + t[0].title + '</span>'; i <
						e; i++) a += '<span>' + t[i].title + '</span>';
				return a
			}()),
			content: '<ul class="layui-layer-tabmain">' + (function() {
				var e = t.length;
				var i = 1;
				var a = '';
				if (e > 0)
					for (a = '<li class="layui-layer-tabli ' + n + '">' + (t[0].content ||
							'no content') + '</li>'; i < e; i++) a +=
						'<li class="layui-layer-tabli">' + (t[i].content || 'no  content') +
						'</li>';
				return a
			}()) + '</ul>',
			success: function(t) {
				var o = t.find('.layui-layer-title').children();
				var r = t.find('.layui-layer-tabmain').children();
				o.on('mousedown', function(t) {
					t.stopPropagation ? t.stopPropagation() : t.cancelBubble = !0;
					var a = i(this);
					var o = a.index();
					a.addClass(n).siblings().removeClass(n), r.eq(o).show().siblings()
						.hide(), typeof e.change === 'function' && e.change(o)
				}), typeof a === 'function' && a(t)
			}
		}, e))
	}, r.photos = function(t, n, a) {
		function o(e, t, i) {
			var n = new Image();
			return n.src = e, n.complete ? t(n) : (n.onload = function() {
				n.onload = null, t(n)
			}, void(n.onerror = function(e) {
				n.onerror = null, i(e)
			}))
		}
		var s = {};
		if (t = t || {}, t.photos) {
			var l = !(typeof t.photos === 'string' || t.photos instanceof i);
			var f = l ? t.photos : {};
			var u = f.data || [];
			var d = f.start || 0;
			s.imgIndex = (0 | d) + 1, t.img = t.img || 'img';
			var y = t.success;
			if (delete t.success, l) {
				if (u.length === 0) return r.msg('&#x6CA1;&#x6709;&#x56FE;&#x7247;')
			} else {
				var p = i(t.photos);
				var h = function() {
					u = [], p.find(t.img).each(function(e) {
						var t = i(this);
						t.attr('layer-index', e), u.push({
							alt: t.attr('alt'),
							pid: t.attr('layer-pid'),
							src: t.attr('layer-src') || t.attr('src'),
							thumb: t.attr('src')
						})
					})
				};
				if (h(), u.length === 0) return;
				if (n || p.on('click', t.img, function() {
						h();
						var e = i(this);
						var n = e.attr('layer-index');
						r.photos(i.extend(t, {
							photos: {
								start: n,
								data: u,
								tab: t.tab
							},
							full: t.full
						}), !0)
					}), !n) return
			}
			s.imgprev = function(e) {
				s.imgIndex--, s.imgIndex < 1 && (s.imgIndex = u.length), s.tabimg(e)
			}, s.imgnext = function(e, t) {
				s.imgIndex++, s.imgIndex > u.length && (s.imgIndex = 1, t) || s.tabimg(e)
			}, s.keyup = function(e) {
				if (!s.end) {
					var t = e.keyCode;
					e.preventDefault(), t === 37 ? s.imgprev(!0) : t === 39 ? s.imgnext(!0) : t === 27 && r
						.close(s.index)
				}
			}, s.tabimg = function(e) {
				if (!(u.length <= 1)) return f.start = s.imgIndex - 1, r.close(s.index), r.photos(t, !0, e)
			}, s.event = function() {
				s.bigimg.find('.layui-layer-imgprev').on('click', function(e) {
					e.preventDefault(), s.imgprev(!0)
				}), s.bigimg.find('.layui-layer-imgnext').on('click', function(e) {
					e.preventDefault(), s.imgnext(!0)
				}), i(document).on('keyup', s.keyup)
			}, s.loadi = r.load(1, {
				shade: !('shade' in t) && 0.9,
				scrollbar: !1
			}), o(u[d].src, function(n) {
				r.close(s.loadi), a && (t.anim = -1), s.index = r.open(i.extend({
					type: 1,
					id: 'layui-layer-photos',
					area: (function() {
						var a = [n.width, n.height];
						var o = [i(e).width() - 100, i(e).height() - 100];
						if (!t.full && (a[0] > o[0] || a[1] > o[1])) {
							var r = [a[0] / o[0], a[1] / o[1]];
							r[0] > r[1] ? (a[0] = a[0] / r[0], a[1] = a[1] / r[0]) :
								r[0] < r[1] && (a[0] = a[0] / r[1], a[1] = a[1] / r[
									1])
						}
						return [a[0] + 'px', a[1] + 'px']
					}()),
					title: !1,
					shade: 0.9,
					shadeClose: !0,
					closeBtn: !1,
					move: '.layui-layer-phimg img',
					moveType: 1,
					scrollbar: !1,
					moveOut: !0,
					anim: 5,
					isOutAnim: !1,
					skin: 'layui-layer-photos' + c('photos'),
					content: '<div class="layui-layer-phimg"><img src="' + u[d].src +
						'" alt="' + (u[d].alt || '') + '" layer-pid="' + u[d].pid + '">' + (
							function() {
								return u.length > 1 ?
									'<div class="layui-layer-imgsee"><span class="layui-layer-imguide"><a href="javascript:;" class="layui-layer-iconext layui-layer-imgprev"></a><a href="javascript:;" class="layui-layer-iconext layui-layer-imgnext"></a></span><div class="layui-layer-imgbar" style="display:' +
									(a ? 'block' : '') +
									'"><span class="layui-layer-imgtit"><a href="javascript:;">' +
									(u[d].alt || '') + '</a><em>' + s.imgIndex + ' / ' + u
									.length + '</em></span></div></div>' : ''
							}()) + '</div>',
					success: function(e, i) {
						s.bigimg = e.find('.layui-layer-phimg'), s.imgsee = e.find(
							'.layui-layer-imgbar'), s.event(e), t.tab && t.tab(u[d],
							e), typeof y === 'function' && y(e)
					},
					end: function() {
						s.end = !0, i(document).off('keyup', s.keyup)
					}
				}, t))
			}, function() {
				r.close(s.loadi), r.msg(
					'&#x5F53;&#x524D;&#x56FE;&#x7247;&#x5730;&#x5740;&#x5F02;&#x5E38;<br>&#x662F;&#x5426;&#x7EE7;&#x7EED;&#x67E5;&#x770B;&#x4E0B;&#x4E00;&#x5F20;&#xFF1F;', {
						time: 3e4,
						btn: ['&#x4E0B;&#x4E00;&#x5F20;', '&#x4E0D;&#x770B;&#x4E86;'],
						yes: function() {
							u.length > 1 && s.imgnext(!0, !0)
						}
					})
			})
		}
	}, o.run = function(t) {
		i = t, n = i(e), l.html = i('html'), r.open = function(e) {
			var t = new s(e);
			return t.index
		}
	}, e.layui && layui.define ? (r.ready(), layui.define('jquery', function(t) {
		r.path = layui.cache.dir, o.run(layui.$), e.layer = r, t('layer', r)
	})) : typeof define === 'function' && define.amd ? define(['jquery'], function() {
		return o.run(e.jQuery), r
	}) : (function() {
		r.ready(), o.run(e.jQuery)
	}())
}(window))
