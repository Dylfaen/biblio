<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
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
  <a href="/titi">Authentification</a>
  <a href="/tata">Emprunter</a>
</body>
</html>