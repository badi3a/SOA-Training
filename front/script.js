const API_URL = "http://localhost:8080/LogementRendezVous_Etudiant_war_exploded/api/logement";



function fetchLogements() {
    fetch(`${API_URL}/getAll`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("logementsTableBody");
            tableBody.innerHTML = "";
            data.forEach(logement => {
                const row = `<tr>
                    <td>${logement.reference}</td>
                    <td>${logement.adresse}</td>
                    <td>${logement.delegation}</td>
                    <td>${logement.gouvernorat}</td>
                    <td>${logement.type}</td>
                    <td>${logement.description}</td>
                    <td>${logement.prix} TND</td>
                    <td>
                        <button onclick="deleteLogement(${logement.reference})">Supprimer</button>
                    </td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error("Erreur lors de la récupération des logements:", error));
}


document.getElementById("addLogementForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const logement = {
        reference: document.getElementById("reference").value,
        adresse: document.getElementById("adresse").value,
        delegation: document.getElementById("delegation").value,
        gouvernorat: document.getElementById("gouvernorat").value,
        type: document.getElementById("type").value,
        description: document.getElementById("description").value,
        prix: parseFloat(document.getElementById("prix").value)
    };


    fetch(`${API_URL}/new`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(logement)
    })
    .then(response => response.text())
    .then(message => {
        fetchLogements();
    })
    .catch(error => console.error("Erreur lors de l'ajout du logement:", error));
});


function deleteLogement(reference) {
    fetch(`${API_URL}/delete/${reference}`, { method: "DELETE" })
        .then(response => response.text())
        .then(message => {
            fetchLogements();
        })
        .catch(error => console.error("Erreur lors de la suppression:", error));
}
