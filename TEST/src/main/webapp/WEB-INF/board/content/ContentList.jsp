<%@page import="com.sun.jdi.request.InvalidRequestStateException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Content List</title>
	<!-- root Route -->
	<c:set var="root" value="${pageContext.request.contextPath}" />
    <!-- Bootstrap core CSS -->
	<link href="${root}/css/bootstrap/bootstrap.min.css" rel="stylesheet">  
    <!-- Custom styles for this template -->
    <link href="${root}/css/board/dashboard.css" rel="stylesheet">
    <!-- Bootstrap js -->
    <script src="${root}/js/bootstrap/bootstrap.bundle.min.js" defer></script>
    <!-- Dashboard js -->
    <script src="${root}/js/board/dashboard.js" defer></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/board/etc/header.jsp"></jsp:include>
	
	<!-- sidebar -->
	<c:import url="/etc/sidebar"></c:import>
	
	<div class="container-fluid">
	  <div class="row">
	
   	  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      
      <h2>Section title</h2>
      <div class="table-responsive">
        <table class="table table-striped table-sm">
       	  <!-- Grid title -->
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Name</th>
              <th>Date</th>
              <th>Hit</th>
            </tr>
          </thead>
		  <!-- Data grid Start -->
          <tbody>
             <c:forEach var="li" items="${list}">
            <tr>
              <td>${li.id}</td>
              <td><a href="${root}/detail?id=${li.id}&q=${param.q}&f=${param.f}">${li.title}</a>
              	<c:if test="${li.cocnt!=0}">
              		<a href="comment?id=${li.id}">(${li.cocnt})</a>
              	</c:if>
              	<c:if test="${li.cocnt==0}">
              	</c:if></td>
              <td>${li.writer_id}</td>
              <td><fmt:formatDate pattern="yyyy/MM/dd/ hh:mm" value="${li.regdate}" /></td>
              <td>${li.hit}</td>
            </tr>
             <c:set var="count" value="${li.count}" ></c:set>
           	 </c:forEach>
          </tbody>
        </table>
      </div>
      글 갯수 :  ${count }
      <div class="col-12">
      	<div class="row">
      		<div class="col-4">
      			<!-- 변수선언 -->
      			<c:set var="page" value="${empty param.p?1:param.p}"></c:set>
				<c:set var="startNum" value="${p-(p-1)%5}"></c:set>
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10),'.')}"></c:set>
						 
				<!-- 현재 페이지 -->
					<div>
						<span>${p} </span> / ${lastNum} pages
					</div>
			</div>
      		<div class="col-5">
      			<!-- 페이징처리 시작 -->
				<section aria-label="Page navigation example">
	  				<ul class="pagination">
					<!-- 이전 페이지 -->
					<li class="page-item">
						<c:if test="${startNum > 1 }">
							<a class="page-link" href="?p=${startNum-1}&q=${sv.q}&f=${sv.f}}">Prev</a>
						</c:if>
						<c:if test="${startNum <= 1 }">
							<a class="page-link" href="#" onclick="alert('첫 페이지입니다.');">Prev</a>
						</c:if>
					</li>
				
					<!-- 숫자 페이지 -->
					<c:forEach var="i" begin="0" end="4">
					<li class="page-item">
						<c:if test="${param.p==(startNum+i)}">
							<c:set var="style" value="font-weight:bold; color:red;" />
						</c:if>
						<c:if test="${param.p!=(startNum+i)}">
							<c:set var="style" value="" />
						</c:if>
						<c:if test="${(startNum+i) <=lastNum }">
							<a style="${style}" class="page-link" href="?p=${startNum+i}&q=${sv.q}&f=${sv.f}">${startNum+i}</a>
						</c:if>
					</li>
					</c:forEach>
					<!-- 다음 페이지 -->
					
					 <li class="page-item">
					<c:if test="${startNum+5 <= lastNum }">
						<a class="page-link" href="?p=${startNum+5}&q=${sv.q}&f=${sv.f}">Next</a>
					</c:if>
					<c:if test="${startNum+5 >lastNum }">
						<a class="page-link" href="#" onclick="alert('마지막 페이지입니다.');">Next</a>
					</c:if>
					</li>
		 			</ul>
				</section>
				<!--  검색 -->
			
				<form method="get">
				<div>
					<select name="f">
						<option ${(param.f=="title")?"selected":""} value="title">제목</option>
						<option ${(param.f=="writeid")?"selected":""} value="writer_id">글쓴이</option>
					</select> <input type="text" name="q" /> <span><input type="submit" value="검색"></span>
				</div>
				</form>
      		</div>
      		<div class="col-2"></div>
      		<div class="col-1">
      		<c:if test="${userRank == 'G'}"></c:if>
      		<c:if test="${userRank != 'G'}">
      		<!-- 글쓰기 버튼 -->
				<button class="btn btn-primary" type="button" onclick="location.href='regedit'">Write</button>
      		</c:if>
      		</div>
      	</div>
      </div>
    </main>
  </div> 
</div>
</body>
</html>