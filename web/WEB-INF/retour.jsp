<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 09/03/2018
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Retour Oeuvres</title>
</head>
<body>
<h1>Retour livre</h1>
<div class="scan">
    <input type="button" value="Nouveau Retour" onclick="ficheretour()" />
</div>
<div id="ficheRetour">
    <table class="tab">
        <tr>
            <td><label>Référence:</label></td>
            <td><input type="text" name="ref" id="reference" /></td>
        </tr>
        <tr>
            <td><input type="button" value="Valider Retour" onclick="RetourOeuvre()" /></td>
        </tr>
    </table>
</div>
</body>
</html>;
