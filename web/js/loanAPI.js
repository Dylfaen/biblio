function reloadLoansList(callback) {
    $.post("/get_loans_for_user", function (data) {
        console.log(data.loans);
        updateLoansList(data.loans);
        callback()
    }, "json");
}

function updateLoansList(data) {
    var list_item_wrapper = $('.list-item-wrapper');
    list_item_wrapper.empty();
    data.sort(function(a, b) {
        return a.isReturned - b.isReturned;
    });
    for (var loan in data) {

            var list_item = $(
                '<div class="list-item" data-search="' + data[loan].copy.book.title + '" data-book-id=' + data[loan].id + '>' +
                    '<div class="list-item-header">' +
                        '<p>' + data[loan].copy.book.title+ '</p>' +
                    '</div>' +
                    '<div class="list-item-content row">' +
                        '<div class="col-10">' +
                            '<p>' + data[loan].copy.book.author.firstname + ' ' + data[loan].copy.book.author.lastname + '</p>' +
                            '<p> Date d\'emprunt : ' + data[loan].date + '</p>' +
                        '</div>' +
                        '<div class="actions col-2">' +
                            '<p class="returned_label black hidden"> Retourné <i class="material-icons green">done</i></p>' +
                            '<button class="icon-button black return" onclick="returnCopy(' + data[loan].id + ')">' +
                                '<i class="material-icons">remove_shopping_cart</i>' +
                            '</button>' +
                        '</div>' +
                    '</div>'+
                '</div>');

        if(data[loan].isReturned) {
            list_item.find(".actions .returned_label").removeClass("hidden");
            list_item.find(".actions .icon-button.return").addClass("hidden");
        }

        list_item_wrapper.append(list_item);
    }

}

function returnCopy(id) {
    var data = {
        loanid: id
    };

    $.post("/return_copy", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        switch (response.error_code) {
            case 0:
                reloadLoansList();
                showSnackbar("Exemplaire retourné");
                break;
            case -1:
                showSnackbar("Une erreur s'est produite lors du retour de l'oeuvre");
                break;
            case -2:
                showSnackbar("Cet emprunt ne vous appartient pas");
                break;
            case -3:
                showSnackbar("Vous n'avez pas acces à ce contenu");
                break;
            default:
                showSnackbar("Une erreur inattendue s'est produite");
                break;

        }
        if (response.error_code === -1) {
            console.log("erreur -1");
        } else {
            console.log("success");
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
    reloadLoansList();
});

