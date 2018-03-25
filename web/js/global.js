function showSnackbar(message) {
    var snackbar_wrapper = $('<div class="snackbar-wrapper"></div>');
    var snackbar = $('<div class="snackbar" id="snackbar"></div>\n');
    snackbar_wrapper.append(snackbar);
    $('body').append(snackbar_wrapper);
    snackbar.text(message);
    snackbar.addClass('show');

    setTimeout(function() {
        snackbar.removeClass('show');
        snackbar_wrapper.remove();
    }, 3000);

}