<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_nouvelleVente" var="r" />
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/styles.css" />
<script src="https://kit.fontawesome.com/919a307c94.js" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title><fmt:message key="titleM" bundle="${r}"></fmt:message></title>
</head>
<body>

	<header class="sticky-top divHeader">
		<div class="container">
			<a href="<%=request.getContextPath()%>/">
				<h1>
					<fmt:message key="h1" bundle="${r}"></fmt:message>
				</h1>
			</a>
		</div>
	</header>

	<div class="container">
		
		<div class="mx-auto col-12 col-md-6 ">
			<h2 class="mt-4 mb-5"><fmt:message key="h2M" bundle="${r}"></fmt:message></h2>
			
			<form action="<%=request.getContextPath()%>/ServletModifierVente?noArticle=${article.no_Article}" method="POST">
				<div>
					<label for="nom" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="art" bundle="${r}"></fmt:message></label>
					<input type="text" name="nom" class="col-12 col-md-6" value="${article.nomArticle }"/>
				</div>
				<div>
					<label for="description" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="des" bundle="${r}"></fmt:message></label>
					<textarea name="description" rows="" cols="" class="col-12 col-md-6">${article.description }</textarea>
				</div>
				<div>
					<label for="categorie" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="cat" bundle="${r}"></fmt:message></label>
					<select class="col-12 col-md-6" name="categorie">
						<option value="1">Informatique</option>
						<option value="2">Ameublement</option>
						<option value="3">Vêtement</option>
						<option value="4">Sport & Loisirs</option>
					</select>
				</div>
				<div>
					<label for="image" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="pht" bundle="${r}"></fmt:message></label>
					<input type="file" name="image" class="col-12 col-md-6" />
				</div>
				<div>
					<label for="prix" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="mise" bundle="${r}"></fmt:message></label>
					<input type="number" name="prix" class="col-12 col-md-6" min="10" max="500" step="10" value="${article.miseAPrix }"/>
				</div>
				<div>
					<label for="debut" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="deb" bundle="${r}"></fmt:message></label>
					<input type="date" name="debut" class="col-12 col-md-6" value="${article.dateDebutEncheres }"/>
				</div>
				<div>
					<label for="fin" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="fin" bundle="${r}"></fmt:message></label>
					<input type="date" name="fin" class="col-12 col-md-6" value="${article.dateFinEncheres }"/>
				</div>
				<fieldset class="border">
					<legend class="h4"><fmt:message key="retr" bundle="${r}"></fmt:message></legend>
					
					<div>
						<label for="rue" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="rue" bundle="${r}"></fmt:message></label>
						<input type="text" name="rue" class="col-12 col-md-6"
						value="${retrait.rue}"/>
					</div>
					<div>
						<label for="cPostal" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="cp" bundle="${r}"></fmt:message></label>
						<input type="text" name="cPostal" class="col-12 col-md-6"
						value="${retrait.codePostal}"/>
					</div>
					<div>
						<label for="ville" class="col-12 col-md-4 my-3 fw-bold"><fmt:message key="vil" bundle="${r}"></fmt:message></label>
						<input type="text" name="ville" class="col-12 col-md-6"
						value="${retrait.ville}"/>
					</div>
				</fieldset>
				
				<div>
					<button type="submit" class="btn btn-outline-primary mx-md-2 col-12 col-md-3 my-3"><i class="far fa-save"></i> <fmt:message key="btnEn" bundle="${r}"></fmt:message></button>
					<a class="btn btn-outline-warning mx-md-2 col-12 col-md-3 my-3" href="<%=request.getContextPath()%>/"><i class="fas fa-angle-double-left"></i> <fmt:message key="btnAnnul" bundle="${r}"></fmt:message></a>
					<button type="submit" name="delete" class="btn btn-outline-danger mx-md-2 col-12 col-md-3 my-3"><i class="far fa-save"></i> Supprimer</button>
					<a></a>
				</div>
				
			</form>
		</div>
	</div>

</body>
</html>