<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 09/03/2018
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/header.jsp"/>
<script type="text/javascript" src="js/myAccountAPI.js" defer></script>
<div class="container my_account">
    <div class="content-holder">
        <div class="section row">
            <div class="title col-1">
                <i class="material-icons">account_circle</i>
            </div>
            <div class="content col-11">
                <div class="sub-section">
                    <p class="label">
                        Nom
                    </p>
                    <p class="info" id="name-field">
                    </p>
                </div>
                <div class="sub-section">
                    <p class="label">
                        Date de naissance
                        <button id="birthdate-edit-button" class="icon-button black" onclick="edit('birthdate')">
                            <i class="material-icons">edit</i>
                        </button>
                    </p>
                    <p class="info" id="birthdate-field">
                    </p>
                    <div class="form info hidden" id="birthdate-form">
                        <input class="input" type="date" id="birthdate-edit-field">
                        </input>
                        <button id="birthdate-edit-validate-button" class="icon-button">
                            <i class="material-icons">done</i>
                        </button>
                        <button id="birthdate-edit-cancel-button" class="icon-button">
                            <i class="material-icons">close</i>
                        </button>
                    </div>
                </div>
                <div class="sub-section">
                    <p class="label">
                        Adresse
                        <button id="address-edit-button" class="icon-button black" onclick="edit('address')">
                            <i class="material-icons">edit</i>
                        </button>
                    </p>
                    <p class="info" id="address-field">
                    </p>
                    <div class="form info hidden" id="address-form">
                        <input class="input" type="text" id="address-edit-field">
                        </input>
                        <button id="address-edit-validate-button" class="icon-button">
                            <i class="material-icons">done</i>
                        </button>
                        <button id="address-edit-cancel-button" class="icon-button">
                            <i class="material-icons">close</i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="section row">
            <div class="title col-1">
                <i class="material-icons">lock</i>
            </div>
            <div class="content col-11">
                <div class="sub-section">
                    <p class="label">
                        Identifiant
                        <button id="username-edit-button" class="icon-button black" onclick="edit('username')">
                            <i class="material-icons">edit</i>
                        </button>
                    </p>
                    <p class="info" id="username-field">
                    </p>
                    <div class="form info hidden" id="username-form">
                        <input class="input" type="password" id="username-edit-field">
                        </input>
                        <button id="username-edit-validate-button" class="icon-button">
                            <i class="material-icons">done</i>
                        </button>
                        <button id="username-edit-cancel-button" class="icon-button">
                            <i class="material-icons">close</i>
                        </button>
                    </div>
                </div>
                <div class="sub-section">
                    <p class="label">
                        Mot de passe
                        <button id="password-edit-button" class="icon-button black" onclick="edit('password')">
                            <i class="material-icons">edit</i>
                        </button>
                    </p>
                    <div class="form info hidden" id="password-form">
                        <input class="input" type="password" id="password-edit-field">
                        </input>
                        <button id="password-edit-validate-button" class="icon-button">
                            <i class="material-icons">done</i>
                        </button>
                        <button id="password-edit-cancel-button" class="icon-button">
                            <i class="material-icons">close</i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>

