function validate_not_null(field, alerttext) {
	with(field) {
		if(value == null || value == "") {
			alert(alerttxt);
			return false
		} else {
			return true
		}
	}
}

function validate_length(field,alerttext) {
	if(document.a.b.value.length > 50) {
		alert("不能超过50个字符！");
		document.a.b.focus();
		return false;
	}
}

function validate_form(thisform) {
	with(thisform) {
		if(validate_required(email, "邮箱不能为空") == false) {
			email.focus();
			return false
		}
	}
}