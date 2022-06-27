/**
 * view-controller für KKartelist.html
 * @author Francesco Ryu
 */
document.addEventListener("DOMContentLoaded", () => {
    readKKarte();
});

/**
 * Liest alle KKarten
 */
function readKKarte() {
    fetch("./resource/kkarte/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showKKarteList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * Tabellarische Darstellung für die KKarten
 * @param data
 */
function showKKarteList(data) {
    let tBody = document.getElementById("kkartelist");
    data.forEach(kkarte => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = kkarte.kartenNummer;
        row.insertCell(-1).innerHTML = kkarte.kunde.vorName + " " + kkarte.kunde.nachName;
        row.insertCell(-1).innerHTML = kkarte.institut;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editKKarte";
        button.setAttribute("data-kkarteuuid", kkarte.kkarteUUID);
        button.addEventListener("click", editKKarte);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteKKarte";
        button.setAttribute("data-kkarteuuid",kkarte.kkarteUUID);
        button.addEventListener("click", deleteKKarte);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * link für kkarteedit.html
 * @param event  the click-event
 */
function editKKarte(event) {
    const button = event.target;
    const kkarteUUID = button.getAttribute("data-kkarteuuid");
    window.location.href = "./kkarteedit.html?uuid=" + kkarteUUID;
}

/**
 * löscht eine KKarte
 * @param event  the click-event
 */
function deleteKKarte(event) {
    const button = event.target;
    const kkarteUUID = button.getAttribute("data-kkarteuuid");

    fetch("./resource/kkarte/delete?uuid=" + kkarteUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./kkartelist.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}