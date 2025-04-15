<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/main.jsp</title>
</head>
<body>

<jsp:include page="../inc/top.jsp"></jsp:include>

<h1>member/main.jsp</h1>

<a href="${pageContext.request.contextPath }/board/list">게시판 글목록</a><br>

<!-- 로그인 한 회원만 글쓰기 버튼이 보이게 설정 -->
<c:if test="${! empty sessionScope.id }">

<a href="${pageContext.request.contextPath }/board/write">게시판 글쓰기</a><br>

</c:if>



</body>
</html>