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
	<h1>Details Vente</h1>
	
	<label>${article.nomArticle}</label><br>
	
	<label><fmt:message key="des" bundle="${r}"></fmt:message></label>
	<label>${article.description}</label><br>
	
	<label><fmt:message key="deb" bundle="${r}"></fmt:message></label>
	<label>${article.dateDebutEncheres }</label><br>
	
	<label><fmt:message key="fin" bundle="${r}"></fmt:message></label>
	<label>${article.dateFinEncheres }</label><br>
	
	<label><fmt:message key="mise" bundle="${r}"></fmt:message></label>
	<label>${article.miseAPrix }</label><br>
	
	<label><fmt:message key="offre" bundle="${r}"></fmt:message></label>
	<label>${article.prixVente}</label><br>
	
	<label><fmt:message key="pseudo" bundle="${r}"></fmt:message></label>
	<label>${article.pseudoUtilisateur}</label><br>
	
	<label><fmt:message key="rue" bundle="${r}"></fmt:message></label>
	<label>${retrait.rue}</label><br>
	
	<label><fmt:message key="cp" bundle="${r}"></fmt:message></label>
	<label>${retrait.codePostal}</label><br>
	
	<label><fmt:message key="vil" bundle="${r}"></fmt:message></label>
	<label>${retrait.ville}</label><br>

</body>
</html>