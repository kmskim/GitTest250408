<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/info.jsp</title>
</head>
<body>

<h1>member/info.jsp</h1>

아이디 : ${memberDTO.id }<br>
비밀번호 : ${memberDTO.passwd }<br>
이름 : ${memberDTO.name }<br>
<a href="${pageContext.request.contextPath }/member/main">메인으로 이동</a>


</body>
</html>