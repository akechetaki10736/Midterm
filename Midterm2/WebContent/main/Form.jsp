<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.sql.*, javax.naming.* , javax.sql.*"%>

 

<!DOCTYPE html>
<html>
<head>
<script src="Verification1.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	font-family: 微軟正黑體;
}

form {
	margin: 0 auto;
	width: 600px;
}
</style>
</head>
<body onload="javascript:document.m2bform.uno.focus();">
	<form name="m2bform" action="ProcessMidterm2Servlet" method="POST">
		<table border="1">
			<thead>
				<tr bgcolor="tan">
					<th height="60" colspan="2" align="center">新設公司資料管理</th>
				</tr>
			</thead>
			<tbody>
				<tr bgcolor="tan">
					<td width="120" height="40">功能選擇：</td>
					<td width="600" height="40" align="left"><select id="s1"
						name="s1" onchange="purpose()">
							<option id="o1" value="1">新增</option>
							<option id="o2" value="2">查詢</option>
							<option id="o3" value="3">修改</option>
							<option id="o4" value="4">刪除</option>
					</select>
						<div  style="color: #FF0000; display: inline"></div></td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">統一編號：</td>
					<td width="600" height="40" align="left"><input
						style="text-align: left" type="text" name="uno" id="uno"
						placeholder="必填欄位" value="${ param.uno }" > <span id="unosp"></span> 
						<div id="unodiv" style="color: #FF0000; display: inline">${ErrorMsg.uno}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">公司名稱：</td>
					<td width="600" height="40" align="left"><input
						style="text-align: left" type="text" name="cname" id="cname"
						value="${ param.cname }"> <!-- 					<span id="cnamesp"></span> -->
						<div id="cnamediv" style="color: #FF0000; display: inline">${ErrorMsg.cname}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">公司地址：</td>
					<td width="600" height="40" align="left"><input
						style="text-align: left" type="text" name="address" id="address"
						value="${ param.address }"> <!-- 					<span id="addresssp"></span> -->
						<div id="addressdiv" style="color: #FF0000; display: inline">${ErrorMsg.addr}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">負責人：</td>
					<td width="600" height="40" align="left"><input
						style="text-align: left" type="text" name="principal"
						id="principal" value="${ param.principal }"> <!-- 					<span id="principalsp"></span> -->
						<div id="principaldiv" style="color: #FF0000; display: inline">${ErrorMsg.principal}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">資本額：</td>
					<td width="600" height="40" align="left"><input
						style="text-align: left" type="text" name="capital" id="capital"
						value="${ param.capital }"> <!-- 					<span id="capitalsp"></span> -->
						<div id="capitaldiv" style="color: #FF0000; display: inline">${ErrorMsg.capital}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">設立日期：</td>
					<td width="600" height="40" align="left"><input type="text"
						name="setdate" id="setdate" placeholder="yyyy-mm-dd"
						value="${ param.setdate }"> <!-- 					<span id="setdatesp"></span> -->
						<div id="setdatediv" style="color: #FF0000; display: inline">${ErrorMsg.setdate}</div>
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td height="50" colspan="2" align="center"><input id="b1"
						name="buttons" type="submit" value="送出"></td>
				</tr>
			</tbody>
		</table>
		<div style="color: #FF0000; display: inline">${ErrorMsg.exception}</div>
	</form>

</body>
</html>