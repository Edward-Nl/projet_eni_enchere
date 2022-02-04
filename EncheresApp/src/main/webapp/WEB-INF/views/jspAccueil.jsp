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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(function() {
		$('input[id="filtreRadioA"]').click(function() {
			if ($(this).is(':checked')) {
				$('input[name="chkAchat"]').prop("disabled", false)
				$('input[name="chkVente"]').prop("disabled", true)
			}
		});
	});
	$(function() {
		$('input[id="filtreRadioV"]').click(function() {
			if ($(this).is(':checked')) {
				$('input[name="chkAchat"]').prop("disabled", true)
				$('input[name="chkVente"]').prop("disabled", false)
			}
		});
	});
</script>
</head>
<body class="">

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
									bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/ServletNouvelleVente"><fmt:message
									key="aVend" bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/Profil?userPseudo=${sessionScope.utilisateurCourant}"><fmt:message
									key="aProf" bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/Profil/Deconnexion"><fmt:message
									key="aDeco" bundle="${r}"></fmt:message></a>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<a class="mx-1" href="<%=request.getContextPath()%>/inscription"><fmt:message
									key="aIns" bundle="${r}"></fmt:message></a> <a class="mx-1"
								href="<%=request.getContextPath()%>/connexion"><fmt:message
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
			<form action="<%=request.getContextPath()%>/" method="post"
				class="d-flex">
				<div>
					<label class="h4" for="filtre"><fmt:message key="lbFil"
							bundle="${r}"></fmt:message></label><br> <input type="text"
						name="filtre" class="col-10 bordArr"
						value="${filtre != null?filtre:'' }"> <br> <label
						class="my-3" for="catg"><fmt:message key="lbCat"
							bundle="${r}"></fmt:message></label> <select name="catg"
						class="mx-3 col-8">
						<option value="0" ${catg == 0?'selected=\"selected\"':'' }>Toutes
							Catégories</option>
						<c:if test="${categories != null}">
							<c:forEach var="categorie" items="${categories}">
								<option value="${categorie.no_categorie}"
									${catg == categorie.no_categorie?'selected=\"selected\"':'' }>${categorie.libelle }</option>
							</c:forEach>
						</c:if>
					</select>
					<!-- Si l'utilisateur est connect alors on affiche les radio et checkbox -->
					<c:if test="${sessionScope.utilisateurCourant != null}">
						<div class="d-flex">
							<div class="d-flex flex-column me-3">
								<div>
									<input type="radio" name="filtreRadio" id="filtreRadioA"
										value="Achats" checked="checked" /> <label for="achat"><fmt:message
											key="radioAchat" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="chkAchat" /> <label
										for=""><fmt:message key="chEOuv" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="chkAchat" /> <label
										for=""><fmt:message key="chECou" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="chkAchat" /> <label
										for=""><fmt:message key="chERem" bundle="${r}"></fmt:message></label>
								</div>
							</div>
							<div class="d-flex flex-column mx-3">
								<div>
									<input type="radio" name="filtreRadio" id="filtreRadioV"
										value="Mes ventes" /> <label for="vente"><fmt:message
											key="radioVente" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="chkVente"
										disabled="disabled" /> <label for=""><fmt:message
											key="chVCou" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="chkVente"
										disabled="disabled" /> <label for=""><fmt:message
											key="chVNon" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4" type="checkbox" name="chkVente"
										disabled="disabled" /> <label for=""><fmt:message
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
			<div class="d-flex flex-wrap">
				<c:choose>
					<c:when test="${articles != null && articles.size() > 0}">
						<c:forEach var="article" items="${articles }">
							<a href="<%=request.getContextPath()%>/ServletDetailsArticle?noArticle=${article.no_Article}" class="col-3 mx-3 my-3 text-decoration-none text-dark">
								<div class="card border border-dark bg-warning shadow rounded">
									<div class="card-body">
										<h4 class="card-title">${article.nomArticle}</h4>
										<label class="ms-2">Prix : </label><label>
											${article.miseAPrix>article.prixVente?article.miseAPrix:article.prixVente}
											points</label><br> <label class="ms-2">Fin de l'enchère :
										</label><label> ${article.dateFinEncheres}</label><br>
		
		
										<c:choose>
											<c:when test="${sessionScope.utilisateurCourant != null}">
												<label class="ms-2">Pseudo : </label>
												<a href="<%=request.getContextPath()%>/Profil?userPseudo=${article.pseudoUtilisateur}">${article.pseudoUtilisateur}</a>
												<br>
											</c:when>
											<c:otherwise>
												<label class="ms-2">Pseudo : </label>
												<label>${article.pseudoUtilisateur}</label>
												<br>
											</c:otherwise>
										</c:choose>
									</div>
								</div>	
							</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>Aucun article a afficher</p>
					</c:otherwise>
				</c:choose>
			</div>
		</main>

	</div>


</body>
</html>