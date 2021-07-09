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
			
				<h3>새 게시판 생성</h3>
			
			<div class="col-6">
				<form action="${root}/addBoard" method="post">
					<table class="table table-bordered">
						<tr>
							<th>게시판 제목</th>
							<td><input type="text" name="boardname"></td>
						</tr>
						<tr>
							<th>권한</th>
							<td><input type="checkbox" id="A">A
								<input type="checkbox" id="U">U
								<input type="checkbox" id="G">G
							</td>
						</tr>


					</table>

					<div>
						<input class="btn btn-primary" type="submit" value="게시판 생성">
					</div>
							
					
					<div class="col-6">
					</div>
			</div>


			</form>
</body>
</html>