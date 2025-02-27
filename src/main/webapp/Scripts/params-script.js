/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const urlParams = new URLSearchParams(window.location.search);

if (urlParams.get('register') === 'invalid') {
    document.getElementById('message-for-user').innerText = 'User with this email already exists!';
}

if (urlParams.get('error') === 'invalid') {
    document.getElementById('message-for-user').innerText = 'Wrong email and/or password!';
}

if (urlParams.get('role') === 'invalid') {
    document.getElementById('message-for-user').innerText = 'Error getting role!';
}

if (urlParams.get('register') === 'valid') {
    document.getElementById('message-for-user').innerText = 'You have registered successfully! Sign in to get started!';
}

if (urlParams.get('register') === 'roleSetError') {
    document.getElementById('message-for-user').innerText = 'Role set failed!';
}

if (urlParams.get('add') === 'success') {
    document.getElementById('message-for-user').innerText = 'Flight was added successfully!';
}

if (urlParams.get('edit') === 'success') {
    document.getElementById('message-for-user').innerText = 'Flight was edited successfully!';
}

if (urlParams.get('delete') === 'success') {
    document.getElementById('message-for-user').innerText = 'Flight was deleted successfully!';
}

if (urlParams.get('editRole') === 'success') {
    document.getElementById('message-for-admin').innerText = 'Role was updated successfully!';
}