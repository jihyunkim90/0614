<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/dashboard/">
<!-- Bootstrap core CSS -->
<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${root}/css/board/dashboard.css" rel="stylesheet">
<!-- ajax 페이지 호출 전 jQuery 추가 -->
<script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>게시판 관리</h1>
			</div>
			<div class="col-6"></div>
			<br> <br> <br> <br> <br>
			<div class="container">
				<div class="row">
					<div class="col-6">

						게시판
						<c:forEach var="li" items="${list}">

							<table class="table table-bordered">
								<tr>
									<td><a href="${root}/createBoardDetail?id=${li.boardid}">${li.boardname}</a></td>
								</tr>
							</table>
						</c:forEach>

						<div>
						<button class="btn btn-primary" type="button"
							onclick="location.href='${root}/addBoard'">게시판 추가</button>
					</div>

					</div>
					<br> <br>
					<div class="col-6"></div>

				</div>
			</div>


		</div>
	</div>

</body>
</html>