<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function fn(){
		document.getElementById("img").src="${pageContext.request.contextPath}/ckCode?time="
				+ new Date().getTime();
	}
</script>
</head>
<body>
	<input type="button" value="按钮" onclick="fn()">
	<img id="img" src="${pageContext.request.contextPath }/ckCode">
</body>
</html>