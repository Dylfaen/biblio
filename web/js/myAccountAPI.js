function reloadMyAccount() {
    $.post("/get_connected_user", function (data) {
        console.log(data);
        updateMyAccount(data);
    }, "json");
}

function updateMyAccount(data) {
    var name_field = $('#name-field');
    var birthdate_field = $('#birthdate-field');
    var birthdate_edit_field = $('#birthdate-edit-field');
    var address_field = $('#address-field');
    var address_edit_field = $('#address-edit-field');
    var username_field = $('#username-field');
    var username_edit_field = $('#username-edit-field');
    var password_field = $('#password-field');

    name_field.text(data.firstname + " " + data.lastname);
    birthdate_field.text(data.birthdate);
    birthdate_edit_field.val(data.birthdate);
    address_field.text(data.address);
    address_edit_field.val(data.address);
    username_field.text(data.username);
    username_edit_field.val(data.username);

    console.log(data);
}

function edit(field) {
    var data = {};
    var form;
    var input_field;
    var info_field;
    var button;
    var validate_button;
    var cancel_button;

    switch (field) {
        case "birthdate":
            form = $('#birthdate-form');
            button = $('#birthdate-edit-button');
            input_field = $('#birthdate-edit-field');
            info_field = $('#birthdate-field');
            validate_button = $('#birthdate-edit-validate-button');
            cancel_button = $('#birthdate-edit-cancel-button');

            form.removeClass("hidden");
            info_field.addClass("hidden");


            validate_button.click(function() {
                var date = input_field.val();
                data.birthdate = date;
                console.log(input_field);
                editRequest(data, function() {
                    form.addClass('hidden');
                    info_field.removeClass("hidden");

                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                info_field.removeClass("hidden");

                input_field.val("");

            });
            break;
        case "address":
            form = $('#address-form');
            button = $('#address-edit-button');
            input_field = $('#address-edit-field');
            info_field = $('#address-field');
            validate_button = $('#address-edit-validate-button');
            cancel_button = $('#address-edit-cancel-button');

            form.removeClass("hidden");
            info_field.addClass("hidden");


            validate_button.click(function() {
                data.address = input_field.val();
                editRequest(data, function() {
                    form.addClass('hidden');
                    info_field.removeClass("hidden");

                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                info_field.removeClass("hidden");

                input_field.val("");

            });
            break;
        case "username":
            form = $('#username-form');
            button = $('#username-edit-button');
            input_field = $('#username-edit-field');
            info_field = $('#username-field');
            validate_button = $('#username-edit-validate-button');
            cancel_button = $('#username-edit-cancel-button');

            form.removeClass("hidden");
            info_field.addClass("hidden");


            validate_button.click(function() {
                data.username = input_field.val();
                editRequest(data, function() {
                    form.addClass('hidden');
                    info_field.removeClass("hidden");

                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                info_field.removeClass("hidden");

                input_field.val("");

            });
            break;
        case "password":
            form = $('#password-form');
            button = $('#password-edit-button');
            input_field = $('#password-edit-field');
            info_field = $('#password-field');
            validate_button = $('#password-edit-validate-button');
            cancel_button = $('#password-edit-cancel-button');

            form.removeClass("hidden");
            info_field.addClass("hidden");


            validate_button.click(function() {
                data.password = input_field.val();
                editRequest(data, function() {
                    form.addClass('hidden');
                    info_field.removeClass("hidden");

                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                info_field.removeClass("hidden");

                input_field.val("");

            });
            break;
    }
}

function editRequest(data, callback) {
    $.post("/edit_user_field", data, function (response) {
        console.log(response);
        switch(response.error_code) {
            case 0:
                callback();
                reloadMyAccount(response);
                showSnackbar("Profil édité avec succès");
                break;
            case -1:
                showSnackbar("Une erreur s'est produite lors de l'édition de vos données");
                break;
            case -2:
                showSnackbar("Vous tentez d'éditer un champ inexistant");
                break;
            case -3:
                showSnackbar("Vous n'avez pas accès à ce contenu");
                break;
            case -4:
                showSnackbar("Ce nom d'utilisateur est déjà pris");
                break;
            case -5:
                showSnackbar("La date est incorrecte");
                break;
            default:
                showSnackbar("Une erreur inattendue s'est produite")
        }

    }, "json");
}


$(document).ready(function () {
    reloadMyAccount();
});

