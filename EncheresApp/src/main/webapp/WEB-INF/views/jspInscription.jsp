<%@page import="fr.eni.encheresApp.exceptions.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Style CSS -->
		<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
		<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_inscription" var="r" />
		<title><fmt:message key="titre" bundle="${r}"></fmt:message></title>
	</head>
	<body>
	
		<header class="sticky-top divHeader">
			<div class="container">
				<h1>
					<fmt:message key="soustitre" bundle="${r}"></fmt:message>
				</h1>
			</div>
		</header>
		<div class="container">
			<c:if test="${listeCodesErreur != null}">
				<p class="text-danger"><fmt:message key="erreur" bundle="${r}"></fmt:message></p>
				<c:forEach var="erreur" items="${listeCodesErreur}">
					<p class="text-danger">${LecteurMessage.getMessageErreur(erreur)}</p>
				</c:forEach>
			</c:if>
			
			<h2 class="mt-4 mb-5 text-center"><fmt:message key="h2" bundle="${r}"></fmt:message></h2>
			<form method="post" action="" class="my-3">
				<div class="d-flex flex-column flex-md-row justify-content-center">
					<div class="mx-md-3 col-12 col-md-4">
						<fmt:message key="pattern.pseudo.titre" bundle="${r}" var="patternPseudoTitre" />
						<fmt:message key="pattern.general.titre" bundle="${r}" var="patternGeneralTitre" />
						
						<label class="my-2 col-4" for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="text" name="pseudo" value="${pseudo}" pattern="(?=(^[A-Za-z][a-zA-Z0-9]+[_-]?[0-9a-zA-Z]+$))^.{5,30}$" title="${patternPseudoTitre}" placeholder="Eni_Students" required /><br>
						
						<label class="my-2 col-4" for="prenom"><fmt:message key="champ.trois" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="text" name="prenom" value="${prenom}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" title="${patternGeneralTitre}" placeholder="Jean" required /> <br>
						
						<fmt:message key="pattern.telephone" bundle="${r}" var="pattern" />
						<fmt:message key="pattern.telephone.titre" bundle="${r}" var="patternTitre" />
						
						<label class="my-2 col-4" for="telephone"><fmt:message key="champ.cinq" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="tel" name="telephone" value="${telephone}"pattern="${pattern}" title="${patternTitre}"placeholder="+33 565656565/05 65 65 65 65" required /> <br>
						
						<label class="my-2 col-4" for="codePostale"><fmt:message key="champ.sept" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="text" name="codePostale" value="${codePostale}" placeholder="22222" pattern="^(([0-9]{2}|2A|2B)[0-9]{3})$|^[971-974]$" required /> <br>
						
						<label class="my-2 col-4" for="mdp"><fmt:message key="champ.neuf" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="password" name="mdp" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" /><br>
					</div>
					
					<div class="mx-md-3 col-12 col-md-4">
						<label class="my-2 col-4" for="nom"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="text" name="nom" value="${nom}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" title="${patternGeneralTitre}" placeholder="Dupond-Dupond" required /><br> 
						
						<label class="my-2 col-4" for="email"><fmt:message key="champ.quatres" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="email" name="email" value="${email}" placeholder="example@campus-eni.fr" required /> <br> 
						
						<label class="my-2 col-4" for="rue"><fmt:message key="champ.six" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="text" name="rue" value="${rue}" pattern="(?=(^[A-Za-z0-9]*[\s]?[a-zA-Z]+([-\s]?[0-9a-zA-Z]*)+$))^.{5,30}$" placeholder="Rue de Dinan" required /><br> 
						
						<label class="my-2 col-4" for="ville"><fmt:message key="champ.huit" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="text" name="ville" value="${ville}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" placeholder="Paris" required /><br> 
							
						<label class="my-2 col-4" for="mdpC"><fmt:message key="champ.dix" bundle="${r}"></fmt:message></label>
						<input class="col-12 col-md-6" type="password" name="mdpC" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" required /> <br>
					</div>
				</div>
	
				<div class="d-flex flex-column flex-md-row justify-content-center">
					<button class="btn btn-outline-primary mx-md-5 my-3 col-12 col-md-3" type="submit">
						<i class="fas fa-plus"></i>  <fmt:message key="champ.creer" bundle="${r}"></fmt:message>
					</button> 
					<a href="<%=request.getContextPath()%>/" class="btn btn-outline-danger mx-md-5 my-3 col-12 col-md-3">
						<i class="fas fa-angle-double-left"></i>  <fmt:message key="champ.annuler" bundle="${r}"></fmt:message>
					</a>
				</div>
	
	
			</form>
	
		</div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	</body>
</html>