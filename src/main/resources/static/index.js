window.addEventListener('load', () => {
    console.log('loaded');
    initForm();
    getPeople();
});


function getPeople() {
    console.log('getPeople start')
    fetch('/people')
        .then(response => response.json())
        .then(data => {
            console.log('getPeople finish')
            let tableBody = document.querySelector("#tb");
            removeChildNodes(tableBody);
            for (let i = 0; i < data.length; i++) {
                tableBody.innerHTML +=
                    "<tr>" +
                    "<td class='fn'>" + data[i].firstName + "</td>" +
                    "<td class='ln'>" + data[i].lastName + "</td>" +
                    "<td class='age'>" + data[i].age + "</td>" +
                    "<td class='bt'>" +
                    "   <button class='edit btn btn-light' id='" + data[i].id + "'>Edytuj</button> " +
                    "   <button class='delete btn btn-light' id='" + data[i].id + "'>Usuń</button>" +
                    "</td>" +
                    "</tr>";
            }
            let buttonCheck = 0;
            let deleteButtons = document.querySelectorAll(".delete");
            let editButtons = document.querySelectorAll(".edit");

            deleteButtons.forEach(btn => {
                btn.addEventListener("click", function () {
                    deletePerson(btn.id);
                    btn.parentElement.parentElement.remove();
                });
            });

            editButtons.forEach(btn => {
                btn.addEventListener("click", function () {
                    let header = document.querySelector("#header");
                    header.textContent = "Edytuj osobę";

                    let parent = btn.parentElement.parentElement;

                    let fName = document.querySelector("#fname");
                    let lName = document.querySelector("#lname");
                    let age = document.querySelector("#age");

                    fName.value = parent.childNodes[0].textContent;
                    lName.value = parent.childNodes[1].textContent;
                    age.value = parent.childNodes[2].textContent;

                    updatedPersonId = btn.id;

                    let postButton = document.querySelector("#formButton");
                    let putButton = document.querySelector("#formButton2");
                    let cancelButton = document.querySelector("#formButton3");

                    postButton.style.display = "none";
                    cancelButton.style.display = "inline-block";
                    putButton.style.display = "inline-block";

                    cancelButton.addEventListener("click", function (event) {
                        event.preventDefault();

                        header.textContent = "Dodaj osobę";

                        postButton.style.display = "inline-block";
                        putButton.style.display = "none";
                        cancelButton.style.display = "none";

                        fName.value = '';
                        lName.value = '';
                        age.value = '';
                    })
                });
            });

        });
}

function postPerson(person) {
    console.log('postPerson start')
    person = JSON.stringify(person);
    return fetch('/people', {
        method: 'POST',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        body: person
    })
        .then(function (res) {
            console.log('postPerson finish')
            return res.json();
        })
}

function deletePerson(id) {
    fetch('/people/' + id, {
        method: 'DELETE',
    })
        .then(response => response.json())
}

function putPerson(person, id) {
    person = JSON.stringify(person);
    return fetch('/people/' + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        body: person
    })
        .then(function (res) {
            return res.json();
        })
}

function findPerson(id) {

}

function removeChildNodes(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function initForm() {

    var form = document.querySelector('.needs-validation')

    let postButton = document.querySelector("#formButton");
    postButton.addEventListener("click", function (event) {
        event.preventDefault();
        if (form.checkValidity()) {
            let person = {
                firstName: document.querySelector("#fname").value,
                lastName: document.querySelector("#lname").value,
                age: document.querySelector("#age").value
            };

            postPerson(person)
                .then(getPeople);
        }

        form.classList.add('was-validated')
    });

    let putButton = document.querySelector("#formButton2");
    let cancelButton = document.querySelector("#formButton3");
    putButton.addEventListener("click", function (event) {
        event.preventDefault();
        if (form.checkValidity()) {
            let fNameInput = document.querySelector("#fname");
            let lNameInput = document.querySelector("#lname");
            let ageInput = document.querySelector("#age");
            let person = {
                firstName: fNameInput.value,
                lastName: lNameInput.value,
                age: ageInput.value
            };

            let header = document.querySelector("#header");
            header.textContent = "Dodaj osobę";

            postButton.style.display = "inline-block";
            putButton.style.display = "none";
            cancelButton.style.display = "none";

            putPerson(person, updatedPersonId)
                .then(getPeople);
        }

        form.classList.add('was-validated')
    });
}