<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!--  TODO: Retirer temporaire -->
<fmt:setLocale value="fr" />
<fmt:setBundle
	basename="fr.eni.encheresApp.content.contenue_inscription" var="r" />
<!--  -->


<title><fmt:message key="titre" bundle="${r}"></fmt:message></title>
</head>
<body>
	<h1>
		<fmt:message key="soustitre" bundle="${r}"></fmt:message>
	</h1>
	<form action="">
		<label for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label> <input type="text" name="pseudo" />
		<label for="nom"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label> <input type="text" name="nom">
		<br> 
		<label for="prenom"><fmt:message key="champ.trois" bundle="${r}"></fmt:message></label> <input type="text"name="prenom" /> 
		<label for="email"><fmt:message key="champ.quatres" bundle="${r}"></fmt:message></label> <input type="text" name="email"> 
		<br> 
		<label for="telephone"><fmt:message key="champ.cinq" bundle="${r}"></fmt:message></label> <input type="text" name="telephone" /> 
		<label for="rue"><fmt:message key="champ.six" bundle="${r}"></fmt:message></label><input type="text" name="rue"> 
		<br> 
		<label for="codePostale"><fmt:message key="champ.sept" bundle="${r}"></fmt:message></label> <input type="text" name="codePostale" /> 
		<label for="ville"><fmt:message key="champ.huit" bundle="${r}"></fmt:message></label><input type="text" name="ville"> 
		<br> 
		<label for="mdp"><fmt:message key="champ.neuf" bundle="${r}"></fmt:message></label><input type="text" name="mdp" /> 
		<label for="mdpC"><fmt:message key="champ.dix" bundle="${r}"></fmt:message></label><input type="text" name="mdpC"> 
		<br>

	</form>

</body>
</html>