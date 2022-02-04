<%@page import="fr.eni.encheresApp.exceptions.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFsFpd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/styles.css" />
<fmt:setLocale value="fr" />
<fmt:setBundle basename="fr.eni.encheresApp.content.contenue_connexion"
	var="r" />


<title><fmt:message key="title" bundle="${r}"></fmt:message></title>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<h1>
			<fmt:message key="h1" bundle="${r}"></fmt:message>
		</h1>

		<div class="mx-auto d-flex flex-column align-items-center">
			<form action="<%=request.getContextPath()%>/connexion" method="POST"
				class="col-6">
				<c:if test="${listeCodesErreur != null}">
					<p style="color: red;">Erreur lors du login du compte</p>
					<c:forEach var="erreur" items="${listeCodesErreur}">
						<p>${LecteurMessage.getMessageErreur(erreur)}</p>
					</c:forEach>
				</c:if>
				<label for="identifiant" class="col-3 my-3"> <fmt:message
						key="champ.un" bundle="${r}"></fmt:message>
				</label> <input type="text" name="identifiant" required
					placeholder="<fmt:message key="pl.un" bundle="${r}"></fmt:message>"
					class="col-6" value="${cookie.pseudo.value}" /> <br> <label
					for="motDePasse" class="col-3 my-3"><fmt:message
						key="champ.deux" bundle="${r}"></fmt:message></label><input
					type="password" name="motDePasse" required
					placeholder="<fmt:message key="pl.deux" bundle="${r}"></fmt:message>"
					class="col-6" value="${cookie.Mdp.value}" /> <br>
				<div class="d-flex flex-row-reverse justify-content-center my-3">
					<div class="d-flex flex-column mx-1">
						<div>
							<input type="checkbox" name="souvenir" /> <label for="souvenir"><fmt:message
									key="check.souvenir" bundle="${r}"></fmt:message></label>
						</div>
						<a href=""><fmt:message key="a.mdp" bundle="${r}"></fmt:message></a>
					</div>
					<button class="btn btn-primary col-3" type="submit">
						<fmt:message key="btn.connexion" bundle="${r}"></fmt:message>
					</button>
				</div>

			</form>
			<div class="col-6 text-center">
				<a href="<%=request.getContextPath()%>/inscription"
					class="btn btn-outline-warning col-4"><fmt:message
						key="btn.inscription" bundle="${r}"></fmt:message></a> <a
					class="btn btn-outline-danger col-4"
					href="<%=request.getContextPath()%>/"><fmt:message key="retour"
						bundle="${r}"></fmt:message></a>
			</div>
		</div>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>