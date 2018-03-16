<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bibliothèque</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond|Open+Sans|Roboto" rel="stylesheet">
</head>
<body>
<jsp:include page="WEB-INF/view/includes/header.jsp" />
<p>Page d'accueil</p>
<c:out value="test"/>
<div>
    <a href="/toto">Page suivante</a>
</div>
<div>
    <a href="/testjdbc">tests bdd</a>
</div>
<form action="/toto" method="get">
    <label>Nom :</label>
    <input title="Nom" type="text" name="name"/>
    <input type="submit">
</form>
	<h2>Tests fichiers</h2>
  <a href="/login">Authentification</a>
  </body>
</html>