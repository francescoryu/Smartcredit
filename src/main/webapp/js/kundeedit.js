/**
 * view-controller für kundeedit.html
 * @author Francesco Ryu
 */
document.addEventListener("DOMContentLoaded", () => {
    readKunde();

    document.getElementById("kundeeditForm").addEventListener("submit", saveKunde);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * Speichert die Daten für einen Kunden
 */
function saveKunde(event) {
    event.preventDefault();
    window.location.href = "./kundelist.html";

    const kundeForm = document.getElementById("kundeeditForm");
    const formData = new FormData(kundeForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/kunde/";
    const kundeUUID = getQueryParam("uuid");
    if (kundeUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * liest einen Kunden
 */
function readKunde() {
    const kundeUUID = getQueryParam("uuid");
    fetch("./resource/kunde/read?uuid=" + kundeUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showKunde(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * Zeigt die daten für einen Kunden an
 * @param data
 */
function showKunde(data) {
    document.getElementById("vorName").value = data.vorName;
    document.getElementById("nachName").value = data.nachName;
    document.getElementById("alter").value = data.alter;
}

/**
 * Leitet den user zu der Kundenliste um
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./kundelist.html";
}