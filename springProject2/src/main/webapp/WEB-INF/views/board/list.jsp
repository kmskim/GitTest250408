<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/list.jsp</title>
</head>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>

<!-- 로그인한 회원만 글쓰기 버튼이 보이게 설정 -->
<c:if test="${! empty sessionScope.id }">
	<a href="${pageContext.request.contextPath }/board/write">게시판 글쓰기</a><br>
</c:if>

<h1>board/list.jsp</h1>
<h1>board/list.jsp[전체 글개수 : ${pageDTO.count}]</h1>
<table border="1">
<tr><td>글번호</td> <td>글제목</td> <td>글쓴이</td> <td>글쓴날짜</td> <td>조회수</td></tr>
<!-- <tr> -->
<!-- 	<td>1</td> <td><a href="content.bo">글제목1</a></td> <td>글쓴이1</td> <td>글쓴날짜1</td> <td>1</td> -->
<!-- </tr> -->
 <c:forEach var="boardDTO" items="${boardList}">
        <tr>
            <td>${boardDTO.num}</td>
            <td><a href="${pageContext.request.contextPath}/board/content?num=${boardDTO.num }">${boardDTO.subject }</a></td>
            <td>${boardDTO.name}</td>
            <td>${boardDTO.rdate}</td>
            <td>${boardDTO.readcount}</td>
        </tr>
    </c:forEach>
    
</table>

<!-- 10만큼 이전 (1 안보이고 / 11,21,31 보이고) -->
<c:if test="${pageDTO.startPage > pageDTO.pageBlock}">
	<a href="${pageContext.request.contextPath}/board/list?pageNum=${pageDTO.startPage - pageDTO.pageBlock}">[이전]</a>
</c:if>

<c:forEach var="i" begin="${pageDTO.startPage }" end="${pageDTO.endPage }" step="1">
	<a href="${pageContext.request.contextPath}/board/list?pageNum=${i}"> ${i}</a>
</c:forEach>

<!-- 10만큼 다음 -->
<c:if test="${pageDTO.endPage < pageDTO.pageCount}">
	<a href="${pageContext.request.contextPath}/board/list?pageNum=${pageDTO.startPage + pageDTO.pageBlock}">[다음]</a>
</c:if>

</body>
</html>