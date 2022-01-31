<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<!--  TODO: Retirer temporaire -->
<fmt:setLocale value="fr" />
<!--  -->
<fmt:setBundle
	basename="fr.eni.encheresApp.content.contenue_inscription" var="r" />



<title><fmt:message key="titre" bundle="${r}"></fmt:message></title>

</head>
<body>
	<div class="container">
	
		<h1>
			<fmt:message key="soustitre" bundle="${r}"></fmt:message>
		</h1>
		<form method="post" action="" class="my-3">
			<div class="d-flex justify-content-center">
				<div class="mx-3 col-4">
					<label class="my-2 col-4" for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label> <input class="col-6" type="text" name="pseudo" value="${pseudo}" required/><br>
					<label class="my-2 col-4" for="prenom"><fmt:message key="champ.trois" bundle="${r}"></fmt:message></label> <input class="col-6" type="text"name="prenom" value="${prenom}" required /> <br>
					<fmt:message key="pattern.telephone" bundle="${r}" var="pattern" />
					<fmt:message key="pattern.telephone.title" bundle="${r}" var="patternTitre" />
					<label class="my-2 col-4" for="telephone"><fmt:message key="champ.cinq" bundle="${r}"></fmt:message></label> <input class="col-6" type="tel" name="telephone" value="${telephone}" pattern="${pattern}" title="${patternTitre}" required/> <br>
					<label class="my-2 col-4" for="codePostale"><fmt:message key="champ.sept" bundle="${r}"></fmt:message></label> <input class="col-6" type="text" name="codePostale" value="${codePostale}" required/> <br>
					<label class="my-2 col-4" for="mdp"><fmt:message key="champ.neuf" bundle="${r}"></fmt:message></label><input class="col-6" type="password" name="mdp" /><br>
				</div>
				<div class="mx-3 col-4">
					<label class="my-2 col-4" for="nom"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label> <input class="col-6" type="text" name="nom" value="${nom}" required /><br>
					<label class="my-2 col-4" for="email"><fmt:message key="champ.quatres" bundle="${r}"></fmt:message></label> <input class="col-6" type="email" name="email" value="${email}" placeholder="example@campus-eni.fr" required /> <br>
					<label class="my-2 col-4" for="rue"><fmt:message key="champ.six" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="rue" value="${rue}" required /><br>
					<label class="my-2 col-4" for="ville"><fmt:message key="champ.huit" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="ville" value="${ville}" required /><br>
					<label class="my-2 col-4" for="mdpC"><fmt:message key="champ.dix" bundle="${r}"></fmt:message></label><input class="col-6" type="password" name="mdpC" required/> <br>
				</div>
			</div>
			
			<div class="d-flex justify-content-center">
				<fmt:message key="champ.creer" bundle="${r}" var="creer" />
				<fmt:message key="champ.annuler" bundle="${r}" var="annuler" />
				<input class="mx-5 my-3 col-3" type="submit" value="${creer}"> 
				<input class="mx-5 my-3 col-3" type="button" value="${annuler}">
			</div>
			
			
		</form>
	
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>