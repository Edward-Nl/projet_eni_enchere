<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<fmt:setLocale value="fr" />
<fmt:setBundle
	basename="fr.eni.encheresApp.content.contenue_connexion" var="r" />
<!--  -->


<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
<meta charset="UTF-8">
</head>
<body>

	<h1><fmt:message key="h1" bundle="${r}"></fmt:message></h1>
	
	<form action="<%=request.getContextPath()%>/ServletConnexion" method="POST">
		<label for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label>
		<input type="text" name="pseudo" placeholder="<fmt:message key="pl.un" bundle="${r}"></fmt:message>" />
		<br>
		<label for="motDePasse"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label>
		<input type="password" name="motDePasse" placeholder="<fmt:message key="pl.deux" bundle="${r}"></fmt:message>" />
		<br>
		<input type="checkbox" name="souvenir" />
		<label for="souvenir"><fmt:message key="check.souvenir" bundle="${r}"></fmt:message></label>
		<a href=""><fmt:message key="a.mdp" bundle="${r}"></fmt:message></a>
		<button type="submit"><fmt:message key="btn.connexion" bundle="${r}"></fmt:message></button>
	</form>
	
	<a href="<%=request.getContextPath()%>/ServletInscription"><button><fmt:message key="btn.inscription" bundle="${r}"></fmt:message></button></a>

</body>
</html>