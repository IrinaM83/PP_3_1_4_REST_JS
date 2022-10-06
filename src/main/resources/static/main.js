/**
 *
 */
const url = "http://localhost:8080/admin/users/"

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

const printUser = (data) => {
    console.log(data);
    if(data.length > 0) {
        var temp = "";
        data.forEach((u) => {
            temp += "<tr>";
            temp += "<td>" + u.id + "</td>";
            temp += "<td>" + u.username + "</td>";
            temp += "<td>" + u.lastName + "</td>";
            temp += "<td>" + u.age + "</td>";
            temp += "<td>" + u.email + "</td>";
            temp += "<td>" + u.stringUserAuthorities + "</td>";
            temp += "<td><a class='btn btn-primary eBtn' data-bs-toggle='modal' id='editUserBtn' data-bs-target='#editUser'>Edit</a></td>"
            temp += "<td><a class='btn btn-danger dltBtn' data-bs-toggle = 'modal' id='deleteUserBtn' data-bs-target='#deleteUser'>Delete</a></td>"
        })
        document.getElementById("data").innerHTML = temp;
    }
}

fetch(url)
    .then(res => res.json())
    .then(data => printUser(data))


//Edit
let editId = null
on(document, 'click', '#editUserBtn', e => {
    editId= e.target.parentNode.parentNode.children[0].innerHTML
    document.getElementById('editUserId').value = e.target.parentNode.parentNode.children[0].innerHTML
    document.getElementById('editUsername').value = e.target.parentNode.parentNode.children[1].innerHTML
    document.getElementById('editLastname').value = e.target.parentNode.parentNode.children[2].innerHTML
    document.getElementById('editAge').value = e.target.parentNode.parentNode.children[3].innerHTML
    document.getElementById('editEmail').value = e.target.parentNode.parentNode.children[4].innerHTML
})
const editUserForm = document.querySelector('#editUserForm')

editUserForm.addEventListener('submit', (e) => {
    fetch(url + editId, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "username": document.getElementById('editUsername').value,
            "surname": document.getElementById('editLastname').value,
            "email": document.getElementById('editEmail').value,
            "password": document.getElementById('editPassword').value,
            "age": document.getElementById('editAge').value,
            "rol": document.getElementById('editRoles').value + " "
        })

    })
        .then(res => res.json())
        .then(data => printUser(data))
        .catch((e) => console.error(e))

})
