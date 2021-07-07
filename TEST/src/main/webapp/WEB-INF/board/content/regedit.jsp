<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
<!-- root Route -->
<c:set var="root" value="${pageContext.request.contextPath}" />
<!-- Ck Editor Route -->
<script type="text/javascript" src="${root}/js/ckeditor/ckeditor.js"></script>

</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
	<!-- side bar -->
	<jsp:include page="/WEB-INF/board/etc/sidebar.jsp"></jsp:include>
	<br>
	<form action="regeditSave" method="post">
		<div class="container">
			<div class="row">

				<div class=col-2></div>
				<div class="col-9">
					<div class="col-4">
						<label for="Board" class="form-label">Board Select</label> <select
							name="boardid">
							<c:forEach var="bl" items="${boardlist}">
								<c:if test="${userRank != A}">
									<c:if test="${bl.boardid == 1}">
									</c:if>
									<c:if test="${bl.boardid != 1}">
										<option value="${bl.boardid}">${bl.boardname}</option>
									</c:if>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label for="Title" class="form-label">Title</label> <input
							type="text" class="form-control" name="title" id="title"
							placeholder="Title" required>
					</div>
					<div class="col-8">
						<label for="content" class="form-label">Content</label>
						<textarea class="form-control" name="content"></textarea>
					</div>
					<script type="text/javascript" defer>
						CKEDITOR.replace('content', {
							height : 300
						});
					</script>
				</div>
				<div class=col-1></div>
			</div>
			<br>
			<div class="row">
				<div class="col-7"></div>
				<input type="hidden" name="writer_id" value="${userID}">
				<div class="col-5">
					<button class="btn btn-primary" type="submit">Save</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>