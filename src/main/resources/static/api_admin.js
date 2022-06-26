let url = "/api/admin";
let urlUser = "api/user";


function showAllUsers() {
    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((allusers) => {
            let tbody = '';
            console.log(allusers);
            tbody = document.getElementById('table_allusers');
            allusers.forEach((user) => {
                let roles = "";
                user.roles.forEach((role) => {
                    roles = roles + role.name.replace("ROLE_", "") + ' '
                })
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + user.id + '</td>' +
                    '<td>' + user.firstname + '</td>' +
                    '<td>' + user.lastname + '</td>' +
                    '<td>' + user.age + '</td>' +
                    '<td>' + user.username + '</td>' +
                    '<td>' + '[' + roles + ']' + '</td>' +
                    '<td>' + '<button type="button" class="btn btn-info btn-sm" data-toggle="modal" ' +
                    'data-whatever="' + user.id + '" data-target="#editModal">Edit</button>' + '</td>' +
                    '<td>' + '<button type="button" class="btn btn-danger btn-sm" ' +
                    'data-toggle="modal" data-whatever="' + user.id + '" data-target="#deleteModal">Delete</button>' + '</td>';
                tbody.appendChild(tr);
            })
        })
}

function showUser() {
    fetch(urlUser)
        .then((response) => {
            return response.json();
        })
        .then((userActive) => {
            console.log(userActive);
            let tbody = document.getElementById('table_user');
            let roles = "";
            userActive.roles.forEach((role) => {
                roles = roles + role.name.replace("ROLE_", "") + ' '
            });
            let tr = document.createElement('tr');
            tr.innerHTML = '<td>' + userActive.id + '</td>' +
                '<td>' + userActive.firstname + '</td>' +
                '<td>' + userActive.lastname + '</td>' +
                '<td>' + userActive.age + '</td>' +
                '<td>' + userActive.username + '</td>' +
                '<td>' + '[' + roles + ']' + '</td>';
            tbody.appendChild(tr);
        })
}

async function showEditModal(id) {
    let user = await getUser(id);
    document.getElementById("editId").value = user.id;
    document.getElementById("editFirstname").value = user.firstname;
    document.getElementById("editLastname").value = user.lastname;
    document.getElementById("editAge").value = user.age;
    document.getElementById("editUsername").value = user.username;
    document.getElementById("editPassword").value = user.password;
    $("#editRoles").empty();
    let selectEdit = document.getElementById('editRoles');
    let allRoles = await getAllRoles();

    allRoles.forEach((role) => {
        let option = document.createElement('option');
        option.setAttribute('value', role.name);
        option.setAttribute('id', role.id);
        option.setAttribute('name', role.name);
        option.appendChild(document.createTextNode(role.name));
        selectEdit.appendChild(option);
    })
    let userRoles = [];
    let i = 0;
    user.roles.forEach((role) => userRoles[i++] = role);
    let optionToSelect;
    for (let i = 0; i < selectEdit.options.length; i++) {
        optionToSelect = selectEdit.options[i];
        userRoles.forEach((ur) => {
            if (optionToSelect.text == ur) {
                optionToSelect.selected = true;
            }
        });
    }
}

function editUser() {
    let editForm = document.getElementById("editForm")
    let formData = new FormData(editForm);

    let user = {
        id: formData.get('id'),
        firstname: formData.get('firstname'),
        lastname: formData.get('lastname'),
        age: formData.get('age'),
        username: formData.get('username'),
        password: formData.get('password'),
        roles: Array.from(document.getElementById("editRoles"))
            .filter(option => option.selected)
            .map(option => ({name: option.value, id: option.id}))
    }

    fetch(url, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then((response) => {
            document.getElementById('editForm').onsubmit;
        })
}

async function showNewModal() {
    $("#newRoles").empty();
    let selectNew = document.getElementById('newRoles');
    let allRoles = await getAllRoles();
    allRoles.forEach((role) => {
        let option = document.createElement('option');
        option.setAttribute('value', role.name);
        option.setAttribute('id', role.id);
        option.setAttribute('name', role.name);
        option.appendChild(document.createTextNode(role.name));
        selectNew.appendChild(option);
    })
}

function newUser() {
    let newUserForm = document.getElementById("newUserForm")
    let formData = new FormData(newUserForm);
    let user = {
        firstname: formData.get('firstname'),
        lastname: formData.get('lastname'),
        age: formData.get('age'),
        username: formData.get('username'),
        password: formData.get('password'),
        roles: Array.from(document.getElementById("newRoles"))
            .filter(option => option.selected)
            .map(option => ({name: option.value, id: option.id}))
    }
    fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then((response) => {
            document.getElementById('newUserForm').onsubmit;
        })
        .then((r) => {
            $('#nav-user-table-tab').tab('show');
            $('#nav-new-user').removeClass('active');
        /*    showAllUsers();*/
        });
}

async function showDeleteModal(id) {
    let deleteUser = await getUser(id);
    document.getElementById("deleteId").value = deleteUser.id;
    document.getElementById("deleteFirstname").value = deleteUser.firstname;
    document.getElementById("deleteLastname").value = deleteUser.lastname;
    document.getElementById("deleteAge").value = deleteUser.age;
    document.getElementById("deleteUsername").value = deleteUser.username;
    $("#deleteRoles").empty();
    let selectDel = document.getElementById('deleteRoles');
    let allRoles = await getAllRoles();

    allRoles.forEach((role) => {
        let option = document.createElement('option');
        option.setAttribute('value', role.name);
        option.setAttribute('id', role.id);
        option.setAttribute('name', role.name);
        option.appendChild(document.createTextNode(role.name));
        selectDel.appendChild(option);
    })
    let userRoles = [];
    let i = 0;
    deleteUser.roles.forEach((role) => userRoles[i++] = role);
    let optionToSelect;
    for (let i = 0; i < selectDel.options.length; i++) {
        optionToSelect = selectDel.options[i];
        userRoles.forEach((ur) => {
            if (optionToSelect.text == ur) {
                optionToSelect.selected = true;
            }
        });
    }
}

async function deleteUser() {
    let deleteUserID = document.getElementById('deleteId').value;
    fetch(url + '/' + deleteUserID, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
        .then((response) => {

        })
        .then((r) => {
            $('#nav-user-table-tab').tab('show');

        })
}

function getAllRoles() {
    return fetch("/api/admin/getroles")
        .then((response) => {
            let res = response.json();
            return res;
        })
        .then((roles) => {
            console.log('all roles:')
            console.log(roles);
            return roles;
        })
}

async function getUser(id) {
    let response = await fetch(url + '/' + id);
    return await response.json();
}

async function getAllUser() {
    let response = await fetch(url);
    return await response.json();
}

showUser();
showAllUsers();

