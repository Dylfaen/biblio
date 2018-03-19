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
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond|Open+Sans|Roboto" rel="stylesheet">
</head>
<body>
<jsp:include page="includes/header.jsp" />
<div class="cadre">
    <form id="myform"  action="/retour">
        <h1>Emprunter un livre</h1>
        <div id="emprunt1" style= "display: inline">
            <h2>Oeuvre 1</h2>
            <table class="tab">
                <tr>
                    <td><label>Référence:</label></td>
                    <td><input type="text" name="ref" id="reference" /></td>
                </tr>
                <tr>
                    <td><label>Titre:</label></td>
                    <td><input class="larger" type="text" name="title" id="title" /></td>
                </tr>
                <tr>
                    <td><label>Auteur(s):</label></td>
                    <td><input class="larger" type="text" name="autd" id="auteurs" /></td>
                </tr>
                <tr>
                    <td><label>Editeur:</label></td>
                    <td><input type="text" name="edit" id="editeur" /></td>
                    <td><input type="submit" value="Valider Emprunt" onclick="validationEmprunt1()" /></td>
                    <td><input type="button" value="Annulation" onclick="annuleEmprunt1()" /></td>
                </tr>
            </table>
        </div>
    </form>
    <form id="myform2"  action="/retour">
        <div id="emprunt2" style= "display: inline">
            <h2>Oeuvre 2</h2>
            <table class="tab" >
                <tr>
                    <td><label>Référence:</label></td>
                    <td><input type="text" name="ref" id="reference2" /></td>
                </tr>
                <tr>
                    <td><label>Titre:</label></td>
                    <td><input class="larger" type="text" name="title" id="titre2" /></td>
                </tr>
                <tr>
                    <td><label>Auteur(s):</label></td>
                    <td><input class="larger" type="text" name="autd" id="auteurs2" /></td>
                </tr>
                <tr>
                    <td><label>Editeur:</label></td>
                    <td><input type="text" name="edit" id="editeur2" /></td>
                    <td><input type="submit" value="Valider Emprunt" onclick="validationEmprunt2()" /></td>
                    <td><input type="button" value="Annulation" onclick="annuleEmprunt2()" /></td>
                </tr>
            </table>
        </div>
    </form>
    <form id="myform3"  action="/retour">
        <div id="emprunt3" style= "display: none">
            <h2>Oeuvre 3</h2>
            <table class="tab" >
                <tr>
                    <td><label>Référence:</label></td>
                    <td><input type="text" name="ref" id="reference3" /></td>
                </tr>
                <tr>
                    <td><label>Titre:</label></td>
                    <td><input class="larger" type="text" name="title" id="titre3" /></td>
                </tr>
                <tr>
                    <td><label>Auteur(s):</label></td>
                    <td><input class="larger" type="text" name="autd" id="auteurs3" /></td>
                </tr>
                <tr>
                    <td><label>Editeur:</label></td>
                    <td><input type="text" name="edit" id="editeur3" /></td>
                    <td><input type="submit" value="Valider Emprunt" onclick="validationEmprunt3()" /></td>
                    <td><input type="button" value="Annulation" onclick="annuleEmprunt3()" /></td>
                </tr>
            </table>
        </div>
    </form>
    <form id="myformall"  action="/retour">
        <div id="btnAll">
            <tr>
                <td><input type="button" value="Valider Tous" onclick="validationAll()" /></td>
                <td><input type="button" value="Annuler Tous" onclick="annuleAll()" /></td>
            </tr>
        </div>
    </form>
</div>
</body>
</html>
