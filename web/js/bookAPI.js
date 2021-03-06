function reloadBooksList(callback) {
    $.post("/get_all_books", function (data) {
        console.log(data.books);
        updateBooksList(data.books);
        callback();
    }, "json");
}

function updateBooksList(data) {
    var list_item_wrapper = $('.list-item-wrapper');
    list_item_wrapper.empty();
    for (var book in data) {
        var list_item = $('<div class="list-item" data-search="' + data[book].book.title + '" data-book-id=' + data[book].book.id + '>' +
                '<div class="list-item-header">' +
                    '<p>' + data[book].book.title + '</p>' +
                '</div>' +
                '<div class="list-item-content">' +
                    '<div>' +
                        '<p>' + data[book].book.author.firstname + ' ' + data[book].book.author.lastname + '</p>' +
                    '</div>' +
                    '<div class="actions">' +
                        '<p class="unavailable_label hidden"> Indisponible </p>' +
                        '<button class="icon-button black loan" onclick="loanBook(' + data[book].book.id + ')">' +
                            '<i class="material-icons">shopping_cart</i>' +
                        '</button>' +
                        '<button class="icon-button red remove" onclick="removeBook(' + data[book].book.id + ')">' +
                            '<i class="material-icons">delete</i>' +
                        '</button>' +
                    '</div>' +
                '</div>'+
            '</div>');

        if(!data[book].is_available) {
            list_item.find(".actions .unavailable_label").removeClass("hidden");
            list_item.find(".actions .icon-button.loan").addClass("hidden");
        }

        if(sessionStorage.getItem('isAdmin') === "false") {
            list_item.find(".actions .icon-button.remove").addClass("hidden");
        }

        console.log(list_item.find(".actions .icon-button.remove"));

        list_item_wrapper.append(list_item);
    }

}

function showAddBookModal() {
    $('#add-book-modal').removeClass('hidden');
    reloadAuthors();

}

function hideAddBookModal() {
    $('#add-book-modal').addClass('hidden');

}

function showAddAuthorForm() {
    $('#author-form').removeClass('hidden');
}

function hideAddAuthorForm() {
    $('#author-form').addClass('hidden');
}

function updateSelectAuthors(authors) {
    var select = $('#add-book-author-select');
    select.empty();
    select.append($('<option selected>Sélectionnez un auteur</option>'));

    for (var author in authors) {
        var option = $('<option></option>');
        option.val(authors[author].id);
        option.text(authors[author].firstname + " " + authors[author].lastname);

        select.append(option);
    }
}

function reloadAuthors() {
    var test = $.post("/get_all_authors", function (data) {
        console.log(data.authors);
        updateSelectAuthors(data.authors);
    }, "json");
}

function insertAuthor() {
    var firstname_input = $('#author-firstname');
    var lastname_input = $('#author-lastname');
    var birthdate_input = $('#author-birthdate');
    var nationality_input = $('#author-nationality');

    var data = {
        author: {
            firstname: firstname_input.val(),
            lastname: lastname_input.val(),
            birthdate: birthdate_input.val(),
            nationality: nationality_input.val(),
        }
    };

    $.post("/insert_author", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        switch (response.error_code) {
            case 0:
                reloadAuthors();
                hideAddAuthorForm();
                showSnackbar("Auteur ajouté");
                break;
            case -1:
                $('#error-author-form').text("Une erreur s'est produite lors de l'ajout de l'auteur");
                break;
            case -2:
                $('#error-author-form').text("Veuillez renseigner une date correcte");
                break;
            case -3:
                $('#error-author-form').text("Vous n'avez pas accès à ce contenu");
                break;
            default:
                $('#error-author-form').text("Une erreur inattendue s'est produite");
                break;

        }
    });


}

function insertBook() {
    var title_input = $('#title-input');
    var author_input = $('#add-book-author-select');
    var copies_input = $('#copies-input');

    var data = {
        book: {
            title: title_input.val(),
            authorid: author_input.val(),
            copies: copies_input.val(),
        }
    };

    $.post("/insert_book", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        var error_input = $('#error-add-book');
        switch(response.error_code) {
            case 0:
                console.log("success");
                reloadBooksList();
                hideAddBookModal();
                showSnackbar("Oeuvre ajoutée");
                break;
            case -1:
                error_input.text("Une erreur s'est produite lors de l'ajout de l'oeuvre");
                break;
            case -2:
                error_input.text("Entrer un nombre de copie supérieur à 0");
                break;
            case -3:
                error_input.text("Vous n'avez pas acces à ce contenu");
                break;
            default:
                error_input.text("Une erreur inattendue s'est produite");
                break;
        }
    });
}


function loanBook(book_id) {
    var data = {
        bookid: book_id
    };

    $.post("/loan_book_for_connected_user", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        switch(response.error_code) {
            case 0:
                console.log("success");
                reloadBooksList();
                showSnackbar("Oeuvre empruntée");
                break;
            case -1:
                showSnackbar("Une erreur s'est produite lors de l'emprunt de l'oeuvre");
                break;
            case -2:
                showSnackbar("Tous les exemplaires sont déjà empruntés");
                break;
            case -3:
                showSnackbar("Vous n'avez pas acces à ce contenu");
                break;
            default:
                showSnackbar("Une erreur inattendue s'est produite");
                break;

        }
    });
}

function removeBook(book_id) {
    var data = {
        bookid: book_id
    };

    $.post("/remove_book", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        console.log(response);
        switch(response.error_code) {
            case 0:
                console.log("success");
                reloadBooksList();
                showSnackbar("Oeuvre supprimée");

                break;
            case -1:
                showSnackbar("Une erreur s'est produite lors de la suppression de l'oeuvre");
                break;
            case -2:
                showSnackbar("Ce livre est encore emprunté, attendez que toutes les copies soient retournées");
                break;
            case -3:
                showSnackbar("Vous n'avez pas acces à ce contenu");
                break;
            default:
                showSnackbar("Une erreur inattendue s'est produite");
                break;

        }
    });
}

function filterList() {
    var list_items = $(".list-item");
    var search_input = $("#book-search-input").val().toLowerCase();
    list_items.removeClass("hidden");
    list_items.each(function(index, list_item) {
        console.log(list_item);
        var data = $(list_item).data("search");
        if(data.toLowerCase().indexOf(search_input) < 0) {
            $(list_item).addClass("hidden");
        }
    })
}

$(document).ready(function () {
    reloadBooksList();
});

