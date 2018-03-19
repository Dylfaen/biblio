<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 18/03/2018
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
</head>
<body>
<jsp:include page="includes/header.jsp" />
<script type="text/javascript" src="js/inscription.js" defer></script>
<form method="post" action="inscription">
    <tr>
        <td><label>Identifiant<span class="requis">*</span>:</label></td>
        <td><input type="text" name="identifiant" id="ident" /></td>
    </tr>
    <tr>
        <td><label>Password<span class="requis">*</span>:</label></td>
        <td><input type="text" name="password" id="pswrd" /></td>
    </tr><tr>
        <td><label>Nom:</label></td>
        <td><input type="text" name="Nom" id="name" /></td>
    </tr><tr>
        <td><label>Prenom:</label></td>
        <td><input type="text" name="Prenom" id="pname" /></td>
    </tr>
    <tr>
        <td><label>Naissance:</label></td>
        <td><input type="date" name="naissance" id="birth" /></td>
    </tr>
    <tr>
        <td><label>Adresse:</label></td>
        <td><input type="text" name="adresse" id="address" /></td>
    </tr>
    <input type="Submit" value="Inscription">
</form>
</body>
</html>
