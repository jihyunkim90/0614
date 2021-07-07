<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  검색 -->
	<div>
		<form method="post">
			<div>
				<select name="f">
					<option ${(param.f=="title")?"selected":""} value="title">제목</option>
					<option ${(param.f=="writeid")?"selected":""} value="writer_id">글쓴이</option>
				</select> <input type="text" name="q" /> <span><input type="submit"
					value="검색"></span>
			</div>
		</form>
		<br>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Content</th>
				<th>DATE</th>

				<th>HIT</th>
			</tr>
		</thead>
		<!--  data grid start -->
		<tbody>
			<c:forEach var="li" items="${list}">
				<tr>
					<td>${li.id}</td>
					<td>${li.title}</td>
					<td>${li.writer_id}</td>
					<td>${li.content }</td>
					<td><fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분"
							value="${li.regdate}" /></td>
					<td>${li.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>