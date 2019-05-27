<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
form {
	margin: 0 auto;
	width: 600px;
}
h1{
	text-align:center;
}
</style>
</head>
<body>
<h1>結果顯示頁面</h1>
<form>
		<table border="1">
			<thead>
				<tr bgcolor="tan">
					<th height="60" colspan="2" align="center">新設公司資料管理</th>
				</tr>
			</thead>
			<tbody>
				<tr bgcolor="tan">
					<td width="120" height="40">統一編號：</td>
					<td width="600" height="40" align="left">${ Midterm2Bean.uno }
					<span id="unosp"></span>
					<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.uno}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">公司名稱：</td>
					<td width="600" height="40" align="left">${ Midterm2Bean.cname }
					<span id="cnamesp"></span>
					<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.cname}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">公司地址：</td>
					<td width="600" height="40" align="left">${ Midterm2Bean.local }
					<span id="addresssp"></span>
					<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.addr}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">負責人：</td>
					<td width="600" height="40" align="left">${ Midterm2Bean.principal }
					<span id="principalsp"></span>
					<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.principal}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">資本額：</td>
					<td width="600" height="40" align="left">${ Midterm2Bean.capital }
					<span id="capitalsp"></span>
					<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.capital}</div>
					</td>
				</tr>
				<tr bgcolor="tan">
					<td width="120" height="40">設立日期：</td>
					<td width="600" height="40" align="left">${ Midterm2Bean.setdate }
					<span id="setdatesp"></span>
					<div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.setdate}</div>
					</td>
				</tr>
				<tr bgcolor='tan'>
					<td height="50" colspan="2" align="center">
						<input type="button" value="回到首頁"onclick="location.href='Form.jsp'">

					</td>
				</tr>
			</tbody>
		</table>
		</form>
</body>
</html>