<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>

<html>
	<head>
		<title>Book List</title>
		<%@ include file="../head_tag.jsp"%>
	</head>
	<body>
		<%@ include file="../header.jsp"%>
		<table class="table table-bordered">
			<tr>
				<th>S.No</th>
				<th>Title</th>
				<th>Author</th>
				<th>Detail</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<tbody>
				<c:forEach items="${books}" var="book" varStatus="itr">
					<tr>
						<td>${itr.index+1}</td>
						<td>${book.title}</td>
						<td>${book.author}</td>
						<td><a href="/book/detail/${book.id}" class="btn btn-warning">Detail</a> </td>
						<td><a href="/book/edit/${book.id}" class="btn btn-warning">Edit</a> </td>
						<td><a href="/book/delete/${book.id}" class="btn btn-warning">Delete</a> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/book/create" class="btn btn-success">Add Book</a>
		<c:if test="${invalid_by_edit != null && invalid_by_edit}">
			<script>
			   alert("You have not permit to edit this book!");
			   window.location = "/book/list"
			</script>
		</c:if>
		<c:if test="${invalid_by_delete != null && invalid_by_delete}">
			<script>
			   alert("You have not permit to delete this book!");
			   window.location = "/book/list"
			</script>
		</c:if>
		<c:if test="${id_invalid != null && id_invalid}">
			<script>
			   alert("The book that you choose is invalid!");
			   window.location = "/book/list"
			</script>
		</c:if>
	</body>
</html>