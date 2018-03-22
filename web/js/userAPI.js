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
    for (var users in data) {
        var list_item = $('<div class="list-item" data-book-id=' + data[book].id + '>' +
            '<div class="list-item-header">' +
            '<p>' + data[book].title + '</p>' +
            '</div>' +
            '<div class="list-item-content">' +
            '<div>' +
            '<p>' + data[book].author.firstname + ' ' + data[book].author.lastname + '</p>' +
            '</div>' +
            '<div>' +
            '<button class="icon-button black" onclick="loanBook(' + data[book].id + ')">' +
            '<i class="material-icons">cart</i>' +
            '</button>' +
            '</div>' +
            '</div>'+
            '</div>');

        list_item_wrapper.append(list_item);
    }}

    function showAddUserModal() {
        $('#add-book-modal').removeClass('hidden');
        reloadAuthors();

    }

    function hideAddUserModal() {
        $('#add-book-modal').addClass('hidden');

    }