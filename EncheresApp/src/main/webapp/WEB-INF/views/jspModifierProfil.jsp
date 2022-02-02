<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!--  TODO: Retirer temporaire -->
<fmt:setLocale value="fr" />
<!--  -->
<fmt:setBundle
	basename="fr.eni.encheresApp.content.contenue_inscription" var="r" />



<title><fmt:message key="titre" bundle="${r}"></fmt:message></title>
</head>
<div class="container">
	
		<h1>
			<fmt:message key="soustitre" bundle="${r}"></fmt:message>
		</h1>
		<form method="post" action="<%=request.getContextPath() %>/ServletModifierProfil" class="my-3">
			<div class="d-flex justify-content-center">
				<div class="col-6">
					<fmt:message key="pattern.pseudo.titre" bundle="${r}" var="patternPseudoTitre" />
					<fmt:message key="pattern.general.titre" bundle="${r}" var="patternGeneralTitre" />
					<label class="my-2 col-4" for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="pseudo" value="${sessionScope.utilisateurCourant.pseudo}" pattern="(?=(^[A-Za-z][a-zA-Z0-9]+[_-]?[0-9a-zA-Z]+$))^.{5,30}$" title="${patternPseudoTitre}" placeholder="Eni_Students" required/><br>
					<label class="my-2 col-4" for="prenom"><fmt:message key="champ.trois" bundle="${r}"></fmt:message></label><input class="col-6" type="text"name="prenom" value="${sessionScope.utilisateurCourant.prenom}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" title="${patternGeneralTitre}" placeholder="Jean" required /> <br>
					<fmt:message key="pattern.telephone" bundle="${r}" var="pattern" />
					<fmt:message key="pattern.telephone.titre" bundle="${r}" var="patternTitre" /> <!-- Regex pour num�ro francais ou anglais -->
					<label class="my-2 col-4" for="telephone"><fmt:message key="champ.cinq" bundle="${r}"></fmt:message></label><input class="col-6" type="tel" name="telephone" value="${sessionScope.utilisateurCourant.telephone}" pattern="${pattern}" title="${patternTitre}" placeholder="+33 565656565/05 65 65 65 65" required/> <br>
					<label class="my-2 col-4" for="codePostal"><fmt:message key="champ.sept" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="codePostal" value="${sessionScope.utilisateurCourant.codePostal}" placeholder="22222" required/> <br>
					<label class="my-2 col-4" for="mdpO"><fmt:message key="champ.onze" bundle="${r}"></fmt:message></label><input class="col-6" type="password" name="mdpO" required/><br>
					<label class="my-2 col-4" for="mdp"><fmt:message key="champ.NvPas" bundle="${r}"></fmt:message></label><input class="col-6" type="password" name="mdp" /><br>
				</div>
				<div class="col-6">
					<label class="my-2 col-4" for="nom"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="nom" value="${sessionScope.utilisateurCourant.nom}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" title="${patternGeneralTitre}" placeholder="Dupond-Dupond" required /><br>
					<label class="my-2 col-4" for="email"><fmt:message key="champ.quatres" bundle="${r}"></fmt:message></label><input class="col-6" type="email" name="email" value="${sessionScope.utilisateurCourant.email}" placeholder="example@campus-eni.fr"  required /> <br>
					<label class="my-2 col-4" for="rue"><fmt:message key="champ.six" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="rue" value="${sessionScope.utilisateurCourant.rue}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" placeholder="Rue de Dinan" required /><br>
					<label class="my-2 col-4" for="ville"><fmt:message key="champ.huit" bundle="${r}"></fmt:message></label><input class="col-6" type="text" name="ville" value="${sessionScope.utilisateurCourant.ville}" pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$" placeholder="Paris" required /><br>
					<div style="height: 40px;"></div>
					<label class="my-2 col-4" for="mdpC"><fmt:message key="champ.dix" bundle="${r}"></fmt:message></label><input class="col-6" type="password" name="mdpC" /> <br>
				</div>
			</div>
			
			<div class="d-flex justify-content-center">
				<fmt:message key="champ.enregistrer" bundle="${r}" var="enregistrer" />
				<input class="btn btn-warning mx-2 my-3 col-3" type="submit" value="${enregistrer}"> 
				<fmt:message key="champ.supprimer" bundle="${r}" var="supprimer" />
				<input class=" btn btn-outline-danger mx-2 my-3 col-3" type="button" value="${supprimer}">
				<a class="btn btn-outline-primary mx-2 col-3 my-3" href="<%=request.getContextPath()%>/ServletAccueil"><fmt:message key="retour" bundle="${r}"></fmt:message></a>
			</div>
		</form>
	
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>