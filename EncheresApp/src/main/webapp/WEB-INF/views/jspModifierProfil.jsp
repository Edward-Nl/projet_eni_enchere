<%@page import="fr.eni.encheresApp.exceptions.LecteurMessage"%>
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
	<c:if test="${listeCodesErreur != null}">
		<p style="color: red;">Erreur lors du login du compte</p>
		<c:forEach var="erreur" items="${listeCodesErreur}">
			<p>${LecteurMessage.getMessageErreur(erreur)}</p>
		</c:forEach>
	</c:if>
	<form method="post"
		action="<%=request.getContextPath()%>/Profil/Modifier" class="my-3">
		<div class="d-flex justify-content-center">
			<div class="col-6">
				<fmt:message key="pattern.pseudo.titre" bundle="${r}"
					var="patternPseudoTitre" />
				<fmt:message key="pattern.general.titre" bundle="${r}"
					var="patternGeneralTitre" />
				<label class="my-2 col-4" for="pseudo"><fmt:message
						key="champ.un" bundle="${r}"></fmt:message></label><input class="col-6"
					type="text" name="pseudo"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.pseudo:sessionScope.utilisateurCourantComplet.pseudo}"
					pattern="(?=(^[A-Za-z][a-zA-Z0-9]+[_-]?[0-9a-zA-Z]+$))^.{5,30}$"
					title="${patternPseudoTitre}" placeholder="Eni_Students" required /><br>
				<label class="my-2 col-4" for="prenom"><fmt:message
						key="champ.trois" bundle="${r}"></fmt:message></label><input class="col-6"
					type="text" name="prenom"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.prenom:sessionScope.utilisateurCourantComplet.prenom}"
					pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$"
					title="${patternGeneralTitre}" placeholder="Jean" required /> <br>
				<fmt:message key="pattern.telephone" bundle="${r}" var="pattern" />
				<fmt:message key="pattern.telephone.titre" bundle="${r}"
					var="patternTitre" />
				<!-- Regex pour numéro francais ou anglais -->
				<label class="my-2 col-4" for="telephone"><fmt:message
						key="champ.cinq" bundle="${r}"></fmt:message></label><input class="col-6"
					type="tel" name="telephone"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.telephone:sessionScope.utilisateurCourantComplet.telephone}"
					pattern="${pattern}" title="${patternTitre}"
					placeholder="+33 565656565/05 65 65 65 65" required /> <br> <label
					class="my-2 col-4" for="codePostal"><fmt:message
						key="champ.sept" bundle="${r}"></fmt:message></label><input class="col-6"
					type="text" name="codePostal"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.codePostal:sessionScope.utilisateurCourantComplet.codePostal}"
					placeholder="22222"
					pattern="^(([0-9]{2}|2A|2B)[0-9]{3})$|^[971-974]$" required /> <br>
				<label class="my-2 col-4" for="mdpO"><fmt:message
						key="champ.onze" bundle="${r}"></fmt:message></label><input class="col-6"
					type="password" name="mdpO" required /><br> <label
					class="my-2 col-4" for="mdp"><fmt:message key="champ.NvPas"
						bundle="${r}"></fmt:message></label><input class="col-6" type="password"
					name="mdp"
					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" /><br>
			</div>
			<div class="col-6">
				<label class="my-2 col-4" for="nom"><fmt:message
						key="champ.deux" bundle="${r}"></fmt:message></label><input class="col-6"
					type="text" name="nom"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.nom:sessionScope.utilisateurCourantComplet.nom}"
					pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$"
					title="${patternGeneralTitre}" placeholder="Dupond-Dupond" required /><br>
				<label class="my-2 col-4" for="email"><fmt:message
						key="champ.quatres" bundle="${r}"></fmt:message></label><input
					class="col-6" type="email" name="email"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.email:sessionScope.utilisateurCourantComplet.email}"
					placeholder="example@campus-eni.fr" required /> <br> <label
					class="my-2 col-4" for="rue"><fmt:message key="champ.six"
						bundle="${r}"></fmt:message></label><input class="col-6" type="text"
					name="rue"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.rue:sessionScope.utilisateurCourantComplet.rue}"
					pattern="(?=(^[A-Za-z0-9]*[\s]?[a-zA-Z]+([-\s]?[0-9a-zA-Z]*)+$))^.{5,30}$"
					placeholder="Rue de Dinan" required /><br> <label
					class="my-2 col-4" for="ville"><fmt:message
						key="champ.huit" bundle="${r}"></fmt:message></label><input class="col-6"
					type="text" name="ville"
					value="${sessionScope.utilisateurModifier != null ? sessionScope.utilisateurModifier.ville:sessionScope.utilisateurCourantComplet.ville}"
					pattern="(?=^([a-zA-Z]+([-\s][a-zA-Z]+)*)$)^.{1,30}$"
					placeholder="Paris" required /><br>
				<div style="height: 40px;"></div>
				<label class="my-2 col-4" for="mdpC"><fmt:message
						key="champ.dix" bundle="${r}"></fmt:message></label><input class="col-6"
					type="password" name="mdpC"
					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" />
				<br>
			</div>
		</div>

		<div class="d-flex justify-content-center">
			<fmt:message key="champ.enregistrer" bundle="${r}" var="enregistrer" />
			<input class="btn btn-warning mx-2 my-3 col-3" type="submit"
				value="${enregistrer}" name="update">
			<fmt:message key="champ.supprimer" bundle="${r}" var="supprimer" />
			<input class=" btn btn-outline-danger mx-2 my-3 col-3" type="submit"
				value="${supprimer}" name="delete"> <a
				class="btn btn-outline-primary mx-2 col-3 my-3"
				href="<%=request.getContextPath()%>/"><fmt:message key="retour"
					bundle="${r}"></fmt:message></a>
		</div>
	</form>

</div>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</body>
</html>