<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="fr.eni.encheresApp.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_accueil"
	var="r" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link type="text/css"
	href="<%=request.getContextPath()%>/css/styles.css" rel="stylesheet" />
<meta charset="UTF-8">
<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
</head>
<body>

	<div class="container">
		<header class="d-flex justify-content-between align-items-center">
			<h1>
				<fmt:message key="titre" bundle="${r}"></fmt:message>
			</h1>
			<div>
				<!-- Selon le status de connexion on affiche ou non les liens  -->
				<c:choose>
					<c:when test="${sessionScope.utilisateurCourant != null}">
						<div>
							<a class="mx-1" href=""><fmt:message key="aEnch"
									bundle="${r}"></fmt:message></a> <a class="mx-1" href=""><fmt:message
									key="aVend" bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/Profil?userPseudo=${sessionScope.utilisateurCourant}"><fmt:message
									key="aProf" bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/Profil/Deconnexion"><fmt:message
									key="aDeco" bundle="${r}"></fmt:message></a>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<a class="mx-1" href="<%=request.getContextPath()%>/Inscription"><fmt:message
									key="aIns" bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/Connexion"><fmt:message
									key="aConx" bundle="${r}"></fmt:message></a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</header>
		<main>
			<h2 class="text-center my-3">
				<fmt:message key="sousTitre" bundle="${r}"></fmt:message>
			</h2>

			<form action="" method="" class="d-flex">
				<div>
					<label class="h4" for="filtre"><fmt:message key="lbFil"
							bundle="${r}"></fmt:message></label><br> <input type="text"
						name="filtre" class="col-10 bordArr"> <br> <label
						class="my-3" for="catg"><fmt:message key="lbCat"
							bundle="${r}"></fmt:message></label> <select name="catg"
						class="mx-3 col-8">
						<option>Informatique</option>
						<option>Ameublement</option>
						<option>Vêtement</option>
						<option>Sport & Loisirs</option>
					</select>
					<!-- Si l'utilisateur est connect alors on affiche les radio et checkbox -->
					<c:if test="${sessionScope.utilisateurCourant != null}">
						<div class="d-flex">
							<div class="d-flex flex-column me-3">
								<div>
									<input type="radio" name="achat" value="Achats" /> <label
										for="achat"><fmt:message key="radioAchat"
											bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="" /> <label for=""><fmt:message
											key="chEOuv" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="" /> <label for=""><fmt:message
											key="chECou" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="" /> <label for=""><fmt:message
											key="chERem" bundle="${r}"></fmt:message></label>
								</div>
							</div>
							<div class="d-flex flex-column mx-3">
								<div>
									<input type="radio" name="vente" value="Mes ventes" /> <label
										for="vente"><fmt:message key="radioVente"
											bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="" /> <label for=""><fmt:message
											key="chVCou" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="" /> <label for=""><fmt:message
											key="chVNon" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="" /> <label for=""><fmt:message
											key="chVterm" bundle="${r}"></fmt:message></label>
								</div>
							</div>
						</div>
					</c:if>
				</div>

				<button id="btnValider"
					class="btn btn-outline-success my-auto col-4" type="submit">
					<fmt:message key="btnRech" bundle="${r}"></fmt:message>
				</button>
			</form>

			<!-- Récuperation ici de la liste des articles depuis la BD -->






		</main>

	</div>



</body>
</html>