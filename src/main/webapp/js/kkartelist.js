/**
 * view-controller for bookshelf.html
 * @author Marcel Suter
 */
document.addEventListener("DOMContentLoaded", () => {
    readKKarte();
});

/**
 * reads all books
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
 * shows the booklist as a table
 * @param data  the books
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
 * redirects to the edit-form
 * @param event  the click-event
 */
function editKKarte(event) {
    const button = event.target;
    const kkarteUUID = button.getAttribute("data-kkarteuuid");
    window.location.href = "./kkarteedit.html?uuid=" + kkarteUUID;
}

/**
 * deletes a book
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