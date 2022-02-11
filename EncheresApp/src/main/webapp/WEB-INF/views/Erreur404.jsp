<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
		<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<fmt:setLocale value="fr" />
		<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_erreur" var="r" />
		<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
	</head>
<body>
	<header class="sticky-top divHeader">
			<div class="container">
				<h1>
					<fmt:message key="h1" bundle="${r}"></fmt:message>
				</h1>
			</div>
		</header>
		<div class="container text-center">
			<h4 class="text-center my-5"><fmt:message key="p" bundle="${r}"></fmt:message></h4>
			
			<a class="btn btn-outline-light" href="<%=request.getContextPath()%>/"><fmt:message key="btn" bundle="${r}"></fmt:message></a>
		</div>
</body>
</html>