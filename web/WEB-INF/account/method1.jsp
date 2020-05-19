<?xml version="1.0" encoding="utf-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=utf-8" 
		pageEncoding="utf-8" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>
<body>
	<form action = "DoLogin" method = "post">
	用户名：<input type="text" name = "uname" value="" /> <br />
	密码:<input type = "text" name = "pwd" value="" /><br />
	<input type = "submit" value = "登陆" />
	</form>
</body>
</html>
</jsp:root>