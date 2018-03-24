(document).ready(function () {
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
        '<div class="actions">' +
            '<button class="icon-button red remove" onclick="removeUser(' + data[user].user.id + ')">' +
            '<i class="material-icons">delete</i>' +
            '</button>' +
            '</div>' +
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
    var identifiant = $('#identifiant-input');
    var password = $('#password-input');
    var name = $('#nom-input');
    var prenom = $('#prenom-input');
    var birthdate = $('#birthdate-input');
    var address = $('#address-input');
    var isAdmin = $('#isAdmin-input').attr('checked',true);

    var birthdate_array = birthdate.val().split("-");
    var birthdateVar = {
        year: birthdate_array[0],
        month: birthdate_array[1],
        day: birthdate_array[2],
    };


    var data = {
         user: {
             username: identifiant.val(),
             password: password.val(),
             lastname: name.val(),
             firstname: prenom.val(),
             birthdate: birthdateVar,
             address: address.val(),
             isAdmin: isAdmin.val()
         }
     }

     console.log("debut insertion")
    $.post("/insert_user", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        if (response.error_code === -1) {
            $('#error-add-user').text("Une erreur s'est produite lors de l'ajout");
            console.log("erreur -1");

        } else if (response.error_code === -2) {
            $('#error-add-user').text("Veuillez renseigner une date correcte");
            console.log("erreur -2");

        } else {
            console.log("success");
            reloadUsersList();
            hideAddUserModal();
        }
    });
    function removeUser(user_id) {
        var data = {
            userid: user_id
        };

        $.post("/remove_user", data, function (response) {
            console.log(response);
            response = JSON.parse(response);
            if (response.error_code === -1) {
                $('#error-del-user').text("Une erreur s'est produite lors de la suppression de l'utilisateur");
                console.log("erreur -1");
            } else {
                console.log("success");
                reloadUsersList();
            }
        });
    }
}