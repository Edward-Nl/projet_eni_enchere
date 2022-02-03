<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_nouvelleVente" var="r" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
</head>
<body>

	<div class="container">
		<h1><fmt:message key="h1" bundle="${r}"></fmt:message></h1>
		
		<div class="mx-auto col-6 ">
			<h2><fmt:message key="h2" bundle="${r}"></fmt:message></h2>
			
			<form action="<%=request.getContextPath()%>/ServletNouvelleVente" method="POST">
				<div>
					<label for="nom" class="col-4 my-3 fw-bold"><fmt:message key="art" bundle="${r}"></fmt:message></label>
					<input type="text" name="nom" class="col-6"/>
				</div>
				<div>
					<label for="description" class="col-4 my-3 fw-bold"><fmt:message key="des" bundle="${r}"></fmt:message></label>
					<textarea name="description" rows="" cols="" class="col-6"></textarea>
				</div>
				<div>
					<label for="categorie" class="col-4 my-3 fw-bold"><fmt:message key="cat" bundle="${r}"></fmt:message></label>
					<select class="col-6" name="categorie">
						<option value="1">Informatique</option>
						<option value="2">Ameublement</option>
						<option value="3">Vêtement</option>
						<option value="4">Sport & Loisirs</option>
					</select>
				</div>
				<div>
					<label for="image" class="col-4 my-3 fw-bold"><fmt:message key="pht" bundle="${r}"></fmt:message></label>
					<input type="file" name="image" class="col-6"/>
				</div>
				<div>
					<label for="prix" class="col-4 my-3 fw-bold"><fmt:message key="mise" bundle="${r}"></fmt:message></label>
					<input type="number" name="prix" class="col-6" min="10" max="500" step="10"/>
				</div>
				<div>
					<label for="debut" class="col-4 my-3 fw-bold"><fmt:message key="deb" bundle="${r}"></fmt:message></label>
					<input type="date" name="debut" class="col-6"/>
				</div>
				<div>
					<label for="fin" class="col-4 my-3 fw-bold"><fmt:message key="fin" bundle="${r}"></fmt:message></label>
					<input type="date" name="fin" class="col-6"/>
				</div>
				<fieldset class="border">
					<legend class="h4"><fmt:message key="retr" bundle="${r}"></fmt:message></legend>
					
					<div>
						<label for="rue" class="col-4 my-3 fw-bold"><fmt:message key="rue" bundle="${r}"></fmt:message></label>
						<input type="text" name="rue" class="col-6"/>
					</div>
					<div>
						<label for="cPostal" class="col-4 my-3 fw-bold"><fmt:message key="cp" bundle="${r}"></fmt:message></label>
						<input type="text" name="cPostal" class="col-6"/>
					</div>
					<div>
						<label for="ville" class="col-4 my-3 fw-bold"><fmt:message key="vil" bundle="${r}"></fmt:message></label>
						<input type="text" name="ville" class="col-6"/>
					</div>
				</fieldset>
				
				<div>
					<input type="submit" class="btn btn-primary mx-2 col-3 my-3"/>
					<a class="btn btn-outline-danger mx-2 col-3 my-3" href="<%=request.getContextPath()%>/"><fmt:message key="btnAnnul" bundle="${r}"></fmt:message></a>
					<a></a>
				</div>
				
			</form>
		</div>
	</div>

</body>
</html>