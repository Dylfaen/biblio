<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 18/03/2018
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration Bibliothèque</title>
</head>
<body>
<jsp:include page="includes/header.jsp" />
<div>
    <h2>Creer une nouvelle oeuvre</h2>
    <form>
        <label>Titre :</label>
        <input title="Titre" type="text" name="titre oeuvre"/>
        <label>Auteur :</label>
        <input title="Auteur" type="text" name="nom auteur"/>
        <button type="button" onclick="">Nouvel Ouvrage</button>
    </form>
    </div>
<div>
    <a href="/inscription" class="button">Inscription d'un nouveau membre</a>
</div>
</body>
</html>
