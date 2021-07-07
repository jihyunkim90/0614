<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-6"></div>
			<div class="col-6">
	<h3>게시판 이름 수정</h3>
				<form action="${root}/BoardNameUpdate" method="post">
					<table border="1">
						<tr>
							<th>게시판이름</th>
							<td><input type="text" name="boardname" value="${list.boardname}"></td>
						</tr>
					</table>
					<input type="hidden" name="boardid" value="${list.boardid}" />
					<input class="btn btn-primary" type="submit" value="수정">
									
				</form>
			</div>

		</div>
	</div>



</body>
</html>