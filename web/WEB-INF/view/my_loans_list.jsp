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
<script type="text/javascript" src="js/loanAPI.js" defer></script>
<div class="container list">
    <div class="list-wrapper">
        <div class="list-header">
            <div class="search-wrapper">
                <input type="text" class="search-input" id="book-search-input" oninput="filterList()" placeholder="Rechercher par titre"/>
            </div>
            <div class="action-wrapper">
                <button class="reload-button icon-button white" onclick="reloadLoansList(function(){showSnackbar('Liste mise à jour')})">
                    <i class="material-icons">refresh</i>
                </button>
            </div>
        </div>
        <div class="list-item-wrapper">

        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>

