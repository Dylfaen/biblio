<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cesar
  Date: 15/03/2018
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Bibliothèque</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond|Open+Sans|Roboto" rel="stylesheet">
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/global.js" defer></script>

</head>
<body>
<div class="header">
    <div class="nav-bar row">
        <div class="tabs features col-4">
            <c:if test="${user != null}">
                <a class="tab" href="/loan">
                    <p>
                        Emprunter
                    </p>
                </a>
                <a class="tab" href="/my_loans">
                    <p>
                        Mes emprunts
                    </p>
                </a>
            </c:if>
            <c:if test="${user.isAdmin()}">
                <a class="tab" href="/admin">
                    <p>
                        Administration
                    </p>
                </a>
            </c:if>
        </div>
        <a class="title col-4" href="/">
            <p>Bibliothèque</p>
        </a>

        <div class="tabs account col-4">
            <c:if test="${user == null}">
                <a class="tab" href="/login">
                    <p>
                        Se connecter
                    </p>
                </a>
                <a class="tab" href="/inscription">
                    <p>
                        S'inscrire
                    </p>
                </a>
            </c:if>
            <c:if test="${user != null}">
                <a class="tab" href="/my_account">
                    <p>
                            ${user.getFirstname()} ${user.getLastname()}
                    </p>
                </a>
                <a class="tab" href="/logout">
                    <p>Déconnexion</p>
                </a>
            </c:if>
        </div>

    </div>
    <div class="info-bar">
        <p>${pageTitle}</p>
    </div>

</div>
