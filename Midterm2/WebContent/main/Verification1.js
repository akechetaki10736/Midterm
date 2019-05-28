document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("uno").addEventListener("blur", checkUno);
	document.getElementById("cname").addEventListener("blur", checkCname);
	document.getElementById("address").addEventListener("blur", checkAddr);
	document.getElementById("principal").addEventListener("blur", checkPrincipal);
	document.getElementById("capital").addEventListener("blur", checkCapital);
	document.getElementById("setdate").addEventListener("blur", checkSetdate);
}); //end of DOM

function checkUno() {
	let uno = document.getElementById("uno").value;
	let checkFormat = /^[0-9]{8}$/;
	if (uno == null || uno.trim().length == 0) {
		document.getElementById("unodiv").innerHTML = "<img src=images/warning.png>  統一編號欄位不可為空白";
		document.getElementById("b1").disabled = true;

	} else if (uno.trim().length != 8) {
		document.getElementById("unodiv").innerHTML = "<img src=images/warning.png>  統一編號長度須為8";
		document.getElementById("b1").disabled = true;

	} else if (!checkFormat.test(uno)) {
		document.getElementById("unodiv").innerHTML = "<img src=images/warning.png>  統一編號須為數字";
		document.getElementById("b1").disabled = true;

	} else {
		document.getElementById("unodiv").innerHTML = "<img src='images/check.png'/>";
		document.getElementById("b1").disabled = false;

	}
} //end of checkUno

function checkCname() {
	let cname = document.getElementById("cname").value;
	if (cname == null || cname.trim().length == 0) {
		document.getElementById("cnamediv").innerHTML = "<img src=images/warning.png>  公司名稱欄位未輸入";
	} else {
		document.getElementById("cnamediv").innerHTML = "<img src='images/check.png'/>";
	}
} //end of checkCname

function checkAddr() {
	let addr = document.getElementById("address").value;
	if (addr == null || addr.trim().length == 0) {
		document.getElementById("addressdiv").innerHTML = "<img src=images/warning.png>  地址欄位未輸入";
	} else {
		document.getElementById("addressdiv").innerHTML = "<img src='images/check.png'/>";
	}
} // end of checkAddr

function checkPrincipal() {
	let principal = document.getElementById("principal").value;
	if (principal == null || principal.trim().length == 0) {
		document.getElementById("principaldiv").innerHTML = "<img src=images/warning.png>  負責人欄位未輸入";
	} else {
		document.getElementById("principaldiv").innerHTML = "<img src='images/check.png'/>";
	}
} //end of checkPrincipal

function checkCapital() {
	let numberCheck = /^[0-9]{1,}$/ ;
	let capital = document.getElementById("capital").value;
	if (capital == null || capital.trim().length == 0) {
		document.getElementById("capitaldiv").innerHTML = "<img src=images/warning.png>  資本額欄位未輸入";
	} else if(isNaN(parseInt(capital))){
		document.getElementById("capitaldiv").innerHTML = "<img src=images/warning.png>  資本額欄位須為數字";
	} else if(!numberCheck.test(capital)){
		document.getElementById("capitaldiv").innerHTML = "<img src=images/warning.png>  資本額欄位須為數字";
	} else {
		document.getElementById("capitaldiv").innerHTML = "<img src='images/check.png'/>";
	}
} // end of checkCapital

function checkSetdate() {
	let setdate = document.getElementById("setdate").value;
	let checkDFormat = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	if (setdate == "") {
		document.getElementById("setdatediv").innerHTML = "<img src=images/warning.png>  設立日期欄未輸入";
	} else if (checkDFormat.test(setdate)) {
		let dateString = setdate.split('-');
		let dateTime = new Date(setdate);
		if (dateTime) {
			let dateTimeYear = dateTime.getFullYear();
			let dateTimeMonth = dateTime.getMonth() + 1;
			let dateTimeDate = dateTime.getDate();
			if (parseInt(dateString[1]) != dateTimeMonth) {
				document.getElementById("setdatediv").innerHTML = "<img src=images/warning.png>  不合法日期";
			} else {
				document.getElementById("setdatediv").innerHTML = "<img src='images/check.png'/>";
			}
		} else {
			document.getElementById("setdatediv").innerHTML = "<img src=images/warning.png>  不合法日期";
		}
	}
} // end of checkSetdate

function focusUno() {
	document.getElementById("uno").removeEventListener('blur', checkUno);
	document.getElementById("unodiv").innerHTML = ""
	document.getElementById("uno").addEventListener("blur", unoExist);
	
	document.getElementById("cname").removeEventListener("blur", checkCname);
	document.getElementById("cnamediv").innerHTML = ""
	document.getElementById("address").removeEventListener("blur", checkAddr);
	document.getElementById("addressdiv").innerHTML = ""
	document.getElementById("principal").removeEventListener("blur", checkPrincipal);
	document.getElementById("principaldiv").innerHTML = ""
	document.getElementById("capital").removeEventListener("blur", checkCapital);
	document.getElementById("capitaldiv").innerHTML = ""
	document.getElementById("setdate").removeEventListener("blur", checkSetdate);
	document.getElementById("setdatediv").innerHTML = ""
} // end of focusUno

function purpose() {
	let selectValue = document.getElementById("s1").value;
	if (selectValue == 2 || selectValue == 3 || selectValue == 4) {
		focusUno();
	} else if (selectValue == 1) {
		document.getElementById("unodiv").innerHTML = ""
		document.getElementById("uno").removeEventListener('blur', unoExist);
		document.getElementById("uno").addEventListener("blur", checkUno);
		document.getElementById("cnamediv").innerHTML = ""
		document.getElementById("cname").addEventListener("blur", checkCname);
		document.getElementById("addressdiv").innerHTML = ""
		document.getElementById("address").addEventListener("blur", checkAddr);
		document.getElementById("principaldiv").innerHTML = ""
		document.getElementById("principal").addEventListener("blur", checkPrincipal);
		document.getElementById("capitaldiv").innerHTML = ""
		document.getElementById("capital").addEventListener("blur", checkCapital);
		document.getElementById("setdatediv").innerHTML = ""
		document.getElementById("setdate").addEventListener("blur", checkSetdate);
		
	} // end of purpose
}

function unoExist() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var passed = eval('(' + xhttp.responseText + ')');
			var msg = ((passed) ? "<img src='images/check.png'/>  "
					: "<img src=images/warning.png>  ")
					+ "  " + "統一編號" + ((passed) ? "存在 " : "不存在 ");
			var fontColor = (passed) ? "green" : "red";
			document.getElementById("unodiv").innerHTML = " <font color = ' "
					+ fontColor + " '><b>" + msg + " </b></font>";
			if (!passed) {
				document.getElementById("b1").disabled = true;
			} else {
				document.getElementById("b1").disabled = false;
			}
		}
	}
	var uno = document.getElementById("uno").value;
	var url = "http://localhost:8080/Midterm2/main/ProcessMidterm2Servlet?uno=" + uno;
	xhttp.open("GET", url, true);

	xhttp.send();
} // end of UnoExist
