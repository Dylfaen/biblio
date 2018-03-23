<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 18/03/2018
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/header.jsp" />
<script type="text/javascript" src="js/inscription.js" defer></script>
<div class="form">
    <div class="row">
        <div class="col-12 input">
            <label>
                <input id="identifiant-input" class="text-input" type="text" name="identifiant-input"
                       placeholder="Identifiant"/><span class="requis">*</span>
            </label>
        </div>
    </div>
    <div class="row">
        <div class="col-12 input">
            <label>
                <input id="password-input" class="text-input" type="text" name="password-input"
                       placeholder="Mot de Passe"/><span class="requis">*</span>
            </label>
        </div>
    </div>
    <div class="row">
        <div class="col-6 input">
            <label>
                <input id="nom-input" class="text-input" type="text" name="nom-input"
                       placeholder="Nom"/>
            </label>
        </div>
        <div class="col-6 input">
            <label>
                <input id="prenom-input" class="text-input" type="text" name="prenom-input"
                       placeholder="Prenom"/>
            </label>
        </div>
    </div>
    <p class="error" id="error-add-user"></p>
    <div class="center">
        <button type="submit" class="button" onclick="insertUser()">
            <i class="material-icons">check</i>
            <p>S'inscrire</p>
        </button>
    </div>
</div>
</body>
</html>
