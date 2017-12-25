<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html >

<html>
	<head>
		<title>Login</title>
		<%@ include file="head_tag.jsp"%>
	</head>
	<body>
		<%@ include file="header.jsp"%>
		<div class="wrapper">
			<form class="form-signin" name='loginForm' action="<c:url value='/login' />" method='POST'>
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Login fail, please try again!</p>
					</div>
				</c:if>
				<h2 class="form-signin-heading">Login</h2>
				<input type="text" class="form-control" name="email" placeholder="email"/>
				<input type="password" class="form-control" name="password" placeholder="password" style="margin-top: 10px;"/>
				<label class="checkbox">
				</label>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</body>
</html>