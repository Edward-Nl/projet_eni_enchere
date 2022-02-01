<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="fr.eni.encheresApp.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_accueil" var="r" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<meta charset="UTF-8">
<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
</head>
<body>

	<%
		Utilisateur utils = null; 
		int no_utilisateur= 0;
		if(session.getAttribute("utilisateurCourant")!=null){
			utils = (Utilisateur)session.getAttribute("utilisateurCourant");
			no_utilisateur = utils.getNoUtilisateur();
		}	
	%>
	<div class="container">
		<header class="d-flex justify-content-between">
			<h1><fmt:message key="titre" bundle="${r}"></fmt:message></h1>
			<div>
				<c:if test="${no_utilisateur != 0}">
					<div>
						<a href=""><fmt:message key="aEnch" bundle="${r}"></fmt:message></a>
						<a href=""><fmt:message key="aVend" bundle="${r}"></fmt:message></a>
						<a href=""><fmt:message key="aProf" bundle="${r}"></fmt:message></a>
						<a href=""><fmt:message key="aDeco" bundle="${r}"></fmt:message></a>
					</div>
				</c:if>
				<c:if test="${no_utilisateur == 0}">
						<a href=""><fmt:message key="aIns" bundle="${r}"></fmt:message></a>
						<a href=""><fmt:message key="aConx" bundle="${r}"></fmt:message></a>
				</c:if>
			</div>
			<!--  A integrer par la suite avec une conditions si l'utilisateur est un utilisateur connecté ou non  -->
			
		</header>
		<main>
			<h2><fmt:message key="sousTitre" bundle="${r}"></fmt:message></h2>
			
			<form action="" method="" >
				<label for="filtre"><fmt:message key="lbFil" bundle="${r}"></fmt:message></label><br>
				<input type="text" name="filtre"> <br>
				<label for="catg"><fmt:message key="lbCat" bundle="${r}"></fmt:message></label>
				<select name="catg">
					<option>Informatique</option>
					<option>Ameublement</option>
					<option>Vêtement</option>
					<option>Sport & Loisirs</option>
				</select>
				<button type="submit"><fmt:message key="btnRech" bundle="${r}"></fmt:message></button>
			</form>
			
			
			<!--  A integrer par la suite avec une conditions si l'utilisateur est un utilisateur connecté ou non  -->
			<c:if test="${no_utilisateur != 0 }">
				<div class="d-flex">
					<div class="d-flex flex-column">
						<div>
							<input type="radio" name="achat" value="Achats"/>
							<label for="achat"><fmt:message key="radioAchat" bundle="${r}"></fmt:message></label>
						</div>
						<div>
							<input type="checkbox" name=""/>
							<label for=""><fmt:message key="chEOuv" bundle="${r}"></fmt:message></label>
						</div>
						<div>
							<input type="checkbox" name=""/>
							<label for=""><fmt:message key="chECou" bundle="${r}"></fmt:message></label>
						</div>
						<div>
							<input type="checkbox" name=""/>
							<label for=""><fmt:message key="chERem" bundle="${r}"></fmt:message></label>
						</div>
					</div>
					<div class="d-flex flex-column">
						<div>
							<input type="radio" name="vente" value="Mes ventes"/>
							<label for="vente"><fmt:message key="radioVente" bundle="${r}"></fmt:message></label>
						</div>
						<div>
							<input type="checkbox" name=""/>
							<label for=""><fmt:message key="chVCou" bundle="${r}"></fmt:message></label>
						</div>
						<div>
							<input type="checkbox" name=""/>
							<label for=""><fmt:message key="chVNon" bundle="${r}"></fmt:message></label>
						</div>
						<div>
							<input type="checkbox" name=""/>
							<label for=""><fmt:message key="chVterm" bundle="${r}"></fmt:message></label>
						</div>
					</div>
				</div>
			</c:if>
			
			
			<!-- Récuperation ici de la liste des articles depuis la BD -->
			
			
			
			
			
			
		</main>
	
	</div>



</body>
</html>