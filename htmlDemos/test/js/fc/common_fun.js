(function($) {

	this.numFormatt = function(num) {
		return num < 10 ? '0' + num : num;
	}

	this.dateFormatt = function(d, Y, M, D, h, m, s) {
		var date = new Date(d);
		var result = '';
		if (Y != undefined) {
			result += date.getFullYear()
		}
		if (M != undefined) {
			var num = date.getMonth() + 1;
			result += '-' + numFormatt(num)
		}
		if (D != undefined) {
			var num = date.getDate();
			result += '-' + numFormatt(num)
		}
		if (h != undefined) {
			var num = date.getHours();
			result += ' ' + numFormatt(num)
		}
		if (m != undefined) {
			var num = date.getMinutes()
			result += ':' + numFormatt(num)
		}
		if (s != undefined) {
			var num = date.getSeconds()
			result += ':' + numFormatt(num)
		}
		return result;
	}

	this.getTodayDate = function(date) {
		return date.getFullYear() + '-' + numFormatt(date.getMonth() + 1) + '-' + numFormatt(date.getDate());
	}

	this.getNextDate = function(date) {
		date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
		return date.getFullYear() + '-' + numFormatt(date.getMonth() + 1) + '-' + numFormatt(date.getDate());
	}

	this.getTime = function(date) {
		return numFormatt(date.getHours()) + ':' + numFormatt(date.getMinutes());
	}

})(jQuery);