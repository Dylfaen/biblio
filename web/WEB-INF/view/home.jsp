<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="includes/header.jsp" />
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

<jsp:include page="includes/footer.jsp" />

