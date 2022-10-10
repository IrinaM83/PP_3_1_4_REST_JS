function getAllUsers() {
    fetch("http://localhost:8080/admin/users")
        .then(res => res.json())
        .then(users => {
            let temp = '';
            console.log(users);
            users.forEach(function (user) {
                temp += `
                <tr>
                <td id="id${user.id}">${user.id}</td>
                <td id="username${user.id}">${user.username}</td> 
                <td id="surname${user.id}">${user.age}</td> 
                <td id="age${user.id}">${user.email}</td>
                <td id="roles${user.id}">${user.roles.map(r => r.name.replace("ROLE_", ""))}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#modalEdit" 
                onclick="openModal(${user.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#modalDelete" 
                onclick="openModal(${user.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("dataTable").innerHTML = temp;
        });
}
getAllUsers()

function openModal(id) {
    fetch("http://localhost:8080/admin/users/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {
            console.log(u);
            document.getElementById('idEdit').value = u.id;
            document.getElementById('usernameEdit').value = u.username;
            document.getElementById('passwordEdit').value = u.password;
            document.getElementById('ageEdit').value = u.age;
            document.getElementById('emailEdit').value = u.email;
            document.getElementById('rolesEdit').value = u.roles;

            document.getElementById('idDelete').value = u.id;
            document.getElementById('usernameDelete').value = u.username;
            document.getElementById('ageDelete').value = u.age;
            document.getElementById('emailDelete').value = u.email;
            document.getElementById('rolesDelete').value = u.roles;
        })
    });
}

document.getElementById("editForm")
    .addEventListener("submit", editUser);

async function editUser() {
    event.preventDefault()
    await fetch('http://localhost:8080/admin/users', {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            id: document.getElementById('idEdit').value,
            username: document.getElementById('usernameEdit').value,
            password: document.getElementById('passwordEdit').value,
            age: document.getElementById('ageEdit').value,
            email: document.getElementById('emailEdit').value,
            roles: getRoles(document.getElementById('rolesEdit'))
        })
    }).then(response => console.log(response));
    $("#modalEdit .close").click();
    refreshTable();
}

function addNewUser() {
    event.preventDefault()
    let username = document.getElementById('createUsername').value;
    let password = document.getElementById('createPassword').value;
    let age = document.getElementById('createAge').value;
    let email = document.getElementById('createEmail').value;
    let roles = getRoles(document.getElementById('createRoles'));
    fetch("http://localhost:8080/admin/users", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            age: age,
            email: email,
            roles: roles
        })
    })
        .then(() => {
            document.getElementById("nav-table-tab").click();
            getAllUsers();
            document.getElementById("createUserForm").reset();
        })
}

async function deleteUser() {
    event.preventDefault()
    await fetch("http://localhost:8080/admin/users/" + document.getElementById("idDelete").value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
    })

    $("#modalDelete .close").click();
    refreshTable();
}

function getRoles(selector) {
    let collection = selector.selectedOptions
    let roles = []
    for (let i = 0; i < collection.length; i++) {
        if (collection[i].value === '1') {
            roles.push({
                id: 1,
                name: 'ROLE_ADMIN'
            })
        } else if (collection[i].value === '2') {
            roles.push({
                id: 2,
                name: 'ROLE_USER'
            })
        }
    }
    return roles
}

function refreshTable() {
    let table = document.getElementById('dataTable')
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    getAllUsers()
}