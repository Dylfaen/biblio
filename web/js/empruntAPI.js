
function request() {
    var test = $.post( "/emprunt_api", function( data ) {
        console.log(data.oeuvres);
        update( data.oeuvres );
    }, "json");
}

function update(data) {
        var list_item_wrapper = $('.list-item-wrapper');
        list_item_wrapper.empty();
        for(var oeuvre in data) {
            var list_item = $('<div class="list-item"></div>');
            list_item.append('<p>' + data[oeuvre].titre + '</p>');
            list_item_wrapper.append(list_item);
        }

}

$(document).ready(function() {
    request();
});

