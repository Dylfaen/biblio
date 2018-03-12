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
</head>
<body>
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
                    <td><input class="larger" type="text" name="title" id="titre" /></td>
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
    <form id="myform"  action="/retour">
        <div id="emprunt2" style= "display: inline">
            <h2>Oeuvre 2</h2>
            <table class="tab" >
                <tr>
                    <td><label>Référence:</label></td>
                    <td><input type="text" name="ref" id="reference" /></td>
                </tr>
                <tr>
                    <td><label>Titre:</label></td>
                    <td><input class="larger" type="text" name="title" id="titre" /></td>
                </tr>
                <tr>
                    <td><label>Auteur(s):</label></td>
                    <td><input class="larger" type="text" name="autd" id="auteurs" /></td>
                </tr>
                <tr>
                    <td><label>Editeur:</label></td>
                    <td><input type="text" name="edit" id="editeur" /></td>
                    <td><input type="submit" value="Valider Emprunt" onclick="validationEmprunt2()" /></td>
                    <td><input type="button" value="Annulation" onclick="annuleEmprunt2()" /></td>
                </tr>
            </table>
        </div>
    </form>
    <form id="myform"  action="/retour">
        <div id="emprunt3" style= "display: none">
            <h2>Oeuvre 3</h2>
            <table class="tab" >
                <tr>
                    <td><label>Référence:</label></td>
                    <td><input type="text" name="ref" id="reference" /></td>
                </tr>
                <tr>
                    <td><label>Titre:</label></td>
                    <td><input class="larger" type="text" name="title" id="titre" /></td>
                </tr>
                <tr>
                    <td><label>Auteur(s):</label></td>
                    <td><input class="larger" type="text" name="autd" id="auteurs" /></td>
                </tr>
                <tr>
                    <td><label>Editeur:</label></td>
                    <td><input type="text" name="edit" id="editeur" /></td>
                    <td><input type="submit" value="Valider Emprunt" onclick="validationEmprunt3()" /></td>
                    <td><input type="button" value="Annulation" onclick="annuleEmprunt3()" /></td>
                </tr>
            </table>
        </div>
    </form>
    <form id="myform"  action="/retour">
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
