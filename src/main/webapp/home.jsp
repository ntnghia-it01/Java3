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
<style>
	.text-red{
		color: red
	}
</style>
</head>
<body>
	<%@include file="components/header.jsp" %>
	
	<h1 class="text-red">Lập trình java 3</h1>
	<h1 class="text-red">${name}</h1>
	<h1 class="text-red">Tuổi: ${age}</h1>
	<h1 class="text-red">Điểm: ${points[0]}</h1>
	
	<h1 class="text-red">MSSV: ${student.code}</h1>
	
	<h1>Địa chỉ: ${user.address}</h1>
	
	<c:forEach items="${points}" var="point">
		<h1>Điểm: ${point}</h1>
	</c:forEach>
	
	<c:forEach items="${points}" var="point">
	</c:forEach>
	
	<c:if test="${points[0] > 8}">
		<h1>Giỏi</h1>
	</c:if>
	
	<%-- <h1>${points[0] > 8 ? 'Giỏi' : ''}</h1> --%>
	
	<!--  SEO  -->
	
	<!-- !empty user <=> user != null  -->
	<!-- empty user <=> user == null  -->
	
	<p>Footer</p>
</body>
</html>

