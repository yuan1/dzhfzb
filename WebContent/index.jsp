<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>恒丰集团经营指标分析系统</title>
<style type="text/css">
<!--
* {
	overflow: hidden;
	font-size: 9pt;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(resources/images/bg.gif);
	background-repeat: repeat-x;
}

#login {
	cursor: pointer;
}
-->
</style>
</head>
<body>
<form action="Login_submit" method="post">
<table style="width: 100%; height: 100%;" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="561" style="background: url(resources/images/lbg.gif)">
				<table width="940" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="238"
							style="background: url(resources/images/login01.gif)">&nbsp;</td>
					</tr>
					<tr>
						<td height="190">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="208" height="190"
									style="background: url(resources/images/login02.jpg)">&nbsp;</td>
								<td width="518"
									style="background: url(resources/images/login03.jpg)">
								<table width="320" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td></td>
										<td></td>
										<td><font color="red">${info}</font></td>
									</tr>
									<tr>
										<td width="40" height="50"><img
											src="resources/images/user.gif" width="30" height="30" /></td>
										<td width="38" height="50">用户</td>
										<td width="242" height="50"><input type="text"
											name="users.username" id=TxtUserName
											style="width: 164px; height: 32px; line-height: 34px; background: url(resources/images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 9pt; font-family: Verdana, Geneva, sans-serif;" /></td>
									</tr>
									<tr>
										<td height="45"><img src="resources/images/password.gif"
											width="28" height="32" /></td>
										<td height="45">密码</td>
										<td height="50"><input type="password"
											name="users.password" id="TxtPassword"
											style="width: 164px; height: 32px; line-height: 34px; background: url(resources/images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 9pt;" /></td>
									</tr>
									<tr>
									</tr>
									<tr>
										<td height="35">&nbsp;</td>
										<td height="35">&nbsp;</td>
										<td height="45"><input name="submit" type="submit"
											value="."
											style="border: none; width: 95px; height: 34px; background: url('resources/images/login.gif') no-repeat left;" />
										</td>
									</tr>
								</table>
								</td>
								<td width="214"
									style="background: url(resources/images/login04.jpg)">&nbsp;</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td height="133"
							style="background: url(resources/images/login05.jpg)">&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>