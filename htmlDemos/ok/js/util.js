(function(global, $, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
		typeof define === 'function' && define.amd ? define(factory) : global.moment = factory()
}(this, jQuery, function() {

	/**
	 * 数字格式化 number 需要格式化的数字 digits 格式化后的数字位数
	 */
	this.formatDigits = function(number, digits) {
		number += '';
		if (number.length < digits) {
			let frontZero = '';
			for (let i = number.length; i < digits; i++) {
				frontZero += '0';
			}
			return frontZero + number;
		} else {
			return number;
		}
	}

	/**
	 * 对Date的扩展，将Date转化为指定格式的
	 * String (new Date()).format("YYYY-MM-DD HH:mm:ss.S d") ==> 2019-10-18 17:20:43.94 星期五
	 */
	let weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
	this.Date.prototype.format = function(fmt) {
		let o = {
			"M+": this.getMonth() + 1, // 月份
			"D+": this.getDate(), // 日
			"d+": weeks[this.getDay()], // 星期
			"H+": this.getHours(), // 小时
			"m+": this.getMinutes(), // 分
			"s+": this.getSeconds(), // 秒
			"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
			"S": this.getMilliseconds() // 毫秒
		};
		if (/(Y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (let k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt
	}

	this.Date.prototype.fromNow = function() {
		let now = new Date();
		return now < this ? this - now : now - this;
	}

	/**
	 * 获取date的次日日期
	 */
	this.getNextDate = function(date) {
		return new Date(new Date(date).getTime() + 24 * 60 * 60 * 1000).format("YYYY-MM-DD");
	}

	/**
	 * 获取date的前一日日期
	 */
	this.getPreviousDate = function(date) {
		return new Date(new Date(date).getTime() - 24 * 60 * 60 * 1000).format("YYYY-MM-DD");
	}

	this.secondsToTime = function(x) {
		let t = '';
		if (x > -1) {
			let d = Math.floor(x / 1000 / 60 / 60 / 24)
			x = x - d * 24 * 60 * 60 * 1000
			let h = Math.floor(x / 1000 / 60 / 60)
			x = x - h * 60 * 60 * 1000
			let m = Math.floor(x / 1000 / 60)
			x = x - m * 60 * 1000
			let s = Math.floor(x / 1000)
			t = formatDigits(d, 2) + '日' + formatDigits(h, 2) + '时' + formatDigits(m, 2) + '分' + formatDigits(s, 2) + '秒';
		}
		return t;
	}

	this.secondsToDate = function(x) {
		let t = '';
		if (x > -1) {
			let d = Math.floor(x / 1000 / 60 / 60 / 24)
			t = formatDigits(d, 2) + '日';
		}
		return t;
	}

	/**
	 * 获取浏览器的类型及版本
	 */
	this.getBroswer = function() {
		var Sys = {},
			ua = navigator.userAgent.toLowerCase(),
			s = 0;
		(s = ua.match(/edge\/([\d.]+)/)) ? Sys.edge = s[1]:
			(s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1] :
			(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
			(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
			(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
			(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
			(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
		if (Sys.edge) return {
			broswer: "Edge",
			version: Sys.edge
		};
		if (Sys.ie) return {
			broswer: "IE",
			version: Sys.ie
		};
		if (Sys.firefox) return {
			broswer: "Firefox",
			version: Sys.firefox
		};
		if (Sys.chrome) return {
			broswer: "Chrome",
			version: Sys.chrome
		};
		if (Sys.opera) return {
			broswer: "Opera",
			version: Sys.opera
		};
		if (Sys.safari) return {
			broswer: "Safari",
			version: Sys.safari
		};
		return {
			broswer: "",
			version: "0"
		};
	}

}));
