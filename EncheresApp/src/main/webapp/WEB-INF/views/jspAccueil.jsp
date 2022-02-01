<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_accueil" var="r" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<meta charset="UTF-8">
<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between">
			<h1><fmt:message key="titre" bundle="${r}"></fmt:message></h1>
			<div>
				<a href=""><fmt:message key="aIns" bundle="${r}"></fmt:message></a>
				<a href=""><fmt:message key="aConx" bundle="${r}"></fmt:message></a>
			</div>
		</header>
		<main>
			<h2><fmt:message key="sousTitre" bundle="${r}"></fmt:message></h2>
			
			<form action="" method="" >
				<label for="filtre"><fmt:message key="lbFil" bundle="${r}"></fmt:message></label><br>
				<input type="text"> <br>
				<label for="catg"><fmt:message key="lbCat" bundle="${r}"></fmt:message></label>
			</form>
		</main>
	
	</div>



</body>
</html>