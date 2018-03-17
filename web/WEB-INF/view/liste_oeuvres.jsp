<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 09/03/2018
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Emprunt Oeuvres</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond|Open+Sans|Roboto" rel="stylesheet">
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<script type="text/javascript" src="js/empruntAPI.js" defer></script>
<div class="container list">
    <div class="list-wrapper">
        <div class="list-header">
            <div class="search-wrapper">
                <input type="text" class="search-input" placeholder="Rechercher"/>
            </div>
        </div>

       <button onclick="request()">
            request
       </button>
        <script>
            request();
        </script>
        <c:forEach items="${oeuvres}" var="oeuvre">
            <div class="list-item">
                <p>${oeuvre.getTitre()}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
