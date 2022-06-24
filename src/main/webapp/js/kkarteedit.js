/**
 * view-controller for bookedit.html
 * @author Marcel Suter
 */
document.addEventListener("DOMContentLoaded", () => {
    readKunden();
    readKKarte();

    document.getElementById("bookeditForm").addEventListener("submit", saveKKarte);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
 */
function saveKKarte(event) {
    event.preventDefault();

    const kkarteForm = document.getElementById("kkarteeditForm");
    const formData = new FormData(kkarteForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/kkarte/";
    const kkarteUUID = getQueryParam("uuid");
    if (kkarteUUID == null) {
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
function readKKarte() {
    const kkarteUUID = getQueryParam("uuid");
    fetch("./resource/kkarte/read?uuid=" + kkarteUUID)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showKKarte(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a book
 * @param data  the book-data
 */
function showKKarte(data) {
    document.getElementById("kkarteUUID").value = data.kkarteUUID;
    document.getElementById("institut").value = data.institut;
    document.getElementById("kartenNummer").value = data.kartenNummer;
    document.getElementById("kunde").value = data.kundeUUID;
}

/**
 * reads all publishers as an array
 */
function readKunden() {

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
}

/**
 * shows all publishers as a dropdown
 * @param data
 */
function showKunden(data) {
    let dropdown = document.getElementById("kunde");
    data.forEach(publisher => {
        let option = document.createElement("option");
        option.text = kunde.kunde;
        option.value = kunde.kundeUUID;
        dropdown.add(option);
    })
}

/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./smartcredit.html";
}