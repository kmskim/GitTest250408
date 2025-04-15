<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/update.jsp</title>
</head>
<body>
<h1>board/update.jsp</h1>

<jsp:include page="../inc/top.jsp"></jsp:include>

<form action="${pageContext.request.contextPath}/board/updatePro" method="post">
<table border="1">
<tr><td>글번호</td><td><input type="text" name="num" value="${boardDTO.num }" readonly="readonly"></td></tr>
<tr><td>글쓴이</td><td><input type="text" name="name" value="${boardDTO.name }" readonly="readonly"></td></tr>
<tr><td>글제목</td><td><input type="text" name="subject" value="${boardDTO.subject }"></td></tr>
<tr><td>글내용</td><td><textarea name="content" rows="10" cols="20">${boardDTO.content }</textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정"></td></tr>
</table>
</form>


</body>
</html>