<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/delete.jsp</title>
</head>
<body>

<jsp:include page="../inc/top.jsp"></jsp:include>

<!-- http://localhost:8080/MVCProject/insert.me -->
<!-- http://localhost:8080/MVCProject/member/delete.jsp -->
<h1>가상주소 delete.me => 실제페이지 member/delete.jsp</h1>
<h1>회원정보삭제</h1>
<form action="${pageContext.request.contextPath}/member/deletePro" method="post">
	아이디 : <input type="text" name="id" value="${sessionScope.id }" readonly="readonly"><br>
	비밀번호 : <input type="password" name="passwd"><br> 
	<input type="submit" value="삭제">
</form>


</body>
</html>