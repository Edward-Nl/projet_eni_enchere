<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="fr.eni.encheresApp.exceptions.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<fmt:setLocale value="fr" />
<fmt:setBundle
	basename="fr.eni.encheresApp.content.contenue_nouvelleVente" var="r" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_Header"
	var="h" />
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/styles.css" />
<script src="https://kit.fontawesome.com/919a307c94.js"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title><fmt:message key="titleM" bundle="${r}"></fmt:message></title>
</head>
<body>

	<header
		class="navbar navbar-expand-lg navbar-dark sticky-top divHeader">
		<div class="container d-flex justify-content-between">
			<button class="navbar-toggler d-md-none" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<a href="<%=request.getContextPath()%>/">
				<h1>
					<fmt:message key="titre" bundle="${h}"></fmt:message>
				</h1>
			</a>
			<div class="my-auto collapse navbar-collapse nav-masthead "
				id="navbarNavAltMarkup">
				<c:choose>
					<c:when test="${sessionScope.utilisateurCourant != null}">
						<div class="my-auto navbar-nav">
							<div class="mx-2 ahead nav-link">
								<i class="fas fa-coins"></i>
								<fmt:message key="credit" bundle="${h}"></fmt:message>${sessionScope.credit}
							</div>
							<a class="mx-2 ahead nav-link"
								href="<%=request.getContextPath()%>/article/nouvelleVente">
								<i class="fas fa-share-square"></i> <fmt:message key="aVend"
									bundle="${h}"></fmt:message>
							</a> <a class="mx-2 ahead nav-link"
								href="<%=request.getContextPath()%>/Profil?userPseudo=${sessionScope.utilisateurCourant}">
								<i class="fas fa-user-alt"></i> <fmt:message key="aProf"
									bundle="${h}"></fmt:message>
							</a> <a class="mx-2 ahead nav-link"
								href="<%=request.getContextPath()%>/Profil/Deconnexion"> <i
								class="fas fa-sign-out-alt"></i> <fmt:message key="aDeco"
									bundle="${h}"></fmt:message>
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="navbar-nav">
							<a class="mx-2 ahead"
								href="<%=request.getContextPath()%>/inscription"> <i
								class="fas fa-user-plus"></i> <fmt:message key="aIns"
									bundle="${h}"></fmt:message>
							</a> <a class="mx-2 ahead"
								href="<%=request.getContextPath()%>/connexion"> <i
								class="fas fa-sign-in-alt"></i> <fmt:message key="aConx"
									bundle="${h}"></fmt:message>
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>

	<div class="container">

		

		<div class="mx-auto col-12 col-md-6 ">
			<h2 class="mt-4 mb-5">
				<fmt:message key="h2M" bundle="${r}"></fmt:message>
			</h2>

			<c:if test="${listeCodesErreur != null}">
				<p class="text-danger">
					<fmt:message key="erreur" bundle="${r}"></fmt:message>
				</p>
				<c:forEach var="erreur" items="${listeCodesErreur}">
					<p class="text-danger">${LecteurMessage.getMessageErreur(erreur)}</p>
				</c:forEach>
			</c:if>
			<c:if test="${ModificationValider}">
				<fmt:message key="valider" bundle="${r}"></fmt:message>
			</c:if>
			<form
				action="<%=request.getContextPath()%>/article/modifierArticle?noArticle=${article.no_Article}"
				method="POST">
				<div>
					<label for="nom" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="art" bundle="${r}"></fmt:message></label> <input type="text"
						name="nom" class="col-12 col-md-6"
						value="${articlesModifier != null?articlesModifier.nomArticle:article.nomArticle }"
						required="required" />
				</div>
				<div>
					<label for="description" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="des" bundle="${r}"></fmt:message></label>
					<textarea name="description" rows="" cols=""
						class="col-12 col-md-6" required="required">${articlesModifier != null?articlesModifier.description:article.description }</textarea>
				</div>
				<div>
					<label for="categorie" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="cat" bundle="${r}"></fmt:message></label> <select
						class="col-12 col-md-6" name="categorie" required="required">
						<c:if test="${categories != null}">
							<c:forEach var="categorie" items="${categories}">
								<option value="${categorie.no_categorie}"
									${catg == categorie.no_categorie?'selected=\"selected\"':'' }>${categorie.libelle }</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<div>
					<label for="image" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="pht" bundle="${r}"></fmt:message></label> <input type="file"
						name="image" class="col-12 col-md-6" />
				</div>
				<div>
					<label for="prix" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="mise" bundle="${r}"></fmt:message></label> <input type="number"
						name="prix" class="col-12 col-md-6" min="10" max="500" step="10"
						value="${articlesModifier != null?articlesModifier.miseAPrix:article.miseAPrix }"
						required="required" />
				</div>
				<div>
					<label for="debut" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="deb" bundle="${r}"></fmt:message></label> <input type="date"
						name="debut" class="col-12 col-md-6"
						value="${articlesModifier != null?articlesModifier.dateDebutEncheres:article.dateDebutEncheres }"
						required="required" />
				</div>
				<div>
					<label for="fin" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
							key="fin" bundle="${r}"></fmt:message></label> <input type="date"
						name="fin" class="col-12 col-md-6"
						value="${articlesModifier != null?articlesModifier.dateFinEncheres:article.dateFinEncheres }"
						required="required" />
				</div>
				<fieldset class="border">
					<legend class="h4">
						<fmt:message key="retr" bundle="${r}"></fmt:message>
					</legend>

					<div>
						<label for="rue" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
								key="rue" bundle="${r}"></fmt:message></label> <input type="text"
							name="rue" class="col-12 col-md-6"
							value="${retraitModifier != null?retraitModifier.rue:retrait.rue}"
							required="required" />
					</div>
					<div>
						<label for="cPostal" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
								key="cp" bundle="${r}"></fmt:message></label> <input type="text"
							name="cPostal" class="col-12 col-md-6"
							value="${retraitModifier != null?retraitModifier.codePostal:retrait.codePostal}"
							required="required" />
					</div>
					<div>
						<label for="ville" class="col-12 col-md-4 my-3 fw-bold"><fmt:message
								key="vil" bundle="${r}"></fmt:message></label> <input type="text"
							name="ville" class="col-12 col-md-6"
							value="${retraitModifier != null?retraitModifier.ville:retrait.ville}"
							required="required" />
					</div>
				</fieldset>

				<div>
					<button type="submit"
						class="btn btn-outline-primary mx-md-2 col-12 col-md-3 my-3">
						<i class="far fa-save"></i>
						<fmt:message key="btnEn" bundle="${r}"></fmt:message>
					</button>
					<a class="btn btn-outline-warning mx-md-2 col-12 col-md-3 my-3"
						href="<%=request.getContextPath()%>/"><i
						class="fas fa-angle-double-left"></i> <fmt:message key="btnAnnul"
							bundle="${r}"></fmt:message></a>
					<button type="submit" name="delete"
						class="btn btn-outline-danger mx-md-2 col-12 col-md-3 my-3">
						<i class="far fa-times-circle"></i>
						<fmt:message key="btnSup" bundle="${r}"></fmt:message>
					</button>
					<a></a>
				</div>

			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>