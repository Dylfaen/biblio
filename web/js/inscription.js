$(document).ready(function() {
    request();
});

function request() {
    var test = $.post( "/emprunt_api", function( data ) {
        console.log(data.oeuvres);
        update( data.oeuvres );
    }, "json");
}

function validate(){
    var reference = document.getElementById("reference").value;
}