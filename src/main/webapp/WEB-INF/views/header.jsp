<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>

<html>
	<body>

		<div class="panel panel-default" id="header-content">
			<p id="header-content-first">NOVAHUB - TEST</p>
			<div id="header-content-second">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<a href="javascript:formSubmit()" class="btn btn-success">Logout</a>
				</c:if>
			</div>
		</div>
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
	</body>
</html>