function reloadMyAccount() {
    $.post("/get_connected_user", function (data) {
        console.log(data);
        updateMyAccount(data);
    }, "json");
}

function updateMyAccount(data) {
    var name_field = $('#name-field');
    var birthdate_field = $('#birthdate-field');
    var address_field = $('#address-field');
    var username_field = $('#username-field');
    var password_field = $('#password-field');

    name_field.text(data.firstname + " " + data.lastname);
    birthdate_field.text(data.birthdate);
    address_field.text(data.address);
    username_field.text(data.username);

    console.log(data);
}

function edit(field) {
    var data = {};
    var form;
    var input_field;
    var button;
    var validate_button;
    var cancel_button;

    switch (field) {
        case "birthdate":
            form = $('#birthdate-form')
            button = $('#birthdate-edit-button');
            input_field = $('#birthdate-edit-field');
            validate_button = $('#birthdate-edit-validate-button');
            cancel_button = $('#birthdate-edit-cancel-button');

            form.removeClass("hidden");

            validate_button.click(function() {
                var date = input_field.val();
                data.birthdate = {
                    year: date.split('-')[0],
                    month: date.split('-')[1],
                    day: date.split('-')[2]
                };
                editRequest(data, function() {
                    form.addClass('hidden');
                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                input_field.val("");

            });
            break;
        case "address":
            form = $('#address-form')
            button = $('#address-edit-button');
            input_field = $('#address-edit-field');
            validate_button = $('#address-edit-validate-button');
            cancel_button = $('#address-edit-cancel-button');

            form.removeClass("hidden");

            validate_button.click(function() {
                data.address = input_field.val();
                editRequest(data, function() {
                    form.addClass('hidden');
                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                input_field.val("");

            });
            break;
        case "username":
            form = $('#username-form')
            button = $('#username-edit-button');
            input_field = $('#username-edit-field');
            validate_button = $('#username-edit-validate-button');
            cancel_button = $('#username-edit-cancel-button');

            form.removeClass("hidden");

            validate_button.click(function() {
                data.username = input_field.val();
                editRequest(data, function() {
                    form.addClass('hidden');
                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                input_field.val("");

            });
            break;
        case "password":
            form = $('#password-form')
            button = $('#password-edit-button');
            input_field = $('#password-edit-field');
            validate_button = $('#password-edit-validate-button');
            cancel_button = $('#password-edit-cancel-button');

            form.removeClass("hidden");

            validate_button.click(function() {
                data.password = input_field.val();
                editRequest(data, function() {
                    form.addClass('hidden');
                    input_field.val("");
                });
            });

            cancel_button.click(function() {
                form.addClass('hidden');
                input_field.val("");

            });
            break;
    }
}

function editRequest(data, callback) {
    $.post("/edit_user_field", data, function (response) {
        console.log(response);
        callback();
        reloadMyAccount(response);
    }, "json");
}


$(document).ready(function () {
    reloadMyAccount();
});

