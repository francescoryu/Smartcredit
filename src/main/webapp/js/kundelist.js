/**
 * view-controller für kundelist.html
 * @author Francesco Ryu
 */
document.addEventListener("DOMContentLoaded", () => {
    readKunde();
});

/**
 * liest alle Kunden
 */
function readKunde() {
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
            showKundeList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * zeigt die Kunden als Tabelle an
 * @param data
 */
function showKundeList(data) {
    let tBody = document.getElementById("kundelist");
    data.forEach(kunde => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = kunde.vorName;
        row.insertCell(-1).innerHTML = kunde.nachName;
        row.insertCell(-1).innerHTML = kunde.alter;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editKunde";
        button.setAttribute("data-kundeuuid", kunde.kundeUUID);
        button.addEventListener("click", editKunde);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteKunde";
        button.setAttribute("data-kundeuuid",kunde.kundeUUID);
        button.addEventListener("click", deleteKunde);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * leitet den User zu kundeedit.html um
 * @param event  the click-event
 */
function editKunde(event) {
    const button = event.target;
    const kundeUUID = button.getAttribute("data-kundeuuid");
    window.location.href = "./kundeedit.html?uuid=" + kundeUUID;
}

/**
 * löscht einen Kunden
 * @param event  the click-event
 */
function deleteKunde(event) {
    const button = event.target;
    const kundeUUID = button.getAttribute("data-kundeuuid");

    fetch("./resource/kunde/delete?uuid=" + kundeUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./kundelist.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}