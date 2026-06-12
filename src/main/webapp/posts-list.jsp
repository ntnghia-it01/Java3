<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/admin/posts" class="btn btn-primary">
			Thêm bài viết
		</a>
	
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">ID</th>
		      <th scope="col">Tiêu đề</th>
		      <th scope="col">Danh mục</th>
		      <th scope="col">Người đăng</th>
		      <th scope="col">Hình ảnh</th>
		      <th scope="col">Hành động</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach items="${posts}" var="item">
			    <tr>
			      <th scope="row">${item.id}</th>
			      <td>${item.title}</td>
			      <td>${item.category.name}</td>
			      <td>${item.user.fullName}</td>
			      <td>
			      <!-- ${pageContext.request.contextPath} == tên project  -->
			      	<img src="${pageContext.request.contextPath}${item.thumbnail}" width="100" height="100"/>
			      </td>
			      <td>
			      	<a class="btn btn-warning">Sửa</a>
			      	<br/>
			      	<form action="${pageContext.request.contextPath}/admin/post-delete"
			      		method="POST">
			      		<input type="hidden" name="id" value="${item.id}"/>
			      		<input type="submit" class="btn btn-danger" value="Xoá"/>
			      	</form>
			      	
			      </td>
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>