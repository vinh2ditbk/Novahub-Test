<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>

<html>
	<head>
		<title>Book Detail</title>
		<%@ include file="../head_tag.jsp"%>
	</head>
	<body>
		<%@ include file="../header.jsp"%>
		<h2>View Detail Book</h2>
		<table class="table table-bordered">
			<tbody>
				<tr><th>Title</th><td><p><c:out value="${book.title}"/></td></tr>
				<tr><th>Author</th><td><p><c:out value="${book.author}"/></td></tr>
				<tr><th>Description</th><td><p><c:out value="${book.description}"/></td></tr>
				<tr><th>Created At</th><td><p><c:out value="${book.createdAt}"/></td></tr>
				<tr><th>Updated at</th><td><p><c:out value="${book.updatedAt}"/></td></tr>
			</tbody>
		</table>
		<a href="/book/list" class="btn btn-success">Back</a>
		<script type="text/javascript">
		</script>
	</body>
</html>