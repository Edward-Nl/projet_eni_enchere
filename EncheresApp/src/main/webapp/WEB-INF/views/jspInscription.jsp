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
<!--  -->
<fmt:setBundle
	basename="fr.eni.encheresApp.content.contenue_inscription" var="r" />



<title><fmt:message key="titre" bundle="${r}"></fmt:message></title>

</head>
<body>
	<h1>
		<fmt:message key="soustitre" bundle="${r}"></fmt:message>
	</h1>
	<form method="post" action="">
		<label for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label> <input type="text" name="pseudo" value="${pseudo}" required/>
		<label for="nom"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label> <input type="text" name="nom" value="${nom}" required />
		<br> 
		<label for="prenom"><fmt:message key="champ.trois" bundle="${r}"></fmt:message></label> <input type="text"name="prenom" value="${prenom}" required /> 
		<label for="email"><fmt:message key="champ.quatres" bundle="${r}"></fmt:message></label> <input type="email" name="email" value="${email}" placeholder="example@campus-eni.fr" required /> 
		<br> 
		
		<fmt:message key="pattern.telephone" bundle="${r}" var="pattern" />
		<fmt:message key="pattern.telephone.title" bundle="${r}" var="patternTitre" />
		<label for="telephone"><fmt:message key="champ.cinq" bundle="${r}"></fmt:message></label> <input type="tel" name="telephone" value="${telephone}" pattern="${pattern}" title="${patternTitre}" required/> 
		<label for="rue"><fmt:message key="champ.six" bundle="${r}"></fmt:message></label><input type="text" name="rue" value="${rue}" required /> 
		<br> 
		<label for="codePostale"><fmt:message key="champ.sept" bundle="${r}"></fmt:message></label> <input type="text" name="codePostale" value="${codePostale}" required/> 
		<label for="ville"><fmt:message key="champ.huit" bundle="${r}"></fmt:message></label><input type="text" name="ville" value="${ville}" required /> 
		<br> 
		<label for="mdp"><fmt:message key="champ.neuf" bundle="${r}"></fmt:message></label><input type="password" name="mdp" /> 
		<label for="mdpC"><fmt:message key="champ.dix" bundle="${r}"></fmt:message></label><input type="password" name="mdpC" required/> 
		<br>
		<fmt:message key="champ.creer" bundle="${r}" var="creer" />
		<fmt:message key="champ.annuler" bundle="${r}" var="annuler" />
		<input type="submit" value="${creer}"> 
		<input type="button" value="${annuler}">
		
	</form>
</body>
</html>