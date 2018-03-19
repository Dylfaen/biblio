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
            <div class="action-wrapper">
                <c:if test="${user.isAdmin()}">
                    <button class="add-button icon-button">
                        <i class="material-icons">add</i>
                    </button>
                </c:if>
                <button class="reload-button icon-button">
                    <i class="material-icons">refresh</i>
                </button>
            </div>
        </div>
        <div class="list-item-wrapper">

        </div>

       <button onclick="request()">
            request
       </button>
    </div>
</div>
</body>
</html>
