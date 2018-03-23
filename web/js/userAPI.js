$(document).ready(function () {
    reloadUsersList();
});

function reloadUsersList() {
    $.post("/get_all_users", function (data) {
        console.log(data.users);
        updateUsersList(data.users);
    }, "json");
}

function updateUsersList(data) {
    var list_item_wrapper = $('.list-item-wrapper');
    list_item_wrapper.empty();
    for (var user in data) {
        var list_item = $('<div class="list-item" data-user-id=' + data[user].id + '>' +
            '<div class="list-item-header">' +
            '<p>' + data[user].username + '</p>' +
            '</div>' +
            '<div class="list-item-content">' +
            '<div>' +
            '<p>' + data[user].lastname + ' ' + data[user].firstname + '</p>' +
            '</div>'+
            '</div>'+
            '</div>');

        list_item_wrapper.append(list_item);
    }}

    function showAddUserModal() {
        $('#add-user-modal').removeClass('hidden');

    }

    function hideAddUserModal() {
        $('#add-user-modal').addClass('hidden');

    }


    /*----*/
function insertUser() {
    var title_input = $('#title-input');
    var author_input = $('#add-book-author-select');
    var copies_input = $('#copies-input');

    // var data = {
    //     user: {
    //         username: title_input.val(),
    //         password: author_input.val(),
    //         lastname: copies_input.val(),
    //         firstname: ;
    //         birthdate:
    //         isAdmin: .val();
    //     }
    // }

    $.post("/insert_user", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        if (response.error_code === -1) {
            $('#error-add-user').text("Une erreur s'est produite lors de l'ajout");
            console.log("erreur -1");

        } else if (response.error_code === -2) {
            $('#error-add-user').text("XXXXXX");
            console.log("erreur -2");

        } else {
            console.log("success");
            reloadUsersList();
            hideAddUserModal();
        }
    });
}