
function reloadBooksList() {
    $.post( "/get_all_books", function( data ) {
        console.log(data.books);
        updateBooksList( data.books );
    }, "json");
}

function updateBooksList(data) {
        var list_item_wrapper = $('.list-item-wrapper');
        list_item_wrapper.empty();
        for(var book in data) {
            var list_item = $('<div class="list-item"></div>');
            list_item.append('<p>' + data[book].title + '</p>');
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

    for(var author in authors) {
        var option = $('<option></option>');
        option.val(authors[author].id);
        option.text(authors[author].firstname + " " + authors[author].lastname)

        select.append(option);
    }
}

function reloadAuthors() {
    var test = $.post( "/get_all_authors", function( data ) {
        console.log(data.authors);
        updateSelectAuthors( data.authors );
    }, "json");
}

function insertAuthor() {
    var firstname_input = $('#author-firstname');
    var lastname_input = $('#author-lastname');
    var birthdate_input = $('#author-birthdate');
    var nationality_input = $('#author-nationality');

    var birthdate_array = birthdate_input.val().split("-");

    var birthdate = {
        year: birthdate_array[0],
        month: birthdate_array[1],
        day: birthdate_array[2],
    };

    var data = {
      author: {
          firstname: firstname_input.val(),
          lastname: lastname_input.val(),
          birthdate: birthdate,
          nationality: nationality_input.val(),
      }
    };

    $.post( "/insert_author", data, function( response) {
        console.log(response);
        response = JSON.parse(response);
        if(response.error_code === -1 ) {
            $('#error-author-form').text("Une erreur s'est produite lors de l'ajout de l'auteur");
            console.log("erreur -1");

        } else if(response.error_code === -2) {
            $('#error-author-form').text("Veuillez renseigner une date correcte");
            console.log("erreur -2");

        } else {
            console.log("success");
            reloadAuthors();
            hideAddAuthorForm();
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

    $.post( "/insert_book", data, function( response) {
        console.log(response);
        response = JSON.parse(response);
        if(response.error_code === -1 ) {
            $('#error-add-book').text("Une erreur s'est produite lors de l'ajout de l'oeuvre");
            console.log("erreur -1");

        } else if(response.error_code === -2) {
            $('#error-add-book').text("Entrer un nombre de copie supérieur à 0");
            console.log("erreur -2");

        } else {
            console.log("success");
            reloadBooksList();
            hideAddBookModal();
        }
    });
}


$(document).ready(function() {
    reloadBooksList();
});
