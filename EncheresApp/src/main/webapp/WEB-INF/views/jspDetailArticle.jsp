<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_nouvelleVente" var="r" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	
		<h1><fmt:message key="h1" bundle="${r}"></fmt:message></h1>
		
		<div class="td-flex flex-column justify-content-center align-item-center">
		
			<h2 class="mx-auto text-center my-4"><fmt:message key="h2Det" bundle="${r}"></fmt:message></h2>
			
			<div class="col-4 mx-auto">
		
				<p class="h4">${article.nomArticle}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="des" bundle="${r}"></fmt:message></span> ${article.description}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="cat" bundle="${r}"></fmt:message></span> ${article.libelleCat}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="offre" bundle="${r}"></fmt:message></span> ${article.prixVente}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="mise" bundle="${r}"></fmt:message></span> ${article.miseAPrix }</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="fin" bundle="${r}"></fmt:message></span> ${article.dateFinEncheres }</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="deb" bundle="${r}"></fmt:message></span> ${article.dateDebutEncheres }</p><br>
				
				<p class="my-0 py-0"><span class="h6 float-start"><fmt:message key="retr" bundle="${r}"></fmt:message> : </span> ${retrait.rue}<br>
					${retrait.codePostal} ${retrait.ville}</p><br>
				
				<p class="my-0 py-0"><span class="h6"><fmt:message key="pseudo" bundle="${r}"></fmt:message></span> ${article.pseudoUtilisateur}</p><br>
				
				<a href="<%=request.getContextPath()%>/" class="btn btn-outline-primary"><fmt:message key="btnRetour" bundle="${r}"></fmt:message></a>
			
			</div>
		</div>
	
	</div>

</body>
</html>