<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="fr.eni.encheresApp.bo.Utilisateur"%>
<%@page import="fr.eni.encheresApp.exceptions.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<fmt:setLocale value="fr" />
	<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_accueil" var="r" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel=stylesheet type="text/css" href="/EncheresApp/css/styles.css" />
	<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
	<meta charset="UTF-8">
	<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(function() {
			$('input[id="filtreRadioA"]').click(function() {
				if ($(this).is(':checked')) {
					$('.chkAchat').prop("disabled", false)
					$('.chkVente').prop("checked", false)
					$('.chkVente').prop("disabled", true)
				}
			});
		});
		$(function() {
			$('input[id="filtreRadioV"]').click(function() {
				if ($(this).is(':checked')) {
					$('.chkAchat').prop("checked", false)
					$('.chkAchat').prop("disabled", true)
					$('.chkVente').prop("disabled", false)
	
				}
			});
		});
	</script>
</head>
<body>
	<header class="navbar navbar-expand-lg navbar-dark sticky-top divHeader">
		<div class="container d-flex justify-content-between">
			<button class="navbar-toggler d-md-none" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<h1>
				<fmt:message key="titre" bundle="${r}"></fmt:message>
			</h1>
			<div class="my-auto collapse navbar-collapse nav-masthead " id="navbarNavAltMarkup">
				<c:choose>
					<c:when test="${sessionScope.utilisateurCourant != null}">
						<div class="my-auto navbar-nav">
							<a class="mx-2 ahead nav-link" href="">
								<i class="fas fa-cart-arrow-down"></i> <fmt:message key="aEnch" bundle="${r}"></fmt:message>
							</a> 
							<a class="mx-2 ahead nav-link" href="<%=request.getContextPath()%>/article/nouvelleVente">
								<i class="fas fa-share-square"></i> <fmt:message key="aVend" bundle="${r}"></fmt:message>
							</a>
							<a class="mx-2 ahead nav-link" href="<%=request.getContextPath()%>/Profil?userPseudo=${sessionScope.utilisateurCourant}">
								<i class="fas fa-user-alt"></i> <fmt:message key="aProf" bundle="${r}"></fmt:message>
							</a> 
							<a class="mx-2 ahead nav-link" href="<%=request.getContextPath()%>/Profil/Deconnexion">
								<i class="fas fa-sign-out-alt"></i> <fmt:message key="aDeco" bundle="${r}"></fmt:message>
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="navbar-nav">
							<a class="mx-2 ahead" href="<%=request.getContextPath()%>/inscription">
								<i class="fas fa-user-plus"></i> <fmt:message key="aIns" bundle="${r}"></fmt:message>
							</a>
							<a class="mx-2 ahead" href="<%=request.getContextPath()%>/connexion">
								<i class="fas fa-sign-in-alt"></i> <fmt:message key="aConx" bundle="${r}"></fmt:message>
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	
	<div class="container">
		<c:if test="${listeCodesErreur != null}">
			<p class="text-danger"><fmt:message key="erreur" bundle="${r}"></fmt:message></p>
			<c:forEach var="erreur" items="${listeCodesErreur}">
				<p class="text-danger">${LecteurMessage.getMessageErreur(erreur)}</p>
			</c:forEach>
		</c:if>
		<main>
		
			<h2 class="text-center my-3">
				<fmt:message key="sousTitre" bundle="${r}"></fmt:message>
			</h2>
			
			<form action="<%=request.getContextPath()%>/" method="post" class="d-flex flex-column flex-md-row">
				<div class="col-12 col-md-6">
					<label class="h4" for="filtre"><fmt:message key="lbFil" bundle="${r}"></fmt:message></label><br> 
					<input type="text" name="filtre" class="col-10 bordArr" value="${filtre != null?filtre:'' }"> <br> 
					
					<label class="my-3" for="catg"><fmt:message key="lbCat" bundle="${r}"></fmt:message></label> 
					<select name="catg" class="mx-3 col-8">
						<option value="0" ${catg == 0?'selected=\"selected\"':'' }><fmt:message key="ttCat" bundle="${r}"></fmt:message></option>
						<c:if test="${categories != null}">
							<c:forEach var="categorie" items="${categories}">
								<option value="${categorie.no_categorie}" ${catg == categorie.no_categorie?'selected=\"selected\"':'' }>${categorie.libelle }</option>
							</c:forEach>
						</c:if>
					</select>
					
					<c:if test="${sessionScope.utilisateurCourant != null}">
						<div class="d-flex flex-column flex-md-row">
							<div class="d-flex flex-column me-3">
								<div>
									<input type="radio" name="filtreRadio" id="filtreRadioA" value="Achats" ${filtreRadio == 'Achats'?'checked=\"checked\"':''} /> 
									<label for="achat"><fmt:message key="radioAchat" bundle="${r}"></fmt:message></label>
								</div>
								
								<div>
									<input class="ms-4 chkAchat" type="checkbox" name="chkAchat1" ${filtreRadio == 'Achats'?'':'disabled=\"disabled\"'} ${(filtreRadio == 'Achats' && filtreChkBox[0])?'checked=\"checked\"':''} />
									<label for=""><fmt:message key="chEOuv" bundle="${r}"></fmt:message></label>
								</div>
								
								<div>
									<input class="ms-4 chkAchat" type="checkbox" name="chkAchat2" ${filtreRadio == 'Achats'?'':'disabled=\"disabled\"'} ${(filtreRadio == 'Achats' && filtreChkBox[1])?'checked=\"checked\"':''} />
									<label for=""><fmt:message key="chECou" bundle="${r}"></fmt:message></label>
								</div>
								
								<div>
									<input class="ms-4 chkAchat" type="checkbox" name="chkAchat3" ${filtreRadio == 'Achats'?'':'disabled=\"disabled\"'} ${(filtreRadio == 'Achats' && filtreChkBox[2])?'checked=\"checked\"':''} />
									<label for=""><fmt:message key="chERem" bundle="${r}"></fmt:message></label>
								</div>
							</div>
							
							<div class="d-flex flex-column mx-md-3">
								<div>
									<input type="radio" name="filtreRadio" id="filtreRadioV" value="Ventes" ${filtreRadio == 'Ventes'?'checked=\"checked\"':''} /> 
									<label for="vente"><fmt:message key="radioVente" bundle="${r}"></fmt:message></label>
								</div>
								
								<div>
									<input class="ms-4 chkVente" type="checkbox" name="chkVente1" ${filtreRadio == 'Ventes'?'':'disabled=\"disabled\"'} ${(filtreRadio == 'Ventes' && filtreChkBox[0])?'checked=\"checked\"':''} />
									<label for=""><fmt:message key="chVCou" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4 chkVente" type="checkbox" name="chkVente2" ${filtreRadio == 'Ventes'?'':'disabled=\"disabled\"'} ${(filtreRadio == 'Ventes' && filtreChkBox[1])?'checked=\"checked\"':''} />
									<label for=""><fmt:message key="chVNon" bundle="${r}"></fmt:message></label>
								</div>
								<div>
									<input class="ms-4 chkVente" type="checkbox" name="chkVente3" ${filtreRadio == 'Ventes'?'':'disabled=\"disabled\"'} ${(filtreRadio == 'Ventes' && filtreChkBox[2])?'checked=\"checked\"':''} />
									<label for=""><fmt:message key="chVterm" bundle="${r}"></fmt:message></label>
								</div>
							</div>
						</div>
					</c:if>
				</div>

				<button class="btn btn-outline-success my-auto col-12 col-md-4 btnRecherche" type="submit">
					<i class="fas fa-search"></i> <fmt:message key="btnRech" bundle="${r}"></fmt:message>
				</button>
			</form>
			<c:if test="${articles.size() > 0}">
				<c:forEach var="articlesList" items="${articles}" varStatus="loop">
					<c:choose>
						<c:when test="${(filtreRadio == 'Achats' && filtreChkBox[loop.index])}">
							<c:choose>
								<c:when test="${loop.index == 0 }">
									<h4 class="my-3 text-decoration-underline"><fmt:message key="chEOuv" bundle="${r}"></fmt:message></h4>
								</c:when>
								<c:when test="${loop.index == 1 }">
									<h4 class="my-3 text-decoration-underline"><fmt:message key="chECou" bundle="${r}"></fmt:message></h4>
								</c:when>
								<c:when test="${loop.index == 2 }">
									<h4 class="my-3 text-decoration-underline"><fmt:message key="chERem" bundle="${r}"></fmt:message></h4>
								</c:when>
							</c:choose>
						</c:when>
						<c:when test="${(filtreRadio == 'Ventes' && filtreChkBox[loop.index])}">
							<c:choose>
								<c:when test="${loop.index == 0 }">
									<h4 class="my-3 text-decoration-underline"><fmt:message key="chVCou" bundle="${r}"></fmt:message></h4>
								</c:when>
								<c:when test="${loop.index == 1 }">
									<h4 class="my-3 text-decoration-underline"><fmt:message key="chVNon" bundle="${r}"></fmt:message></h4>
								</c:when>
								<c:when test="${loop.index == 2 }">
									<h4 class="my-3 text-decoration-underline"><fmt:message key="chVterm" bundle="${r}"></fmt:message></h4>
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
					
					<div class="d-flex flex-wrap">
						<c:forEach var="article" items="${articlesList}">
							
							<div class="card text-white mx-3 my-3 rounded">
								<div class="card-body px-auto py-auto">
									<h4 class="card-title">${article.nomArticle}</h4>
									<label class="ms-2 fw-bold"><fmt:message key="prix" bundle="${r}"></fmt:message></label> 
									<label class="colorGrey">
										${article.miseAPrix>article.prixVente?article.miseAPrix:article.prixVente}<fmt:message key="ptn" bundle="${r}"></fmt:message>
									</label><br> 
									<label class="ms-2 fw-bold"><fmt:message key="fin" bundle="${r}"></fmt:message></label>
									<label class="colorGrey">
										${article.dateFinEncheres}
									</label><br> 
									<label class="ms-2 fw-bold"><fmt:message key="stts" bundle="${r}"></fmt:message></label>
									<label class="colorGrey"> ${article.etatVente}</label><br>
									<c:choose>
										<c:when test="${sessionScope.utilisateurCourant != null}">
											<label class="ms-2 fw-bold"><fmt:message key="pseudo" bundle="${r}"></fmt:message></label>
											<a href="<%=request.getContextPath()%>/Profil?userPseudo=${article.pseudoUtilisateur}" class="name">${article.pseudoUtilisateur}</a> <br>
											<a href="<%=request.getContextPath()%>/article?noArticle=${article.no_Article}" class="btn btnLinkCard mt-3 ms-2">
												<i class="fas fa-eye"></i> <fmt:message key="voir" bundle="${r}"></fmt:message>
											</a>
										</c:when>
										<c:otherwise>
											<label class="ms-2 fw-bold"><fmt:message key="pseudo" bundle="${r}"></fmt:message></label>
											<label class="colorGrey">${article.pseudoUtilisateur}</label>
											<br>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</c:if>

			<c:if test="${(articles[0] == null || articles[0].size() < 0) && (articles[1] == null || articles[1].size() < 0) && (articles[2] == null || articles[2].size() < 0)}">
				<p><fmt:message key="aucun" bundle="${r}"></fmt:message></p>
			</c:if>

		</main>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

</body>
</html>