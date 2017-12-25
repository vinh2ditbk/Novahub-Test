<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>

<html>
	<head>
		<title>Book Edit</title>
		<%@ include file="../head_tag.jsp"%>
	</head>
	<body>
		<%@ include file="../header.jsp"%>
		<h2>Edit Of Book</h2>
		<form action="/book/update" method="post">
			<input type="hidden" name="id" value="${book.id}">
			<table class="table table-bordered">
				<tbody>
					<tr><th>Title</th><td><input type="text" name="title" required="required" value="${book.title}"></td></tr>
					<tr><th>Author</th><td><input type="text" name="author" required="required" value="${book.author}"></td></tr>
					<tr><th>Description</th><td><input type="text" name="description" required="required" value="${book.description}"></td></tr>
				</tbody>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</table>
			<div>
				<input type="submit" value="Save" class="btn btn-success" style="display: inline">
				<a href="/book/list" class="btn btn-success" style="float: right">Back</a>
			</div>
		</form>
		<c:if test="${not_updated != null && not_updated}">
			<script>
			   alert("You have not yet change any thing!");
			   window.location = "/book/edit/${book.id}"
			</script>
		</c:if>
	</body>
</html>