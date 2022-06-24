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
            showKkarteList(data);
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
        row.insertCell(-1).innerHTML = book.institut;
        row.insertCell(-1).innerHTML = kkarte.kunde.kunde;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editBook";
        button.setAttribute("data-bookuuid", kkarte.kkarteUUID);
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
    const bookUUID = button.getAttribute("data-kkarteuuid");
    window.location.href = "./kkarteedit.html?uuid=" + kkarteUUID;
}

/**
 * deletes a book
 * @param event  the click-event
 */
function deleteKKarte(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-kkarteuuid");

    fetch("./resource/kkarte/delete?uuid=" + kkarteUUID,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./bookshelf.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}