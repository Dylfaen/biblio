<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandre
  Date: 09/03/2018
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/header.jsp"/>
<script defer>sessionStorage.setItem('isAdmin', <c:out value="${user.isAdmin()}">false</c:out>)</script>
<script type="text/javascript" src="js/bookAPI.js" defer></script>
<div class="container list">
    <div class="list-wrapper">
        <div class="list-header">
            <div class="search-wrapper">
                <input type="text" class="search-input" id="book-search-input" oninput="filterList()" placeholder="Rechercher par titre"/>
            </div>
            <div class="action-wrapper">
                <c:if test="${user.isAdmin()}">
                    <button class="add-button icon-button white" onclick="showAddBookModal()">
                        <i class="material-icons">add</i>
                    </button>
                </c:if>
                <button class="reload-button icon-button white" onclick="reloadBooksList()">
                    <i class="material-icons">refresh</i>
                </button>
            </div>
        </div>
        <div class="list-item-wrapper">

        </div>
    </div>

    <div class="modal-wrapper hidden" id="add-book-modal">
        <div class="modal">
            <div class="modal-header">
                <div class="title">Ajouter une oeuvre</div>
                <button class="close-button icon-button white" onclick="hideAddBookModal()">
                    <i class="material-icons">close</i>
                </button>
            </div>
            <div class="modal-content">
                <div class="form-wrapper">
                    <div class="form">
                        <div class="row">
                            <div class="col-12 input">
                                <label>
                                    <input id="title-input" class="text-input" type="text" name="title-input"
                                           placeholder="Titre"/>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6 input">
                                <label>
                                    <select id="add-book-author-select" value="">
                                        <option>Sélectionnez un auteur</option>
                                    </select>
                                </label>
                            </div>
                            <div class="col-6 input">
                                <button class="icon-button black" onclick="showAddAuthorForm()">
                                    <i class="material-icons">add</i>
                                    <p>Créer un auteur</p>
                                </button>
                            </div>
                        </div>
                        <div class="form-wrapper hidden" id="author-form">
                            <div class="form">
                                <div class="row">
                                    <div class="col-6 input">
                                        <label>
                                            <input id="author-firstname" class="text-input" type="text"
                                                   name="author-firstname"
                                                   placeholder="Prénom"/>
                                        </label>
                                    </div>
                                    <div class="col-6 input">
                                        <label>
                                            <input id="author-lastname" class="text-input" type="text"
                                                   name="author-firstname"
                                                   placeholder="Nom"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6 input">
                                        <label>
                                            <input id="author-birthdate" class="text-input" type="date"
                                                   name="author-birthdate"
                                                   placeholder="Date de naissance"/>
                                        </label>
                                    </div>
                                    <div class="col-6 input">
                                        <label>
                                            <input id="author-nationality" class="text-input" type="text"
                                                   name="author-nationality"
                                                   placeholder="Nationalité"/>
                                        </label>
                                    </div>
                                </div>
                                <p class="error" id="error-author-form"></p>
                                <div class="row">
                                    <div class="col-6 input">
                                        <button class="icon-button black" onclick="insertAuthor()">
                                            <i class="material-icons">check</i>
                                            <p>Valider</p>
                                        </button>
                                    </div>

                                    <div class="col-6 input">
                                        <button class="icon-button black" onclick="hideAddAuthorForm()">
                                            <i class="material-icons">close</i>
                                            <p>Annuler</p>
                                        </button>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-6 input">
                                <label>
                                    <input id="copies-input" class="text-input" type="number" min=1 name="copies-input"
                                           placeholder="Nombre de copies"/>
                                </label>
                            </div>
                        </div>
                        <p class="error" id="error-add-book"></p>
                        <div class="center">
                            <button type="submit" class="button" onclick="insertBook()">
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
</body>
</html>
