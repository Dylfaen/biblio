<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 18/03/2018
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/header.jsp"/>
<script type="text/javascript" src="js/userAPI.js" defer></script>
<div class="container list">
    <div class="list-wrapper">
        <div class="list-header">
            <div class="search-wrapper">
                <input type="text" class="search-input" placeholder="Rechercher"/>
            </div>
            <div class="action-wrapper">
                <c:if test="${user.isAdmin()}">
                    <button class="add-button icon-button white" onclick="showAddUserModal()">
                        <i class="material-icons">add</i>
                    </button>
                </c:if>
                <button class="reload-button icon-button white" onclick="reloadUsersList()">
                    <i class="material-icons">refresh</i>
                </button>
            </div>
        </div>
        <div class="list-item-wrapper">

        </div>
    </div>

    <div class="modal-wrapper hidden" id="add-user-modal">
        <div class="modal">
            <div class="modal-header">
                <div class="title">Ajouter Utilisateur
                </div>
                <button class="close-button icon-button white" onclick="hideAddUserModal()">
                    <i class="material-icons">close</i>
                </button>
            </div>
            <div class="modal-content">
                <div class="form-wrapper">
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
                                    <input id="password-input" class="text-input" type="password" name="password-input"
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
                        <div class="row">
                            <div class="col-6 input">
                                <label>
                                    <input id="birthdate-input" class="text-input" type="date" name="birthdate-input"/>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 input">
                                <label>
                                    <input id="address-input" class="text-input" type="text" name="address-input" placeholder="Adresse"/>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                                <label>
                                    <p>Administrateur<input id="isAdmin" class="text-input" type="checkbox" name="isAdmin" value="Admin"/></p>
                                </label>
                        </div>
                        <p class="error" id="error-add-user"></p>
                        <div class="center">
                            <button type="submit" class="button" onclick="insertUser()">
                                <i class="material-icons">check</i>
                                <p>Ajouter</p>
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<jsp:include page="includes/footer.jsp"/>


