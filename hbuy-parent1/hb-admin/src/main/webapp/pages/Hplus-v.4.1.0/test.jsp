<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>测试fastDFS图片上传</title>
</head>
<body>
	<center>
		<form action="<%=basePath %>/upload/uploadFile" method="post" enctype="multipart/form-data">
			<input type="file" multiple name="wenJian">
			<input type="submit" value="上传">
		</form>
	</center>
</body>
</html>
