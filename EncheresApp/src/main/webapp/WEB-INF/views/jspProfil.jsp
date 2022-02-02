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
	<div class="container">
		<h1>
			<fmt:message key="soustitre" bundle="${r}"></fmt:message>
		</h1>
		<div class="text-center">
			<c:choose>
				<c:when test="${utilisateurShow == null}">
					<p>
						<fmt:message key="inconnue" bundle="${r}"></fmt:message>
					</p>
				</c:when>
				<c:otherwise>
					<label class="my-2 fw-bold" for="pseudo"><fmt:message
							key="champ.un" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.pseudo}</label>
					<br>
					<label class="my-2 fw-bold" for="nom"><fmt:message
							key="champ.deux" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.nom}</label>
					<br>
					<label class="my-2 fw-bold" for="prenom"><fmt:message
							key="champ.trois" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.prenom}</label>
					<br>
					<label class="my-2 fw-bold" for="email"><fmt:message
							key="champ.quatres" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.email}</label>
					<br>
					<label class="my-2 fw-bold" for="telephone"><fmt:message
							key="champ.cinq" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.telephone}</label>
					<br>
					<label class="my-2 fw-bold" for="rue"><fmt:message
							key="champ.six" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.rue}</label>
					<br>
					<label class="my-2 fw-bold" for="codePostal"><fmt:message
							key="champ.sept" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.codePostal}</label>
					<br>
					<label class="my-2 fw-bold" for="ville"><fmt:message
							key="champ.huit" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.ville}</label>
					<br>
					<c:if
						test="${sessionScope.utilisateurCourrant.noUtilisateur == utilisateurShow.noUtilisateur}">
						<fmt:message key="champ.modifier" bundle="${r}" var="modifier" />
						<form method="get"
							action="<%=response.encodeURL(request.getContextPath() + "/Profil/Modifier")%>">
							<input class="btn btn-warning col-2 my-3" type="submit"
								value="${modifier}">
						</form>
					</c:if>

					<a class="btn btn-primary col-2 my-3"
						href="<%=request.getContextPath()%>/">Retour à l'accueil</a>

				</c:otherwise>

			</c:choose>
		</div>
	</div>


</body>
</html>