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
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_profil"
	var="r" />

<title><fmt:message key="titre" bundle="${r}"></fmt:message></title>
</head>
<body>
	<h1>
		<fmt:message key="soustitre" bundle="${r}"></fmt:message>
	</h1>

	<c:choose>
		<c:when test="${utilisateur == null}">
			<p>
				<fmt:message key="inconnue" bundle="${r}"></fmt:message>
			</p>
		</c:when>
		<c:otherwise>
			<label for="pseudo"><fmt:message key="champ.un" bundle="${r}"></fmt:message></label>
			<label>${utilisateur.pseudo}</label>
			<br>
			<label for="nom"><fmt:message key="champ.deux" bundle="${r}"></fmt:message></label>
			<label>${utilisateur.nom}</label>
			<br>
			<label for="prenom"><fmt:message key="champ.trois"
					bundle="${r}"></fmt:message></label>
			<label>${utilisateur.prenom}</label>
			<br>
			<label for="email"><fmt:message key="champ.quatres"
					bundle="${r}"></fmt:message></label>
			<label>${utilisateur.email}</label>
			<br>
			<label for="telephone"><fmt:message key="champ.cinq"
					bundle="${r}"></fmt:message></label>
			<label>${utilisateur.telephone}</label>
			<br>
			<label for="rue"><fmt:message key="champ.six" bundle="${r}"></fmt:message></label>
			<label>${utilisateur.rue}</label>
			<br>
			<label for="codePostal"><fmt:message key="champ.sept"
					bundle="${r}"></fmt:message></label>
			<label>${utilisateur.codePostal}</label>
			<br>
			<label for="ville"><fmt:message key="champ.huit"
					bundle="${r}"></fmt:message></label>
			<label>${utilisateur.ville}</label>
			<br>


			<!-- TODO: Afficher que quand utilisateur log = utilisateur regarder -->
			<c:if
				test="${sessionScope.utilisateur.noUtilisateur == utilisateurShow.noUtilisateur}"></c:if>
			<fmt:message key="champ.modifier" bundle="${r}" var="modifier" />
			<form action="">
				<input type="submit" value="${modifier}">
			</form>
		</c:otherwise>

	</c:choose>



</body>
</html>