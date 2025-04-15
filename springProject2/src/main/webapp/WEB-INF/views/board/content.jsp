<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/content.jsp</title>
</head>
<body>
<h1>김민수 바보가?</h1>
<h2>이나아</h2>
<jsp:include page="../inc/top.jsp"></jsp:include>

<h1>board/content.jsp</h1>
<table border="1">
<tr><td>글번호</td><td>${boardDTO.num }</td></tr>
<tr><td>글제목</td><td>${boardDTO.subject }</td></tr>
<tr><td>글쓴이</td><td>${boardDTO.name }</td></tr>
<tr><td>글쓴날짜</td><td>${boardDTO.rdate }</td></tr>
<tr><td>조회수</td><td>${boardDTO.readcount }</td></tr>
<tr><td>글내용</td><td>${boardDTO.content }</td></tr>

<tr>
<td colspan="2">
<!-- 로그인이 되어있고 => 로그인 표시값${sessionScope.id }, 글쓴이 일치 => 글수정, 글삭제 버튼 보이기 -->
<c:if test="${! empty sessionScope.id }">
	<c:if test="${sessionScope.id == boardDTO.name }">
		<input type="button" value="글수정" onclick="location.href='update.bo?num=${boardDTO.num}'">
		<input type="button" value="글삭제" onclick="location.href='delete.bo?num=${boardDTO.num}'">
	</c:if>
</c:if>	
				
<input type="button" value="글목록" onclick="location.href='list'">
</td>
</tr>



</table>



</body>
</html>
