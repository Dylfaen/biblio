$(document).ready(function () {
    reloadUsersList();
});

function reloadUsersList(callback) {
    $.post("/get_all_users", function (data) {
        console.log(data.users);
        updateUsersList(data.users);
        callback();
    }, "json");
}

function updateUsersList(data) {

    var list_item_wrapper = $('.list-item-wrapper');
    list_item_wrapper.empty();
    for (var user in data) {
        console.log(data[user].user);
        var list_item = $('<div class="list-item" data-user-id=' + data[user].user.id + '>' +
            '<div class="list-item-header">' +
            '<p>' + data[user].user.username + '</p>' +
            '</div>' +
            '<div class="list-item-content">' +
            '<div>' +
            '<p>' + data[user].user.lastname + ' ' + data[user].user.firstname + '</p>' +
            '</div>' +
            '<div class="actions">' +
            '<button class="icon-button red remove" onclick="removeUser(' + data[user].user.id + ')">' +
            '<i class="material-icons">delete</i>' +
            '</button>' +
            '</div>' +
            '</div>' +
            '</div>');

        list_item_wrapper.append(list_item);
    }
}

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
    var isAdmin = $('#is-admin-input');




    var data = {
        user: {
            username: identifiant.val(),
            password: password.val(),
            lastname: name.val(),
            firstname: prenom.val(),
            birthdate: birthdate.val(),
            address: address.val(),
            isAdmin: isAdmin.is(":checked")
        }
    };



    console.log("user");
    console.log(data.user);
    $.post("/insert_user", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        var error_input = $('#error-add-user');
        switch(response.error_code) {
            case 0:
                console.log("success");
                reloadUsersList();
                hideAddUserModal();
                showSnackbar("Utilisateur ajouté")
                break;
            case -1:
                error_input.text("Une erreur s'est produite lors de l'ajout");
                break;
            case -2:
                error_input.text("Veuillez renseigner une date correcte");
                break;
            case -3:
                error_input.text("Vous n'avez pas acces à ce contenu");
                break;
            case -4:
                error_input.text("Ce nom d'utilisateur existe déjà");
                break;
            default:
                error_input.text("Vous n'avez pas acces à ce contenu");
                break;

        }
    });
}

function removeUser(user_id) {
    var data = {
        userid: user_id
    };

    $.post("/remove_user", data, function (response) {
        console.log(response);
        response = JSON.parse(response);
        switch(response.error_code) {
            case 0:
                console.log("success");
                reloadUsersList();
                showSnackbar("Utilisateur supprimé")
                break;
            case -1:
                showSnackbar("Une erreur s'est produite lors de la suppression de l'utilisateur");
                break;
            case -2:
                showSnackbar("Cet utilisateur à des emprunts en cours, attendez que toutes les copies soient retournées");
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