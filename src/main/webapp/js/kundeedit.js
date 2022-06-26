/**
 * view-controller for bookedit.html
 * @author Marcel Suter
 */
document.addEventListener("DOMContentLoaded", () => {
    readKunde();

    document.getElementById("kundeeditForm").addEventListener("submit", saveKunde);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
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
 * reads a book
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
 * show the data of a book
 * @param data  the book-data
 */
function showKunde(data) {
    document.getElementById("vorName").value = data.vorName;
    document.getElementById("nachName").value = data.nachName;
    document.getElementById("alter").value = data.alter;
}

/**
 * reads all publishers as an array
 */
/*function readKunden() {

    fetch("./resource/kunde/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showKunden(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}*/

/**
 * shows all publishers as a dropdown
 * @param data
 */
/*function showKunden(data) {
    let dropdown = document.getElementById("kunde");
    data.forEach(kunde => {
        let option = document.createElement("option");
        option.text = kunde.vorName + " " + kunde.nachName;
        option.value = kunde.kundeUUID;
        dropdown.add(option);
    })
}*/

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./kundelist.html";
}