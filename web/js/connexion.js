function connect() {

    var email = $('.text-input#email').val();
    var password = $('.text-input#password').val();

    var data = {
        email: email,
        password: password
    };

    console.log(data);



    var test = $.post( "/checklogin", data);

    test.done(function(data) {
        console.log(data);

    });

}