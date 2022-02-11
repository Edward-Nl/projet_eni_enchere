<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="fr.eni.encheresApp.exceptions.LecteurMessage"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<fmt:setLocale value="fr" />
		<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_nouvelleVente" var="r" />
		<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_Header" var="h" />
		<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
		<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<title><fmt:message key="title2" bundle="${r}"></fmt:message></title>
	</head>
	<body>
	
		<header class="navbar navbar-expand-lg navbar-dark sticky-top divHeader">
		<div class="container d-flex justify-content-between">
			<button class="navbar-toggler d-md-none" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<a href="<%=request.getContextPath()%>/">
			<h1>
				<fmt:message key="titre" bundle="${h}"></fmt:message>
			</h1>
			</a>
			<div class="my-auto collapse navbar-collapse nav-masthead " id="navbarNavAltMarkup">
				<c:choose>
					<c:when test="${sessionScope.utilisateurCourant != null}">
						<div class="my-auto navbar-nav">
							<div class="mx-2 ahead nav-link">
								<i class="fas fa-coins"></i> <fmt:message key="credit" bundle="${h}"></fmt:message>${sessionScope.credit}
							</div> 
							<a class="mx-2 ahead nav-link" href="<%=request.getContextPath()%>/article/nouvelleVente">
								<i class="fas fa-share-square"></i> <fmt:message key="aVend" bundle="${h}"></fmt:message>
							</a>
							<a class="mx-2 ahead nav-link" href="<%=request.getContextPath()%>/Profil?userPseudo=${sessionScope.utilisateurCourant}">
								<i class="fas fa-user-alt"></i> <fmt:message key="aProf" bundle="${h}"></fmt:message>
							</a> 
							<a class="mx-2 ahead nav-link" href="<%=request.getContextPath()%>/Profil/Deconnexion">
								<i class="fas fa-sign-out-alt"></i> <fmt:message key="aDeco" bundle="${h}"></fmt:message>
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="navbar-nav">
							<a class="mx-2 ahead" href="<%=request.getContextPath()%>/inscription">
								<i class="fas fa-user-plus"></i> <fmt:message key="aIns" bundle="${h}"></fmt:message>
							</a>
							<a class="mx-2 ahead" href="<%=request.getContextPath()%>/connexion">
								<i class="fas fa-sign-in-alt"></i> <fmt:message key="aConx" bundle="${h}"></fmt:message>
							</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
		<div class="container">
			<c:if test="${listeCodesErreur != null && listeCodesErreur.size() > 0}">

				<p class="text-danger"><fmt:message key="erreur" bundle="${r}"></fmt:message></p>
				<c:forEach var="erreur" items="${listeCodesErreur}">
					<p class="text-danger">${LecteurMessage.getMessageErreur(erreur)}</p>
				</c:forEach>
			</c:if>
			
			<div class="d-flex flex-column justify-content-center align-item-center">
			
				<h2 class="mx-auto text-center my-4"><fmt:message key="h2Det" bundle="${r}"></fmt:message> - ${article.nomArticle}</h2>
				
				<div class="col-12 col-md-4 mx-auto">
					
					<p class="my-0 py-0"><span class="h6"><fmt:message key="des" bundle="${r}"></fmt:message></span> ${article.description}</p><br>
					
					<p class="my-0 py-0"><span class="h6"><fmt:message key="cat" bundle="${r}"></fmt:message></span> ${article.libelleCat}</p><br>
					<c:if test="${enchere.montant_enchere != null }">
						<p class="my-0 py-0"><span class="h6"><fmt:message key="offre" bundle="${r}"></fmt:message></span> ${enchere.montant_enchere} par <a class="lienColor" href="<%=request.getContextPath()%>/Profil?userPseudo=${enchere.pseudo}">${enchere.pseudo}</a></p><br>
						<button type="button" class="btn btn-outline-light my-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
						  <fmt:message key="popVoir" bundle="${r}"></fmt:message>
						</button>
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content bg-dark">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="popHead" bundle="${r}"></fmt:message></h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <c:forEach var="enchere" items="${ListeEnchere}">
						         <p>${enchere.pseudo} - ${enchere.montant_enchere} - ${enchere.dateEnchere }</p>
						        </c:forEach>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-outline-light" data-bs-dismiss="modal"><fmt:message key="popClose" bundle="${r}"></fmt:message></button>
						      </div>
						    </div>
						  </div>
						</div>
					</c:if>
					<c:if test="${enchere.montant_enchere == null }">
						<p class="my-0 py-0"><span class="h6"><fmt:message key="offre" bundle="${r}"></fmt:message></span> <fmt:message key="aucEnch" bundle="${r}"></fmt:message></p><br>
					</c:if>
					
					
					<p class="my-0 py-0"><span class="h6"><fmt:message key="mise" bundle="${r}"></fmt:message></span> ${article.miseAPrix }</p><br>
					
					<p class="my-0 py-0"><span class="h6"><fmt:message key="fin" bundle="${r}"></fmt:message></span> ${article.dateFinEncheres }</p><br>
					
					<p class="my-0 py-0"><span class="h6"><fmt:message key="deb" bundle="${r}"></fmt:message></span> ${article.dateDebutEncheres }</p><br>
					
					<p class="my-0 py-0"><span class="h6 float-start"><fmt:message key="retr" bundle="${r}"></fmt:message> : </span> ${retrait.rue}<br>
						${retrait.codePostal} ${retrait.ville}</p><br>
					
					<p class="my-0 py-0"><span class="h6"><fmt:message key="pseudo" bundle="${r}"></fmt:message></span><a class="lienColor" href="<%=request.getContextPath()%>/Profil?userPseudo=${article.pseudoUtilisateur}">${article.pseudoUtilisateur}</a></p><br>
					
					<c:if test="${article.etatVente == 'En Cours' }">
						<c:if test="${article.pseudoUtilisateur != sessionScope.utilisateurCourant }">
							<c:if test="${enchere.no_utilisateur != noUtilCourant }">
								<form action="<%=request.getContextPath()%>/article?noArticle=${article.no_Article}" method="post">
									<label for="enchere"><fmt:message key="prop" bundle="${r}"></fmt:message></label>
									<input type="number" name="enchere" min="${enchere.montant_enchere != 0 && enchere.montant_enchere != null ? enchere.montant_enchere+1:article.miseAPrix}"
									placeholder="<fmt:message key="placHol" bundle="${r}"></fmt:message>"/>
									<button class="btn btn-outline-primary" type="submit"><fmt:message key="btnEnch" bundle="${r}"></fmt:message></button>
								</form>
							</c:if>
							<c:if test="${enchere.no_utilisateur == noUtilCourant }">
								<p><fmt:message key="best" bundle="${r}"></fmt:message></p>
							</c:if>
						</c:if>
						<c:if test="${article.pseudoUtilisateur == sessionScope.utilisateurCourant }">
							<p><fmt:message key="your" bundle="${r}"></fmt:message></p>
						</c:if>
					</c:if>
					
					<a href="<%=request.getContextPath()%>/" class="btn btn-outline-warning my-3"><i class="fas fa-home"></i> <fmt:message key="btnRetour" bundle="${r}"></fmt:message></a>
					<c:if test="${article.pseudoUtilisateur == sessionScope.utilisateurCourant &&  article.etatVente == 'Non commencée'}">
						<a class="btn btn-outline-light" href="<%=request.getContextPath()%>/article/modifierArticle?noArticle=${article.no_Article}"><i class="fas fa-edit"></i> <fmt:message key="btnModif" bundle="${r}"></fmt:message></a>
					</c:if>
				</div>
			</div>
		
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
	</body>
</html>