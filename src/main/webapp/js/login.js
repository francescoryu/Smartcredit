/*
 * Verwaltet login von User
 * @author: Francesco Ryu
 */

$(document).ready(function () {

    $("#loginForm").submit(sendLogin);
});

function sendLogin(form) {
    form.preventDefault();
    $
        .ajax({
            url: "./resource/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function () {
            window.location.href = "/Smartcredit-1.0/select.html";

        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })
}



function sendLogoff() {
    $.ajax()
}