<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>

<html>
	<head>
		<title>Book Create</title>
		<%@ include file="../head_tag.jsp"%>
	</head>
	<body>
		<%@ include file="../header.jsp"%>
		<h2>Create new Book</h2>
		<form action="/book/save" method="post">
			<table class="table table-bordered">
				<tbody>
					<tr><th>Title</th><td><input type="text" name="title" required="required"></td></tr>
					<tr><th>Author</th><td><input type="text" name="author" required="required"></td></tr>
					<tr><th>Description</th><td><input type="text" name="description" required="required"></td></tr>
				</tbody>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</table>
			<div>
				<input type="submit" value="Add Book" class="btn btn-success" style="display: inline">
				<a href="/book/list" class="btn btn-success" style="float: right">Back</a>
			</div>
		</form>
	</body>
</html>