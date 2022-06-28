/**
 * view-controller für kkarteedit.html
 * @author Francesco Ryu
 */
document.addEventListener("DOMContentLoaded", () => {
    readKunden();
    readKKarte();

    document.getElementById("kkarteeditForm").addEventListener("submit", saveKKarte);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * Speichert die Daten für eine Kreditkarte
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
 * liest eine Kreditkarte
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
 * Zeigt die Daten einer Kreditkarte an
 * @param data
 */
function showKKarte(data) {
    document.getElementById("kkarteUUID").value = data.kkarteUUID;
    document.getElementById("institut").value = data.institut;
    document.getElementById("kartenNummer").value = data.kartenNummer;
    document.getElementById("kunde").value = data.kundeUUID;
}

/**
 * Liest alle Kunden
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
 * Zeigt alle Kunden als Dropdown an
 * @param data
 */
function showKunden(data) {
    let dropdown = document.getElementById("kunde");
    data.forEach(kunde => {
        let option = document.createElement("option");
        option.text = kunde.vorName + " " + kunde.nachName;
        option.value = kunde.kundeUUID;
        dropdown.add(option);
    })
}

/**
 * leitet dn User zu kkartelist.html um
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./kkartelist.html";
}