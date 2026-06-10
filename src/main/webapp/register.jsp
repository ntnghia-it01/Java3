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
	<!-- Code giao diện  -->
	<div class="container">
		<div class="col-6 offset-3">
			<form action="${pageContext.request.contextPath}/register"
				method="POST"
				enctype="multipart/form-data">
				<div class="mb-3">
				  <label class="form-label">Email</label>
				  <input type="text" class="form-control" name="email" value="${bean.email}">
				  <small class="text-danger">${bean.errors.errEmail}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Mật khẩu</label>
				  <input type="password" class="form-control" name="password" value="${bean.password}">
				  <small class="text-danger">${bean.errors.errPassword}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Họ và tên</label>
				  <input type="text" class="form-control" name="name" value="${bean.name}">
				  <small class="text-danger">${bean.errors.errName}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Số điện thoại</label>
				  <input type="text" class="form-control" name="phone" value="${bean.phone}">
				  <small class="text-danger">${bean.errors.errPhone}</small>
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Ảnh đại diện</label>
				  <input name="avatar" accept="image/*" class="form-control" type="file" id="formFile">
				  <small class="text-danger">${errImage}</small>
				</div>
				
				<button type="submit" class="btn btn-primary">Đăng ký</button>
				<small class="text-danger">${errServices}</small>
			</form>
		</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>