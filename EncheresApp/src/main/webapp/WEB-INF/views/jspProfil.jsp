<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
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
<header class="sticky-top divHeader">
		<div class="container">
			<a href="<%=request.getContextPath()%>/">
				<h1>
					<fmt:message key="soustitre" bundle="${r}"></fmt:message>
				</h1>
			</a>
		</div>
	</header>
	<div class="container">
		<div class="d-flex flex-column">
			<c:choose>
				<c:when test="${utilisateurShow == null}">
					<p>
						<fmt:message key="inconnue" bundle="${r}"></fmt:message>
					</p>
				</c:when>
				<c:otherwise>
					<h2 class="mt-4 mb-5 text-center"><fmt:message key="h2Pro" bundle="${r}"></fmt:message>${utilisateurShow.prenom} ${utilisateurShow.nom}</h2>
					<div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="pseudo"><fmt:message
							key="champ.un" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.pseudo}</label>
					</div>
					<div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="nom"><fmt:message
							key="champ.deux" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.nom}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="prenom"><fmt:message
							key="champ.trois" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.prenom}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="email"><fmt:message
							key="champ.quatres" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.email}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="telephone"><fmt:message
							key="champ.cinq" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.telephone}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="rue"><fmt:message
							key="champ.six" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.rue}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="codePostal"><fmt:message
							key="champ.sept" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.codePostal}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="ville"><fmt:message
							key="champ.huit" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.ville}</label>
					</div><div class="col-12 col-md-4 mx-auto">
					<label class="my-2 fw-bold" for="credit"><fmt:message key="crd" bundle="${r}"></fmt:message></label>
					<label class="my-2">${utilisateurShow.credit}</label>
					</div>
					<c:if
						test="${sessionScope.utilisateurCourant == utilisateurShow.pseudo}">
						
						<form class="text-center" method="get"
							action="<%=response.encodeURL(request.getContextPath() + "/Profil/Modifier")%>">
							<button class="btn btn-outline-warning col-10 col-md-2 mx-auto my-3" type="submit">
							<i class="fas fa-pen"></i> <fmt:message key="champ.modifier" bundle="${r}" /></button>
						</form>
					</c:if>

					<a class="btn btn-outline-primary col-10 col-md-2 mx-auto my-3"
						href="<%=request.getContextPath()%>/"><i class="fas fa-home"></i> Retour à l'accueil</a>

				</c:otherwise>

			</c:choose>
		</div>
	</div>


</body>
</html>