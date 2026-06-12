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
			<!-- Click submit thì action sẽ mở url  -->
			<!-- Lấy <domain hiện tại> + <action> -->
			<!-- localhost:8080/Java3/admin/posts -->
			<!-- pageContext.request.contextPath = <Tên project>  -->
			<%-- <a href="${pageContext.request.contextPath}/register">Đăng ký</a> --%>
			<form action="${pageContext.request.contextPath}/admin/posts"
				method="POST"
				enctype="multipart/form-data">
				<div class="mb-3">
				  <label class="form-label">Tiêu đề</label>
				  <!-- bean.title => bean.getTitle() -->
				  <input type="text" class="form-control" name="title" value="${bean.title}">
				  <small class="text-danger">${bean.errors.errTitle}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Nội dung</label>
				  <input type="text" class="form-control" name="content" value="${bean.content}">
				  <small class="text-danger">${bean.errors.errContent}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Danh mục</label>
				  <select class="form-select" name="category">
					  <option ${bean == null ? 'selected' : ''}>---Chọn danh mục----</option>
					  
					  <c:forEach items="${categories}" var="cat">
					  	<option ${bean.category == cat.id ? 'selected' : ''} 
					  		value="${cat.id}">
					  		${cat.name}
					  	</option>
					  </c:forEach>
					  
					</select>
					<small class="text-danger">${bean.errors.errCategory}</small>
				</div>
				<div class="mb-3">
				  <label class="form-label">Trạng thái</label>
				  <div class="form-check">
					  <input ${bean.status == 1 ? 'checked' : ''} name="status" value="1" class="form-check-input" type="radio" id="radioDefault1">
					  <label class="form-check-label" for="radioDefault1">
					   	Nháp
					  </label>
					</div>
					<div class="form-check">
					  <input ${bean.status == 2 ? 'checked' : ''} name="status" value="2" class="form-check-input" type="radio" id="radioDefault2">
					  <label class="form-check-label" for="radioDefault2">
					    Xuất bản
					  </label>
					</div>
					<small class="text-danger">${bean.errors.errStatus}</small>
				</div>
				<div class="mb-3">
				  <label for="formFile" class="form-label">Ảnh bài viết</label>
				  <input name="image" accept="image/*" class="form-control" type="file" id="formFile">
				</div>
				
				<button type="submit" class="btn btn-primary">Thêm</button>
				<small class="text-danger">${error}</small>
			</form>
		</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>