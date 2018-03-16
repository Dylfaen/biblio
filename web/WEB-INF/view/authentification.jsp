<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 09/03/2018
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/header.jsp"/>
<script type="text/javascript" src="js/connexion.js" defer></script>
<div class="container auth">
    <div class="card">
        <div class="title">
            Connexion
        </div>
        <div class="form-wrapper">
            <div class="form">
                <div class="inputs">
                    <label>
                        <input id="email" class="text-input" type="text" name="email" placeholder="Email"/>
                    </label>
                    <label>
                        <input id="password" class="text-input" type="password" name="password" placeholder="Mot de passe"/>
                    </label>
                </div>

                <button class="button" onclick="connect()">Me connecter</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>

