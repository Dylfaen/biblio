


function request() {
    var test = $.post( "/emprunt_api", function( data ) {
        console.log( data );
    }, "json");
}