<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_nouvelleVente" var="r" />
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header class="sticky-top divHeader">
		<div class="container">
			<h1>
				<fmt:message key="h1" bundle="${r}"></fmt:message>
			</h1>
		</div>
	</header>
	<div class="container">
		
		<div class="td-flex flex-column justify-content-center align-item-center">
		
			<h2 class="mx-auto text-center my-4"><fmt:message key="h2Det" bundle="${r}"></fmt:message> - ${article.nomArticle}</h2>
			
			<div class="col-4 mx-auto">
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="des" bundle="${r}"></fmt:message></span> ${article.description}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="cat" bundle="${r}"></fmt:message></span> ${article.libelleCat}</p><br>
				<c:if test="${enchere.montant_enchere != null }">
					<p class="my-0 py-0"><span class="h6"><fmt:message key="offre" bundle="${r}"></fmt:message></span> ${enchere.montant_enchere} par <a class="lienColor" href="<%=request.getContextPath()%>/Profil?userPseudo=${enchere.pseudo}">${enchere.pseudo}</a></p><br>
				</c:if>
				<c:if test="${enchere.montant_enchere == null }">
					<p class="my-0 py-0"><span class="h6"><fmt:message key="offre" bundle="${r}"></fmt:message></span> Aucune enchère</p><br>
				</c:if>
				
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="mise" bundle="${r}"></fmt:message></span> ${article.miseAPrix }</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="fin" bundle="${r}"></fmt:message></span> ${article.dateFinEncheres }</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="deb" bundle="${r}"></fmt:message></span> ${article.dateDebutEncheres }</p><br>
				
				<p class="my-0 py-0"><span class="h6 float-start"><fmt:message key="retr" bundle="${r}"></fmt:message> : </span> ${retrait.rue}<br>
					${retrait.codePostal} ${retrait.ville}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="pseudo" bundle="${r}"></fmt:message></span><a class="lienColor" href="<%=request.getContextPath()%>/Profil?userPseudo=${article.pseudoUtilisateur}">${article.pseudoUtilisateur}</a></p><br>
				
				<c:if test="${article.etatVente == 'EC' }">
					<form action="<%=request.getContextPath()%>/ServletDetailsArticle?noArticle=${article.no_Article}" method="post">
						<label for="enchere"><fmt:message key="prop" bundle="${r}"></fmt:message></label>
						<input type="number" name="enchere" min="${article.prixVente != 0 ? article.prixVente+10:article.miseAPrix}" step="10"
						placeholder="<fmt:message key="placHol" bundle="${r}"></fmt:message>"/>
						<button class="btn btn-outline-primary" type="submit"><fmt:message key="btnEnch" bundle="${r}"></fmt:message></button>
					</form>
				</c:if>
				
				<a href="<%=request.getContextPath()%>/" class="btn btn-outline-warning"><i class="fas fa-home"></i> <fmt:message key="btnRetour" bundle="${r}"></fmt:message></a>
			
			</div>
		</div>
	
	</div>

</body>
</html>